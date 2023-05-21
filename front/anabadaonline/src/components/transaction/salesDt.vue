<template>
	<div class="form">
		<div class="deal">거래 내역</div>

		<div class="historyText">
			<div class="purchaseText" :class="{ blue: check }" @click="toggle">구매 내역</div>
			<div class="salesText" :class="{ blue: !check }" @click="toggle">판매 내역</div>
		</div>
		<div>
			<select class="sortList">
				<option>가격순</option>
				<option>거래순</option>
			</select>
		</div>
		<div class="productInfo" v-for="item in salesList">
			<img class="productPicture" :src="item.productThumbnail" />
			<div class="productDetail">
				<div class="detailND">{{ item.productName }}</div>
				<div class="detailPrice">등록 가격 :{{ item.productPrice }}</div>
			</div>
		</div>
	</div>
</template>
<script>
	/* Code generated with AutoHTML Plugin for Figma */
	import axios from '@/axios';
	export default {
		name: '',
		components: {},
		props: {},
		data() {
			// quickfix to have components available to pass as props
			return {
				check: false,
				salesList: [],
			};
		},
		methods: {
			toggle() {
				console.log('되는거 맞아?');
				this.check = true;
			},
			async productSalesList() {
				const result = await axios.post('/mypage/sales');
				this.salesList = result.data.list;
				console.log(result);
			},
		},
		async created() {
			await this.productSalesList();
		},
	};
</script>
<style scoped>
	.form {
		background: #ffffff;
		width: 800px;
		height: 800px;
		display: flex;
		flex-direction: column;
		margin: 80px auto 0;
		overflow: hidden;
	}

	.form > * {
		margin-bottom: 20px;
		padding: 30px;
	}

	.deal {
		color: #000000;
		font: 700 24px 'Roboto', sans-serif;
	}

	.historyText {
		display: flex;
		gap: 150px;
		justify-content: center;
	}

	.purchaseText {
		font: 700 24px 'Roboto', sans-serif;
	}
	.blue {
		color: #0075ff;
	}
	.salesText {
		font: 700 24px 'Roboto', sans-serif;
	}

	.productPicture {
		background: #d9d9d9;
		border-radius: 10px;
		width: 160px;
		height: 160px;
	}

	.productDetail {
		margin-left: 20px;
		display: flex;
		flex-direction: column;
		justify-content: center;
		flex: 1;
	}

	.detailND {
		color: #000000;
		font: 500 16px 'Roboto', sans-serif;
		margin-bottom: 10px;
	}

	.detailPrice {
		color: #000000;
		font: 700 16px 'Roboto', sans-serif;
		margin-bottom: 10px;
	}

	.sortList {
		margin-left: 150px;
		font-size: 16px;
		border-style: solid;
		text-align: center;
		width: 140px;
		height: 25px;
	}
	.flexLine {
		display: flex;
		flex-direction: row;
		justify-content: center;
		width: 100%;
	}
	.productInfo {
		display: flex;
	}
</style>
