<template>
    <span v-for="s in messages">
        {{ s }}
    </span>
    <textarea></textarea>
    <button :on-click="sendMessage">보내기</button>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import token from '@/common/token'
export default {
    data() {
        return {
            messages: [],
            socket: null
        }
    },
    mounted() {
        const serverURL = 'http://localhost:8081'
        let socket = new SockJS(serverURL)
        this.stompClient = Stomp.over(socket)
        console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)
        this.stompClient.connect(
            {},
            (frame) => {
                // 소켓 연결 성공
                this.connected = true
                console.log('소켓 연결 성공', frame)
                // 서버의 메시지 전송 endpoint를 구독합니다.
                // 이런형태를 pub sub 구조라고 합니다.
                this.stompClient.subscribe('/bid', (res) => {
                    // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
                    console.log()
                    this.messages.push(JSON.parse(res.body))
                })
            }, (error) => {
                console.log(error)
            })
    },
    methods: {
        sendMessage(message) {
            this.socket.emit('message', message);//보내는부분
        },


    }
}
</script>