<template>
  <div class="card" :class="{'selected': selected}">
    <h1 class="card-title" >{{ title.toUpperCase() }}</h1>
    <div class="card-body">
      <p class="card-text" v-if="selected">{{ description }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'card',
  props: ['title', 'description', 'selected']
}
</script>

<style lang="scss" scoped>
  .card {
    position: relative;
    background-color: $second-light;
    height: 400px;
    width: 200px;
    min-width: 200px;
    margin: 40px;
    padding: 30px;
    overflow: hidden;
    box-shadow: 0px 2px 4px 0px $second-dark;
    border-radius: 10px;

    transition: box-shadow .2s, height .2s, width .2s;
  }

  .card-image {
    /* center horizontally overflown image */
    position: absolute;
    left: -9999px;
    right: -9999px;
    margin: auto;

    height: 80%;
    min-width: 100%;
  }

  .card-body {
    position: absolute;
    bottom: 0;
    height: 0%;
    min-width: 100%;
    border-style: none;
    pointer-events: none;
  }

  .card-title {
    margin: auto;
    font-size: 60px;
    color: var(--primary-color);
    writing-mode: vertical-rl;
    text-orientation: mixed;
    pointer-events: none;
  }

  .card-text {
    color: white;
  }

  .selected {
    width: 400px;
    height: 600px;
    box-shadow: 5px 5px 20px 0px $second-dark;
 }

  .selected .card-title {
    font-size: 250px;
    transition: font-size .2s, opacity .2s, filter .2s;

    opacity: 0.2;
    filter: blur(3px);
  }

  .selected .card-body {
    height: 60%;
    transition: height .2s;
  }

  .selected img {
    transition: opacity .2s, filter .2s, height .2s, width .2s;
    height: 100%;
    filter: blur(1px);
    opacity: 0.5;
  }
</style>
