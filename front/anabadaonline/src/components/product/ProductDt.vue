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

		<button class="prev" @click="prevSlide">&lt;</button>
		<button class="next" @click="nextSlide">&gt;</button>

		<div class="userInfo">
			<img class="userImage" src="@/assets/userImage.jpg" />
			<div class="box1">
				<div class="sellerName">판매자 이름 : {{ seller.memberName }}</div>
				<div class="mainDeal">판매자 주 거래지 : {{ seller.memberAddr }}</div>
			</div>

			<div class="box2">
				<!-- <div class="mainDeal">방사능 지수 0</div> -->
				<div class="jjim"><img class="starImage" src="@/assets/star.jpg" />찜하기</div>
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
			<div class="productNamePrice" v-if="seller.productHighPrice">상품 낙찰까지 남은 시간 : {{ leftTimer }}</div>

			<div class="productExplain">상품 설명 : {{ seller.productDetail }}</div>
			<div class="productExplain">상품 사용기간 : {{ seller.productUseDate }}</div>
		</div>

		<div class="productStatus">
			<a class="productText1">찜 0</a>
			<a class="productText1">조회 {{ seller.productVisit }}</a>
		</div>

		<button class="auctionText" :class="{ clicked: isClicked }" @click="bidStart">경매 참여</button>

		<div class="line"></div>
		<BidList v-if="check" :memberNo="seller.memberNo"></BidList>

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

			<div class="product2">
				<div class="productImage"></div>
				<div class="productName">상품 이름</div>
				<div class="productPrice">가격</div>
				<div class="productRegion">지역</div>
			</div>

			<div class="product3">
				<div class="productImage"></div>
				<div class="productName">상품 이름</div>
				<div class="productPrice">가격</div>
				<div class="productRegion">지역</div>
			</div>
		</div>

		<div class="box5">
			<div class="product4">
				<div class="productImage"></div>
				<div class="productName">상품 이름</div>
				<div class="productPrice">가격</div>
				<div class="productRegion">지역</div>
			</div>

			<div class="product5">
				<div class="productImage"></div>
				<div class="productName">상품 이름</div>
				<div class="productPrice">가격</div>
				<div class="productRegion">지역</div>
			</div>

			<div class="product6">
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

		watch: {
			isSocket: function (isSocket) {
				this.subscribe();
			},
		},
		name: '',
		components: { BidList },
		props: {
			isSocket: {
				type: Boolean,
				required: false,
			},
		},
		data() {
			return {
				seller: '',
				temporaryData: '',
				productNo: this.$route.query.productNo,
				isClicked: false,
				stompClient: '',
				resultObj: {},
				testInput: 0,
				imageCurrIndex: 0,
				leftTimer: '',
			};
		},

		//axios 통신
		methods: {
			async sellerInfo() {
				const response = await axios.get('/product?productNo=' + this.productNo);
				this.seller = response.data;
				const timer = setInterval(this.timer, 1000);
			},
			bidStart() {
				this.check = !this.check;
				this.isClicked = !this.isClicked;
			},
			timer() {
				let bidTime = new Date(this.seller.bidTime);
				bidTime.setMinutes(bidTime.getMinutes() + 10);
				let date = new Date();
				const diffMSec = bidTime - date;
				let s = Math.floor(diffMSec / 1000);
				let m = Math.floor(s / 60);
				s = s - m * 60;
				this.leftTimer = m + '분 ' + s + '초';
				console.log(this.leftTimer);
				if (m && s) {
					clearInterval(this.timer);
				}
			},
			recevieFunc(resObj) {
				console.log(resObj.price);
				this.seller.productHighPrice = resObj.price;
			},

			subscribe() {
				this.socket.subscribe('/product/' + this.productNo, this.recevieFunc);
			},
			prevSlide() {
				if (this.imageCurrIndex) this.imageCurrIndex--;
			},
			nextSlide() {
				if (this.imageCurrIndex < this.seller.productImageList.length - 1) this.imageCurrIndex++;
			},
		},
		created() {
			this.sellerInfo();
		},
		mounted() {
			if (this.isSocket) {
				this.socket.subscribe('/product/' + this.productNo, this.recevieFunc);
			}
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
		margin-left: 280px;
		display: flex;
		flex-direction: column;
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

	.hackNumber {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
	}

	.jjim {
		color: #0075ff;
		font: 20px 'Roboto', sans-serif;
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
