import axios from 'axios';
import token from './common/token';
import store from './store';
axios.defaults.baseURL = process.env.VUE_APP_API_URL;
// Add a response interceptor
axios.interceptors.response.use(
	function (response) {
		// Any status code that lie within the range of 2xx cause this function to trigger
		// Do something with response data
		return response;
	},
	async (error) => {
		const {
			config,
			response: { status },
		} = error;

		if (status === 500) {
			if (error.response.data.message === '토큰 만료') {
				alert('토큰이 만료되었습니다.');
				token.remove();
				store.commit('removeMember');
				location.reload();
				return null;
			}
		}
		// Any status codes that falls outside the range of 2xx cause this function to trigger
		// Do something with response error
		return Promise.reject(error);
	},
);
export default axios;
