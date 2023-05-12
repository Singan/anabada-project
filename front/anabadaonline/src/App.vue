<template>
	<Header :isToken="isToken"></Header>
	<router-view @logined="login" />
</template>

<script>
	import Header from './common/Header.vue';
	import token from '@/common/token';
	import axios from '@/axios';
	import socket from '@/common/socket';
	export default {
		components: {
			Header,
		},
		data() {
			return {
				isToken: token.is(),
				token: token.token,
			};
		},
		methods: {
			login() {
				this.isToken = token.is();
			},
		},
		created() {
			if (token.is() && this.$store.getters.getMember == null) {
				let today = new Date();

				let seconds = today.getSeconds(); // 초
				let milliseconds = today.getMilliseconds(); // 밀리초
				console.log('token.is()' + token.is());
				console.log('현재' + seconds + '' + milliseconds);
				let tokenVal = token.getToken();
				axios.defaults.headers.common['x-auth-token'] = tokenVal;

				axios.get('/member').then((res) => {
					this.$store.commit('setMember', res.data);
					this.$store.commit('setSocket', socket);
					console.log(tokenVal)

					let headers = {
                        'x-auth-token': tokenVal, 'accept-version': '1.2'
					}
					this.$store.getters.getSocket.init();
					this.$store.getters.getSocket.connect();
					this.$store.getters.getSocket.setHeaders(headers)

					// socket.setSocketList(this.$store.getters.getMember.socketList)
				});
			}
		},
	};
</script>

<style>
	body {
		width: 100%;
		margin: 0px;
		height: 100%;
	}

	#app {
		width: 100vw;
		height: 100vh;
	}

	a {
		text-decoration: none;
	}
</style>
