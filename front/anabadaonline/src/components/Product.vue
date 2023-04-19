<template>
  <form @submit.prevent="productRegister">
  <div class="------------">
    <div class="-----"></div>

    <div class="group-46">
      <div class="--">
        <div class="--2">상품 등록하기</div>
      </div>

      <div class="---------">
        <div class="---------2"></div>
          
        <div class="_18px">
          <div class="_18px2">이미지 변경</div>
          <input id="productImages" type="file" @change="onInputImage" accept="image/*" multiple="multiple">
        </div>
        
      </div>

      <div class="_20px">
        <div class="_20px2">상품명</div>
      </div>

      <div class="group-10">
        <div class="rectangle-23"></div>

        <div class="nameBox">
          <div class="nameText" id="name">
            <input 
                type="text"
                 placeholder="상품명을 입력해주세요"    
            >
          </div>
        </div>
      </div>

      <!-- <div class="---5" id="id"><input type="text" v-model="id"></div> -->

      <div class="group-18">
        <div class="_20px3">
          <div class="_20px2">상품 카테고리</div>
        </div>

        <div class="group-11">
          <div class="rectangle-232"></div>
         
          <div class="categoryText">
            
             <select v-model="selectCategory">
                
                <option v-for="item in categoryList " :key="item.categoryNo" :value="item.categoryNo">{{ item.categoryName }}</option> 
               
            </select>
          </div>
        </div>
      </div>

      <div class="_20px4">
        <div class="_20px2">상품 설명</div>
      </div>

      <div class="group-112">
        <div class="rectangle-233"></div>

        <div class="sub----4">
          <div class="detail" contenteditable="true">
            <input
              type="text"
              placeholder="상품 설명을 입력해주세요"
            />
          </div>
          
        </div>
      </div>

      <div class="group-17">
        

        <div class="useageBox">
          <div class="usingDate">상품 사용기간
            
            <div class="radioSelect">
              
               <input type="radio" id="one" class="one" value="하나"  />
              <label for="one">미사용</label>

              <input type="radio" id="two"  value="둘"/>
              <label for="two">0 ~ 4주</label>

              <input type="radio" id="three" value="삼"/>
                <label for="two">4 ~ 8주</label>

                <input type="radio" id="four" value="사"/>
                <label for="two">8주 이상</label>

            </div>
          </div>
        </div>
        
      </div>

      <div class="--7">
        <div class="rectangle-25"></div>

        <button class="_18px3">
          등록하기
        </button>
      </div>

      <div class="rectangle-26"></div>
    </div>
  </div>
  </form>
</template>
<script>
import axios from '@/axios.js'
import { useCookies } from "vue3-cookies";
  const { cookies } = useCookies();
  console.log(cookies.get('token')) 
