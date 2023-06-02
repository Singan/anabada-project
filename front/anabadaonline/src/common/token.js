import { useCookies } from 'vue3-cookies';
const { cookies } = useCookies();

const token = {
	is: function () {
		return !!this.getToken();
	},
	setToken: (token) => {
		localStorage.setItem('token', token);
	},
	getToken: () => {
		return localStorage.getItem('token');
	},
	remove: () => {
		localStorage.removeItem('token');
	},
};

export default token;
