<template>
	<div class="form">
		<div class="SuccessText1">축하합니다!</div>

		<div class="SuccessText2">
			<span style="color: #0075ff">"{{ productName }}"</span> 상품이
			<span style="color: #0075ff">낙찰</span>
			되었습니다!
		</div>

		<img class="productPicture" :src="productThumbnail" />

		<div class="detailInfo">
			<div class="productInfo">상품명 : {{ productName }}</div>
			<div class="productPrice">최종 낙찰가 : {{ productPrice }}</div>
			<div class="productInfo">상품 사용기간 : {{ productUseDate }}</div>
			<div class="productInfo">상품 거래지 : {{ wishAddr }}</div>
		</div>

		<div class="sellerBuyerBox">
			<div class="sellerBuyerInfo">
				<div class="sellerBuyer">상품 판매자</div>
				<div class="sellerBuyerName">{{ productUploadMember }}</div>
			</div>

			<div class="processImage">
				<div class="processComplete1"></div>
				<div class="processComplete2"></div>
				<div class="processComplete3"></div>
			</div>

			<div class="sellerBuyerInfo">
				<div class="sellerBuyer">상품 구매자</div>
				<div class="sellerBuyerName">{{ memberName }}</div>
			</div>
		</div>

		<button class="okayButton">확인</button>
	</div>
</template>

<script>
	import axios from '@/axios';
	export default {
		name: 'SuccessfulBid',
		props: {
			successBidNo: {
				type: Number,
			},
		},
		data() {
			return {
				memberName: '',
				productPrice: '',
				productName: '',
				productUseDate: '',
				productThumbnail: '',
				productUploadMember: '',
				wishAddr: '',
			};
		},
		async created() {
			const res = await axios.get('/success?successBidNo=' + this.successBidNo).catch((error) => {
				this.$swal({
					title: '에러',
					icon: 'error',
					text: error.response.data.message,
				}).then(() => {
					this.$router.push('/');
				});
			});

			this.memberName = res.data.memberName;
			this.productPrice = res.data.productPrice;
			this.productName = res.data.productName;
			this.productUseDate = res.data.productUseDate;
			this.productThumbnail = res.data.productThumbnail;
			this.productUploadMember = res.data.productUploadMember;
			this.wishAddr = res.data.wishAddr;
		},
	};
</script>

<style scoped>
	.form {
		width: 100%;
		background: #ffffff;
		width: 800px;
		height: 800px;
		display: flex;
		flex-direction: column;
		margin: 50px auto 0;
		overflow: hidden;
		align-items: center;
	}

	.form > * {
		margin-bottom: 20px;
		padding: 10px;
	}

	.SuccessText1 {
		color: #000000;
		font: 700 28px 'Roboto', sans-serif;
	}

	.SuccessText2 {
		color: #000000;
		font: 700 20px 'Roboto', sans-serif;
	}

	.productPicture {
		background: #d9d9d9;
		border-radius: 5px;
		width: 200px;
		height: 200px;
	}

	.detailInfo {
	}

	.productInfo {
		color: #000000;
		font: 500 14px 'Roboto', sans-serif;
		margin-bottom: 10px;
	}
	.productPrice {
		color: #000000;
		font: 900 14px 'Roboto', sans-serif;
		margin-bottom: 10px;
	}

	.sellerBuyerBox {
		display: flex;
		flex-direction: row;
		gap: 100px;
		justify-content: center;
		align-items: center;
	}

	.sellerBuyerInfo {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	.sellerBuyer {
		color: #000000;
		font: 16px 'Roboto', sans-serif;
	}

	.sellerBuyerName {
		color: #000000;
		font: 900 18px 'Roboto', sans-serif;
		margin-top: 20px;
	}

	.processComplete1 {
		width: 0;
		height: 0;
		border-top: 25px solid transparent;
		border-bottom: 25px solid transparent;
		border-left: 35px solid #74b4ff;
		transform: rotate(0deg);
	}

	.processComplete2 {
		width: 0;
		height: 0;
		border-top: 25px solid transparent;
		border-bottom: 25px solid transparent;
		border-left: 35px solid #3692ff;
		transform: rotate(0deg);
	}

	.processComplete3 {
		width: 0;
		height: 0;
		border-top: 25px solid transparent;
		border-bottom: 25px solid transparent;
		border-left: 35px solid #0075ff;
		transform: rotate(0deg);
	}

	.processImage {
		display: flex;
		gap: 50px;
	}

	.okayButton {
		background: #0075ff;
		border-radius: 5px;
		width: 470px;
		height: 40px;
		font: 900 18px 'Roboto', sans-serif;
		color: #ffffff;
		border-radius: 5px;
		text-align: center;
		border: none;
		letter-spacing: 2px;
		margin-top: 50px;
	}
</style>
