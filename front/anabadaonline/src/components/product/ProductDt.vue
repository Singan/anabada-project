<template>
	<div class="form" :style="'height : ' + { isClicked } ? '2800px' : '1900x'">
		<div class="imgBox">
			<div
				class="productPicture"
				:style="{
					marginLeft: `-${imageCurrIndex * 100}%`,
				}"
			>
				<img :src="image" v-for="(image, index) in seller.productImageList" :key="index" />
			</div>
		</div>
		<button class="listButton" @click="goList">
			<svg
				xmlns="http://www.w3.org/2000/svg"
				width="50"
				height="50"
				fill="currentColor"
				class="bi bi-x"
				viewBox="0 0 16 16"
			>
				<path
					d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"
				/>
			</svg>
		</button>

		<button class="prev" @click="prevSlide">&lt;</button>
		<button class="next" @click="nextSlide">&gt;</button>

		<div class="userInfo">
			<img class="userImage" src="@/assets/userImage.jpg" />
			<div class="box1">
				<div class="sellerName">판매자 이름 : {{ seller.memberName }}</div>
				<div class="mainDeal">판매자 주 거래지 : {{ seller.memberAddr }}</div>
			</div>

			<div class="box2">
				<button class="starIcon" :class="{ filled: isfilled }" @click="starFill">
					<svg
						xmlns="http://www.w3.org/2000/svg"
						width="25"
						height="25"
						fill="currentColor"
						class="bi bi-star-fill"
						viewBox="0 0 16 16"
					>
						<path
							d="M3.612 15.443c-.386.198-.824-.149-.746-.592l.83-4.73L.173 6.765c-.329-.314-.158-.888.283-.95l4.898-.696L7.538.792c.197-.39.73-.39.927 0l2.184 4.327 4.898.696c.441.062.612.636.282.95l-3.522 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256z"
						/>
					</svg>
				</button>
				<div class="jjim">찜하기</div>
			</div>
		</div>

		<div class="line"></div>

		<div class="prouductInfo">
			<div class="productNamePrice">상품 이름 : {{ seller.productName }}</div>
			<div class="productTime">등록 시간 : {{ seller.productTime }}</div>
			<div class="productNamePrice">상품 등록 가격 : {{ seller.productPrice }}원</div>
			<div class="productNamePrice" v-if="seller.productHighPrice">
				현재 최고가 : {{ seller.productHighPrice }} 원
			</div>
			<div class="productNamePrice" v-if="seller.productHighPrice">
				상품 낙찰까지 남은 시간 : {{ leftTimerView }}
			</div>

			<div class="productExplain">상품 설명 : {{ seller.productDetail }}</div>
			<div class="productExplain">상품 사용기간 : {{ seller.productUseDate }}</div>
		</div>

		<div class="productStatus">
			<a class="productText1">찜 0</a>
			<a class="productText1">조회 {{ seller.productVisit }}</a>
		</div>

		<button class="auctionText" :class="{ clicked: isClicked }" @click="bidStart">경매 참여</button>

		<div class="line"></div>
		<BidList v-if="isClicked" :memberNo="seller.memberNo"></BidList>

		<div class="box3">
			<div class="actionProduct">인기경매 상품</div>
			<a class="others">다른 상품 보러가기</a>
		</div>
		<div class="box4">
			<div class="product1">
				<div class="productImage"></div>
				<div class="productName">상품 이름</div>
				<div class="productPrice">가격</div>
				<div class="productRegion">지역</div>
			</div>
		</div>
	</div>
</template>

<script>
	import axios from '@/axios.js';
	import BidList from './BidList.vue';

	export default {
		inject: ['socket'],
		props: {
			isSocket: {
				type: Boolean,
				required: false,
			},
		},
		watch: {
			isSocket: function (isSocket) {
				if (isSocket) {
					this.subscribe();
				}
			},
		},
		name: '',
		components: { BidList },

		data() {
			return {
				seller: '',
				temporaryData: '',
				productNo: this.$route.query.productNo,
				isClicked: false,
				isfilled: false,
				stompClient: '',
				resultObj: {},
				testInput: 0,
				imageCurrIndex: 0,
				leftTimerView: '',
				leftTime: '',
				productSubscribe: '',
			};
		},

		//axios 통신
		methods: {
			async sellerInfo() {
				const response = await axios.get('/product?productNo=' + this.productNo);
				this.seller = response.data;
				console.log(this.seller.bidTime);
				let bidTime = new Date(this.seller.bidTime);
				bidTime.setMinutes(bidTime.getMinutes() + 10);
				this.leftTime = bidTime;
				let date = new Date();
				this.leftTime = this.leftTime - date;
				if (this.leftTime >= 0) {
					const ti = setInterval(this.timer, 1000);
				}
			},
			bidStart() {
				this.isClicked = !this.isClicked;
			},

			starFill() {
				this.isfilled = !this.isfilled;
			},
			// subscription.unsubscribe(headers);

			timer() {
				let s = Math.floor(this.leftTime / 1000);
				let m = Math.floor(s / 60);
				s = s - m * 60;
				this.leftTimerView = m + '분 ' + s + '초';
				this.leftTime -= 1000;
				if (this.leftTime <= 0) {
					clearInterval(this.timer); // 타이머가 0 이하가 되었을 때 타이머를 멈추도록 함
				}
			},
			recevieFunc(resObj) {
				console.log(resObj.price);
				this.seller.productHighPrice = resObj.price;
			},

			subscribe() {
				this.productSubscribe = this.socket.subscribe('/product/' + this.productNo, this.recevieFunc);
			},
			prevSlide() {
				if (this.imageCurrIndex) this.imageCurrIndex--;
			},
			nextSlide() {
				if (this.imageCurrIndex < this.seller.productImageList.length - 1) this.imageCurrIndex++;
			},

			goList() {
				this.$router.push('./auction');
			},
		},
		created() {
			this.sellerInfo();
		},
		mounted() {
			if (this.isSocket) {
				this.subscribe();
			}
		},
		unmounted() {
			console.log('디스트로이드');
			console.log(this.productSubscribe);
			this.productSubscribe.unsubscribe();
		},
	};
