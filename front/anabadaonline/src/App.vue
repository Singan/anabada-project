@@ -1,74 +1,23 @@
<template>
	<Header :isToken="isToken" @logout="logout"></Header>
	<router-view @logined="login" :isSocket="isSocket" v-if="isLoad" />
</template>

<script>
	import Header from './common/Header.vue';
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
				isToken: this.$token.is(),
				isSocket: false,
				isLoad: false,
				myProductSocketList: [],
			};
		},
		methods: {
			subscribe(res) {
				console.log('으음');
				this.$swal({
					position: 'bottom-end',
					title: '새로운 입찰이 들어왔습니다!',
					showConfirmButton: false,
					timer: 1500,
				});
			},
			async login() {
				this.isToken = this.$token.is();

				if (this.$token.is() && !this.$store.getters.getMember.no) {
					const tokenVal = this.$token.getToken();
					axios.defaults.headers.common['x-auth-token'] = tokenVal;
					const headers = {
						'x-auth-token': tokenVal,
						'accept-version': '1.2',
					};
					socket.setHeaders(headers);
					const res = await axios.get('/member');
					this.$store.commit('setMember', res.data);
					this.mySocketList = res.data.productSocketList;
				}
			},
			logout() {
				this.$token.remove();

				this.isToken = this.$token.is();
				this.$store.commit('removeMember');
			},
		},

		async created() {
			await this.login();
			this.isLoad = true;
			socket.init();
			await socket.connect();
			this.isSocket = socket.connected();
			// console.log(this.mySocketList);
			// this.mySocketList.forEach(({ socketId }) => {
			// 	socket.subscribe('/product/myProduct/' + socketId, this.subscribe);
			// });
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
