<template>
	<div class="container-fluid">
		<div class="content1">
			<div class="Text1">
				원하는 물품<br />원하는 가격으로<br /><br />
				<div class="Text2">중고 거래, 이제 경매에 직접 참여해서<br />원하는 가격으로 구매해보세요</div>
			</div>
			<div class="productImage"></div>
			<div class="productImage2"></div>
		</div>

		<div class="content2">
			<div class="Text3">
				우리 동네에서 <br />펼쳐지는 경매장<br /><br />
				<div class="Text4">내가 사는 지역에서 이루어지는 거래를 지금 경험해보세요</div>
			</div>
			<div class="productImage3"></div>
			<div class="productImage4"></div>
		</div>

		<div class="content3">
			<div class="Text5">현재 경매 되고 있는 상품을 둘러보세요</div>
			<div class="box1">
				<router-link
					class="productFlexItem"
					v-for="item in productList"
					:key="item.productNo"
					:to="'/productDt?productNo=' + item.productNo"
				>
					<img class="ProductImg1" :src="item.productImage" style="width: 200px; height: 200px" />

					<div class="ProductNT1">{{ item.productName }}</div>

					<div class="PriceT1">{{ item.price }} 원</div>

					<div class="AddressT1">{{ item.wishAddr }}</div>
				</router-link>
			</div>
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
				axios.get('/product/main?size=10').then((response) => {
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
		margin-top: 3rem;
		margin-left: 1rem;
	}

	.content1 {
		background: #f1f8ff;
		position: relative;
		width: 100%;
		height: 70vh;
	}

	.content2 {
		background: #ffffff;
		position: relative;
		width: 100%;
		height: 70vh;
	}

	.content3 {
		background: #fbf7f2;
		height: 90vh;
		display: flex;
		flex-direction: column;
		width: 100%;
		align-items: center;
	}

	.box1 {
		display: grid;
		grid-template-columns: repeat(5, 1fr);
		grid-gap: 10px;
	}

	.Text1 {
		margin-left: 10rem;
		color: #000000;
		text-align: left;
		font: 700 48px 'Roboto', sans-serif;
		align-items: center;
		position: absolute;
		left: 12%;
		top: 30%;
	}

	.Text2 {
		color: #000000;
		text-align: left;
		font: 400 18px 'Roboto', sans-serif;
		align-items: center;
		justify-content: flex-start;
	}

	.Text3 {
		margin-left: 10rem;
		color: #000000;
		text-align: left;
		font: 700 48px 'Roboto', sans-serif;
		align-items: center;
		position: absolute;
		left: 46%;
		top: 30%;
	}

	.Text4 {
		color: #000000;
		text-align: left;
		font: 400 18px 'Roboto', sans-serif;
		align-items: center;
		justify-content: flex-start;
	}

	.Text5 {
		color: #000000;
		text-align: center;
		font: 700 34px 'Roboto', sans-serif;
		margin-top: 5rem;
	}

	.topImages {
		position: absolute;
		width: 300px;
		margin-right: 300px;
	}

	.ProductNT1 {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		align-items: center;
		margin-bottom: 8px;
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

	.productImage3 {
		margin-left: 15rem;
		position: absolute;
		left: 12%;
		top: 15%;
		background: #ffffff;
		width: 16rem;
		height: 26rem;
		border-radius: 25px;
		box-shadow: 10px 0px 12px rgba(0, 0, 0, 0.08);
	}

	.productImage4 {
		margin-left: 25rem;
		position: absolute;
		left: 12%;
		top: 24%;
		background: #ffffff;
		width: 16rem;
		height: 26rem;
		border-radius: 25px;
		box-shadow: 10px 0px 12px rgba(0, 0, 0, 0.08);
	}

	.productImg1 {
		background-color: transparent;
	}
</style>
