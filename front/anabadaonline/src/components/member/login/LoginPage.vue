<template>
	<div class="inputForm">
		<div class="logo">
			<div class="logoImg"></div>
			<div class="logoName" @click="goMain">아나바다</div>
		</div>

		<form class="idPw">
			<div class="id" id="id">
				<div class="idBox">
					<label for="id">ID </label>
					<input
						type="text"
						v-model="id"
						style="
							border: none;
							outline: none;
							width: 430px;
							height: 50px;
							font-size: 19px;
						"
					/>
				</div>
			</div>
			<div class="pwBox">
				<div class="pw" id="pw">
					<label for="pw">PW </label>
					<input
						type="password"
						v-model="pw"
						style="
							border: none;
							outline: none;
							width: 430px;
							height: 50px;
							font-size: 19px;
						"
					/>
				</div>
			</div>
			<div class="loginBox">
				<div class="login" @click="login">로그인</div>
			</div>
		</form>

		<div class="joinBox">
			<div class="idSearch">아이디 찾기</div>

			<div class="pwSearch">비밀번호 찾기</div>
			<div class="join" @click="goJoin">회원가입</div>
		</div>
	</div>
</template>

<script>
	import axios from '@/axios.js';
	import token from '@/common/token';

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
			async login() {
				await axios
					.post('/member/login', {
						id: this.id,
						pw: this.pw,
					})
					.then((response) => {
						if (response.status == 200) {
							token.setToken(`${response.data.accessToken}`);
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
		background: #ffffff;
		width: 100%;
		height: 1024px;
		overflow: hidden;
		display: flex;
		align-items: center;
		justify-items: center;
	}
	.logoName {
		color: #0075ff;
		text-align: center;
		font: 700 35px 'Roboto', sans-serif;

		width: 244px;
		height: 37px;
		align-items: center;
		justify-content: center;
	}

	.inputForm {
		background: #f6f6f6;
		border-radius: 5px;
		width: 600px;
		height: 300px;
		display: flex;
		align-content: center;
		left: 30%;
		top: 20%;
		flex-direction: column;
		align-items: center;
		justify-items: center;
	}

	.logo {
		width: 100%;
		height: 58.2px;
		display: flex;
		justify-content: center;
		align-items: flex-start;
		flex-basis: 100%;
	}

	.logoImg {
		background: #d9d9d9;
		border-radius: 50%;
		width: 58.2px;
		height: 58.2px;
	}

	.idBox {
		background: #ffffff;
		border-radius: 5px;
		width: 446px;
		height: 52px;

		left: 752px;
		top: 429px;
	}

	.pwBox {
		background: #ffffff;
		border-radius: 5px;
		width: 446px;
		height: 52px;

		left: 752px;
		top: 501px;
	}

	.loginBox {
		background: #0075ff;
		border-radius: 5px;
		width: 446px;
		height: 52px;
		justify-content: center;
		align-items: center;
		left: 752px;
		top: 593px;
	}

	.idSearch {
		color: #000000;
		text-align: center;
		font: 400 13px 'Roboto', sans-serif;

		left: 799px;
		top: 653px;
		width: 78px;
		height: 37px;
		align-items: center;
		justify-content: center;
	}

	.pwSearch {
		color: #000000;
		text-align: center;
		font: 400 13px 'Roboto', sans-serif;

		left: 939px;
		top: 653px;
		width: 90px;
		height: 37px;
		align-items: center;
		justify-content: center;
	}

	.join {
		color: #0075ff;
		text-align: center;
		font: 700 13px 'Roboto', sans-serif;

		left: 1094px;
		top: 653px;
		width: 54px;
		height: 37px;
		align-items: center;
		justify-content: center;
	}
	.joinBox {
		width: 100%;
		justify-content: space-around;
		display: flex;
		justify-content: center;
	}

	.id {
		color: #a3a3a3;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;

		left: 762px;
		top: 437px;
		width: 60px;
		height: 35px;
		align-items: center;
		justify-content: center;
	}

	.pw {
		color: #a3a3a3;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;

		left: 762px;
		top: 510px;
		width: 75px;
		height: 35px;
		align-items: center;
		justify-content: center;
	}

	.idPw {
		display: block;
	}

	.login {
		color: #ffffff;
		text-align: center;
		font: 700 18px 'Roboto', sans-serif;
		left: 776px;
		top: 601px;
		width: 398px;
		height: 37px;
		align-items: center;
		justify-content: center;
	}
</style>
