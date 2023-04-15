import Vue from 'vue'
import App from './App.vue'
import routes from './router'
import VueRouter from 'vue-router'
import axios from 'axios'
import VueNavigationBar from "vue-navigation-bar"
Vue.use(VueRouter)
Vue.prototype.$axios = axios;
Vue.component('navigation-bar',VueNavigationBar)
axios.defaults.baseURL = 'http://localhost:8081'

const router = new VueRouter({
  mode:'history',
  routes:routes
})
 

Vue.config.productionTip = false

new Vue({
  router:router,
  render: h => h(App),
}).$mount('#app')