</script>

<style scoped>
	.prouductInfo > div {
		padding-left: 60px;
		width: fit-content;
	}
	.form {
		background: #ffffff;
		width: 700px;
		display: flex;
		flex-direction: column;
		margin: 100px auto 0;
		position: relative;
	}

	.form > * {
		margin-bottom: 20px;
		padding: 5px;
	}

	.productPicture {
		background: #d9d9d9;
		border-radius: 20px;
		height: 100%;
		transition: margin-left 1s;
		width: fit-content;
		display: flex;
	}

	.imgBox {
		height: 400px;
		overflow: hidden;
		padding: 0;
		position: relative;
		width: 100%;
		align-items: center;
		justify-content: center;
	}
	.productPicture > * {
		width: 700px;
		height: 100%;
	}
	.prev,
	.next {
		position: absolute;
		background-color: transparent;
		color: black;
		font-size: 2rem;
		border: none;
		cursor: pointer;
		outline: none;
		top: 180px;
	}
	.next {
		right: -30px;
	}
	.prev {
		left: -30px;
	}

	.listButton {
		position: absolute;
		background-color: transparent;
		color: black;
		border: none;
		cursor: pointer;
		outline: none;
	}
	.userInfo {
		display: flex;

		flex-direction: row;
		margin-top: 30px;
	}

	.box1 {
		margin-left: 20px;
		display: flex;
		flex-direction: column;
		gap: 15px;
	}

	.box2 {
		margin-left: auto;
		display: flex;
		flex-direction: row;
		align-items: center;
		gap: 8px;
	}

	.sellerName {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
		font-weight: bold;
	}

	.mainDeal {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
	}

	.starIcon {
		border: none;
		background-color: transparent;
	}

	.starIcon.filled svg path {
		color: #0075ff;
	}

	.jjim {
		color: #0075ff;
		font: 22px 'Roboto', sans-serif;
	}

	.userImage {
		border-radius: 50%;
		border-style: solid;
		border-color: #000000;
		border-width: 1.5px;
		width: 40px;
		height: 40px;
	}

	.starImage {
		width: 18px;
		height: 18px;
		margin-top: 5px;
	}

	.line {
		border-style: solid;
		border-color: #d9d9d9;
		border-width: 1px 0 0 0;
		width: 100%;
		height: 0px;
		margin-bottom: 5px;
	}

	.productText1 {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
	}

	.auctionText {
		font: 14px 'Roboto', sans-serif;
		background: #ffffff;
		border-radius: 5px;
		width: 150px;
		height: 30px;
		text-align: center;
		border: 2px solid #0075ff;
	}

	.auctionText.clicked {
		font: 14px 'Roboto', sans-serif;
		background: #0075ff;
		color: #ffffff;
		border-radius: 5px;
		width: 150px;
		height: 30px;
		text-align: center;
		border: none;
	}

	.productNamePrice {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
		font-weight: bold;
		margin-bottom: 30px;
	}

	.productTime {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
		opacity: 0.4;
		margin-bottom: 30px;
	}

	.productExplain {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
		margin-bottom: 30px;
		word-break: break-all;
	}

	.productStatus {
		display: flex;
		gap: 50px;
	}

	.box3 {
		gap: 280px;
		display: flex;
		flex-direction: row;
		gap: 400px;
		justify-content: center;
	}

	.others {
		color: #0075ff;
		font: 12px 'Roboto', sans-serif;
	}

	.actionProduct {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
		font-weight: bold;
	}

	.box4 {
		display: flex;
		flex-direction: row;
		gap: 60px;
		justify-content: center;
	}

	.box5 {
		display: flex;
		flex-direction: row;
		gap: 60px;
		justify-content: center;
	}

	.productImage {
		background: #d9d9d9;
		border-radius: 20px;
		width: 160px;
		height: 160px;
	}

	.productName {
		color: #000000;
		font: 300 16px 'Roboto', sans-serif;
		font-weight: light;
	}

	.productPrice {
		color: #000000;
		font: 16px 'Roboto', sans-serif;
		font-weight: bold;
	}

	.productRegion {
		color: #000000;
		font: 16px 'Roboto', sans-serif;
	}

	.arrow {
		width: 0;
		height: 0;
		border-top: 20px solid transparent;
		border-bottom: 20px solid transparent;
		border-right: 20px solid black;
	}
</style>
