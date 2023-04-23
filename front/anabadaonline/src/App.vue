<template>
  <Header :ccc="ccc"></Header>
  <router-view v-on:isToken="isToken" />
</template>

<script>

import Header from './components/Header.vue';
import isToken from '@/common/isToken'
export default {
  components: {
    Header,
  },
  data() {
    return {
      isToken: isToken.is,
      token: isToken.token
    }
  },
}
</script>

<style></style>