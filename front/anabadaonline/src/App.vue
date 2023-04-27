<template>
	<Header :isToken="isToken"></Header>
	<router-view @logined="login" />
</template>

<script>
	import Header from './common/Header.vue';
	import token from '@/common/token';
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
				console.log(this.isToken);
			},
		},
	};
</script>

<style>
	body {
		width: 100%;
	}
</style>
