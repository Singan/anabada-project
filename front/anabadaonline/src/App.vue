@@ -1,74 +1,23 @@
<template>
	<Header :isToken="isToken"></Header>
	<router-view @logined="login" :isSocket="isSocket" v-if="isLoad" />
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
				isLoad: false,
			};
		},
		methods: {
			async login() {
				this.isToken = token.is();

				if (token.is() && !this.$store.getters.getMember.no) {
					const tokenVal = token.getToken();
					axios.defaults.headers.common['x-auth-token'] = tokenVal;
					const headers = {
						'x-auth-token': tokenVal,
						'accept-version': '1.2',
					};
					socket.setHeaders(headers);

					const res = await axios.get('/member');
					this.$store.commit('setMember', res.data);
				}
			},
		},

		async created() {
			await this.login();
			this.isLoad = true;

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
