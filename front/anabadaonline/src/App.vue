<template>
	<Header :isToken="isToken"></Header>
	<router-view @logined="login" />
</template>

<script>
	import Header from './common/Header.vue';
	import token from '@/common/token';
	import axios from '@/axios';
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
			if (token.is() && !this.$store.getters.getMember) {
				const header = {
					'x-auth-token': token.getToken(),
				};
				axios
					.get('/member', {
						headers: header,
					})
					.then((res) => {
						this.$store.commit('setMember', res.data);
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
</style>
