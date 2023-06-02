<template>
	<div class="content1 flexContainer">
		<div class="Text3">
			원하는 물품,<br />원하는 가격으로<br /><br />
			<div class="Text4">중고 거래, 이제 경매에 직접 참여해서<br />원하는 가격으로 구매해보세요</div>
		</div>
	</div>

	<div class="box1">
		<div class="Text1">내가 사는 지역에서 펼쳐지는 경매장</div>
	</div>
	<div class="content2">
		<div class="Illust2">
			<img src="@/assets/change2.jpg" style="width: 100%; height: 100%" />
		</div>

		<div class="Illust1">
			<img src="@/assets/change.png" style="width: 100%; height: 100%" />
		</div>
	</div>

	<div class="Text2 content3Title">현재 경매 되고 있는 상품을 둘러보세요</div>

	<div class="content3 productFlex">
		<div class="productFlexItem" v-for="item in productList" :key="item.productNo">
			<img class="ProductImg1" :src="item.productImage" style="width: 320px; height: 240px" />

			<div class="ProductNT1">{{ item.productName }}</div>

			<div class="AddressT1">{{ item.wishAddr }}</div>

			<div class="PriceT1">{{ item.price }} 원</div>
		</div>
	</div>
</template>

<script>
	import axios from '@/axios.js';

	export default {
		props: {
			isSocket: {
				type: Boolean,
			},
		},
		data() {
			return {
				productList: '',
			};
		},

		methods: {
			goLogin() {
				this.$router.push('./login');
			},
			goAuction() {
				this.$router.push('./auction');
			},
			goChat() {
				this.$router.push('./chat');
			},

			product() {
				axios.get('/product/main').then((response) => {
					this.productList = response.data.list;
				});
			},
			// searchresultshow(keyword) {
			// 	if (keyword !== '') {
			// 		//검색어를 입력한 경우
			// 		this.$router.push({
			// 			name: 'SearchPage',
			// 			params: {
			// 				keyword: this.keyword,
			// 				isResultShow: true,
			// 			},
			// 		});
			// 		this.keyword = '';
			// 		console.log('"', keyword, '"' + ' 검색');
			// 	} else {
			// 		alert('검색어를 입력해주세요!'); //검색어를 입력하지 않은 경우
			// 	}
			// },
		},

		created() {
			this.product();
		},
	};
</script>

<style scoped>
	.productFlex {
		display: flex;
		width: 100%;
		justify-content: center;
		align-items: center;
	}

	.productFlexItem {
		margin-right: 30px;
	}

	/* .productImgflexContainer {
      justify-content: flex-end;
      width: 100%;
      display: flex;
      flex-direction: column;
   } */

	.flexContainer {
		position: relative;
		width: 100%;
	}

	.content1 {
		background: #f1f8ff;
		height: 400px;
	}

	.content2 {
		background: #f3f4ff;
		width: 100%;
		display: flex;
		justify-content: space-evenly;
		height: 350px;
		justify-content: center;
	}

	.content3 {
		background: #f6efff;
		height: 400px;
	}

	.content3Title {
		background: #f6efff;
		width: 100%;
	}

	.box1 {
		color: #000000;
		text-align: center;
		font: 700 33px 'Roboto', sans-serif;
		height: 100px;
		line-height: 100px;
		display: flex;
		flex-direction: column;
		background: #f3f4ff;
		justify-content: center;
		width: 100%;
	}

	.Text1 {
		color: #000000;
		text-align: center;
		font: 700 33px 'Roboto', sans-serif;
		margin-top: 80px;
	}

	.Text2 {
		color: #000000;
		text-align: center;
		font: 700 33px 'Roboto', sans-serif;
		height: 100px;
		line-height: 100px;
	}

	.Text3 {
		color: #000000;
		text-align: left;
		font: 700 35px 'Roboto', sans-serif;
		align-items: center;
		position: absolute;
		left: 15%;
		top: 30%;
	}

	.Text4 {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		align-items: center;
		justify-content: flex-start;
	}

	.topImages {
		position: absolute;
		width: 300px;
		margin-right: 300px;
	}

	.Illust2 {
		/* margin-left: 50px; */
		margin-top: 50px;
		background: #bdbdbd;
		width: 500px;
		height: 240px;
		box-shadow: 5px 4px 4px 0px rgba(0, 0, 0, 0.25);
	}
	.Illust1 {
		/* margin-left: 50px; */
		margin-top: 50px;
		background: #bdbdbd;
		width: 500px;
		height: 240px;
		box-shadow: 5px 4px 4px 0px rgba(0, 0, 0, 0.25);
	}

	.ProductNT1 {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;

		align-items: center;
	}

	.AddressT1 {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;

		width: 240px;
		height: 20px;
		align-items: center;
		justify-content: flex-start;
	}

	.PriceT1 {
		color: #000000;
		text-align: left;
		font: 700 16px 'Roboto', sans-serif;

		align-items: center;
		justify-content: flex-start;
	}

	* {
		padding: 0;
		margin: 0;
	}
</style>
