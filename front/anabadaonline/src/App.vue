<template>
    <Header :ccc="ccc"></Header>
    <router-view v-on:aaa="bbb"/>
</template>

<script>

import MainPage from './components/MainPage.vue'
import Header from './components/Header.vue';
export default {
  components: {
    "MainPage": MainPage,
    Header,
  },
  data(){
    return{
      ccc:false
    }
  },
  methods: {
  bbb() {
    console.log('test')
    this.ccc=true
  }
}
}
</script>

<style>

</style>