import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';

let url = process.env.VUE_APP_API_URL;
let socketResult = {
	headers: {},
	setHeaders(headers) {
		this.headers = headers;
	},
	socket: '',
	stompClient: '',
	init() {
		this.socket = new SockJS(url + '/ws');
		this.stompClient = Stomp.over(this.socket);
	},
	connect() {
		return new Promise((resolve, reject) => {
			// Promise로 감싸기
			this.stompClient.connect(
				this.headers,
				(frame) => {
					this.stompClient.connected = true;
					resolve(); // 연결 성공 시 resolve 호출
				},
				(error) => {
					reject(error); // 연결 실패 시 reject 호출
				},
			);
		});
	},
	send(msgObj, pubUrl) {
		if (this.stompClient && this.stompClient.connected) {
			console.log(this.headers);
			this.stompClient.send(pubUrl, JSON.stringify(msgObj), this.headers);
		}
	},
	subscribe(subUrl, recevieFunc) {
		if (this.stompClient && this.stompClient.connected) {
			return this.stompClient.subscribe(subUrl, (res) => {
				let result = JSON.parse(res.body);
				recevieFunc(result);
			});
		}
	},
	connected() {
		return this.stompClient.connected;
	},
};

export default socketResult;
