<template>
	<div class="w-100">
		<nav class="navbar navbar-expand-md navbar-light bg-light w-100">
			<router-link class="navbar-brand Logo" to="/">아나바다</router-link>
			<button
				class="navbar-toggler"
				type="button"
				data-bs-toggle="collapse"
				data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent"
				aria-expanded="false"
				aria-label="Toggle navigation"
			>
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav flex-grow-1 justify-content-start align-items-center g-4">
					<router-link class="nav-link active" to="/" aria-current="page">
						<li class="nav-item ml-auto btn text-primary">홈</li>
					</router-link>
					<router-link to="/auction?category=1" class="nav-link nav-item">
						<li class="btn">경매</li>
					</router-link>
					<router-link :to="{ name: '/chatList' }" class="nav-link active nav-item">
						<li class="nav-item btn">채팅</li></router-link
					>
					<router-link to="/Product" class="nav-link active"
						><li class="nav-item btn" v-if="isToken">상품등록</li></router-link
					>
				</ul>
			</div>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav flex-grow-1 justify-content-end align-items-center g-4">
					<router-link to="/login" class="nav-link active">
						<li class="nav-item btn btn-outline-info" v-if="!isToken">로그인</li></router-link
					>

					<router-link to="/JoinMember" class="nav-link active">
						<li class="nav-item btn btn-outline-info" v-if="!isToken">회원가입</li></router-link
					>

					<template v-if="isToken">
						<li class="nav-item">
							<div class="userInfo">
								<img class="userImage" :src="$store.getters.getMember.image" />
								<router-link class="nav-link active" to="/mypage">{{
									$store.getters.getMember.name
								}}</router-link>
							</div>
						</li>
						<li class="nav-item">
							<div class="nav-link active" @click="logout" style="cursor: pointer">로그아웃</div>
						</li>
					</template>
				</ul>
				<form class="d-flex">
					<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
				</form>
			</div>
		</nav>
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
			goHome() {
				this.$router.push('/');
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
<style scoped lang="scss">
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
		height: 4em;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.Logo {
		color: $primary-color;
		text-align: center;
		font: 700 24px 'Roboto', sans-serif;
		cursor: pointer;
		width: 110px;
		height: 40px;
	}

	.ChatButton {
		color: #000000;
		text-align: center;
		font: 700 18px 'Roboto', sans-serif;
		cursor: pointer;
		width: 40px;
		height: 40px;
	}

	.SearchBox {
		border-radius: 5px;
		width: 450px;
		height: 25px;
		padding: 10px;
		line-height: 40px;
		cursor: pointer;
		border-color: $primary-color;
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

	.AuctionButton {
		height: 25px;
	}

	.userInfo {
		display: flex;
		justify-content: center;
		align-items: center;
		overflow: hidden;
	}

	.userImage {
		border-radius: 50%;
		border-style: solid;
		border-color: #000000;
		border-width: 1px;
		max-width: 40px;
		height: 40px;
	}

	.userName {
		font: 500 18px 'Roboto', sans-serif;
		margin-left: 10px;
	}

	.SearchText1 {
		width: 80px;
		height: 30px;
	}

	.button {
		border-radius: 5px;
		background: $primary-color;
		color: #ffffff;
		cursor: pointer;
		font: 700 18px 'Roboto', sans-serif;
		box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.3);
		border-color: $primary-color;
		margin-left: 5px;
	}
	.button:active {
		box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0);
		transform: translateY(4px);
	}

	.btn-outline-info {
		border: none;
		color: #000000;
		letter-spacing: -0.8px;
		font-size: 1.1rem;
		position: relative;
		bottom: 3px;
	}

	.btn-outline-info:hover {
		background-color: #f8f9fa;
		color: $primary-color;
		letter-spacing: -0.8px;
	}
</style>
