<template>
	<div class="form">
		<div class="deal">거래 내역</div>

		<div class="historyBox">
			<router-link
				to="purchaseDt"
				class="historyText"
				:class="{ blue: check == 'purchase' }"
				@click="check = 'purchase'"
				>구매 내역</router-link
			>
			<router-link to="./salesDt" class="historyText" :class="{ blue: check == 'sales' }" @click="check = 'sales'"
				>판매 내역</router-link
			>
			<router-link to="./bidDt" class="historyText" :class="{ blue: check == 'bid' }" @click="check = 'bid'"
				>입찰 내역</router-link
			>
		</div>

		<div class="listReapeat">
			<select class="sortList">
				<option>가격순</option>
				<option>거래순</option>
			</select>
			<div class="productInfo" v-for="item in salesList">
				<img class="productPicture" :src="item.productThumbnail" />
				<div class="productDetail">
					<div class="detailPrice">상품명 : {{ item.productName }}</div>
					<div class="detailPrice">등록 가격 :{{ item.productPrice }}</div>
					<div class="detailPrice">상품 등록 시간 : {{ item.createDateTime }}</div>
					<template v-if="item.isBidComplete">
						<div class="detailPrice">경매 낙찰자 : {{ item.memberName }}</div>
						<div class="detailPrice">경매 낙찰 가격 : {{ item.bidPrice }}</div>
						<div class="detailPrice">경매 낙찰 시간 : {{ item.bidTime }}</div>
					</template>
				</div>
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
				check: 'sales',
				salesList: [],
			};
		},
		methods: {
			toggle() {
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
		display: flex;
		flex-direction: column;
		margin: 80px auto 0;
		overflow: hidden;
	}

	.form > * {
		margin-bottom: 20px;
		padding: 30px;
	}

	.listReapeat {
		width: 100%;
	}

	.listReapeat > * {
		margin-bottom: 60px;
	}

	.deal {
		color: #000000;
		font: 700 24px 'Roboto', sans-serif;
	}

	.historyBox {
		display: flex;
		gap: 150px;
		justify-content: center;
	}

	.historyText {
		font: 700 24px 'Roboto', sans-serif;
		cursor: pointer;
	}

	.blue {
		color: #0075ff;
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

	.detailPrice {
		color: #000000;
		font: 700 16px 'Roboto', sans-serif;
		margin-bottom: 5px;
	}

	.sortList {
		margin-left: 600px;
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
