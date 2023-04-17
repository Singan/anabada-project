import App from './App.vue'
import routes from './router'
import { createRouter, createWebHistory } from "vue-router";
import { createApp } from 'vue'


let router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});





const app = createApp(App).use(router);
app.mount("#app");

