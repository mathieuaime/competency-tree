<template>
  <div id="app">
    <!-- <img src="./assets/logo.png"> -->
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style lang="scss">
body{
  background-color: $bg;
  font-family: 'Roboto';
  color: $w;
  font-size: 16px;

}
*{
  box-sizing: border-box;
}
button:focus{
  outline: none;
}

h1{
  font-size: 48px;
  font-style: italic;
  font-weight: bold;
}
h2{
  font-size: 36px;
  font-weight: bold;
}
.remark{
  font-size: 14px;
}

//Components
.btn {
  -webkit-appearance: none;
  border: 1px solid #fff;
  background-color: transparent;
  color: #fff;
  width: 100%;
  padding: 8px;
  font-size: 16px;
  margin: 5px;
  cursor: pointer;
  &:hover,&.isActive {
      background-color: var(--primary-color);
      border-color: var(--primary-color);
  }
}

// Transitions
.fade-enter-active, .fade-leave-active {
  transition: opacity .1s;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
}

@keyframes fadeIn {
  0% { opacity: 0; transform: translateY(-100%); }
  100% { opacity: 1; transform: translateY(0px); }
}

@keyframes fadeOut {
  0% { opacity: 1; transform: translateY(0px); }
  100% { opacity: 0; transform: translateY(100%); }
}

@keyframes rollIn {
  0% { opacity: 0; transform: translateX(-100%) rotate(-120deg); }
  100% { opacity: 1; transform: translateX(0px) rotate(0deg); }
}

@keyframes rollOut {
  0% { opacity: 1; transform: translateX(0px) rotate(0deg); }
  100% { opacity: 0; transform: translateX(100%) rotate(120deg); }
}

.fadeIn {
  animation: fadeIn .4s cubic-bezier(0.55, 0.085, 0.68, 0.53) both;
}

.fadeOut {
  animation: fadeOut .4s cubic-bezier(0.55, 0.085, 0.68, 0.53) both;
}

.rollIn {
  animation: rollIn .4s cubic-bezier(0.55, 0.085, 0.68, 0.53) both;
}

.rollOut {
  animation: rollOut .4s cubic-bezier(0.55, 0.085, 0.68, 0.53) both;
}

.slide-fade-enter-active {
  transition: all .1s ease;
}

.slide-fade-leave-active {
  transition: all .1s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}

.slide-fade-enter, .slide-fade-leave-to {
  transform: translateX(20px);
  opacity: 0.5;
}

.list-fade-enter {
  opacity: 0;
  transform: translateX(400%);
}
</style>
