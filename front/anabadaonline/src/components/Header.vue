<template>
<div class="Header">
      <div class="HeaderButton">
        <div class="AuctionButton" @click="goAuction">경매</div>
  
        <div class="Logo">아나바다</div>
  
        <div class="ChatButton" @click="goChat">채팅</div>
  
        <div class="SearchBox" contenteditable="true" ></div>
  
        <div class="Search">
          <div class="SearchButton"></div>
          <div class="SearchText">
            <div class="SearchText1">검색하기</div>
          </div>
        </div>
  
        <div class="Login">
          <div class="LoginBox"></div>
          <div class="LoginText">
            <div class="LoginText1" @click="goLogin">
              로그인
            </div>          
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