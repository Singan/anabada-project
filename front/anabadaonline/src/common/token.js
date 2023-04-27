import { useCookies } from "vue3-cookies";
const { cookies } = useCookies();

const token = cookies.get("token");

const result = {
    is: function () {
        return !!this.getToken();

    },
    token,
    setToken: (token) => {
        token = cookies.set("token", token)
        result.token = token;
    },
    getToken: () => {
        return token;
    }
};

export default result;
