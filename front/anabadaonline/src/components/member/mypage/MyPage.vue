<template>
	<div class="mypageContainer">
		<div class="mypageHeader">
			<img class="userImage" v-bind:src="this.$store.getters.getMember.image" />
			<div class="userName">
				{{ myData.memberName }}
				<img src="@/assets/profileButton.png" alt="profileButton" class="showProfileButton" />
			</div>
			<div class="moneyInfoBox">
				<!-- span 태그 a 태그로 변경하기 -->
				<span class="anaMoneyText">내 아나머니</span>
				<button class="anaMoneyChargeButton" @click="goPayment">충전</button>
				<div class="showMoney">
					<span class="moneyText">머니</span>
					<span class="anaMoney">{{ myData.memberMoney }}</span>
				</div>
			</div>
		</div>

		<div class="mypageUserSetting">
			<div class="container">
				<div class="userInfoText">개인정보</div>
				<a href="./MemberChange" class="userInfoChange" @click="goChange"> 회원 정보 수정 </a>
				<a href="./MemberExit" class="withDrawal" @click="goExit">회원 탈퇴</a>
				<div class="userInfoLine"></div>
			</div>
		</div>

		<div class="mypageTradeInfo">
			<div class="container">
				<div class="myTradeText">내 거래</div>
				<a href="../../TransactionDt2" class="sellListButton" @click="goDt2">판매 내역</a>
				<a href="../../TransactionDt1" class="buyListButton" @click="goDt1">구매 내역</a>
				<a href="../../Product" class="productRegButton" @click="goProduct">상품등록</a>
				<a href="./Jjim" class="wishListButton" @click="goJjim">찜 목록</a>
				<div class="line-1"></div>
			</div>
		</div>
		<!-- <div class="group-30">
			<div class="kb------94290200086122">KB 국민은행 94290200086122</div>
		</div> -->

		<!-- <div class="group-6">
			<div class="ellipse-10"></div>

			<div class="ellipse-11"></div>

			<div class="ellipse-12"></div>
		</div> -->
	</div>
</template>

