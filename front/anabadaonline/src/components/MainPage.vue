<template>
	<div form>
		<div class="content1">
			<div class="Text3">
				원하는 물품,<br />원하는 가격으로<br /><br />
				<div class="Text4">중고 거래, 이제 경매에 직접 참여해서<br />원하는 가격으로 구매해보세요</div>
			</div>
			<div class="productImage"></div>
			<div class="productImage2"></div>
		</div>

		<div class="box1">
			<div class="Text1">내가 사는 지역에서 펼쳐지는 경매장</div>
		</div>
		<div class="content2">
			<div class="Illust2">
				<img src="@/assets/chatList.jpg" style="width: 100%; height: 100%" />
			</div>

			<div class="Illust1">
				<img src="@/assets/change.png" style="width: 100%; height: 100%" />
			</div>
		</div>

		<div class="box2">
			<div class="Text2">현재 경매 되고 있는 상품을 둘러보세요</div>
		</div>
		<div class="content3">
			<router-link
				class="productFlexItem"
				v-for="item in productList"
				:key="item.productNo"
				:to="'/productDt?productNo=' + item.productNo"
			>
				<img class="ProductImg1" :src="item.productImage" style="width: 320px; height: 240px" />

				<div class="ProductNT1">{{ item.productName }}</div>

				<div class="AddressT1">{{ item.wishAddr }}</div>

				<div class="PriceT1">{{ item.price }} 원</div>
			</router-link>
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
		},

		created() {
			this.product();
		},
	};
</script>

<style scoped>
	.productFlexItem {
		margin-right: 30px;
		margin-top: 30px;
	}

	/* .productImgflexContainer {
	     justify-content: flex-end;
	     width: 100%;
	     display: flex;
	     flex-direction: column;
	  } */

	.content1 {
		background: #f1f8ff;
		position: relative;
		width: 100%;
		height: 70vh;
	}

	.content2 {
		background: #f3f4ff;
		width: 100%;
		display: flex;
		justify-content: space-evenly;
		height: 70vh;
		justify-content: center;
		gap: 200px;
	}

	.content3 {
		background: #f6efff;
		height: 70vh;
		display: flex;
		width: 100%;
		justify-content: center;
		align-items: center;
	}

	.box1 {
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

	.box2 {
		background: #f6efff;
		text-align: center;
		height: 100px;
		line-height: 100px;
		display: flex;
		flex-direction: column;
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
		margin-top: 80px;
	}

	.Text3 {
		margin-left: 10rem;
		color: #000000;
		text-align: left;
		font: 700 48px 'Roboto', sans-serif;
		align-items: center;
		position: absolute;
		left: 12%;
		top: 30%;
	}

	.Text4 {
		color: #000000;
		text-align: left;
		font: 400 18px 'Roboto', sans-serif;
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
		margin-top: 65px;
		background: #bdbdbd;
		width: 500px;
		height: 240px;
		box-shadow: 5px 4px 4px 0px rgba(0, 0, 0, 0.25);
	}
	.Illust1 {
		/* margin-left: 50px; */
		margin-top: 65px;
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

	.productImage {
		margin-left: 50rem;
		position: absolute;
		left: 10%;
		top: 15%;
		background: #ffffff;
		width: 32rem;
		height: 16rem;
		border-radius: 25px;
		box-shadow: 2px 4px 12px rgba(0, 0, 0, 0.08);
		/* border: 2px solid #111111; */
	}

	.productImage2 {
		margin-left: 50rem;
		align-items: center;
		position: absolute;
		left: 18%;
		top: 45%;
		background: #ffffff;
		width: 32rem;
		height: 16rem;
		border-radius: 25px;
		box-shadow: 5px 10px 12px rgba(0, 0, 0, 0.08);
		/* border: 2px solid #111111; */
	}
</style>
