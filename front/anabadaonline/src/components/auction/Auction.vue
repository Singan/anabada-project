<template>
	<div class="title">인기 매물</div>
	<div class="productFlex">
		<div class="flexItem" v-for="item in product" :key="item.productNo">
			<img
				class="productImg"
				:src="product.list[0].productImg"
				style="width: 160px; height: 160px"
			/>
			<div class="productName">{{ product.list[0].productName }}</div>

			<div class="price">{{ product.list[0].price }} 원</div>

			<div class="area">{{ product.list.productNo }}</div>

			<!-- <div class="interest">관심</div> -->

			<!-- <div class="chat">채팅</div>

			<div class="chatCount">0</div> -->

			<!-- <div class="interestCount">0</div> -->
		</div>
	</div>
</template>
<script>
	let a = {
		length: '이승민여친들',
		list: [
			{
				productNo: 0,
				productImg:
					'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTb6-sS074EpvRupdxfK_nfyZMuEDsu1wHddA&usqp=CAU',
				productName: '설윤',
				price: 10000,
			},
			{
				productNo: 1,
				productImg:
					'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQJkHRm4Aa3mumb_kxjOGYCKPf7POnBVPJfyQ&usqp=CAU',
				productName: '카리나',
				price: 20000,
			},
			{
				productNo: 2,
				productImg:
					'data:image/jpeg;base64,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',
				productName: '민지',
				price: 30000,
			},
			{
				productNo: 3,
				productImg:
					'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRvrOLTMp44dilF5aBRbut6AyIR8XFBveyisA&usqp=CAU',
				productName: '제니',
				price: 40000,
			},
		],
	};

	/* Code generated with AutoHTML Plugin for Figma */
	import One8Px from './One8Px.vue';
	import axios from '@/axios.js';

	export default {
		name: 'Off',
		components: {
			One8Px,
		},
		props: {},
		data() {
			// quickfix to have components available to pass as props

			return {
				One8Px,
				product: a,
			};
		},
		// methods: {
		// 	product() {
		// 		axios.get('/product/list').then((response) => {
		// 			console.dir(response.data);
		// 			this.product = response.data.list;
		// 		});
		// 	},
		// },
	};
</script>
<style scoped>
	.productFlex {
		margin: auto;
		display: flex;
		width: 60%;
		justify-content: center;
		align-items: center;
		flex-wrap: wrap;
	}

	.flexItem {
		margin-right: 70px;
		margin-bottom: 40px;
	}

	.title {
		color: #000000;
		text-align: center;
		font: 600 24px 'Roboto', sans-serif;
		margin: 50px auto;
		left: 825px;
		top: 161px;
		width: 270px;
		height: 39px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.rectangle-20 {
		position: absolute;
		left: 385px;
		top: 285px;
		overflow: visible;
	}

	.productImg {
		background: #d9d9d9;
		border-radius: 20px;
		width: 160px;
		height: 160px;

		left: 384px;
		top: 285px;
	}

	.productName {
		color: #000000;
		text-align: left;
		font: 300 16px 'Roboto', sans-serif;

		left: 384px;
		top: 445px;
		width: 160px;
		height: 23px;

		align-items: center;
		justify-content: flex-start;
	}

	.price {
		color: #000000;
		text-align: left;
		font: 600 16px 'Roboto', sans-serif;

		left: 387px;
		top: 473px;
		width: 157px;
		height: 23px;

		align-items: center;
		justify-content: flex-start;
	}

	.area {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;

		left: 387px;
		top: 501px;
		width: 157px;
		height: 23px;

		align-items: center;
		justify-content: flex-start;
	}

	.interest {
		color: #797979;
		text-align: left;
		font: 400 13px 'Roboto', sans-serif;
		position: absolute;
		left: 386.67px;
		top: 524px;
		width: 38.67px;
		height: 23px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.chat {
		color: #797979;
		text-align: left;
		font: 400 13px 'Roboto', sans-serif;
		position: absolute;
		left: 470.67px;
		top: 524px;
		width: 40px;
		height: 23px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.chatCount {
		color: #797979;
		text-align: left;
		font: 400 13px 'Roboto', sans-serif;
		position: absolute;
		left: 520px;
		top: 524px;
		width: 24px;
		height: 23px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.interestCount {
		color: #797979;
		text-align: left;
		font: 400 13px 'Roboto', sans-serif;
		position: absolute;
		left: 425.33px;
		top: 524px;
		width: 40px;
		height: 23px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.rectangle-202 {
		background: #ffffff;
		width: 160px;
		height: 260px;
		position: absolute;
		left: 385px;
		top: 630px;
	}

	.rectangle-162 {
		background: #d9d9d9;
		border-radius: 20px;
		width: 160px;
		height: 160px;
		position: absolute;
		left: 384px;
		top: 630px;
	}

	.rectangle-203 {
		background: #ffffff;
		width: 160px;
		height: 260px;
		position: absolute;
		left: 705px;
		top: 285px;
	}

	.rectangle-163 {
		background: #d9d9d9;
		border-radius: 20px;
		width: 160px;
		height: 160px;
		position: absolute;
		left: 703px;
		top: 285px;
	}

	.rectangle-204 {
		position: absolute;
		left: 700.5px;
		top: 630px;
		overflow: visible;
	}

	.rectangle-164 {
		background: #d9d9d9;
		border-radius: 20px;
		width: 160px;
		height: 160px;
		position: absolute;
		left: 700px;
		top: 630px;
	}
</style>
