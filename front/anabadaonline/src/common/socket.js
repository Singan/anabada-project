import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';
import token from '@/common/token.js';

const headers = {
	'x-auth-token': token.getToken(),
};
this.socket = new SockJS(VUE_APP_API_URL + 'ws');
this.stompClient = Stomp.over(this.socket);
console.log(`소켓 연결을 시도합니다. 서버 주소: http://localhost:8081/ws`);

this.stompClient.connect(
	headers,
	(frame) => {
		this.stompClient.subscribe('/send', (res) => {
			console.log(res);
			this.recvList.push(JSON.parse(res.body));
		});
	},
	(error) => {
		console.log('소켓 연결 실패', error);
		this.connected = false;
	},
);
function send() {
	if (this.stompClient && this.stompClient.connected) {
		const msg = {
			memberId: this.$store.getters.getMember,
			message: this.message,
		};
		this.stompClient.send('/bid', JSON.stringify(msg), headers);
	}
}
let result = {
	socketList: [],
	setSocketList: (socketList) => {
		this.socketList = socketList;
	},
	addSocketList: (socket) => {
		this.socketList.push(socket);
	},
};
