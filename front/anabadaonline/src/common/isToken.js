import { useCookies } from "vue3-cookies";
const { cookies } = useCookies();

let result = {
    is: false,
    token: null
};
let token = cookies.get("token")

if (token != null) {
    result.is = true;
    result.token = token;
}
export default result;