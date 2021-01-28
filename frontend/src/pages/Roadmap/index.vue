<template src="./template.html"></template>

<script>
import Note from '../Note'
import Progression from '../Progression/Progression'

import RoadmapService from '../../services/RoadmapService'

const Categories = {
  BASIC: { name: 'basic', rank: 'initiate developer' },
  BEGINNER: { name: 'beginner', rank: 'padawan developer' },
  CONFIRMED: { name: 'confirmed', rank: 'jedi developer' },
  EXPERT: { name: 'expert', rank: 'jedi master developer' },
  MASTER: { name: 'master', rank: 'master of the order' }
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
        require('@/assets/rank-basic.png'),
        require('@/assets/rank-beginner.png'),
        require('@/assets/rank-confirmed.png'),
        require('@/assets/rank-expert.png'),
        require('@/assets/rank-master.png'),
        require('@/assets/rank-admin.png')
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
    basicSkills () {
      return this.roadmap.skills.filter(
        (i) => i.category.toLowerCase() === Categories.BASIC.name
      )
    },
    beginnerSkills () {
      return this.roadmap.skills.filter(
        (i) => i.category.toLowerCase() === Categories.BEGINNER.name
      )
    },
    confirmedSkills () {
      return this.roadmap.skills.filter(
        (i) => i.category.toLowerCase() === Categories.CONFIRMED.name
      )
    },
    expertSkills () {
      return this.roadmap.skills.filter(
        (i) => i.category.toLowerCase() === Categories.EXPERT.name
      )
    },
    currentCategorySkills () {
      return this.getSkills(this.currentCategory.name)
    },
    totalProgression () {
      let tasks = this.roadmap.skills.flatMap((skill) => skill.tasks)
      return tasks.length === 0
        ? 0
        : Math.round(
          (tasks.filter((item) => item.done).length * 100) / tasks.length
        )
    },
    itemLeft () {
      if (this.currentCategory.name === 'master') {
        return 0
      }

      let tasks =
        this.currentCategory.name === 'expert'
          ? this.roadmap.skills.flatMap((skill) => skill.tasks)
          : this.currentCategorySkills
            .flatMap((skill) => skill.tasks)
            .filter((i) => i.required)

      return tasks.length - tasks.filter((item) => item.done).length
    },
    primaryColor () {
      let color

      switch (this.roadmap.name) {
        case 'frontend':
          color = '#35ba2a'
          break
        case 'backend':
          color = '#fb0000'
          break
        case 'devops':
          color = '#82def9'
          break
        case 'spring':
          color = '#ffe81f'
          break
        case 'cdb':
          color = '#808080'
          break
      }

      return color
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
      switch (category) {
        case Categories.BASIC.name:
          return this.basicSkills
        case Categories.BEGINNER.name:
          return this.beginnerSkills
        case Categories.CONFIRMED.name:
          return this.confirmedSkills
        case Categories.EXPERT.name:
          return this.expertSkills
        case Categories.MASTER.name:
          return this.expertSkills
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
        !this.isAdmin && this.getSkills(category.name).every((i) => i.done)
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
      var images = require.context('../../assets', false, /\.png$/)
      return images('./ship-' + category + '.png')
    }
  }
}
</script>

<style lang="scss" src="./style.scss" scoped></style>
