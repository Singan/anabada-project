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
		async created() {
			this.$store.commit('setSocket', socket);
			this.$store.getters.getSocket.init();

			await this.$store.getters.getSocket.connect();

			let headers = {
				'x-auth-token': token.getToken(),
				'accept-version': '1.2',
			};
			this.$store.getters.getSocket.setHeaders(headers);
			if (token.is() && this.$store.getters.getMember == null) {
				let tokenVal = token.getToken();
				axios.defaults.headers.common['x-auth-token'] = tokenVal;

				axios.get('/member').then((res) => {
					this.$store.commit('setMember', res.data);
					console.log(tokenVal);

					//this.$store.getters.getSocket.subscribe('/product/' + this.productNo, this.recevieFunc);

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
