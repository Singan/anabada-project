<template>
	<div class="container main">
		<div class="container header">
			<div class="container header_home">
				<!-- 로고 -->
				<div class="logo_area">
					<img class="main_logo" src="@/assets/logo.jpg" alt="ana_logo" @click="goMain" />
					<span class="main_text">아나바다 프로필</span>
				</div>
				<div class="profile_area">
					<div class="profile_inner">
						<!-- 프로필 -->
						<img class="profile_image" :src="this.$store.getters.getMember.image" alt="profile_image" />
						<div class="profile">
							<p class="user_name">{{ myData.memberName }}</p>
							<p class="user_id">{{ myData.memberId }}</p>
						</div>
					</div>
				</div>
				<div class="container header_menu">
					<!-- 프로필 밑 메뉴 -->
					<ul class="menu">
						<li class="li_text">
							내아나머니
							<p class="money">{{ myData.memberMoney }}&nbsp;머니</p>
							<button type="button" class="btn btn-outline-primary btn-sm" @click="goPayment">
								충전
							</button>
						</li>
					</ul>
					<ul class="menu link">
						<li class="exist_text">
							<router-link to="/MemberExit">
								<a href="" style="color: #b4b4b4">회원탈퇴</a>
							</router-link>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="container content">
				<div class="content_wrap">
					<div class="content_top"></div>
					<!-- 내정보 -->
					<div class="account_box">
						<div class="box_title">
							<a style="color: #fff; cursor: default">내정보</a>
							<button class="change btn btn-primary btn-sm" @click="goChange">수정하기</button>
						</div>
						<div class="box_contents1">
							<div class="name_row">
								<img src="@/assets/user.png" alt="user" class="box_image_size" />
								<span class="box_text">{{ myData.memberName }}</span>
							</div>
						</div>
						<div class="box_contents1">
							<div class="name_row">
								<img src="@/assets/home.png" alt="addr" class="box_image_size" />
								<span class="box_text">{{ myData.memberFullAddr }}</span>
							</div>
						</div>
						<div class="box_contents_end">
							<div class="name_row">
								<img src="@/assets/pin.png" alt="wish_addr" class="box_image_size" />
								<span class="box_text">{{ myData.memberWishAddr }}</span>
							</div>
						</div>
					</div>
					<div class="trade_box">
						<div class="box_title">
							<a style="color: #fff; cursor: default">내거래</a>
							<button class="change btn btn-primary btn-sm" @click="goMyTrade">거래내역</button>
						</div>
						<div class="box_contents1">
							<div class="name_row">
								<img src="@/assets/shopping-cart.png" alt="buy" class="box_image_size" />
								<router-link to="/my/history/buy">
									<a class="box_text" href="">구매내역</a>
								</router-link>
							</div>
						</div>
						<div class="box_contents1">
							<div class="name_row">
								<img src="@/assets/selling-cart.png" alt="sales" class="box_image_size" />
								<router-link to="/my/history/sales">
									<a class="box_text" href="">판매내역</a>
								</router-link>
							</div>
						</div>
						<div class="box_contents1">
							<div class="name_row">
								<img src="@/assets/bidding.png" alt="bid" class="box_image_size" />
								<router-link to="/my/history/bid">
									<a class="box_text" href="">입찰내역</a>
								</router-link>
							</div>
						</div>
						<div class="box_contents1">
							<div class="name_row">
								<img src="@/assets/product.png" alt="upload" class="box_image_size" />
								<router-link to="/Product">
									<a class="box_text" href="">상품등록</a>
								</router-link>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- <div class="mypageContainer">
		<div class="mypageHeader">
			<img class="userImage" :src="this.$store.getters.getMember.image" />
			<div class="userNameProfile">
				<div class="showMoney">
					<div class="userName">
						{{ myData.memberName }}
					</div>
					<span class="moneyText">머니</span>
					<span class="anaMoney">{{ myData.memberMoney }}</span>
				</div>
				
			</div>

			<div class="moneyInfoBox">
				
				<span class="anaMoneyText">내 아나머니</span>
				<button class="anaMoneyChargeButton" @click="goPayment">충전</button>
			</div>
		</div>

		<div class="userInfoText">개인정보</div>

		<div class="mypageUserSetting">
			<span class="userInfoChange" @click="goChange(0)"> 회원 정보 수정 </span>
			<span class="withDrawal" @click="goChange(1)">회원 탈퇴</span>
		</div>
		<div class="line"></div>

		<div class="myTradeText">내 거래</div>

		<div class="mypageTradeInfo">
			<router-link to="./my/history/buy" class="dealButton">구매 내역</router-link>
			<router-link to="/my/history/sales" class="dealButton">판매 내역</router-link>
			<router-link to="/my/history/bid" class="dealButton">입찰 내역</router-link>
			<router-link to="./Product" class="dealButton">상품등록</router-link>
			<router-link to="./Jjim" class="dealButton">찜목록</router-link>
		</div>
		<div class="line"></div>
	</div> -->
