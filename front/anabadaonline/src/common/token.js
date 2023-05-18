import { useCookies } from 'vue3-cookies';
const { cookies } = useCookies();

const token = {
	is: function () {
		return !!this.getToken();
	},
	setToken: (token) => {
		cookies.set('token', token);
	},
	getToken: () => {
		return cookies.get('token');
	},
	remove: () => {
		cookies.remove('token');
	},
};

export default token;
