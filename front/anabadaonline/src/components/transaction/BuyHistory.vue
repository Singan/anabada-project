<template>
	<div class="productInfo">
		<img class="productPicture" />
		<div class="productDetail">
			<div class="detailPrice">상품명 :</div>
			<div class="detailPrice">등록 가격 :</div>
			<div class="detailPrice">상품 등록 시간 :</div>
			<div class="detailPrice">경매 낙찰자 :</div>
			<div class="detailPrice">경매 낙찰 가격 :</div>
			<div class="detailPrice">경매 낙찰 시간 :</div>
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
				check: 'purchase',
				size: 4,
				page: 0,
			};
		},
		methods: {
			toggle() {
				this.check = true;
			},
		},
	};
</script>
<style scoped>
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

	.productInfo {
		display: flex;
	}
</style>
