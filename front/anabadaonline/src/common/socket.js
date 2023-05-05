import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';

let url = process.env.VUE_APP_API_URL

let result = {
    headers: {},
    setHeaders(headers) {
        this.headers = headers;
    },
    socket: '1',
    stompClient: '',
    init() {

        this.socket = new SockJS(url + '/ws');
        this.stompClient = Stomp.over(this.socket);

    },
    socketList: [],
    setSocketList(socketList) {
        this.socketList = socketList;
        this.connect()
    },
    addSocketList(socket) {
        this.socketList.push(socket);
    },
    connect() {
        this.stompClient.connect(
            this.headers,
            (frame) => {
                console.dir(frame)
                this.stompClient.subscribe('/send', (res) => {

                }, this.headers);
            },
            (error) => {
                console.log('소켓 연결 실패', error);
            },
        );
        this.stompClient.subscribe('/send', (res) => {

        }, this.headers);

    },
    send(msg) {
        if (stompClient && stompClient.connected) {
            const msg = {
                memberId: this.$store.getters.getMember,
                message: message,
            };
            stompClient.send('/bid', JSON.stringify(msg), this.headers);
        }
    }
};



export default result;