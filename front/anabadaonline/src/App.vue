<template>
    <Header :isToken="isToken"></Header>
    <router-view @logined="login" />
</template>

<script>
import Header from './common/Header.vue';
import token from '@/common/token';
import axios from '@/axios';
import socket from '@/common/socket'
export default {
    components: {
        Header,
    },
    data() {
        return {
            isToken: token.is(),
            token: token.token,
        };
    },
    methods: {
        login() {
            this.isToken = token.is();
        },
    },
    created() {
        if (token.is() && !this.$store.getters.getMember) {
            let tokenVal = token.getToken();
            axios.defaults.headers.common['x-auth-token'] = tokenVal;

            axios
                .get('/member')
                .then((res) => {
                    this.$store.commit('setMember', res.data);
                    console.log(this.$store.getters.getMember)
                    socket.setHeaders({
                        'x-auth-token': tokenVal, 'accept-version': '1.2'
                    })
                    // socket.init();
                    // socket.setSocketList(this.$store.getters.getMember.socketList)
                    // socket.connect();

                });
        }

    },
};
</script>

<style>
body {
    width: 100%;
    margin: 0px;
    height: 100%;
}

#app {
    width: 100vw;
    height: 100vh;
}

a {
    text-decoration: none;
}
</style>