<script>
	import axios from '@/axios';
	import { all } from 'axios';
	export default {
		data() {
			// quickfix to have components available to pass as props
			return {
				myData: '',
			};
		},
		methods: {
			goExit() {
				this.$router.push('./MemberExit');
			},
			goChange() {
				this.$router.push('./MemberChange');
			},
			goProduct() {
				this.$router.push('./Product');
			},
			goJjim() {
				this.$router.push('./Jjim');
			},
			goDt1() {
				this.$router.push('./TransactionDt1');
			},
			goDt2() {
				this.$router.push('./TransactionDt2');
			},
			goAcount() {
				this.$router.push('./AcountRegister');
			},
			goMain() {
				this.$router.push('./');
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
		mounted() {
			this.getMypage();
		},
		components: { all },
	};
</script>

<style scoped>
	.mypageHeader {
		display: flex;
		justify-content: center;
		margin-top: 40px;
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

	.userName {
		color: #000000;
		font: 700 18px 'Roboto', sans-serif;
		width: 126px;
		height: 30px;
		margin: 10px;
		text-align: center;
		align-items: center;
		justify-content: center;
	}

	.showProfileButton {
		width: 120px;
		height: 40px;
		margin-top: 20px;
		text-align: center;
		cursor: pointer;
	}

	.moneyInfoBox {
		background: #ffffff;
		border-radius: 0px 20px 0px 20px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		width: 350px;
		height: 120px;
		margin-left: 10px;
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
		background: #0075ff;
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
		background: #0075ff;
		width: 165px;
		height: 60px;
		left: 150px;
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
		justify-content: center;
	}

	.container {
		width: 700px;
	}

	.userInfoText {
		color: #000000;
		font: 600 20px 'Roboto', sans-serif;
		margin-top: 40px;
		margin-bottom: 40px;
	}

	.userInfoChange {
		color: #000000;
		font: 500 16px 'Roboto', sans-serif;
		padding-right: 120px;
		cursor: pointer;
	}

	.withDrawal {
		color: #000000;
		font: 500 16px 'Roboto', sans-serif;
		cursor: pointer;
	}

	.userInfoLine {
		border-style: solid;
		border-color: #000000;
		border-width: 1px 0 0 0;
		width: 700px;
		box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
		margin-top: 10px;
		transform-origin: 0 0;
		transform: rotate(0deg) scale(1, 1);
	}

	.mypageTradeInfo {
		display: flex;
		justify-content: center;
	}

	.myTradeText {
		color: #000000;
		font: 600 20px 'Roboto', sans-serif;
		margin-top: 80px;
		margin-bottom: 40px;
	}

	.sellListButton {
		color: #000000;
		font: 400 16px 'Roboto', sans-serif;
		padding-right: 155px;
		cursor: pointer;
	}

	.productRegButton {
		color: #000000;
		font: 400 16px 'Roboto', sans-serif;
		padding-right: 155px;
		cursor: pointer;
	}

	.buyListButton {
		color: #000000;
		font: 400 16px 'Roboto', sans-serif;
		padding-right: 155px;
		cursor: pointer;
	}

	.wishListButton {
		color: #000000;
		font: 400 16px 'Roboto', sans-serif;
		cursor: pointer;
	}

	.line-1 {
		border-style: solid;
		border-color: #000000;
		border-width: 1px 0 0 0;
		width: 700px;
		box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
		transform-origin: 0 0;
		transform: rotate(0deg) scale(1, 1);
		margin-top: 10px;
	}

	.-----4 {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		width: 80px;
		height: 63px;
		align-items: center;
		justify-content: flex-start;
	}

	.------2 {
		color: #000000;
		text-align: center;
		font: 600 20px 'Roboto', sans-serif;
		width: 133px;
		height: 63px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.----6 {
		color: #000000;
		text-align: center;
		font: 600 20px 'Roboto', sans-serif;
		position: absolute;
		left: 996px;
		top: 667px;
		width: 133px;
		height: 63px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.line-2 {
		border-style: solid;
		border-color: #000000;
		border-width: 1px 0 0 0;
		width: 750px;
		height: 0px;
		box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
		transform-origin: 0 0;
		transform: rotate(0deg) scale(1, 1);
	}

	.--2 {
		color: #ffffff;
		text-align: center;
		font: 500 16px 'Roboto', sans-serif;
		position: absolute;
		left: 1223.31px;
		top: 233.33px;
		width: 61.22px;
		height: 13.81px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	._0 {
		color: #ffffff;
		text-align: right;
		font: 400 24px 'Roboto', sans-serif;
		width: 169.25px;
		height: 21.9px;
		align-items: center;
		justify-content: flex-end;
	}

	.group-5 {
		width: 1060px;
		height: 42px;
		position: static;
	}

	.--3 {
		color: #0075ff;
		text-align: center;
		font: 700 18px 'Roboto', sans-serif;
		position: absolute;
		left: 620px;
		top: 40px;
		width: 40px;
		height: 20px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.----10 {
		color: #0075ff;
		text-align: center;
		font: 700 24px 'Roboto', sans-serif;
		position: absolute;
		left: 430px;
		top: 35px;
		width: 120px;
		height: 30px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.--4 {
		color: #000000;
		text-align: center;
		font: 700 18px 'Roboto', sans-serif;
		position: absolute;
		left: 700px;
		top: 40px;
		width: 40px;
		height: 20px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.--- {
		background: #d9d9d9;
		border-radius: 5px;
		width: 450px;
		height: 40px;
		position: absolute;
		left: 850px;
		top: 30px;
	}

	.----11 {
		background: #d9d9d9;
		border-radius: 5px;
		width: 150px;
		height: 40px;
		position: absolute;
		left: 1340px;
		top: 28px;
	}

	.----12 {
		width: 80px;
		height: 30px;
		position: absolute;
		left: 1375px;
		top: 33px;
	}

	._18px {
		color: #000000;
		text-align: center;
		font: 700 18px 'Roboto', sans-serif;
		position: absolute;
		left: 0px;
		top: 0px;
		width: 80px;
		height: 30px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.group-30 {
		width: 197px;
		height: 19px;
		position: static;
	}

	.kb------94290200086122 {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		position: absolute;
		left: 1072px;
		top: 189px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.group-6 {
		width: 5.88px;
		height: 25px;
		position: static;
	}

	.ellipse-10 {
		background: #000000;
		border-radius: 50%;
		width: 5.88px;
		height: 5.88px;
		position: absolute;
		left: 1305.88px;
		top: 179px;
		transform-origin: 0 0;
		transform: rotate(0deg) scale(-1, 1);
	}

	.ellipse-11 {
		background: #000000;
		border-radius: 50%;
		width: 5.88px;
		height: 5.88px;
		position: absolute;
		left: 1305.88px;
		top: 188.56px;
		transform-origin: 0 0;
		transform: rotate(0deg) scale(-1, 1);
	}

	.ellipse-12 {
		background: #000000;
		border-radius: 50%;
		width: 5.88px;
		height: 5.88px;
		position: absolute;
		left: 1305.88px;
		top: 198.12px;
		transform-origin: 0 0;
		transform: rotate(0deg) scale(-1, 1);
	}
</style>
