<template>
	<div class="form">
		<div class="deal">거래 내역</div>

		<div class="historyBox">
			<router-link
				to="/my/history/buy"
				class="historyText"
				:class="{ blue: check == 'buy' }"
				@click="check = 'buy'"
				>구매 내역</router-link
			>
			<router-link
				to="/my/history/sales"
				class="historyText"
				:class="{ blue: check == 'sales' }"
				@click="check = 'sales'"
				>판매 내역</router-link
			>
			<router-link
				to="/my/history/bid"
				class="historyText"
				:class="{ blue: check == 'bid' }"
				@click="check = 'bid'"
				>입찰 내역</router-link
			>
		</div>

		<div class="listReapeat">
			<select class="sortList">
				<option>가격순</option>
				<option>거래순</option>
			</select>
			<BuyHistory v-if="check == 'buy'"></BuyHistory>
			<BidHistory v-if="check == 'bid'"></BidHistory>
			<SalesHisotry v-if="check == 'sales'"></SalesHisotry>
		</div>
	</div>
</template>
<script>
	/* Code generated with AutoHTML Plugin for Figma */
	import axios from '@/axios';
	import BuyHistory from '@/components/transaction/BuyHistory.vue';
	import BidHistory from '@/components/transaction/BidHistory.vue';
	import SalesHisotry from '@/components/transaction/SalesHisotry.vue';

	export default {
		name: '',
		components: {
			BuyHistory,
			BidHistory,
			SalesHisotry,
		},
		props: {
			check: {
				Type: String,
				default: 'buy',
			},
		},
		data() {
			// quickfix to have components available to pass as props
			return {
				salesList: [],
			};
		},
		methods: {
			toggle(e) {},
		},
		async created() {},
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
		display: flex;
		flex-direction: column;
		gap: 60px;
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
