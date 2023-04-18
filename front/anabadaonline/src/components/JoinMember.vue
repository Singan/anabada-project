<template>
	<div class="container">
		<form @submit.prevent="submitForm">
			
      <div class="text">
        본인 정보를 입력해 주세요 
      </div>
      <li>
			<div class="id" >
				<label for="id">id</label>
				<input type="text" id="email" v-model="id" />
			</div>
			<div class="pw">
				<label for="pw">pw</label>
				<input type="password" id="pw" v-model="pw" />
			</div>
			<div class="name"> 
				<label for="name">이름</label>
				<input type="text" id="name" v-model="name" />
			</div>
			<div class="birth">
				<label for="birth">생년월일</label>
				<input type="date" id="birth" v-model="birth" />
			</div>
            <div class="addr">
				<label for="addr">주소</label>
				<input type="text" id="addr" v-model="addr" />
			</div>
			<div class="detailaddr">
				<label for="detailaddr">상세주소</label>
				<input type="text" id="detailaddr" v-model="detailaddr" />
			</div>
            <div class="Wishaddr">
				<label for="Wishaddr">거래희망지</label>
				<input type="text" id="Wishaddr" v-model="Wishaddr" />
			</div>
            <div class="image">
				<label for="image">프로필 이미지</label>
				<input id="image" type="file" @change="onFileChange">
			</div>
			
			<button type="submit" class="join">회원가입</button>
      <button class="cancel" v-on:click="Main">취소하기</button>
      </li>
     

      
			
		</form>
	</div>
</template>

<script>
 import axios from '@/axios.js'
export default {
	name: 'JoinMember',
	data() {
		return {
			id: '',
			pw: '',
			name: '',
			birth: '',
			detailaddr: '',
			addr: '',
            Wishaddr: '',
            image: null,
		};
	},
	methods: {
    onInputImage() {
      this.input.image = this.$refs.serveyImage.files
        console.log("this.input.image")
        },
	},

  methods: {
    Main: function() {
       this.$router.push({ path: '/' })
      
    }
  }
};
</script>

<style scoped>


.id {
  color: #000000;
  text-align: left;
  font: 400 16px "Roboto", sans-serif;
  position: absolute;
  left: 648px;
  top: 114px;
  width: 398px;
  height: 37px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.text  {
  color: #0075ff;
  text-align: left;
  font: 700 28px "Roboto", sans-serif;
  position: absolute;
  left: 614px;
  top: 53px;
  width: 398px;
  height: 37px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.pw {
  color: #000000;
  text-align: left;
  font: 400 16px "Roboto", sans-serif;
  position: absolute;
  left: 648px;
  top: 176px;
  width: 398px;
  height: 37px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.name {
    color: #000000;
  text-align: left;
  font: 400 16px "Roboto", sans-serif;
  position: absolute;
  left: 648px;
  top: 240px;
  width: 398px;
  height: 37px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.birth {
    color: #000000;
  text-align: left;
  font: 400 16px "Roboto", sans-serif;
  position: absolute;
  left: 648px;
  top: 304px;
  width: 398px;
  height: 37px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.addr {
   color: #000000;
  text-align: left;
  font: 400 16px "Roboto", sans-serif;
  position: absolute;
  left: 648px;
  top: 368px;
  width: 398px;
  height: 37px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.detailaddr {
    color: #000000;
  text-align: left;
  font: 400 16px "Roboto", sans-serif;
  position: absolute;
  left: 648px;
  top: 432px;
  width: 398px;
  height: 37px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.Wishaddr {
    color: #000000;
  text-align: left;
  font: 400 16px "Roboto", sans-serif;
  position: absolute;
  left: 648px;
  top: 496px;
  width: 398px;
  height: 37px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.image {
    color: #000000;
  text-align: left;
  font: 400 16px "Roboto", sans-serif;
  position: absolute;
  left: 648px;
  top: 560px;
  width: 398px;
  height: 37px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.join {
   width: 8.5rem;
   height: 50px;
   line-height: 50px;
   background-color: white;
   border-radius: 5px;
   margin: 0 auto;
   color:#0075ff;
    position: absolute;
  left: 648px;
  top: 610px;

}

.cancel {
   width: 8.5rem;
   height: 50px;
   line-height: 50px;
   background-color: white;
   border-radius: 5px;
   margin: 0 auto;
   color:#e20303;
  position: absolute;
  left: 1000px;
  top: 610px;

}

</style>