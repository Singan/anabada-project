<template>
	<div class="Footer1"></div>

	<div class="Footer2"></div>

	<div class="Footer3"></div>

	<div class="TextBox1">
		<div class="Text1">현재 경매 되고 있는 상품을 둘러보세요</div>
	</div>

	<div class="TextBox2">
		<div class="Text2">내가 사는 지역에서 펼쳐지는 경매장</div>
	</div>

	<div class="TextBox3">
		<div class="Text3">원하는 물품,<br />원하는 가격으로</div>
	</div>

	<div class="TextBox4">
		<div class="Text4">
			중고 거래, 이제 경매에 직접 참여해서<br />원하는 가격으로
			구매해보세요
		</div>
	</div>

	<div class="ImgArea1">
		<div class="rectangle-13">
			<img src="@/assets/bike.jpg" style="width: 100%; height: 130%" />
		</div>

		<div class="rectangle-14">
			<img src="@/assets/samsung.jpg" style="width: 100%; height: 100%" />
		</div>

		<div class="rectangle-12">
			<img
				src="@/assets/iphone14.jpg"
				style="width: 100%; height: 100%"
			/>
		</div>
	</div>

	<div class="Illust2">
		<img src="@/assets/change2.jpg" style="width: 100%; height: 100%" />
	</div>

	<div class="Illust1">
		<img src="@/assets/change.png" style="width: 100%; height: 100%" />
	</div>

	<div v-for="item in productList" :key="item.productNo" class="product">
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
	.product {
		display: inline-block;
		margin-right: 100px;
		display: flex-inline;
	}
	.Header,
	.Header * {
		box-sizing: border-box;
	}

	.Header {
		background: #ffffff;
		width: 1920px;
		height: 1900px;
		position: relative;
		overflow: hidden;
	}

	.HeaderButton {
		width: 1130px;
		height: 40px;
		position: static;
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

	.Footer1 {
		background: #f1f8ff;
		width: 100%;
		height: 600px;
		position: absolute;
		left: 0px;
		top: 100px;
	}

	.Footer2 {
		background: #f3f4ff;
		width: 100%;
		height: 600px;
		position: absolute;
		left: 0px;
		top: 700px;
	}

	.Footer3 {
		background: #f6efff;
		width: 100%;
		height: 600px;
		position: absolute;
		left: 0px;
		top: 1300px;
	}

	.TextBox1 {
		width: 693px;
		height: 60px;
		position: absolute;
		left: 613px;
		top: 1380px;
	}

	.Text1 {
		color: #000000;
		text-align: center;
		font: 700 33px 'Roboto', sans-serif;
		position: absolute;
		left: 83px;
		top: 11px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.TextBox2 {
		width: 666px;
		height: 60px;
		position: absolute;
		left: 627px;
		top: 780px;
	}

	.Text2 {
		color: #000000;
		text-align: center;
		font: 700 35px 'Roboto', sans-serif;
		position: absolute;
		left: 90px;
		top: 10px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.TextBox3 {
		width: 307px;
		height: 80px;
		position: absolute;
		left: 333px;
		top: 290px;
	}

	.Text3 {
		color: #000000;
		text-align: left;
		font: 700 35px 'Roboto', sans-serif;
		position: absolute;
		left: 0px;
		top: -1px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.TextBox4 {
		width: 334px;
		height: 50px;
		position: absolute;
		left: 333px;
		top: 404px;
	}

	.Text4 {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		position: absolute;
		left: 0px;
		top: 0px;
		width: 334px;
		height: 50px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.ImgArea1 {
		width: 773.33px;
		height: 358px;
		position: static;
	}

	.rectangle-13 {
		background: #bdbdbd;
		width: 400px;
		height: 190px;
		position: absolute;
		left: 1000px;
		top: 368px;
		box-shadow: 5px 4px 4px 0px rgba(0, 0, 0, 0.25);
	}

	.rectangle-14 {
		background: #bdbdbd;
		width: 400px;
		height: 190px;
		position: absolute;
		left: 1240px;
		top: 200px;
		box-shadow: 5px 4px 4px 0px rgba(0, 0, 0, 0.25);
	}

	.rectangle-12 {
		background: #bdbdbd;
		width: 400px;
		height: 190px;
		position: absolute;
		left: 866.67px;
		top: 256px;
		box-shadow: 5px 4px 4px 0px rgba(0, 0, 0, 0.25);
	}

	.ProductImg1 {
		background: #bdbdbd;
		border-radius: 5px;
		width: 240px;
		height: 180px;
		position: relative;
		left: 320px;
		top: 1490px;
	}

	.Illust2 {
		background: #bdbdbd;
		width: 600px;
		height: 270px;
		position: absolute;
		left: 1000px;
		top: 900px;
		box-shadow: 5px 4px 4px 0px rgba(0, 0, 0, 0.25);
	}

	.Illust1 {
		background: #bdbdbd;
		width: 600px;
		height: 270px;
		position: absolute;
		left: 320px;
		top: 900px;
		box-shadow: 5px 4px 4px 0px rgba(0, 0, 0, 0.25);
	}

	.ProductN1 {
		width: 133px;
		height: 20px;
		position: relative;
		left: 320px;
		top: 1680px;
		display: inline-block;
	}

	.ProductNT1 {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		position: relative;
		left: 0px;
		top: 0px;
		width: 240px;
		height: 20px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
		display: inline-block;
	}

	.Adress1 {
		width: 240px;
		height: 20px;
		position: relative;
		left: 320px;
		top: 1730px;
	}

	.AdressT1 {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		position: relative;
		left: 0px;
		top: 0px;
		width: 240px;
		height: 20px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.Price1 {
		width: 133px;
		height: 20px;
		position: relative;
		left: 320px;
		top: 1705px;
	}

	.PriceT1 {
		color: #000000;
		text-align: left;
		font: 700 16px 'Roboto', sans-serif;
		position: relative;
		left: 0px;
		top: 0px;
		width: 133px;
		height: 20px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}
	.----11 {
		color: #000000;
		text-align: left;
		font: 700 16px 'Roboto', sans-serif;
		position: absolute;
		left: 0px;
		top: 0px;
		width: 134px;
		height: 20px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}
</style>
