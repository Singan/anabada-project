<template>

  <div class="Header">
      <div class="HeaderButton">
        <div class="AuctionButton" @click="goAuction">경매</div>
  
        <div class="Logo">아나바다</div>
  
        <div class="ChatButton" @click="goChat">채팅</div>
  
        <div class="SearchBox" contenteditable="true" >
          <b-form-input
          size="sm"
          class="mr-sm-2"
          type="text"
          placeholder="검색어를 입력해주세요"
          v-model="keyword"  
          @keyup.enter="searchresultshow(keyword)"
        ></b-form-input>
        </div>
  
        <div class="Search">
          <div class="SearchButton"></div>
          <div class="SearchText">
            <!-- <div class="SearchText1">검색하기</div> -->
            <b-button
              size="sm"
              class="SearchText1"
              type="submit"
              @click="goAuction(keyword)" 
            >검색하기
            </b-button>
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
  
      <div class="Footer1"></div>
  
      <div class="Footer2"></div>
  
      <div class="Footer3"></div>
  
      <div class="TextBox1">
        <div class="Text1">현재 경매 되고 있는 상품을 둘러보세요</div>
      </div>
  
      <div class="TextBox2">
        <div class="Text2">내가 사는 지역에서 펼쳐지는 경매장</div>
      </div>
  
      <div class="TextBox3">
        <div class="Text3">원하는 물품,<br />원하는 가격으로</div>
      </div>
  
      <div class="TextBox4">
        <div class="Text4">
          중고 거래, 이제 경매에 직접 참여해서<br />원하는 가격으로 구매해보세요
        </div>
      </div>
  
      <div class="ImgArea1">
        <div class="rectangle-13">
          <img src="@/assets/bike.jpg" style="width:100%; height:130%;"/>
        </div>
  
        <div class="rectangle-14">
          <img src="@/assets/samsung.jpg" style="width:100%; height:100%;"/>
        </div>
  
        <div class="rectangle-12">
          <img src="@/assets/iphone14.jpg" style="width:100%; height:100%;"/>
        </div>
      </div>
  
      <div class="ProductImg1"></div>
  
      <div class="ProductImg2"></div>
  
      <div class="ProductImg3"></div>
  
      <div class="ProductImg4"></div>
  
      <div class="Illust2">
        <img src="@/assets/change2.jpg" style="width:100%; height:100%;"/>
      </div>
  
      <div class="Illust1">
        <img src="@/assets/change.png" style="width:100%; height:100%;"/>
      </div>
  
      <div class="ProductN1">
        <div class="ProductNT1">아이폰 14 프로</div>
      </div>
  
      <div class="Adress1">
        <div class="AdressT1">경기도 성남시</div>
      </div>
  
      <div class="Price1">
        <div class="PriceT1">1,200,000 원</div>
      </div>
  
      <div class="ProductN2">
        <div class="ProductNT2">위닉스 제습기</div>
      </div>
  
      <div class="Adress2">
        <div class="AdressT1">경기도 성남시</div>
      </div>
  
      <div class="Price2">
        <div class="PriceT1">32,000 원</div>
      </div>
  
      <div class="ProductN3">
        <div class="ProductNT1">LG 코드제로</div>
      </div>
  
      <div class="Adress3">
        <div class="AdressT1">경기도 성남시</div>
      </div>
  
      <div class="Price3">
        <div class="PriceT1">600,000 원</div>
      </div>
  
      <div class="ProductN4">
        <div class="ProductNT1">갤럭시 s22 울트라 256gb</div>
      </div>
  
      <div class="Adress4">
        <div class="AdressT1">경기도 성남시</div>
      </div>
  
      <div class="Price4">
        <div class="PriceT1">900,000 원</div>
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
  
  <style scoped>
  
  .Header,
  .Header * {
    box-sizing: border-box;
  }
  .Header {
    background: #ffffff;
    width: 1920px;
    height: 1900px;
    position: relative;
    overflow: hidden;
  }
  .HeaderButton {
    width: 1130px;
    height: 40px;
    position: static;
  }
  .AuctionButton {
    color: #0075ff;
    text-align: center;
    font: 700 18px "Roboto", sans-serif;
    position: absolute;
    left: 575px;
    top: 40px;
    width: 40px;
    height: 20px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .Logo {
    color: #0075ff;
    text-align: center;
    font: 700 24px "Roboto", sans-serif;
    position: absolute;
    left: 395px;
    top: 35px;
    width: 110px;
    height: 30px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .ChatButton {
    color: #000000;
    text-align: center;
    font: 700 18px "Roboto", sans-serif;
    position: absolute;
    left: 655px;
    top: 40px;
    width: 40px;
    height: 20px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .SearchBox {
    background: #d9ecff;
    border-radius: 5px;
    width: 450px;
    height: 40px;
    position: absolute;
    left: 785px;
    top: 30px;
    text-align: center;
    line-height: 40px;
  }
  .Search {
    width: 130px;
    height: 40px;
    position: static;
  }
  .SearchButton {
    background: #ffffff;
    border-radius: 5px;
    border-style: solid;
    border-color: #000000;
    border-width: 1px;
    width: 130px;
    height: 40px;
    position: absolute;
    left: 1265px;
    top: 30px;
  }
  .SearchText {
    width: 80px;
    height: 30px;
    position: absolute;
    left: 1290px;
    top: 35px;
  }
  .SearchText1 {
    color: #000000;
    text-align: center;
    font: 700 18px "Roboto", sans-serif;
    position: absolute;
    left: 0px;
    top: 0px;
    width: 80px;
    height: 30px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .Login {
    width: 80px;
    height: 40px;
    position: static;
  }
  .LoginBox {
    background: #0075ff;
    border-radius: 5px;
    width: 80px;
    height: 40px;
    position: absolute;
    left: 1445px;
    top: 30px;
  }
  .LoginText {
    width: 80px;
    height: 30px;
    position: absolute;
    left: 1445px;
    top: 35px;
  }
  .LoginText1 {
    color: #ffffff;
    text-align: center;
    font: 700 18px "Roboto", sans-serif;
    position: absolute;
    left: 0px;
    top: 0px;
    width: 80px;
    height: 30px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .Footer1 {
    background: #f1f8ff;
    width: 1920px;
    height: 600px;
    position: absolute;
    left: 0px;
    top: 100px;
  }
  .Footer2 {
    background: #f3f4ff;
    width: 1920px;
    height: 600px;
    position: absolute;
    left: 0px;
    top: 700px;
  }
  .Footer3 {
    background: #f6efff;
    width: 1920px;
    height: 600px;
    position: absolute;
    left: 0px;
    top: 1300px;
  }
  .TextBox1 {
    width: 693px;
    height: 60px;
    position: absolute;
    left: 613px;
    top: 1380px;
  }
  .Text1 {
    color: #000000;
    text-align: center;
    font: 700 33px "Roboto", sans-serif;
    position: absolute;
    left: 83px;
    top: 11px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .TextBox2 {
    width: 666px;
    height: 60px;
    position: absolute;
    left: 627px;
    top: 780px;
  }
  .Text2 {
    color: #000000;
    text-align: center;
    font: 700 35px "Roboto", sans-serif;
    position: absolute;
    left: 90px;
    top: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .TextBox3 {
    width: 307px;
    height: 80px;
    position: absolute;
    left: 333px;
    top: 290px;
  }
  .Text3 {
    color: #000000;
    text-align: left;
    font: 700 35px "Roboto", sans-serif;
    position: absolute;
    left: 0px;
    top: -1px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
  }
  .TextBox4 {
    width: 334px;
    height: 50px;
    position: absolute;
    left: 333px;
    top: 404px;
  }
  .Text4 {
    color: #000000;
    text-align: left;
    font: 400 16px "Roboto", sans-serif;
    position: absolute;
    left: 0px;
    top: 0px;
    width: 334px;
    height: 50px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
  }
  .ImgArea1 {
    width: 773.33px;
    height: 358px;
    position: static;
  }
  .rectangle-13 {
    background: #bdbdbd;
    width: 400px;
    height: 190px;
    position: absolute;
    left: 1000px;
    top: 368px;
    box-shadow: 5px 4px 4px 0px rgba(0, 0, 0, 0.25);
  }
  .rectangle-14 {
    background: #bdbdbd;
    width: 400px;
    height: 190px;
    position: absolute;
    left: 1240px;
    top: 200px;
    box-shadow: 5px 4px 4px 0px rgba(0, 0, 0, 0.25);
  }
  .rectangle-12 {
    background: #bdbdbd;
    width: 400px;
    height: 190px;
    position: absolute;
    left: 866.67px;
    top: 256px;
    box-shadow: 5px 4px 4px 0px rgba(0, 0, 0, 0.25);
  }
  .ProductImg1 {
    background: #bdbdbd;
    border-radius: 5px;
    width: 240px;
    height: 180px;
    position: absolute;
    left: 320px;
    top: 1490px;
  }
  .ProductImg2 {
    background: #bdbdbd;
    border-radius: 5px;
    width: 240px;
    height: 180px;
    position: absolute;
    left: 667px;
    top: 1490px;
  }
  .ProductImg3 {
    background: #bdbdbd;
    border-radius: 5px;
    width: 240px;
    height: 180px;
    position: absolute;
    left: 1013px;
    top: 1490px;
  }
  .ProductImg4 {
    background: #bdbdbd;
    border-radius: 5px;
    width: 240px;
    height: 180px;
    position: absolute;
    left: 1360px;
    top: 1490px;
  }
  .Illust2 {
    background: #bdbdbd;
    width: 600px;
    height: 270px;
    position: absolute;
    left: 1000px;
    top: 900px;
    box-shadow: 5px 4px 4px 0px rgba(0, 0, 0, 0.25);
  }
  .Illust1 {
    background: #bdbdbd;
    width: 600px;
    height: 270px;
    position: absolute;
    left: 320px;
    top: 900px;
    box-shadow: 5px 4px 4px 0px rgba(0, 0, 0, 0.25);
  }
  .ProductN1 {
    width: 133px;
    height: 20px;
    position: absolute;
    left: 320px;
    top: 1680px;
  }
  .ProductNT1 {
    color: #000000;
    text-align: left;
    font: 400 16px "Roboto", sans-serif;
    position: absolute;
    left: 0px;
    top: 0px;
    width: 240px;
    height: 20px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
  }
  .Adress1 {
    width: 240px;
    height: 20px;
    position: absolute;
    left: 320px;
    top: 1730px;
  }
  .AdressT1 {
    color: #000000;
    text-align: left;
    font: 400 16px "Roboto", sans-serif;
    position: absolute;
    left: 0px;
    top: 0px;
    width: 240px;
    height: 20px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
  }
  .Price1 {
    width: 133px;
    height: 20px;
    position: absolute;
    left: 320px;
    top: 1705px;
  }
  .PriceT1 {
    color: #000000;
    text-align: left;
    font: 700 16px "Roboto", sans-serif;
    position: absolute;
    left: 0px;
    top: 0px;
    width: 133px;
    height: 20px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
  }
  .ProductNT2 {
    width: 133px;
    height: 20px;
    position: absolute;
    left: 667px;
    top: 1680px;
  }
  .Adress2 {
    width: 240px;
    height: 20px;
    position: absolute;
    left: 667px;
    top: 1730px;
  }
  .Price2 {
    width: 133px;
    height: 20px;
    position: absolute;
    left: 667px;
    top: 1705px;
  }
  .ProductN3 {
    width: 134px;
    height: 20px;
    position: absolute;
    left: 1013px;
    top: 1680px;
  }
  .Adress3 {
    width: 240px;
    height: 20px;
    position: absolute;
    left: 1013px;
    top: 1730px;
  }
  .Price3 {
    width: 134px;
    height: 20px;
    position: absolute;
    left: 1013px;
    top: 1705px;
  }
  .----11 {
    color: #000000;
    text-align: left;
    font: 700 16px "Roboto", sans-serif;
    position: absolute;
    left: 0px;
    top: 0px;
    width: 134px;
    height: 20px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
  }
  .ProductN4 {
    width: 240px;
    height: 20px;
    position: absolute;
    left: 1360px;
    top: 1680px;
  }
  .Adress4 {
    width: 240px;
    height: 20px;
    position: absolute;
    left: 1360px;
    top: 1730px;
  }
  .Price4 {
    width: 133px;
    height: 20px;
    position: absolute;
    left: 1360px;
    top: 1705px;
  }
  
  </style>