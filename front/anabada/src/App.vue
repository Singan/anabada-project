<template>
  <div id="app">
    
    
    <router-view />

  </div>
</template>

<script>

import AccountRegister from './components/AccountRegister.vue'

export default {
  components: {
    "AccountRegister": AccountRegister,
    
}

}
</script>

<style>

</style>