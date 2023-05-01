import SockJS from "sockjs-client";
import Stomp from "webstomp-client"
import token from "@/common/token.js"

const headers = {
    'x-auth-token': token.getToken()
}
this.socket = new SockJS('http://localhost:8081/ws')
this.stompClient = Stomp.over(this.socket)
console.log(`소켓 연결을 시도합니다. 서버 주소: http://localhost:8081/ws`)

this.stompClient.connect(
    headers,
    frame => {
        // 소켓 연결 성공
        console.log(frame);
        // 서버의 메시지 전송 endpoint를 구독합니다.
        // 이런형태를 pub sub 구조라고 합니다.
        this.stompClient.subscribe("/send", res => {
            console.log(res)
            console.log('구독으로 받은 메시지 입니다.', res.body);

            // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
            this.recvList.push(JSON.parse(res.body))
        });
    },
    error => {
        // 소켓 연결 실패
        console.log('소켓 연결 실패', error);
        this.connected = false;
    }
);
function send() {
    console.log("Send message:" + this.message);
    if (this.stompClient && this.stompClient.connected) {
        const msg = {
            message: this.message
        };
        this.stompClient.send("/bid", JSON.stringify(msg), headers)
    }
}
let result = {
    socketList: [],
    setSocketList: (socketList) => {
        this.socketList = socketList;
    },
    addSocketList: (socket) => {
        this.socketList.push(socket);
    }
}