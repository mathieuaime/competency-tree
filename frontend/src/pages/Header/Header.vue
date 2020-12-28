<template>
  <div class="txt">
      <h1>{{ name }} roadmap</h1>
      <transition enter-active-class="fadeIn" leave-active-class="fadeOut" mode="out-in">
        <div :key="rank">
          <h2>{{ rank }}</h2>
          <h4 v-if="nowTxt < 4">{{ itemLeft }} TASK{{ itemLeft > 1 ? 'S' : ''}} LEFT UNTIL NEXT LEVEL</h4>
          <h4 v-else-if="nowTxt === 5">ALL COMPLETED</h4>
        </div>
      </transition>
  </div>
</template>

<script>
export default {
  props: ['name', 'rank', 'itemLeft', 'primaryColor'],
  name: 'Header'
}
</script>
