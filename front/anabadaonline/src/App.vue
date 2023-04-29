<template>
    <Header :isToken="isToken"></Header>
    <router-view @logined="login" />
</template>

<script>
import Header from './common/Header.vue';
import token from '@/common/token';
console.log(token.is());
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
            console.log('에밋' + this.isToken);
        },
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
</style>