</template>

<script>
	import axios from '@/axios';
	export default {
		data() {
			// quickfix to have components available to pass as props
			return {
				myData: '',
			};
		},
		methods: {
			async goChange(e) {
				const { value: password } = await this.$swal({
					title: '비밀번호 확인',
					input: 'password',
					inputPlaceholder: '비밀번호를 입력해주세요',
					heightAuto: true,
					inputAttributes: {
						maxlength: 20,
						autocapitalize: 'off',
						autocorrect: 'off',
						autocomplete: 'new-password',
					},
					allowOutsideClick: false,
				});
				axios
					.post('/mypage/confirm', {
						confirmPassword: password,
					})
					.then((res) => {
						if (res.data) {
							switch (e) {
								case 0:
									this.$router.push('./MemberChange');
									break;
								case 1:
									this.$router.push('./MemberExit');
									break;
							}
						}
					})
					.catch((response) => {
						const message = response.data;
						this.$swal({
							icon: 'error',
							title: '잘못된 비밀번호입니다.',
						});
					});
			},

			goMain() {
				this.$router.push('./');
			},

			goChange() {
				this.$router.push('./MemberChange');
			},

			goMyTrade() {
				this.$router.push('my/history/bid');
			},

			goAcount() {
				this.$router.push('./AcountRegister');
			},

			goPayment() {
				this.$router.push('./Payment');
			},
			getMypage() {
				axios.get('mypage').then((res) => {
					this.myData = res.data;
					console.log(this.myData);
				});
			},
		},
		created() {
			this.getMypage();
		},
	};
</script>

