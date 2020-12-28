<template>
  <div class="progress">
    <vm-progress :percentage="totalProgression" :strokeColor="primaryColor" type="circle" :width="200" style="margin-left: 50px;">
      <transition  enter-active-class="rollIn" leave-active-class="rollOut" mode="out-in">
        <img :src="nowImg" :key="nowImg" width="150" height="150" alt="">
      </transition>
    </vm-progress>
  </div>
</template>

<script>
export default {
  props: ['skills', 'nowTxt', 'primaryColor'],
  name: 'Progression',
  data () {
    return {
      myImg: [
        require('@/assets/rank-basic.png'),
        require('@/assets/rank-beginner.png'),
        require('@/assets/rank-confirmed.png'),
        require('@/assets/rank-expert.png'),
        require('@/assets/rank-master.png'),
        require('@/assets/rank-admin.png')
      ]
    }
  },
  computed: {
    nowImg () {
      return this.myImg[this.nowTxt]
    },
    totalProgression () {
      let tasks = this.skills.flatMap(skill => skill.tasks)
      return tasks.length === 0 ? 0 : Math.round((tasks.filter(item => item.done).length * 100) / tasks.length)
    }
  }
}
</script>

<style lang="scss" scoped>
  img {
    filter: grayscale(1) invert(1);
    &.rollIn {
      animation: rollIn .4s cubic-bezier(0.55, 0.085, 0.68, 0.53) both;
    }

    &.rollOut {
      animation: rollOut .4s cubic-bezier(0.55, 0.085, 0.68, 0.53) both;
    }
  }

  .progress {
    .vm-progress--circle {
      margin-top: 50px;
      padding-left: 0px;
    }
  }
</style>
