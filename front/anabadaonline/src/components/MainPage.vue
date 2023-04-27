<template>
	<div class="content1 flexContainer">
		<img class="rectangle-13" src="@/assets/bike.jpg" />

		<img class="rectangle-14" src="@/assets/samsung.jpg" />

		<img class="rectangle-12" src="@/assets/iphone14.jpg" />
	</div>

	<div class="content2 flexContainer">
		<div class="Text1">현재 경매 되고 있는 상품을 둘러보세요</div>

		<div class="Text2">내가 사는 지역에서 펼쳐지는 경매장</div>

		<div class="Text3">원하는 물품,<br />원하는 가격으로</div>

		<div class="Text4">
			중고 거래, 이제 경매에 직접 참여해서<br />원하는 가격으로
			구매해보세요
		</div>
	</div>

	<div class="content3 flexContainer">
		<div class="Illust2">
			<img src="@/assets/change2.jpg" style="width: 100%; height: 100%" />
		</div>

		<div class="Illust1">
			<img src="@/assets/change.png" style="width: 100%; height: 100%" />
		</div>
	</div>

	<div class="productFlex">
		<div
			class="productFlexItem"
			v-for="item in productList"
			:key="item.productNo"
		>
			<img class="ProductImg1" :src="item.productImage" />

			<div class="ProductN1">
				<div class="ProductNT1">{{ item.productName }}</div>
			</div>

			<div class="Adress1">
				<div class="AdressT1">{{ item.wishAddr }}</div>
				<!-- 상품거래지 -->
			</div>

			<div class="Price1">
				<div class="PriceT1">{{ item.price }} 원</div>
				<!-- 상품가격 -->
			</div>
		</div>
	</div>
</template>

<script>
	import axios from '@/axios.js';

	export default {
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
				axios.get('/product/list').then((response) => {
					console.dir(response.data);
					this.productList = response.data.list;
				});
			},
			searchresultshow(keyword) {
				if (keyword !== '') {
					//검색어를 입력한 경우
					this.$router.push({
						name: 'SearchPage',
						params: {
							keyword: this.keyword,
							isResultShow: true,
						},
					});
					this.keyword = '';
					console.log('"', keyword, '"' + ' 검색');
				} else {
					alert('검색어를 입력해주세요!'); //검색어를 입력하지 않은 경우
				}
			},
		},

		created() {
			//this.product();
			this.productList = {
				length: '메인화면',
				list: [
					{
						price: 10000,
						productImage: 'C:/Users/leesm/Pictures/수료증.jpg',
						productName: '상품3',
						productNo: 8,
						wishAddr: null,
					},
					{
						price: 10000,
						productImage: 'C:/anabada/product/서명.png',
						productName: '상품3',
						productNo: 7,
						wishAddr: null,
					},
					{
						price: 10000,
						productImage: 'C:/anabada/product/영어.png',
						productName: '상품2',
						productNo: 6,
						wishAddr: null,
					},
					{
						price: 10000,
						productImage: 'C:/anabada/product/캡쳐.png',
						productName: '상품1',
						productNo: 5,
						wishAddr: null,
					},
				],
			}.list;
		},
	};
</script>

