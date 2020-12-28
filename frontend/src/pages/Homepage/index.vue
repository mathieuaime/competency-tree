<template>
  <div class="homepage">
      <div class="card-row">
        <Card v-for="(roadmap, index) in roadmaps"
              :key="index"
              :title="roadmap.title"
              :description="roadmap.description"
              :selected="isSelected(index)"
              :style="{'--primary-color': color(roadmap)}"
              @click.native.stop="selectRoadmap(roadmap.name)"
              @mouseover.native.stop="hoverCard(index)"
              @mouseout.native="hoverCard(-1)"/>
      </div>
  </div>
</template>

<script>
import Card from '../Card/Card'

export default {
  name: 'homepage',
  components: { Card },
  data () {
    return {
      roadmaps: [
        {id: 1, name: 'frontend', title: 'frontend', description: 'Le Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablabla'},
        {id: 2, name: 'backend', title: 'backend', description: 'Le Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablabla'},
        {id: 3, name: 'devops', title: 'devops', description: 'Le Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablabla'},
        {id: 4, name: 'spring', title: 'spring', description: 'Le Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablabla'},
        {id: 5, name: 'cdb', title: 'computer database', description: 'Le Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablablaLe Frontend est blablabla'}
      ],
      selectedCard: -1
    }
  },
  methods: {
    isSelected (id) {
      return this.selectedCard === id
    },
    hoverCard (index) {
      this.selectedCard = index
    },
    selectRoadmap (name) {
      this.$router.push({ path: `/roadmap/${name}` })
    },
    color (roadmap) {
      let color

      switch (roadmap.name) {
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
  }
}
</script>

<style lang="scss" scoped>
  .card-row {
    display: flex;
    overflow-x: auto;
    align-items: center;
    justify-content: center;
    height: 100vh;
  }

  .card-row::-webkit-scrollbar {
    display: none;
  }
</style>
