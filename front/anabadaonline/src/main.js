import App from './App.vue';
import routes from './router';
import { createRouter, createWebHistory } from 'vue-router';
import { createApp } from 'vue';
import VueCookies from 'vue3-cookies';
import store from './store';
import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
//import axios from 'axios';
const router = createRouter({
	history: createWebHistory(),
	routes,
});

const app = createApp(App);
const options = {
	confirmButtonColor: '#41b882',
	cancelButtonColor: '#ff7674',
};
app.use(VueSweetalert2, options);

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