<style scoped>
	.productFlex {
		display: flex;
		width: 100%;
		justify-content: center;
	}

	.productFlexItem {
		margin-right: 30px;
	}

	.flexContainer {
		float: right;
		width: 100%;
		display: flex;
	}

	.AuctionButton {
		color: #0075ff;
		text-align: center;
		font: 700 18px 'Roboto', sans-serif;
		position: absolute;
		left: 575px;
		top: 40px;
		width: 40px;
		height: 20px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.Logo {
		color: #0075ff;
		text-align: center;
		font: 700 24px 'Roboto', sans-serif;
		position: absolute;
		left: 395px;
		top: 35px;
		width: 110px;
		height: 30px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.ChatButton {
		color: #000000;
		text-align: center;
		font: 700 18px 'Roboto', sans-serif;
		position: absolute;
		left: 655px;
		top: 40px;
		width: 40px;
		height: 20px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.SearchBox {
		background: #d9ecff;
		border-radius: 5px;
		width: 450px;
		height: 40px;
		position: absolute;
		left: 785px;
		top: 30px;
		text-align: center;
		line-height: 40px;
	}

	.Search {
		width: 130px;
		height: 40px;
		position: static;
	}

	.SearchButton {
		background: #ffffff;
		border-radius: 5px;
		border-style: solid;
		border-color: #000000;
		border-width: 1px;
		width: 130px;
		height: 40px;
		position: absolute;
		left: 1265px;
		top: 30px;
	}

	.SearchText {
		width: 80px;
		height: 30px;
		position: absolute;
		left: 1290px;
		top: 35px;
	}

	.SearchText1 {
		color: #000000;
		text-align: center;
		font: 700 18px 'Roboto', sans-serif;
		position: absolute;
		left: 0px;
		top: 0px;
		width: 80px;
		height: 30px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.Login {
		width: 80px;
		height: 40px;
		position: static;
	}

	.LoginBox {
		background: #0075ff;
		border-radius: 5px;
		width: 80px;
		height: 40px;
		position: absolute;
		left: 1445px;
		top: 30px;
	}

	.LoginText {
		width: 80px;
		height: 30px;
		position: absolute;
		left: 1445px;
		top: 35px;
	}

	.LoginText1 {
		color: #ffffff;
		text-align: center;
		font: 700 18px 'Roboto', sans-serif;
		position: absolute;
		left: 0px;
		top: 0px;
		width: 80px;
		height: 30px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.content1 {
		background: #f1f8ff;
		height: 600px;
	}

	.content2 {
		background: #f3f4ff;
		height: 600px;
	}

	.content3 {
		background: #f6efff;
		height: 600px;
	}

	.Text1 {
		color: #000000;
		text-align: center;
		font: 700 33px 'Roboto', sans-serif;
		width: 693px;
		height: 60px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.Text2 {
		color: #000000;
		text-align: center;
		font: 700 35px 'Roboto', sans-serif;
		width: 666px;
		height: 60px;
		align-items: center;
		justify-content: center;
	}

	.Text3 {
		width: 307px;
		height: 80px;
		color: #000000;
		text-align: left;
		font: 700 35px 'Roboto', sans-serif;

		align-items: center;
		justify-content: flex-start;
	}

	.Text4 {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;

		width: 334px;
		height: 50px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.rectangle-13 {
		background: #bdbdbd;
		width: 400px;
		height: 190px;

		box-shadow: 5px 4px 4px 0px rgba(0, 0, 0, 0.25);
	}

	.rectangle-14 {
		background: #bdbdbd;
		width: 400px;
		height: 190px;

		box-shadow: 5px 4px 4px 0px rgba(0, 0, 0, 0.25);
	}

	.rectangle-12 {
		background: #bdbdbd;
		width: 400px;
		height: 190px;

		box-shadow: 5px 4px 4px 0px rgba(0, 0, 0, 0.25);
	}

	.ProductImg1 {
		background: #bdbdbd;
		border-radius: 5px;
		width: 240px;
		height: 180px;
	}

	.Illust2 {
		background: #bdbdbd;
		width: 600px;
		height: 270px;

		box-shadow: 5px 4px 4px 0px rgba(0, 0, 0, 0.25);
	}

	.Illust1 {
		background: #bdbdbd;
		width: 600px;
		height: 270px;

		box-shadow: 5px 4px 4px 0px rgba(0, 0, 0, 0.25);
	}

	.ProductN1 {
		width: 133px;
		height: 20px;
	}

	.ProductNT1 {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;

		width: 240px;
		height: 20px;
		align-items: center;
		justify-content: flex-start;
	}

	.Adress1 {
		width: 240px;
		height: 20px;
	}

	.AdressT1 {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;

		width: 240px;
		height: 20px;
		align-items: center;
		justify-content: flex-start;
	}

	.Price1 {
		width: 133px;
		height: 20px;
		left: 320px;
		top: 1705px;
	}

	.PriceT1 {
		color: #000000;
		text-align: left;
		font: 700 16px 'Roboto', sans-serif;

		width: 133px;
		height: 20px;
		align-items: center;
		justify-content: flex-start;
	}

	.----11 {
		color: #000000;
		text-align: left;
		font: 700 16px 'Roboto', sans-serif;
		position: absolute;

		width: 134px;
		height: 20px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}
</style>
