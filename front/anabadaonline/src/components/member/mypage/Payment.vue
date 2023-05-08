<template>
	<div class="paymentContainer">
		<div class="paymentHeaderContainer">
			<div class="paymentHeader">
				<span class="titleInfo">충전할 금액 선택</span>
				<br />
				<span class="won"
					><input
						maxlength="11"
						type="text"
						id="addMoney"
						class="moneyInputBox"
						placeholder="원하는 금액 선택"
						v-model="addMoney"
						readonly
					/>원</span
				>
			</div>
		</div>
		<div class="paymentMiddleContainer">
			<div class="paymentMiddle">
				<table class="moneySelectTable">
					<tr class="trTop">
						<td @click="setMoney1">10,000원</td>
						<td @click="setMoney2">50,000원</td>
						<td @click="setMoney3">100,000원</td>
					</tr>
					<tr>
						<td @click="setMoney4">500,000원</td>
						<td @click="setMoney5">1,000,000원</td>
						<td @click="setMoney6">10,000,000원</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="paymentBottomContianer">
			<div class="paymentBottom">
				<button class="chargeButton" @click="payForm">충전 하기</button>
				<button class="exitButton" onclick="history.go(-1)">
					취소 하기
				</button>
			</div>
		</div>
	</div>
</template>

<script>
	import Bootpay from '@bootpay/client-js';
	import axios from '@/axios';
	import { all } from 'axios';
	export default {
		data() {
			return {
				addMoney: '',
			};
		},
		methods: {
			async payForm() {
				let addMoney = this.addMoney;

				try {
					const response = await Bootpay.requestPayment({
						application_id: '6450b5b4755e27001d375f49',
						price: addMoney,
						order_name: '아나머니 충전',
						order_id: 'TEST_ORDER_ID',
						extra: {
							open_type: 'iframe',
							browser_open_type: [
								{
									browser: 'kakaotalk',
									open_type: 'popup',
								},
								{
									browser: 'naver',
									open_type: 'popup',
								},
								{
									browser: 'mobile_safari',
									open_type: 'popup',
								},
								{
									browser: 'mobile_chrome',
									open_type: 'iframe',
								},
							],
							redirect_url: 'URL',
						},
					});
					switch (response.event) {
						case 'issued':
							break;
						case 'done':
							console.log(response);
							axios
								.post('/mypage/pay', {
									addMoney: response.data.price,
								})
								.then(() => {
									alert('결제가 성공적으로 진행되었습니다.');
									response.data.price;
									this.$router.push('./MyPage');
								});
							break;
						case 'confirm':
							console.log(response.receipt_id);
							const confirmedData = await Bootpay.confirm();
							if (confirmedData.event === 'done') {
							}
							break;
					}
				} catch (e) {
					console.log(e.message);
					switch (e.event) {
						case 'cancel':
							console.log(e.message);
							break;
						case 'error':
							console.log(e.error_code);
							break;
					}
				}
			},
			getMypage() {
				axios.get('mypage').then((res) => {
					this.myData = res.data;
					console.log(this.myData);
				});
			},
			setMoney1() {
				this.addMoney += 10000;
			},
			setMoney2() {
				this.addMoney += 50000;
			},
			setMoney3() {
				this.addMoney += 100000;
			},
			setMoney4() {
				this.addMoney += 500000;
			},
			setMoney5() {
				this.addMoney += 1000000;
			},
			setMoney6() {
				this.addMoney += 10000000;
			},
		},
		mounted() {
			this.getMypage();
		},
		components: { all },
	};
</script>

<style scoped>
	input:focus {
		outline: none;
	}

	table {
		margin: auto;
		position: relative;
		border-collapse: collapse;
		width: 450px;
		height: 180px;
	}

	td {
		border: 2px solid white;
		border-collapse: collapse;
		background-color: rgb(250, 250, 250);
		font: 600 18px 'Roboto', sans-serif;
		cursor: pointer;
	}
	.paymentHeaderContainer {
		display: flex;
		justify-content: center;
		margin-top: 40px;
	}

	.paymentHeader {
		width: 700px;
	}

	.titleInfo {
		color: black;
		font: 600 24px 'Roboto', sans-serif;
		margin-top: 40px;
		margin-bottom: 40px;
	}

	.won {
		color: #0075ff;
		font: 700 22px 'Roboto', sans-serif;
		text-align: right;
		margin: 0 auto;
	}

	.moneyInputBox {
		margin-top: 30px;
		margin-right: 10px;
		width: 666px;
		height: 40px;
		border-top: none;
		border-left: none;
		border-right: none;
		border-bottom: 1px solid black;
		color: #0075ff;
		font: 700 28px 'Roboto', sans-serif;
		text-align: right;
	}

	.paymentMiddleContainer {
		display: flex;
		justify-content: center;
		align-items: center;
		margin-top: 100px;
	}

	.paymentMiddle {
		width: 700px;
		text-align: center;
	}

	.paymentBottomContianer {
		display: flex;
		justify-content: center;
		align-items: center;
		margin-top: 100px;
	}

	.paymentBottom {
		width: 700px;
		text-align: center;
	}

	.chargeButton {
		width: 600px;
		height: 40px;
		border: none;
		border-radius: 10px 10px 10px 10px;
		font: 600 18px 'Roboto', sans-serif;
		background-color: #0075ff;
		color: white;
		cursor: pointer;
	}

	.exitButton {
		margin-top: 10px;
		width: 600px;
		height: 40px;
		border: none;
		border-radius: 10px 10px 10px 10px;
		font: 600 18px 'Roboto', sans-serif;
		background-color: #ff4545;
		color: white;
		cursor: pointer;
	}
</style>
