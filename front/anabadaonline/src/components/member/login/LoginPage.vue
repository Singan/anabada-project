<template>
	<div class="flexContainer">
		<div class="logo">
			<div class="logoImg"></div>
			<div class="logoName" @click="goMain">아나바다</div>
		</div>
		<form class="idPw">
			<input
				type="text"
				v-model="id"
				style="font-size: 19px"
				class="input"
				placeholder="아이디"
				autocomplete="off"
			/>
			<input
				type="password"
				v-model="pw"
				style="font-size: 19px"
				class="input"
				placeholder="패스워드"
				autocomplete="new-password"
			/>
			<div class="login" @click="login">로그인</div>
			<div class="joinBox">
				<div class="idSearch">아이디 찾기</div>

				<div class="pwSearch">비밀번호 찾기</div>
				<div class="join" @click="goJoin">회원가입</div>
			</div>
		</form>
	</div>
</template>

<script>
	import axios from '@/axios.js';

	export default {
		name: 'LoginPage',
		data() {
			return {
				id: '',
				pw: '',
			};
		},
		methods: {
			goJoin() {
				this.$router.push('./JoinMember');
			},
			goMain() {
				this.$router.push('./');
			},
			login() {
				axios
					.post('/member/login', {
						id: this.id,
						pw: this.pw,
					})
					.catch((error) => {
						let message = error.response.data.message;
						(this.id = ''), (this.pw = '');
						this.$swal({
							icon: 'error',
							title: '로그인을 실패하였습니다.',
							text: message,
						});
					})
					.then((response) => {
						if (response.status == 200) {
							this.$token.setToken(`${response.data.accessToken}`);
							axios.defaults.headers.common['x-auth-token'] = this.$token.getToken();
							this.$emit('logined');
							this.$router.push('./');
						}
					});
			},
		},
	};
</script>

<style scoped>
	.flexContainer {
		width: 500px;
		height: 400px;
		display: flex;
		align-items: center;
		justify-items: center;
		flex-direction: column;
		position: relative;
		top: 150px;
		margin: auto;
		border-radius: 5px;
	}

	.idPw {
		display: flex;
		flex-direction: column;
		width: 500px;
		align-items: center;
		height: 300px;
		background: #f6f6f6;
	}

	.idPw > * {
		width: 90%;
		height: 23%;
		margin-top: 5%;
	}

	.input {
		border-radius: 5px;
		border: none;
	}

	.logoName {
		color: #0075ff;
		text-align: center;
		font: 700 35px 'Roboto', sans-serif;
		width: 244px;
		height: 37px;
	}

	.logo {
		width: 100%;
		height: 10%;
		margin: 20px;
		display: flex;
		align-content: center;
		justify-content: center;
		align-items: flex-start;
	}

	.logoImg {
		background: #d9d9d9;
		border-radius: 50%;
		width: 58.2px;
		height: 100%;
	}

	.idBox {
		background: #ffffff;
		border-radius: 5px;
		width: 446px;
		height: 52px;
		display: flex;
		flex-direction: column;
		left: 752px;
		top: 429px;
	}

	.idSearch {
		color: #000000;
		text-align: center;
		font: 400 13px 'Roboto', sans-serif;
		width: 78px;
		height: 37px;
		align-items: center;
		justify-content: center;
	}

	.pwSearch {
		color: #000000;
		text-align: center;
		font: 400 13px 'Roboto', sans-serif;

		width: 90px;
		height: 37px;
	}

	.join {
		color: #0075ff;
		text-align: center;
		font: 700 13px 'Roboto', sans-serif;

		width: 54px;
		height: 37px;
	}

	.joinBox {
		width: 100%;
		justify-content: space-around;
		display: flex;
		justify-content: center;
		height: auto;
	}

	.id {
		color: #a3a3a3;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		width: 60px;
		height: 35px;
	}

	.idSearch {
		color: #000000;
		text-align: center;
		font: 400 13px 'Roboto', sans-serif;
		width: 78px;
		height: 37px;
	}

	.pwSearch {
		color: #000000;
		text-align: center;
		font: 400 13px 'Roboto', sans-serif;

		width: 90px;
		height: 37px;
	}

	.join {
		color: #0075ff;
		text-align: center;
		font: 700 13px 'Roboto', sans-serif;

		width: 54px;
		height: 37px;
	}

	.joinBox {
		width: 100%;
		justify-content: space-around;
		display: flex;
	}

	.id {
		color: #a3a3a3;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;

		width: 60px;
		height: 35px;
	}

	.login {
		text-align: center;
		font: 700 18px 'Roboto', sans-serif;
		color: #ffffff;
		line-height: 52px;
		align-items: center;
		background: #0075ff;
		border-radius: 5px;
		width: 446px;
		height: 52px;
	}

	.login {
		text-align: center;
		font: 700 18px 'Roboto', sans-serif;
		color: #ffffff;
		line-height: 52px;
		align-items: center;
		background: #0075ff;
		border-radius: 5px;
		width: 446px;
		height: 52px;
	}
</style>
