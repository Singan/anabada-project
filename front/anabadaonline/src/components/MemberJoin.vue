<template>
	<div class="container">
		<form @submit.prevent="submitForm">
			<div>
				<label for="id">id</label>
				<input type="text" id="id" v-model="id" />
			</div>
			<div>
				<label for="pw">pw</label>
				<input type="password" id="pw" v-model="pw" />
			</div>
			<div>
				<label for="name">이름</label>
				<input type="text" id="name" v-model="name" />
			</div>
			<div>
				<label for="birth">생년월일</label>
				<input type="date" id="birth" v-model="birth" />
			</div>
            <div>
				<label for="addr">주소</label>
				<input type="text" id="addr" v-model="addr" />
			</div>
			<div>
				<label for="detailaddr">상세주소</label>
				<input type="text" id="detailaddr" v-model="detailaddr" />
			</div>
            <div>
				<label for="Wishaddr">거래희망지</label>
				<input type="text" id="Wishaddr" v-model="Wishaddr" />
			</div>
            <div>
				<label for="image">프로필 이미지</label>
				<input id="image" type="file" @change="onFileChange">
			</div>
			<button type="submit">회원가입</button>
		</form>
	</div>
</template>

<script>
import axios from 'axios';

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
		async submitForm() {
            const userData = {
                id: this.id,
                pw: this.pw,
                name: this.name,
                birth: this.birth,
                detailaddr: this.detailaddr,
                addr: this.addr,
                Wishaddr: this.Wishaddr,
                image: this.image,
            };         
            const { data } = await registerUser(userData)
            this.logMessage = `${data.name} 님이 가입되었습니다.`;
            // try {
			// 	const response = await axios.post('/api/member', userData); 
			// 	if (response.status === 200) {
			// 		alert('회원가입성공.');
			// 		this.$router.push('/login');
			// 	} else {
			// 		alert(response.data);
			// 	}
			// } catch (error) {
			// 	console.error(error);
			// }
        },
	},
};
</script>

<style scoped></style>