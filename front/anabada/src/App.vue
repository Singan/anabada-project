<template>
  <div id="app">
    
    
    <router-view />

  </div>
</template>

<script>

import MyPage from './components/AccountRegister.vue'

export default {
  components: {
    "AccountRegister": AccountRegister,
    
}

}
</script>

<style>

</style>