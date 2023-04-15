<template>
  <div class="--2">
      <div class="--3" @click="goAuction">경매</div>

      <div class="----">아나바다</div>

      <div class="--4" @click="goChat">채팅</div>

      <div class="---" contenteditable="true" ></div>

      <div class="--5">
        <div class="----2"></div>

        <div class="----3">
          <div class="_18px">검색하기</div>
        </div>
      </div>

      <div class="---2">
        <div class="----4"></div>

        <div class="----5">
          <div class="_18px2" @click="goLogin">
            
            로그인
          </div>
          
        </div>
      </div>
    </div>
</template>
<script>
export default {
    methods: {
  
      goLogin() {
        this.$router.push('./login')
      },
      goAuction() {
        this.$router.push('./auction')
      },
      goChat() {
        this.$router.push('./chat')
      },
      
    }
  }
</script>