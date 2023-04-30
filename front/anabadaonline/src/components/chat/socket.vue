<template>
    <span v-for="s in messages">
        {{ s }}
    </span>
    <textarea></textarea>
    <button :on-click="sendMessage">보내기</button>
</template>

<script>
import io from 'socket.io-client';
import token from '@/common/token'
export default {
    data() {
        return {
            messages: [],
            socket: null
        }
    },
    mounted() {
        this.socket = io('http://localhost:8082', {
            transports: ['websocket']
        });
        console.log(this.socket)
        this.socket.on('message', (message) => {
            this.messages.push(message);//데이터받는부분
        });
        this.socket.on('connect', () => {
            console.log('Connected to server'); // 연결 성공 시
        });
        this.socket.on('connect_error', (error) => {
            console.log('Connection failed:', error); // 연결 실패 시
        });
        // this.socket.on('message', (message) => {
        //     this.messages.push(message);//데이터받는부분
        // });
    },
    methods: {
        sendMessage(message) {
            this.socket.emit('message', message);//보내는부분
        }

    }
}
</script>