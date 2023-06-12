<template>
	<div class="container">
		<div class="paymentHeaderContainer">
			<div class="paymentHeader">
				<p class="titleInfo">충전할 금액 선택</p>
				<div class="money_wrap">
					<span class="won">
						<input
							maxlength="11"
							type="text"
							id="addMoney"
							class="moneyInputBox"
							placeholder="원하는 금액
					선택"
							v-model="addMoney"
							readonly
						/>머니
					</span>
				</div>
			</div>
		</div>
		<div class="paymentMiddleContainer">
			<div class="paymentMiddle">
				<table class="moneySelectTable">
					<tr class="trTop">
						<td @click="setMoney1">
							<button class="btn btn-outline-primary">5,000원</button>
						</td>
						<td @click="setMoney2">
							<button class="btn btn-outline-primary">10,000원</button>
						</td>
						<td @click="setMoney3">
							<button class="btn btn-outline-primary">50,000원</button>
						</td>
					</tr>
					<tr>
						<td @click="setMoney4">
							<button class="btn btn-outline-primary">100,000원</button>
						</td>
						<td @click="setMoney5">
							<button class="btn btn-outline-primary">500,000원</button>
						</td>
						<td @click="setMoney6">
							<button class="btn btn-outline-primary">1,000,000원</button>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="paymentBottomContianer">
			<div class="paymentBottom">
				<button class="btn btn-primary" @click="payForm">충전 하기</button>
				<button class="btn btn-danger" onclick="history.go(-1)">취소 하기</button>
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
				addMoney: 5000,
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
					alert(e.message);
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
				});
			},
			setMoney1() {
				this.addMoney = 5000;
			},
			setMoney2() {
				this.addMoney = 10000;
			},
			setMoney3() {
				this.addMoney = 50000;
			},
			setMoney4() {
				this.addMoney = 100000;
			},
			setMoney5() {
				this.addMoney = 500000;
			},
			setMoney6() {
				this.addMoney = 1000000;
			},
		},
		mounted() {
			this.getMypage();
		},
		components: { all },
	};
</script>

<style scoped>
	.header {
		width: 780px;
		margin: 0;
		padding: 0;
	}

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
		font: 600 18px 'Roboto', sans-serif;
		letter-spacing: -0.8px;
	}
	.paymentHeaderContainer {
		display: flex;
		justify-content: center;
		height: 100%;
	}

	.paymentHeader {
		width: 700px;
	}

	.titleInfo {
		color: #00a5ff;
		font: 600 24px 'Roboto', sans-serif;
		letter-spacing: -0.8px;
		margin-top: 40px;
	}

	.won {
		color: #00a5ff;
		font: 700 20px 'Roboto', sans-serif;
		letter-spacing: -0.8px;
		padding: 0px 0px 0px 200px;
	}

	.money_wrap {
		padding: 5px 50px;
		text-align: right;
		border-bottom: 3px solid #009aee;
	}

	.moneyInputBox {
		width: 150px;
		margin-top: 20px;
		padding-right: 10px;
		/* margin-right: 10px; */
		border: none;
		color: #00a5ff;
		font: 700 28px 'Roboto', sans-serif;
		letter-spacing: -0.8px;
		text-align: right;
	}

	.paymentMiddleContainer {
		display: flex;
		justify-content: center;
		align-items: center;
		margin-top: 80px;
	}

	.paymentMiddle {
		width: 700px;
		text-align: center;
	}

	.btn-outline-primary {
		color: #00a5ff;
		border: 1px solid #00a5ff;
		letter-spacing: -0.8px;
		width: 140px;
		height: 80px;
		font-size: 18px;
	}

	.btn-outline-primary:hover {
		color: #fff;
		background-color: #00a5ff;
		border: 1px solid #00a5ff;
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

	.btn-primary {
		width: 500px;
		height: 40px;
		font: 500 16px 'Roboto', sans-serif;
		letter-spacing: -0.8px;
	}

	.btn-danger {
		background-color: #ff4545;
		font: 500 16px 'Roboto', sans-serif;
		margin-top: 10px;
		width: 500px;
		height: 40px;
	}
	.btn-danger:hover {
		background-color: #dc3545;
		font: 500 16px 'Roboto', sans-serif;
		margin-top: 10px;
		width: 500px;
		height: 40px;
	}
</style>
