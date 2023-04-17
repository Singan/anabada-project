<template>
	<div class="container">
		<form @submit.prevent="submitForm">
			
			<div >
				<label for="id">id</label>
				<input type="text" id="id" v-model="id" />
			</div>
			<div >
				<label for="pw">pw</label>
				<input type="password" id="pw" v-model="pw" />
			</div>
			<div > 
				<label for="name">이름</label>
				<input type="text" id="name" v-model="name" />
			</div>
			<div >
				<label for="birth">생년월일</label>
				<input type="date" id="birth" v-model="birth" />
			</div>
            <div >
				<label for="addr">주소</label>
				<input type="text" id="addr" v-model="addr" />
			</div>
			<div >
				<label for="detailaddr">상세주소</label>
				<input type="text" id="detailaddr" v-model="detailaddr" />
			</div>
            <div >
				<label for="Wishaddr">거래희망지</label>
				<input type="text" id="Wishaddr" v-model="Wishaddr" />
			</div>
            <div >
				<label for="image">프로필 이미지</label>
				<input id="image" type="file" @change="onInputImage" accept="image/*"/>
			</div>
			<div>
			<button type="submit">회원가입</button>
			</div>
		</form>
	</div>
</template>

<script>
import axios from '@/axios.js';

export default {
	name: 'SignupForm',
	data() {
		return {
			id: '',
			pw: '',
			name: '',
			birth: '',
			detailaddr: '',
			addr: '',
            Wishaddr: '',
            image: '',
		};
	},
	methods: {
		submitForm() {
			let form = new FormData()
			form.append("id",this.id)
            form.append("pw",this.pw)
            form.append("name",this.name)
            // form.append("birth",this.birth)
            form.append("detailaddr",this.detailaddr)
            form.append("addr",this.addr)
            form.append("Wishaddr",this.Wishaddr)

            axios.post('/member',
            form,
            {
                header: { 'Content-Type': 'multipart/form-data' }
            }
            ).then((response)=>{
            console.log(response)
          })
		},
        onInputImage(e) {
            this.image = e.target.files[0];
        }
        
	},
};
// 일반
//
</script>

<style scoped>

</style>