export default {
  name: '',
  components: {},
  props: {},
  data() {

    return {
      image:'',
      categoryList:'',
      name:'',
     // usingDate:'',
      detail:'',
     // price:'',
      selectCategory:'',
      productImages:null

    };
  },

    methods: {
        goProductDt(){
          this.$router.push('./ProductDt')
        },

        category() {
           axios.get('/category')
           .then((response)=> {
              console.log(response.data) 
              this.categoryList=response.data
              console.log(this.categoryList)
           })
        },

        productRegister() {
          let form = new FormData()
          form.append("name", this.name)
          //form.append("usingDate", this.usingDate)
          form.append("detail", this.detail)
          //form.append("price", this.price)
          form.append("categoryNo", this.selectCategory)
          form.append("productImages", this.productImages)
          axios.defaults.headers.common["x-auth-token"] = cookies.get('token')
          console.log(this.selectCategory)

          axios.post('/product',
            form,
            {
              header: { 
                'Content-Type': 'multipart/form-data' ,
              }
            }
          ).then((response) => {
            console.log(this.category)
            console.log(response)
            // if (response.status == 200) {
            //   this.$router.push('./ProductDt')
            // }
          })
        },
        onInputImage(e) {
          this.productImages = e.target.files;
        }
        },

    
    created() {
        this.category() 
    }


  
};
</script>
<style scoped>
.------------,
.------------ * {
  box-sizing: border-box;
}
.------------ {
  background: #f1f1f1;
  width: 1920px;
  height: 1024px;
  position: relative;
  overflow: hidden;
}
.----- {
  background: #ffffff;
  width: 700px;
  height: 900px;
  position: absolute;
  left: 610px;
  top: 61px;
}
.group-46 {
  width: 540px;
  height: 778px;
  position: static;
}
.-- {
  width: 200px;
  height: 40px;
  position: absolute;
  left: 690px;
  top: 92px;
}
.--2 {
  color: #0075ff;
  text-align: left;
  font: 700 28px "Roboto", sans-serif;
  position: absolute;
  left: 0px;
  top: 4px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.--------- {
  width: 90px;
  height: 30px;
  position: static;
}
.---------2 {
  background: #0075ff;
  border-radius: 10px;
  width: 90px;
  height: 30px;
  position: absolute;
  left: 945px;
  top: 300px;
}
._18px {
  width: 500px;
  height: 200px;
  position: absolute;
  left: 945px;
  top: 300px;
}
._18px2 {
  color: #ffffff;
  text-align: center;
  font: 700 14px "Roboto", sans-serif;
  left: 0px;
  top: 0px;
  width: 90px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
}
._20px {
  width: 60px;
  height: 23px;
  position: absolute;
  left: 760px;
  top: 347px;
}
.usingDate {
  color: #000000;
  text-align: left;
  font: 400 16px "Roboto", sans-serif;
  position: absolute;
  left: 0px;
  top: 2px;
  display: block;
  align-items: center;
  justify-content: flex-start;
}

.radioSelect {
  color: #000000;
  text-align: left;
  font: 400 16px "Roboto", sans-serif;
  left: 0px;
  top: 2px;
  display: block;
  align-items: center;
  justify-content: flex-start;
}

.one {

}

.pick1 {
   width: 110px;
  height: 23px;
  position: absolute;
  left: 900px;
  top: 535px;
}
.group-10 {
  width: 470px;
  height: 30px;
  position: static;
}
.rectangle-23 {
  border-radius: 5px;
  border-style: solid;
  border-color: #0075ff;
  border-width: 1.5px;
  width: 470px;
  height: 30px;
  position: absolute;
  left: 760px;
  top: 380px;
}
.nameBox {
  width: 170px;
  height: 19px;
  position: absolute;
  left: 773px;
  top: 386px;
}
.nameText {
  color: #797979;
  text-align: left;
  font: 300 12px "Roboto", sans-serif;
  position: absolute;
  left: 0px;
  top: 2px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.group-18 {
  width: 470px;
  height: 63px;
  position: static;
}
._20px3 {
  width: 110px;
  height: 23px;
  position: absolute;
  left: 760px;
  top: 440px;
}
.group-11 {
  width: 470px;
  height: 30px;
  position: static;
}
.rectangle-232 {
  border-radius: 5px;
  border-style: solid;
  border-color: #0075ff;
  border-width: 1.5px;
  width: 470px;
  height: 30px;
  position: absolute;
  left: 760px;
  top: 473px;
}
.categoryText {
  width: 170px;
  height: 19px;
  position: absolute;
  left: 773px;
  top: 479px;
}
._20px4 {
  width: 110px;
  height: 23px;
  position: absolute;
  left: 760px;
  top: 626px;
}
.group-112 {
  width: 470px;
  height: 121px;
  position: static;
}
.rectangle-233 {
  border-radius: 5px;
  border-style: solid;
  border-color: #0075ff;
  border-width: 1.5px;
  width: 470px;
  height: 121px;
  position: absolute;
  left: 760px;
  top: 659px;
}
.sub----4 {
  width: 170px;
  height: 76.63px;
  position: absolute;
  left: 773px;
  top: 683.2px;
}
.detail {
  color: #797979;
  text-align: left;
  font: 300 12px "Roboto", sans-serif;
  position: absolute;
  left: 0px;
  top: 31px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.vector-6 {
  position: absolute;
  left: 1195px;
  top: 483px;
  overflow: visible;
}
.group-17 {
  width: 460px;
  height: 63px;
  position: static;
}
.useageBox {
  width: 500px;
  height: 100px;
  position: absolute;
  left: 760px;
  top: 533px;
}
.group-13 {
  width: 70px;
  height: 30px;
  position: static;
}
.ellipse-13 {
  border-radius: 50%;
  border-style: solid;
  border-color: #0075ff;
  border-width: 1.5px;
  width: 20px;
  height: 20px;
  position: absolute;
  left: 775px;
  top: 571px;
}
.--3 {
  width: 40px;
  height: 30px;
  position: absolute;
  left: 805px;
  top: 566px;
}
.---- {
  color: #000000;
  text-align: center;
  font: 400 12px "Roboto", sans-serif;
  position: absolute;
  left: 0px;
  top: 0px;
  width: 40px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.group-14 {
  width: 70px;
  height: 30px;
  position: static;
}
.ellipse-132 {
  border-radius: 50%;
  border-style: solid;
  border-color: #0075ff;
  border-width: 1.5px;
  width: 20px;
  height: 20px;
  position: absolute;
  left: 900px;
  top: 571px;
}
.--4 {
  width: 40px;
  height: 30px;
  position: absolute;
  left: 930px;
  top: 566px;
}
.group-15 {
  width: 70px;
  height: 30px;
  position: static;
}
.ellipse-133 {
  border-radius: 50%;
  border-style: solid;
  border-color: #0075ff;
  border-width: 1.5px;
  width: 20px;
  height: 20px;
  position: absolute;
  left: 1025px;
  top: 571px;
}
.--5 {
  width: 40px;
  height: 30px;
  position: absolute;
  left: 1055px;
  top: 566px;
}
.group-16 {
  width: 70px;
  height: 30px;
  position: static;
}
.ellipse-134 {
  border-radius: 50%;
  border-style: solid;
  border-color: #0075ff;
  border-width: 1.5px;
  width: 20px;
  height: 20px;
  position: absolute;
  left: 1150px;
  top: 571px;
}
.--6 {
  width: 40px;
  height: 30px;
  position: absolute;
  left: 1180px;
  top: 566px;
}
.--7 {
  width: 470px;
  height: 40px;
  position: static;
}
.rectangle-25 {
  background: #0075ff;
  border-radius: 5px;
  width: 470px;
  height: 40px;
  position: absolute;
  left: 760px;
  top: 830px;
}
._18px3 {
  width: 80px;
  height: 30px;
  position: absolute;
  left: 955px;
  top: 835px;
}
._18px4 {
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
.rectangle-26 {
  background: #d9d9d9;
  width: 120px;
  height: 120px;
  position: absolute;
  left: 930px;
  top: 167px;
}
</style>
