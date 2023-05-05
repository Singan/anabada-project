<template>
    <span v-for="receive in recvList">{{ receive.memberName + ":" + receive.message }} <br></span>

    <br>
    <textarea v-model="message"></textarea>
    <button @click="sendMessage" style="width: 100px; height: 100px;">보내기</button>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client"
import token from "@/common/token.js"
export default {
    data() {
        return {
            message: "",
            socket: null,
            stompClient: null,
            msg: "",
            recvList: [
                {
                    message: "시작", memberName: "운영자"
                }
            ]
        };
    },
    mounted() {
        const headers = {
            'x-auth-token': token.getToken()
        }
        this.socket = new SockJS(process.env.VUE_APP_API_URL + '/ws')
        this.stompClient = Stomp.over(this.socket)
        console.log(`소켓 연결을 시도합니다. 서버 주소: ` + process.env.VUE_APP_API_URL)

        this.stompClient.connect(
            headers,
            frame => {
                this.stompClient.subscribe("/send", res => {
                    let resObj = JSON.parse(res.body);
                    console.log("sub")
                    if (resObj.memberNo == this.$store.getters.getMember.no) {
                        resObj.memberName = "나"
                        console.log(resObj.memberName)
                    }
                    this.recvList.push(resObj)
                });
                console.log(frame);
            },
            error => {
                console.log('소켓 연결 실패', error);
                this.connected = false;
            }
        );


    },

    methods: {
        sendMessage() {
            this.send()
            this.message = ''

        },
        send() {
            console.log("Send message:" + this.message);
            if (this.stompClient && this.stompClient.connected) {
                const msg = {
                    message: this.message
                };
                this.stompClient.send("/receive", JSON.stringify(msg), {
                    'x-auth-token': token.getToken()
                })
            }
        },
    }
};
</script>