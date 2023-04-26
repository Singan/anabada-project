<template>
    <Header :isToken="isToken"></Header>
    <router-view v-on:isToken="isToken" />
</template>

<script>

import Header from './components/Header.vue';
import token from '@/common/isToken'
export default {
    components: {
        Header,
    },
    data() {
        return {
            isToken: token.is,
            token: token.token
        }
    },
}
</script>

<style></style>