import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';

let url = process.env.VUE_APP_API_URL

let socketResult = {
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
                for (let socket in this.socketList) {
                    this.stompClient.subscribe('/' + socket.socketId, (res) => {
                        let result = JSON.stringify(res.body);
                        console.log(result)
                    }, this.headers);
                }
            },
            (error) => {
                console.log('소켓 연결 실패', error);
            },
        );
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



export default socketResult;