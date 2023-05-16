<template>
	<CustomHeader :isToken="isToken"></CustomHeader>
	<router-view @logined="login" :isSocket="isSocket" v-if="isLoad" />
</template>

<script>
	import CustomHeader from './common/Header.vue';
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
			CustomHeader,
		},
		data() {
			return {
				isToken: token.is(),
				token: token.token,
				isSocket: false,
				isLoad: false,
			};
		},
		methods: {
			login() {
				this.isToken = token.is();
				if (token.is() && !this.$store.getters.getMember.no) {
					const tokenVal = token.getToken();
					axios.defaults.headers.common['x-auth-token'] = tokenVal;
					const headers = {
						'x-auth-token': tokenVal,
						'accept-version': '1.2',
					};
					socket.setHeaders(headers);

					axios.get('/member').then((res) => {
						this.$store.commit('setMember', res.data);
						this.isLoad = true;
					});
				}
			},
		},

		async created() {
			this.login();

			socket.init();
			await socket.connect();
			this.isSocket = socket.connected();
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
