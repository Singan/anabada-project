<template>
    <div class="---">
        <div class="----" @click="goMain">아나바다</div>

        <div class="rectangle-15"></div>

        <div class="--">
            <div class="ellipse-8"></div>

            <div class="--2">로고</div>
        </div>

        <div class="---2"></div>

        <div class="---3"></div>

        <div class="---4"></div>

        <div class="------">아이디 찾기</div>

        <div class="-------">비밀번호 찾기</div>

        <div class="----2" @click="goJoin">회원가입</div>
        <form>
            <div class="---5" id="id"><input type="text" v-model="id"></div>

            <div class="----3" id="pw"><input type="password" v-model="pw"></div>
        </form>
        <div class="---6" @click="login">로그인</div>
    </div>
</template>

<script>
import axios from '@/axios.js'
import { useCookies } from "vue3-cookies";
const { cookies } = useCookies();

export default {
    name: "LoginPage",
    data() {
        return {
            id: '아이디',
            pw: '패스워드'
        }
    },
    methods: {
        goJoin() {
            this.$router.push('./JoinMember')
        },
        goMain() {
            this.$router.push('./')
        },
        login() {
            console.log(this.id)

            axios.post('/member/login',
                {
                    id: this.id,
                    pw: this.pw
                }).then((response) => {
                    if (response.status == 200) {
                        cookies.set("token", `${response.data.accessToken}`)
                        this.$emit("login")
                        this.$router.push('./')
                    }

                })


        },
    }
}
</script>

<style scoped>
/* .---,
.--- * {
  box-sizing: border-box;
}
.--- {
  background: #ffffff;
  width: 1920px;
  height: 1024px;
  position: relative;
  overflow: hidden;
} */
.---- {
    color: #0075ff;
    text-align: center;
    font: 700 35px "Roboto", sans-serif;
    position: absolute;
    left: 862px;
    top: 312px;
    width: 244px;
    height: 37px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.rectangle-15 {
    background: #f6f6f6;
    border-radius: 5px;
    width: 500px;
    height: 300px;
    position: absolute;
    left: 725px;
    top: 399px;
}

.-- {
    width: 65px;
    height: 58.2px;
    position: static;
}

.ellipse-8 {
    background: #d9d9d9;
    border-radius: 50%;
    width: 58.2px;
    height: 58.2px;
    position: absolute;
    left: 811.78px;
    top: 301px;
}

.--2 {
    color: #0123b4;
    text-align: center;
    font: 400 15px "Roboto", sans-serif;
    position: absolute;
    left: 808px;
    top: 322.92px;
    width: 65px;
    height: 13.6px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.---2 {
    background: #ffffff;
    border-radius: 5px;
    width: 446px;
    height: 52px;
    position: absolute;
    left: 752px;
    top: 429px;
}

.---3 {
    background: #ffffff;
    border-radius: 5px;
    width: 446px;
    height: 52px;
    position: absolute;
    left: 752px;
    top: 501px;
}

.---4 {
    background: #0075ff;
    border-radius: 5px;
    width: 446px;
    height: 52px;
    position: absolute;
    left: 752px;
    top: 593px;
}

.------ {
    color: #000000;
    text-align: center;
    font: 400 13px "Roboto", sans-serif;
    position: absolute;
    left: 799px;
    top: 653px;
    width: 78px;
    height: 37px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.------- {
    color: #000000;
    text-align: center;
    font: 400 13px "Roboto", sans-serif;
    position: absolute;
    left: 939px;
    top: 653px;
    width: 90px;
    height: 37px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.----2 {
    color: #0075ff;
    text-align: center;
    font: 700 13px "Roboto", sans-serif;
    position: absolute;
    left: 1094px;
    top: 653px;
    width: 54px;
    height: 37px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.---5 {
    color: #a3a3a3;
    text-align: left;
    font: 400 16px "Roboto", sans-serif;
    position: absolute;
    left: 762px;
    top: 437px;
    width: 60px;
    height: 35px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
}

.----3 {
    color: #a3a3a3;
    text-align: left;
    font: 400 16px "Roboto", sans-serif;
    position: absolute;
    left: 762px;
    top: 510px;
    width: 75px;
    height: 35px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
}

.---6 {
    color: #ffffff;
    text-align: center;
    font: 700 18px "Roboto", sans-serif;
    position: absolute;
    left: 776px;
    top: 601px;
    width: 398px;
    height: 37px;
    display: flex;
    align-items: center;
    justify-content: center;
}
</style>