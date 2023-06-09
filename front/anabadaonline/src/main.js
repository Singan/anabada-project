import App from './App.vue';
import routes from './router';
import { createRouter, createWebHistory } from 'vue-router';
import { createApp } from 'vue';
import VueCookies from 'vue3-cookies';
import store from './store';
import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import token from './common/token';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css';
import BootstrapVue3 from 'bootstrap-vue-3';

const router = createRouter({
	history: createWebHistory(),
	routes,
});

const app = createApp(App);
const options = {
	confirmButtonColor: '#41b882',
	cancelButtonColor: '#ff7674',
};
app.config.globalProperties.$token = token;
app.use(VueSweetalert2, options);
app.use(BootstrapVue3);
app.use(router);
app.use(store);
//app.use(axios)
app.use(VueCookies, {
	expireTimes: '30d',
	path: '/',
	domain: '',
	secure: true,
	sameSite: 'None',
});
app.mount('#app');
// Set global cookie in component:
