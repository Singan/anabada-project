<template>
	<div class="container-fluid g-0">
		<div class="content1">
			<div class="Text1">
				원하는 물품<br />원하는 가격으로<br /><br />
				<div class="Text2">중고 거래, 이제 경매에 직접 참여해서<br />원하는 가격으로 구매해보세요</div>
			</div>
			<div class="productImage">
				<img src="@/assets/mainImage01.png" alt="메인 사진 1" class="pig" />
			</div>
			<div class="productImage2">
				<img src="@/assets/mainImage02.png" alt="메인 사진 2" class="pig" />
			</div>
		</div>

		<div class="content2">
			<div class="Text3 display-4">
				우리 동네에서 <br />펼쳐지는 경매장<br /><br />
				<div class="Text4">내가 사는 지역에서 이루어지는 거래를 지금 경험해보세요</div>
			</div>
			<div class="productImage3">
				<img src="@/assets/mainImage03.png" alt="메인 사진 3" class="pig2" />
			</div>
			<div class="productImage4">
				<img src="@/assets/chat.png" alt="메인 사진 3" class="pig2" />
			</div>
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
				<div class="moreProduct"><router-link to="/auction?category=1">더 많은 상품 보러가기</router-link></div>
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
	.pig {
		width: 512px;
		height: 256px;
	}
	.pig2 {
		width: 256px;
		height: 416px;
	}
	.productFlexItem {
		max-width: 200px;
		margin-top: 3rem;
		margin-left: 1rem;
		display: flex;
		flex-direction: column;
		justify-content: center;
	}
	.productFlexItem > * {
		text-overflow: ellipsis;
		white-space: nowrap;
		overflow: hidden;
		margin-bottom: 3px;
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
		margin-left: 23%;
		color: #000000;
		text-align: left;
		font: 700 48px 'Roboto', sans-serif;
		align-items: center;
		position: absolute;
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

	.ProductNT1 {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		align-items: center;
		margin-bottom: 5px;
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
		/* margin-left: 50rem; */
		position: absolute;
		left: 52%;
		top: 15%;
		background: #ffffff;
		width: 32rem;
		height: 16rem;
		border-radius: 25px;
		box-shadow: 2px 4px 12px rgba(0, 0, 0, 0.08);
		overflow: hidden;
		/* border: 2px solid #111111; */
	}

	.productImage2 {
		/* margin-left: 50rem; */
		align-items: center;
		position: absolute;
		margin-left: 58%;
		top: 45%;
		background: #ffffff;
		width: 32rem;
		height: 16rem;
		border-radius: 25px;
		box-shadow: 5px 10px 12px rgba(0, 0, 0, 0.08);
		overflow: hidden;
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
		overflow: hidden;
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
		overflow: hidden;
	}
	@media (max-width: 768px) {
		.content1,
		.content2 .content3 {
			height: auto;
			overflow: auto;
			min-height: 70vh;
		}
		.productImage {
			display: block;
			margin: auto;
			margin-left: auto;
			position: relative;
			left: auto;
			top: 20px;
			width: 384px;
			height: 50%;
		}
		.pig {
			width: 100%;
			height: 100%;
		}

		.pig2 {
			width: 100%;
			height: 100%;
		}
		.productImage2 {
			display: block;
			margin: auto;
			margin-left: auto;
			position: relative;
			left: auto;
			top: 40px;
			width: 384px;
			height: 50%;
		}
		.box1 {
			display: flex;
			flex-direction: column; /* 변경: 세로로 정렬 */
			gap: 10px; /* 변경: grid-gap 대신 gap 사용 */
			margin: auto;
		}
		.Text1,
		.Text3 {
			color: #000000;
			text-align: center;
			font: 700 4vw 'Roboto', sans-serif; /* 변경: 뷰포트 너비에 상대적인 폰트 크기로 설정 */
			display: block; /* 변경: flexbox 레이아웃 사용 */
			justify-content: center; /* 변경: 가로 중앙 정렬 */
			align-items: center; /* 변경: 세로 중앙 정렬 */
			position: relative; /* 변경: 절대 위치 해제 */
			left: auto; /* 변경: 왼쪽 위치 초기화 */
			top: auto; /* 변경: 위쪽 위치 초기화 */
			margin-left: 0%;
			top: 5px;
		}
		.content1 {
			overflow-y: auto;
		}
		.Text4,
		.Text2 {
			text-align: center;
			display: block; /* 변경: flexbox 레이아웃 사용 */
			justify-content: center; /* 변경: 가로 중앙 정렬 */
			align-items: center; /* 변경: 세로 중앙 정렬 */
			position: relative; /* 변경: 절대 위치 해제 */
			left: auto; /* 변경: 왼쪽 위치 초기화 */
			top: auto; /* 변경: 위쪽 위치 초기화 */
		}
		.Text2 {
			color: #000000;
			text-align: center;
			font: 400 18px 'Roboto', sans-serif;
			align-items: center;
			display: block;
		}
		.productImage3 {
			display: block;
			margin-left: 25%;
			left: auto;
			position: absolute;
			width: 204px;
			height: 332px;
			top: 180px;
		}

		.productImage4 {
			display: inline-block;
			margin: auto;
			margin-left: auto;
			position: absolute;
			margin-left: 25%;
			left: 150px;
			top: 320px;
			width: 204px;
			height: 332px;
		}
		.Text3 {
			position: relative;
			margin-left: 0;
			left: auto;
			top: auto;
			text-align: center;
		}
	}

	.productImg1 {
		background-color: transparent;
	}

	.moreProduct {
		cursor: pointer;
		font-weight: bold;
		font-size: 1.3rem;
		line-height: 1.5;
		text-decoration: underline;
		color: #212529;
		margin-top: 3rem;
		margin-left: 1rem;
		grid-column: 3;
		grid-row: 3;
	}
</style>
