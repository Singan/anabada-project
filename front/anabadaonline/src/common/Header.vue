<template>
	<div class="Header">
		<div class="Logo" @click="goMain">아나바다</div>

		<div class="AuctionButton" @click="goAuction">경매</div>

		<div class="ChatButton" @click="goChat">채팅</div>

		<div class="SearchBox" contenteditable="true">
			<b-form-input
				size="sm"
				class="mr-sm-2"
				type="text"
				placeholder="검색어를 입력해주세요"
				v-model="keyword"
				@keyup.enter="searchresultshow(keyword)"
			></b-form-input>
		</div>

		<b-button
			size="sm"
			class="SearchButton"
			type="submit"
			@click="goAuction(keyword)"
		>
			검색하기
		</b-button>

		<div class="LoginBox" v-if="!isToken" @click="goLogin">로그인</div>
	</div>
</template>
<script>
	export default {
		props: {
			isToken: {
				type: Boolean,
			},
		},
		data() {
			return {};
		},
		methods: {
			goLogin() {
				this.$router.push('./login');
			},
			goAuction() {
				this.$router.push('./auction');
			},
			goChat() {
				this.$router.push('./chat');
			},
			goMain() {
				this.$router.push('./');
			},
			searchresultshow(keyword) {
				if (keyword !== '') {
					//검색어를 입력한 경우
					this.$router.push({
						name: 'SearchPage',
						params: {
							keyword: this.keyword,
							isResultShow: true,
						},
					});
					this.keyword = '';
					console.log('"', keyword, '"' + ' 검색');
				} else {
					alert('검색어를 입력해주세요!'); //검색어를 입력하지 않은 경우
				}
			},
		},
	};
</script>
<style scoped>
	.Header,
	.Header * {
		margin-right: 50px;
	}

	.Header {
		background: #ffffff;
		width: 100%;
		height: 115px;
		overflow: hidden;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.AuctionButton {
		color: #0075ff;
		text-align: center;
		font: 700 18px 'Roboto', sans-serif;
		cursor: pointer;
		width: 40px;
		height: 20px;
	}

	.Logo {
		color: #0075ff;
		text-align: center;
		font: 700 24px 'Roboto', sans-serif;
		cursor: pointer;
		width: 110px;
		height: 30px;
	}

	.ChatButton {
		color: #000000;
		text-align: center;
		font: 700 18px 'Roboto', sans-serif;
		cursor: pointer;
		width: 40px;
		height: 20px;
	}

	.SearchBox {
		background: #d9ecff;
		border-radius: 5px;
		width: 450px;
		height: 40px;
		padding: 10px;
		line-height: 40px;
		cursor: pointer;
	}

	.SearchButton {
		background: #ffffff;
		border-radius: 5px;
		border-style: solid;
		border-color: #000000;
		border-width: 1px;
		width: 130px;
		height: 40px;
		color: #000000;
		text-align: center;
		font: 700 18px 'Roboto', sans-serif;
		line-height: 40px;
		cursor: pointer;
	}

	.SearchText1 {
		width: 80px;
		height: 30px;
	}

	.LoginBox {
		background: #0075ff;
		border-radius: 5px;
		width: 80px;
		height: 40px;
		color: #ffffff;
		text-align: center;
		font: 700 18px 'Roboto', sans-serif;
		line-height: 40px;
		cursor: pointer;
	}
</style>
