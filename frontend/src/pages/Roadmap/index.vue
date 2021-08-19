<template src="./template.html"></template>

<script>
import Note from '../Note'
import Progression from '../Progression/Progression'

import RoadmapService from '../../services/RoadmapService'

const Categories = {
  BASIC: { name: 'BASIC', rank: 'initiate developer' },
  BEGINNER: { name: 'BEGINNER', rank: 'padawan developer' },
  CONFIRMED: { name: 'CONFIRMED', rank: 'jedi developer' },
  EXPERT: { name: 'EXPERT', rank: 'jedi master developer' },
  MASTER: { name: 'MASTER', rank: 'master of the order' }
}

export default {
  props: ['name'],
  name: 'roadmap',
  components: {
    Note,
    Progression
  },
  data () {
    return {
      user: {
        username: 'maime',
        firstName: 'Mathieu',
        lastName: 'Aimé'
      },
      roadmap: { skills: [] },
      clickList: [],
      checked: 0,
      myImg: [
        require('@/assets/rank-BASIC.png'),
        require('@/assets/rank-BEGINNER.png'),
        require('@/assets/rank-CONFIRMED.png'),
        require('@/assets/rank-EXPERT.png'),
        require('@/assets/rank-MASTER.png'),
        require('@/assets/rank-ADMIN.png')
      ],
      currentSkill: { id: 0, tasks: [] },
      currentTask: undefined,
      loading: true
    }
  },
  async created () {
    await this.fetchRoadmap()
  },
  computed: {
    isAdmin () {
      return this.user.username === 'yoda'
    },
    categories () {
      return Object.values(Categories)
    },
    currentCategory () {
      return this.categories[this.nowTxt]
    },
    nowTxt () {
      this.updateClickList()

      if (this.isAdmin) {
        return 5
      } else if (this.totalProgression === 100) {
        return 4
      } else if (this.isCategory(Categories.EXPERT.name)) {
        return 3
      } else if (this.isCategory(Categories.CONFIRMED.name)) {
        return 2
      } else if (this.isCategory(Categories.BEGINNER.name)) {
        return 1
      } else {
        return 0
      }
    },
    nowImg () {
      return this.myImg[this.nowTxt]
    },
    rank () {
      return this.isAdmin ? 'admin' : this.currentCategory.rank
    },
    skills () {
      return this.roadmap.skills.reduce(function (skillsByCategory, x) {
        (skillsByCategory[x.category] = skillsByCategory[x.category] || []).push(x)
        return skillsByCategory
      }, {})
    },
    currentCategorySkills () {
      return this.getSkills(this.currentCategory)
    },
    totalProgression () {
      let tasks = this.roadmap.skills.flatMap((skill) => skill.tasks)
      return tasks.length === 0
        ? 0
        : Math.round(
          (tasks.filter((item) => item.done).length * 100) / tasks.length
        )
    },
    tasksLeft () {
      if (this.currentCategory.name === Categories.EXPERT.name) {
        return 0
      }

      let tasks =
        this.currentCategory.name === Categories.EXPERT.name
          ? this.roadmap.skills.flatMap((skill) => skill.tasks)
          : this.currentCategorySkills
            .flatMap((skill) => skill.tasks)
            .filter((i) => i.required)

      return tasks.length - tasks.filter((item) => item.done).length
    }
  },
  methods: {
    async fetchRoadmap () {
      this.roadmap = await RoadmapService.findByName(
        this.$props.name,
        this.isAdmin
      )

      this.updateClickList()

      this.loading = false
    },
    getSkills (category) {
      if (category.name === Categories.MASTER.name) {
        return this.skills[Categories.EXPERT.name]
      } else {
        return this.skills[category.name]
      }
    },
    isCategory (category) {
      return (
        this.isAdmin ||
        this.clickList.findIndex((item) => item === category) !== -1
      )
    },
    isCategoryDone (category) {
      return (
        !this.isAdmin && (this.skills[category.name] || []).every((i) => i.done)
      )
    },
    isSkillDone (skill) {
      return skill.tasks.every((i) => i.done)
    },
    addItem (e) {
      this.clickList.push(e)
    },
    showSkill (data) {
      this.currentTask = undefined
      this.currentSkill = data
    },
    updateClickList () {
      this.clickList = [Categories.BASIC.name]

      if (this.isCategoryDone(Categories.BASIC)) {
        this.clickList.push(Categories.BEGINNER.name)
      }
      if (this.isCategoryDone(Categories.BEGINNER)) {
        this.clickList.push(Categories.CONFIRMED.name)
      }
      if (this.isCategoryDone(Categories.CONFIRMED)) {
        this.clickList.push(Categories.EXPERT.name)
      }
      if (this.isCategoryDone(Categories.EXPERT)) {
        this.clickList.push(Categories.MASTER.name)
      }
    },
    getSkillProgression (skill, required) {
      let filteredItems = skill.tasks.filter((i) => i.required === required)
      return (
        filteredItems.filter((item) => item.done).length +
        '/' +
        filteredItems.length
      )
    },
    getCategoryImgUrl (category) {
      let images = require.context('../../assets', false, /\.png$/)
      return images('./ship-' + category + '.png')
    }
  }
}
</script>

<style lang="scss" src="./style.scss" scoped></style>
