import App from './App.vue';
import routes from './router';
import { createRouter, createWebHistory } from 'vue-router';
import { createApp } from 'vue';
import VueCookies from 'vue3-cookies';

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

const app = createApp(App);
app.use(router);
app.use(VueCookies, {
    expireTimes: '30d',
    path: '/',
    domain: '',
    secure: true,
    sameSite: 'None',
});
app.mount('#app');
// Set global cookie in component:

