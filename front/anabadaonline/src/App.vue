<template>
	<Header :isToken="isToken"></Header>
	<router-view @logined="login" :isSocket="isSocket" />
</template>

<script>
	import Header from './common/Header.vue';
	import token from '@/common/token';
	import axios from '@/axios';
	import socket from '@/common/socket';

	export default {
		provide() {
			return {
				socket: socket,
			};
		},
		components: {
			Header,
		},
		data() {
			return {
				isToken: token.is(),
				token: token.token,
				isSocket: false,
			};
		},
		methods: {
			login() {
				this.isToken = token.is();
			},
		},
		async beforeCreate() {
			console.log('beforeCreate 실행');
			socket.init();
			console.log('커넥트 완료 전');
			console.log(socket.connected());
			await socket.connect();
			this.isSocket = socket.connected();
			console.log('커넥트 완료 후');
			if (token.is() && this.$store.getters.getMember == null) {
				let tokenVal = token.getToken();
				axios.defaults.headers.common['x-auth-token'] = tokenVal;

				axios.get('/member').then((res) => {
					this.$store.commit('setMember', res.data);
					let headers = {
						'x-auth-token': token.getToken(),
						'accept-version': '1.2',
					};
					socket.setHeaders(headers);
					this.$emit('socketConnect');
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
