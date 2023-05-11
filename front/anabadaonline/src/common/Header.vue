<template>
	<div class="Header">
		<div class="Logo" @click="this.$router.push('/')">아나바다</div>

		<a href="/auction" class="AuctionButton"> 경매 </a>
		<a href="/chat" class="ChatButton"> 채팅 </a>
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

		<b-button size="sm" class="SearchButton" type="submit"> 검색하기 </b-button>

		<a href="/login" class="LoginBox" v-if="!isToken"> 로그인 </a>
		<a class="mypage" v-if="isToken" href="/mypage">마이페이지</a>
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
	.Header > * {
		margin-right: 50px;
	}

	.Header {
		background: #ffffff;
		width: 100%;
		height: 115px;
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
