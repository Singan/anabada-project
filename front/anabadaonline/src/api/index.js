import axios from 'axios';

const instance = axios.create({
	baseURL: 'http://localhost:8081',
});

// function registerUser(userData) {
// 	return instance.post('signup', userData);
// }
function registerUser(userData) {
    const url = 'http://localhost:8081/member';
    return axios.post(url, userData);
  }
   
  // 함수 export
  export { registerUser };

export { registerUser };