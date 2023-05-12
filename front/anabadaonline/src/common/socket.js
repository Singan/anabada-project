import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';

let url = process.env.VUE_APP_API_URL;

// let socketResult = {
//     headers: {},
//     setHeaders(headers) {
//         this.headers = headers;
//     },
//     socket: '1',
//     stompClient: '',
//     init() {

//         this.socket = new SockJS(url + '/ws');
//         this.stompClient = Stomp.over(this.socket);

//     },
//     socketList: [],
//     setSocketList(socketList) {
//         this.socketList = socketList;
//         this.connect()
//     },
//     addSocketList(socket) {
//         this.socketList.push(socket);
//     },
//     connect() {
//         this.stompClient.connect(
//             this.headers,
//             (frame) => {
//                 console.dir(frame)
//                 for (let socket in this.socketList) {
//                     this.stompClient.subscribe('/' + socket.socketId, (res) => {
//                         let result = JSON.stringify(res.body);
//                         console.log(result)
//                     }, this.headers);
//                 }
//             },
//             (error) => {
//                 console.log('소켓 연결 실패', error);
//             },
//         );
//     },
//     send(msg) {
//         if (stompClient && stompClient.connected) {
//             const msg = {
//                 memberId: this.$store.getters.getMember,
//                 message: message,
//             };
//             stompClient.send('/bid', JSON.stringify(msg), this.headers);
//         }
//     }
// };
let socketResult = {
	headers: {},
	setHeaders(headers) {
		console.log(this.headers);
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
					console.log('소켓 연결 성공');
					this.stompClient.connected = true;
					resolve(); // 연결 성공 시 resolve 호출
				},
				(error) => {
					console.log('소켓 연결 실패', error);
					reject(error); // 연결 실패 시 reject 호출
				},
			);
		});
	},
	send(msgObj, pubUrl) {
		console.log(this.headers);
		if (this.stompClient && this.stompClient.connected) {
			this.stompClient.send(pubUrl, JSON.stringify(msgObj), this.headers);
		}
	},
	subscribe(subUrl, recevieFunc) {
		console.log('서브스크라이브 실행' + subUrl);
		return this.stompClient.subscribe(subUrl, (res) => {
			let result = JSON.parse(res.body);
			recevieFunc(result);
		});
	},
};

export default socketResult;
