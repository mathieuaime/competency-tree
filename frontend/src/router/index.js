import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: () => import('@/pages/Homepage')
    },
    {
      path: '/roadmap/:name',
      name: 'Roadmap',
      component: () => import('@/pages/Roadmap'),
      props: true
    }
  ]
})
