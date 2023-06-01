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
				if (!res.complete) {
					this.$swal({
						position: 'bottom-end',
						title: `새로운 입찰이 들어왔습니다!.`,
						showConfirmButton: false,
						backdrop: false,
						text: `상품이름 : ${res.productName} 에 ${res.price} 원 으로 새로운 입찰이 들어왔습니다!`,
						timer: 5000,
						timerProgressBar: true,
					});
				} else {
					this.$swal({
						title: `상품명 : ${res.productName} 이 낙찰되었습니다!.`,
						showConfirmButton: false,
						backdrop: false,
						text: res.message + res.productName,
						timer: 5000,
						timerProgressBar: true,
					});
				}
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
					this.mySocketList = res.data.productSocketNoList;
					console.log(this.mySocketList);
					this.mySocketList.forEach((socketId) => {
						this.$store.commit(
							'addMysub',
							socket.subscribe('/product/myProduct/' + socketId, this.subscribe),
						);
					});
				}
			},
			logout() {
				this.$token.remove();
				this.$store.commit('removeMember');
				this.isToken = this.$token.is();
				console.log('로그아웃');
			},
		},

		async created() {
			socket.init();
			await socket.connect();
			await this.login();
			this.isLoad = true;
			this.isSocket = socket.connected();
		},
	};
</script>

<style>
	a:visited {
		color: black;
		text-decoration: none;
	}

	body {
		/* width: 100%;
		margin: 0px;
		height: 100%;
		padding: 0px; */
	}

	#app {
		width: 100vw;
		height: 100vh;
	}

	a {
		text-decoration: none;
	}
</style>
