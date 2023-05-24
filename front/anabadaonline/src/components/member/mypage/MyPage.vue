<template>
	<div class="mypageContainer">
		<div class="mypageHeader">
			<img class="userImage" :src="this.$store.getters.getMember.image" />
			<div class="userNameProfile">
				<div class="userName">
					{{ myData.memberName }}
				</div>
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

		<div class="userInfoText">개인정보</div>

		<div class="mypageUserSetting">
			<span class="userInfoChange" @click="goChange(0)"> 회원 정보 수정 </span>
			<span class="withDrawal" @click="goChange(1)">회원 탈퇴</span>
		</div>
		<div class="line"></div>

		<div class="myTradeText">내 거래</div>

		<div class="mypageTradeInfo">
			<router-link to="/salesDt" class="sellListButton">판매 내역</router-link>
			<router-link to="./purchaseDt" class="buyListButton">구매 내역</router-link>
			<router-link to="./Product" class="productRegButton">상품등록</router-link>
			<router-link to="./Jjim" class="wishListButton">찜목록</router-link>
		</div>
		<div class="line"></div>
	</div>
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
	.mypageContainer {
		background: #ffffff;
		width: 800px;
		display: flex;
		flex-direction: column;
		margin: 50px auto 0;
	}

	.mypageContainer > * {
		margin-bottom: 10px;
		padding: 5px;
	}
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
		width: 120px;
		height: 40px;
		margin-top: 10px;
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
		gap: 175px;
	}

	.myTradeText {
		color: #000000;
		font: 600 20px 'Roboto', sans-serif;
		margin-top: 90px;
		margin-bottom: 50px;
	}

	.sellListButton {
		color: #000000;
		font: 400 16px 'Roboto', sans-serif;

		cursor: pointer;
	}

	.productRegButton {
		color: #000000;
		font: 400 16px 'Roboto', sans-serif;

		cursor: pointer;
	}

	.buyListButton {
		color: #000000;
		font: 400 16px 'Roboto', sans-serif;

		cursor: pointer;
	}

	.wishListButton {
		color: #000000;
		font: 400 16px 'Roboto', sans-serif;
		cursor: pointer;
	}
</style>
