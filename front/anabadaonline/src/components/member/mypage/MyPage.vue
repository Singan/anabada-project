<template>
	<div class="mypageController">
		<div class="userName">사용자이름</div>
		<div class="moneyInfoBox"></div>
		<div class="anaMoneyText">아나머니</div>

		<div class="group-1">
			<div class="showProfileButton"></div>
			<div class="showProfileText">프로필 보기</div>
		</div>
		<div class="group-2">
			<div class="anaMoneyChargeButton"></div>
			<div class="--" @click="goAcount">충전</div>
		</div>

		<div class="----8">
			<div class="userInfoText">개인정보</div>

			<div class="userInfoChange" @click="goChange">회원 정보 수정</div>

			<div class="withDrawal" @click="goExit">회원 탈퇴</div>

			<div class="userInfoLine"></div>
		</div>

		<div class="----2">
			<div class="myTradeText">내 거래</div>
			<div class="sellListButton" @click="goDt2">판매 내역</div>
			<div class="buyListButton" @click="goDt1">구매 내역</div>
			<div class="productRegButton" @click="goProduct">상품등록</div>
			<div class="wishListButton" @click="goJjim">찜 목록</div>
			<div class="line-1"></div>
		</div>

		<img class="ellipse-1" src="ellipse-1.png" />

		<div class="group-4">
			<div class="group-3">
				<div class="rectangle-53"></div>

				<div class="--2">머니</div>
			</div>

			<div class="_0">0</div>
		</div>

		<div class="group-30">
			<div class="kb------94290200086122">KB 국민은행 94290200086122</div>
		</div>

		<div class="group-6">
			<div class="ellipse-10"></div>

			<div class="ellipse-11"></div>

			<div class="ellipse-12"></div>
		</div>
	</div>
</template>

<script>
	export default {
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
		},
	};
</script>

<style scoped>
	.userName {
		color: #000000;
		font: 700 18px 'Roboto', sans-serif;
		width: 126px;
		height: 30px;
		align-items: center;
		justify-content: center;
	}

	.moneyInfoBox {
		background: #ffffff;
		border-radius: 0px 20px 0px 20px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1px;
		width: 430px;
		height: 113px;
		box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
	}

	.anaMoneyText {
		color: #000000;
		font: 700 18px 'Roboto', sans-serif;
		width: 126px;
		height: 46px;
		align-items: center;
		justify-content: center;
	}

	.group-1 {
	}

	.showProfileButton {
		background: #0075ff;
		border-radius: 15px;
		width: 121.6px;
		height: 40px;
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

	.group-2 {
	}

	.anaMoneyChargeButton {
		background: #0075ff;
		border-radius: 10px;
		width: 79.14px;
		height: 28px;
	}

	.----2 {
		width: 750px;
		height: 217px;
		position: static;
	}

	.myTradeText {
		color: #000000;
		text-align: left;
		font: 600 20px 'Roboto', sans-serif;
		width: 133px;
		height: 63px;
		align-items: center;
		justify-content: flex-start;
	}

	.sellListButton {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		width: 74px;
		height: 63px;
		align-items: center;
		justify-content: flex-start;
	}

	.productRegButton {
		color: #000000;
		font: 400 16px 'Roboto', sans-serif;
		width: 133px;
		height: 63px;
		align-items: center;
		justify-content: center;
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

	.buyListButton {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		width: 80px;
		height: 63px;
		align-items: center;
		justify-content: flex-start;
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

	.wishListButton {
		color: #000000;
		text-align: right;
		font: 400 16px 'Roboto', sans-serif;
		width: 63px;
		height: 63px;
		align-items: center;
		justify-content: flex-end;
	}

	.line-1 {
		border-style: solid;
		border-color: #000000;
		border-width: 1px 0 0 0;
		width: 750px;
		height: 0px;
		box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
		transform-origin: 0 0;
		transform: rotate(0deg) scale(1, 1);
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

	.----8 {
		width: 750px;
		height: 135px;
		position: static;
	}

	.userInfoText {
		color: #000000;
		text-align: left;
		font: 600 20px 'Roboto', sans-serif;
		width: 133px;
		height: 41px;
		align-items: center;
		justify-content: flex-start;
	}

	.userInfoChange {
		color: #000000;
		text-align: left;
		font: 500 16px 'Roboto', sans-serif;
		width: 120px;
		height: 63px;
		align-items: center;
		justify-content: flex-start;
	}

	.withDrawal {
		color: #000000;
		text-align: left;
		font: 500 16px 'Roboto', sans-serif;
		width: 80px;
		height: 63px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.userInfoLine {
		border-style: solid;
		border-color: #000000;
		border-width: 1px 0 0 0;
		width: 750px;
		height: 0px;
		box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
		transform-origin: 0 0;
		transform: rotate(0deg) scale(1, 1);
	}

	.ellipse-1 {
		border-radius: 50%;
		border-style: solid;
		border-color: #000000;
		border-width: 1px;
		width: 100px;
		height: 100px;
		box-shadow: 0px 4px 4px 0px rgba(0, 0, 0, 0.25);
	}

	.group-4 {
		width: 247px;
		height: 40px;
		position: static;
	}

	.group-3 {
		width: 247px;
		height: 40px;
		position: static;
	}

	.rectangle-53 {
		background: #0075ff;
		border-radius: 10px;
		width: 247px;
		height: 40px;
		position: absolute;
		left: 1047px;
		top: 220px;
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
		position: absolute;
		left: 1050.47px;
		top: 229.05px;
		width: 169.25px;
		height: 21.9px;
		display: flex;
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
