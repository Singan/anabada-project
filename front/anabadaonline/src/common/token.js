import { useCookies } from 'vue3-cookies';
const { cookies } = useCookies();

const result = {
    is: function () {
        return !!this.getToken();
    },
    setToken: (token) => {
        cookies.set('token', token);
    },
    getToken: () => {
        return cookies.get('token');
    },
};

export default result;