<style scoped>
	ul {
		list-style: none;
		margin: 0;
		padding: 0;
	}

	p {
		margin: 0;
	}

	.btn-outline-primary {
		color: #00a5ff;
		border: 1px solid #00a5ff;
		letter-spacing: -0.8px;
		margin-top: 10px;
	}

	.btn-outline-primary:hover {
		color: #fff;
		background-color: #00a5ff;
		border: 1px solid #00a5ff;
	}

	.btn-primary {
		color: #00a5ff;
		letter-spacing: -0.8px;
		background-color: #fff;
		border: none;
		margin: 0;
	}

	.btn-primary:hover {
		background-color: #eeeeee;
		border: none;
	}

	.main {
		display: table;
		table-layout: fixed;
		height: 100%;
		margin: 0 auto;
	}

	.header {
		position: relative;
		display: table-cell;
		width: 395px;
		background-color: #fff;
		box-shadow: 5px 1px 8px 0 rgba(0, 0, 0, 0.06);
		border-left: 1px solid rgba(0, 0, 0, 0.08);
		vertical-align: top;
		z-index: 1;
	}

	.header_home {
		background-color: #fff;
	}

	.logo_area {
		padding: 24px 10px 24px 24px;
	}

	.main_logo {
		position: relative;
		width: 30px;
		height: 30px;
		bottom: 3px;
		cursor: pointer;
	}

	.main_text {
		font: 700 20px 'Roboto', sans-serif;
		letter-spacing: -0.8px;
		padding-left: 5px;
	}

	.profile_area {
		padding: 12px 39px 20px;
		text-align: center;
	}

	.profile_inner {
		width: 100%;
		margin: 0 auto;
	}

	.profile_image {
		width: 120px;
		height: 120px;
	}

	.user_name {
		font: 700 24px 'Roboto', sans-serif;
		letter-spacing: -0.8px;
		display: block;
		margin: 0;
		padding-top: 10px;
	}

	.user_id {
		font: 500 20px 'Roboto', sans-serif;
		color: #929294;
		letter-spacing: -0.8px;
		display: block;
	}

	.header_menu {
		display: block;
		padding: 0 39px;
	}

	.menu {
		margin-top: 20px;
	}

	.li_text {
		font: 500 18px 'Roboto', sans-serif;
		letter-spacing: -0.8px;
	}

	.exist_text {
		font: 400 14px 'Roboto', sans-serif;
		color: #b4b4b4;
		letter-spacing: -0.8px;
		padding-top: 240px;
	}

	.money {
		font: 600 19px 'Roboto', sans-serif;
		letter-spacing: -0.8px;
		padding: 5px 0;
		color: #00a5ff;
	}

	.content {
		padding-left: 100px;
	}

	.content_wrap {
		width: 700px;
	}

	.content_top {
		padding: 25px 0 80px;
	}

	.account_box {
		display: flex;
		flex-direction: column;
		border: 0.5px solid #dfdfdf;
		border-radius: 10px 10px 10px 10px;
	}

	.trade_box {
		display: flex;
		flex-direction: column;
		border: 0.5px solid #dfdfdf;
		border-radius: 10px 10px 10px 10px;
		margin-top: 40px;
	}

	.box_title {
		flex-direction: row;
		font: 500 18px 'Roboto', sans-serif;
		letter-spacing: -0.8px;
		padding: 15px 30px;
		background-color: #00a5ff;
		border-radius: 10px 10px 0px 0px;
	}

	.box_title a {
		padding-right: 520px;
	}

	.box_contents1 {
		border-bottom: 0.5px solid #dfdfdf;
	}

	.box_contents_end {
	}

	.name_row {
		padding: 10px 30px;
	}

	.box_image_size {
		position: relative;
		width: 20px;
		height: 20px;
		margin-right: 10px;
		bottom: 3px;
	}

	.box_text {
		font: 500 16px 'Roboto', sans-serif;
		letter-spacing: -0.8px;
	}

	/* .mypageContainer {
			background: #ffffff;
			width: 800px;
			display: flex;
			flex-direction: column;
			margin: 50px auto 0;
		}

		.mypageContainer > * {
			margin-bottom: 10px;
			padding: 5px;
		} */
	.mypageHeader {
		display: flex;
		margin-top: 40px;
		gap: 20px;
		align-items: center;
	}

	.userImage {
		border-radius: 50%;
		border-style: solid;
		border-color: #000000;
		border-width: 1px;
		width: 100px;
		height: 100px;
		margin-right: 15px;
		box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.15);
	}

	.userNameProfile {
		display: flex;
		flex-direction: column;
		align-items: center;
	}

	.userName {
		color: #000000;
		font: 700 18px 'Roboto', sans-serif;
		width: 126px;
		height: 30px;
		text-align: center;
		display: flex;
		justify-content: center;
		align-content: center;
	}

	.showProfileButton {
		background: #00a5ff;
		width: 120px;
		height: 40px;
		border-radius: 10px;
		color: white;
		border: none;
		position: relative;
		cursor: pointer;
	}

	.moneyInfoBox {
		background: #ffffff;
		border-radius: 0px 20px 0px 20px;
		border-style: solid;
		border-color: #00a5ff;
		border-width: 1.5px;
		width: 350px;
		height: 120px;
		margin-left: 150px;
		border-radius: 10px;
		box-shadow: 1px 4px 4px 0px rgba(0, 0, 0, 0.15);
	}

	.anaMoneyText {
		color: #000000;
		font: 700 18px 'Roboto', sans-serif;
		position: relative;
		left: 30px;
		top: 20px;
		letter-spacing: -0.5px;
	}

	.anaMoneyChargeButton {
		background: #00a5ff;
		width: 70px;
		height: 30px;
		border-radius: 10px;
		color: white;
		border: none;
		position: relative;
		right: 44px;
		top: 65px;
		cursor: pointer;
	}

	.showMoney {
		background: #00a5ff;
		width: 165px;
		height: 60px;
		/* left: 150px; */
		border-radius: 10px;
		position: relative;
	}

	.moneyText {
		color: white;
		float: right;
		margin-right: 10px;
		padding-top: 30px;
	}

	.anaMoney {
		color: white;
		font: 700 20px 'Roboto', sans-serif;
		position: relative;
		float: right;
		right: 10px;
		top: 20px;
	}

	.showProfileText {
		color: #ffffff;
		text-align: center;
		font: 500 16px 'Roboto', sans-serif;
		width: 122.92px;
		height: 40px;
		align-items: center;
		justify-content: center;
	}

	.mypageUserSetting {
		display: flex;
		flex-direction: row;
		gap: 150px;
	}

	.userInfoText {
		color: #000000;
		font: 600 20px 'Roboto', sans-serif;
		margin-top: 90px;
		margin-bottom: 50px;
	}

	.userInfoChange {
		color: #000000;
		font: 500 16px 'Roboto', sans-serif;
		cursor: pointer;
	}

	.withDrawal {
		color: #000000;
		font: 500 16px 'Roboto', sans-serif;
		cursor: pointer;
	}

	.line {
		border-style: solid;
		border-color: #000000;
		border-width: 1px 0 0 0;
		width: 99%;
		height: 0px;
		margin-top: 10px;
	}

	.mypageTradeInfo {
		display: flex;
		flex-direction: row;
		gap: 115px;
	}

	.myTradeText {
		color: #000000;
		font: 600 20px 'Roboto', sans-serif;
		margin-top: 90px;
		margin-bottom: 50px;
	}

	.dealButton {
		color: #000000;
		font: 400 16px 'Roboto', sans-serif;
		cursor: pointer;
	}
</style>
