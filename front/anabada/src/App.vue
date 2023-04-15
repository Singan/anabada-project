<template>
  <div id="app">
    
    
    <router-view />

  </div>
</template>

<script>

import MyPage from './components/MyPage.vue'

export default {
  components: {
    "MyPage": MyPage,
    
}

}
</script>

<style>

</style>