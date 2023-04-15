<template>
  <div id="app">
    
    <router-view />

  </div>
</template>

<script>

import MainPage from './components/MainPage.vue'

export default {
  components: {
    "MainPage": MainPage,
    
}

}
</script>

<style>

</style>