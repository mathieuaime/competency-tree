<template>
  <div class="homepage">
    <div class="card-row" v-if="!loading">
      <Card v-for="(roadmap, index) in roadmaps"
            :key="index"
            :title="roadmap.name"
            :description="roadmap.description"
            :selected="isSelected(index)"
            :style="{'--primary-color': roadmap.color}"
            @click.native.stop="selectRoadmap(roadmap.name)"
            @mouseover.native.stop="hoverCard(index)"
            @mouseout.native="hoverCard(-1)"/>
    </div>
  </div>
</template>

<script>
import Card from '../Card/Card'
import RoadmapService from '../../services/RoadmapService'

export default {
  name: 'homepage',
  components: {Card},
  data () {
    return {
      roadmaps: [],
      selectedCard: -1,
      loading: true
    }
  },
  methods: {
    async fetchRoadmaps () {
      this.roadmaps = await RoadmapService.findAll()
      this.loading = false
    },
    isSelected (id) {
      return this.selectedCard === id
    },
    hoverCard (index) {
      this.selectedCard = index
    },
    selectRoadmap (name) {
      this.$router.push({path: `/roadmap/${name}`})
    }
  },
  async created () {
    await this.fetchRoadmaps()
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
