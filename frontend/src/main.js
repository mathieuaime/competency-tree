// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import Progress from 'vue-multiple-progress'

// import TweenMax from 'gsap'
// import TimelineMax from 'gsap'
import './styles/style.css'
import './styles/applications.scss'

Vue.config.productionTip = false
Vue.use(Progress)

// Vue.use(TweenMax)
// Vue.use(TimelineMax)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
