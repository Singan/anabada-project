<template>
    <Header></Header>
    <router-view />
</template>

<script>

import MainPage from './components/MainPage.vue'
import Header from './components/Header.vue';
export default {
  components: {
    "MainPage": MainPage,
    Header,
  }

}
</script>

<style>

</style>