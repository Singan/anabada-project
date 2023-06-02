<template>
	<div class="Header">
		<router-link class="Logo" to="/">아나바다</router-link>

		<router-link to="/auction?category=1" class="AuctionButton"> 경매 </router-link>
		<router-link :to="{ name: '/chatList' }" class="ChatButton"> 채팅 </router-link>
		<input
			class="SearchBox"
			v-model="keyword"
			@keyup.enter="searchresultshow(keyword)"
			placeholder="검색어를 입력해주세요"
			autocomplete="off"
			type="text"
		/>

		<router-link to="/login" class="LoginBox" v-if="!isToken"> 로그인 </router-link>
		<router-link to="/JoinMember" class="joinBox" v-if="!isToken"> 회원가입 </router-link>

		<div v-if="isToken">
			<div class="userInfo">
				<img class="userImage" :src="$store.getters.getMember.image" />
				<router-link class="userName" to="/mypage">{{ $store.getters.getMember.name }}</router-link>
				<div class="LogoutBox" @click="logout">로그아웃</div>
			</div>
		</div>
	</div>
</template>
<script>
	import axios from 'axios';

	export default {
		props: {
			isToken: {
				type: Boolean,
			},
		},
		data() {
			return {
				keyword: '',
				member: '',
			};
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
			logout() {
				this.$emit('logout');
				this.$router.push('/');
			},
			memberImage() {
				axios.get('/member').then((response) => {
					this.member = response.data;
					console.log(member.image);
				});
			},
		},
	};
</script>
<style scoped>
	.Header,
	.Header > * {
		display: flex;
		justify-content: center;
		align-items: center;
		margin: 0 auto;
	}

	.Header {
		background: #ffffff;
		width: 70vw;
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
		cursor: pointer;
	}

	.userInfo {
		display: flex;
		justify-content: center;
		align-items: center;
		margin-right: 30px;
	}

	.userImage {
		border-radius: 50%;
		border-style: solid;
		border-color: #000000;
		border-width: 1px;
		width: 40px;
		height: 40px;
	}

	.userName {
		font: 500 16px 'Roboto', sans-serif;
		margin-left: 10px;
	}

	.SearchText1 {
		width: 80px;
		height: 30px;
	}

	.LoginBox {
		background: #0075ff;
		margin-left: 40px;
		border-radius: 5px;
		width: 80px;
		height: 40px;
		color: #ffffff;
		text-align: center;
		font: 700 18px 'Roboto', sans-serif;
		line-height: 40px;
		cursor: pointer;
	}
	.joinBox {
		background: #0075ff;
		border-radius: 5px;
		margin-right: 100px;
		width: 90px;
		height: 40px;
		color: #ffffff;
		text-align: center;
		font: 700 18px 'Roboto', sans-serif;
		line-height: 40px;
		cursor: pointer;
	}
	.LogoutBox {
		background: #0075ff;
		margin-left: 35px;
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
