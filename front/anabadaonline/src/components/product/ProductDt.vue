<template>
	<div class="backColor">
		<div class="form overflow-auto">
			<router-link to="/auction?categoryNo=1" class="listButton">
				<button type="button" class="btn-close" aria-label="Close"></button>
			</router-link>
			<div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
				<div class="carousel-indicators">
					<button
						type="button"
						data-bs-target="#carouselExampleIndicators"
						:data-bs-slide-to="index"
						:class="{ active: index == 0 }"
						aria-current="true"
						:aria-label="'Slide' + index"
						v-for="(image, index) in seller.productImageList"
					></button>
				</div>
				<div class="carousel-inner">
					<div
						class="carousel-item w-100"
						v-for="(image, index) in seller.productImageList"
						:key="index"
						:class="{ active: index == 0 }"
					>
						<img class="d-block productImage" :src="image" />
					</div>
				</div>
				<button
					class="carousel-control-prev"
					type="button"
					data-bs-target="#carouselExampleDark"
					data-bs-slide="prev"
				>
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button
					class="carousel-control-next"
					type="button"
					data-bs-target="#carouselExampleDark"
					data-bs-slide="next"
				>
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>

			<div class="userInfo">
				<img class="userImage" :src="seller.memberImage" />
				<div class="box1">
					<div class="sellerName">판매자 이름 : {{ seller.memberName }}</div>
					<div class="mainDeal">판매자 주 거래지 : {{ seller.memberAddr }}</div>
				</div>
			</div>

			<div class="line"></div>

			<div class="test">
				<div class="productHeader">
					<div class="productTitle">상품 이름 :{{ seller.productName }}</div>
					<div class="productTime">{{ seller.productTime }}</div>
				</div>
				<div class="productStatus">
					<a class="productText1">찜 0</a>
					<a class="productText1">조회 {{ seller.productVisit }}</a>
				</div>
			</div>

			<div class="productInfoBox">
				<div class="productRemainTime" v-if="seller.productHighPrice">남은 시간 : {{ leftTimerView }}</div>
				<div class="productRegisterPrice">등록 가격 : {{ productPrice.toLocaleString() }}원</div>
				<div class="productHighPrice" v-if="seller.productHighPrice">
					현재 최고가 : {{ seller.productHighPrice.toLocaleString() }} 원
				</div>

				<div class="productExplain">상품 사용기간 : {{ seller.productUseDate }}</div>
				<div class="productExplain">상품 설명 : {{ seller.productDetail }}</div>
			</div>

			<div class="productFooter">
				<button class="auctionText" :class="{ clicked: isClicked }" @click="bidStart">경매 참여</button>
			</div>

			<div class="line"></div>
			<BidList
				v-if="isClicked"
				:memberNo="seller.memberNo"
				:productName="seller.productName"
				:leftTime="leftTime"
				:isSocket="isSocket"
				:productPrice="seller.productPrice"
			></BidList>
			<div class="recommend">추천 상품</div>
			<div class="box6">
				<div class="box4">
					<router-link
						v-for="item in productList"
						:key="item.productNo"
						:to="'/productDt?productNo=' + item.productNo"
					>
						<img class="productImage" :src="item.productImage" />

						<div class="productName">{{ item.productName }}</div>

						<div class="productRegion">{{ item.wishAddr }}</div>

						<div class="productPrice">{{ item.price }} 원</div>
					</router-link>
				</div>
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
			productNo: {
				type: Number,
			},
		},
		watch: {
			isSocket: function (isSocket) {
				if (isSocket) {
					this.subscribe();
				}
			},
			leftTime: function () {},
			productNo: function (r) {
				this.sellerInfo();
			},
		},
		name: '',
		components: { BidList },

		data() {
			return {
				seller: '',
				temporaryData: '',
				isClicked: false,
				stompClient: '',
				testInput: 0,
				imageCurrIndex: 0,
				leftTimerView: '',
				leftTime: null,
				productSubscribe: null,
				timerInterval: null,
				productList: [],
				productPrice: 0,
			};
		},

		//axios 통신
		methods: {
			async sellerInfo() {
				const response = await axios.get('/product?productNo=' + this.productNo);
				this.seller = response.data;
				this.productPrice = response.data.productPrice;
				this.timeStart();
			},
			bidStart() {
				this.isClicked = !this.isClicked;
			},
			timeStart() {
				this.leftTime = new Date(this.seller.bidTime);
				this.leftTime.setMinutes(this.leftTime.getMinutes() + 10);
				let date = new Date();
				date.setMilliseconds(0);
				this.leftTime = this.leftTime - date;
				if (this.leftTime > 0) {
					this.timerInterval = setInterval(this.timer, 1000);
				} else {
					this.leftTimerView = '경매가 종료되었습니다.';
				}
			},

			timer() {
				this.leftTime -= 1000;
				if (this.leftTime <= 0) {
					clearInterval(this.timerInterval); // 타이머가 0 이하가 되었을 때 타이머를 멈추도록 함
					this.$swal({
						icon: 'info',
						title: '경매가 종료되었습니다.',
					});
					this.leftTimerView = '경매가 종료되었습니다.';
					return;
				}
				let s = Math.floor(this.leftTime / 1000);
				let m = Math.floor(s / 60);
				s = s - m * 60;
				this.leftTimerView = m + '분 ' + s + '초';
			},
			recevieFunc(resObj) {
				this.seller.productHighPrice = resObj.price;
				clearInterval(this.timerInterval);
				this.seller.bidTime = new Date();
				this.timeStart();
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
			product() {
				axios.get('/product/main?size=4').then((response) => {
					this.productList = response.data.list;
				});
			},
		},
		created() {
			if (this.isSocket) {
				this.subscribe();
			}
			this.sellerInfo();
			this.product();
		},
		mounted() {},
		unmounted() {
			if (this.productSubscribe != null) {
				this.productSubscribe.unsubscribe();
			}
		},
	};
</script>

<style scoped>
	.carousel-control-prev-icon,
	.carousel-control-next-icon {
		filter: invert(100%);
	}

	.backColor {
		background: #f2f3f6;
		display: flex;
		justify-content: center;
	}

	.form {
		background: #ffffff;
		width: 950px;
		display: flex;
		flex-direction: column;
		/* margin: 100px auto 0; */
		position: relative;
	}

	.form > * {
		margin-bottom: 20px;
		padding: 5px;
	}
	@media (max-width: 768px) {
		.form {
			background: #ffffff;
			width: 100%;
			display: flex;
			flex-direction: column;
			margin: 100px auto 0;
			position: relative;
		}
	}
	.productHeader {
		display: flex;
		margin-left: 60px;
		align-items: center;
	}

	.productInfoBox {
		margin-left: 60px;
	}

	.productPicture {
		/* background: #d9d9d9; */
		border-radius: 20px;
		height: 100%;
		transition: margin-left 1s;
		width: fit-content;
		display: flex;
	}
	.anotherButton {
		justify-items: flex-end;
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
	.imageBox {
		width: 850px;
		height: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	.imageBox > * {
		object-fit: cover;
		height: 100%;
		max-width: 100%;
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

	.go_list {
		background-color: transparent;
		color: black;
		border: none;
		cursor: pointer;
		outline: none;
		margin: auto;
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
		margin-left: 270px;
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
		cursor: pointer;
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
		color: #868e96;
		font: 14px 'Roboto', sans-serif;
	}

	.auctionText {
		font: 14px 'Roboto', sans-serif;
		background: #f7f7f7;
		border: 1px #f5e7e74d solid;
		border-radius: 5px;
		width: 400px;
		height: 30px;
		text-align: center;
		cursor: pointer;
		box-shadow: 0.5px 0.5px 0.5px 0.5px rgba(1, 1, 1, 0.5);
	}
	.auctionText:active {
		cursor: pointer;
		transform: translateY(4px);
	}
	.auctionText.clicked {
		font: 14px 'Roboto', sans-serif;
		border: 1px #00a5ff solid;

		background: #00a5ff;
		color: #ffffff;
		border-radius: 5px;
		width: 400px;
		height: 30px;
		text-align: center;
		border: none;
	}

	.productTitle {
		color: #000000;
		font: 25px 'Roboto', sans-serif;
		font-weight: bold;
		width: 100%;
	}

	.productRegisterPrice {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
		margin-bottom: 10px;
	}

	.productHighPrice {
		color: #000000;
		font: 16px 'Roboto', sans-serif;
		font-weight: bold;
		margin-bottom: 10px;
	}

	.productTime {
		/* position: absolute;
		top: 550px;
		left: 700px; */
		position: relative;
		color: #000000;
		font: 12px 'Roboto', sans-serif;
		opacity: 0.4;
		width: 200px;
	}

	.productRemainTime {
		color: #000000;
		font: 20px 'Roboto', sans-serif;
		font-weight: bold;
		margin-bottom: 10px;
	}

	.productExplain {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
		margin-bottom: 10px;
		word-break: break-all;
	}

	.productFooter {
		margin-top: 90px;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.productStatus {
		display: flex;
		gap: 40px;
		align-items: center;
		justify-content: flex-end;
	}

	.box3 {
		gap: 280px;
		display: flex;
		flex-direction: row;
		gap: 600px;
		justify-content: center;
	}

	.others {
		color: #0075ff;
		font: 15px 'Roboto', sans-serif;
	}

	.actionProduct {
		color: #000000;
		font: 16px 'Roboto', sans-serif;
		font-weight: bold;
	}

	.box4 {
		display: flex;
		width: 850px;
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

	.box6 {
		display: flex;
		flex-direction: column;
		align-items: center;
	}

	.recommend {
		margin-left: 60px;
	}

	.productImage {
		border-radius: 20px;
		height: 400px;
		width: auto;
		max-width: 100%;
		object-fit: contain; /* 이미지를 비율을 유지하며 부모 요소 내에 맞추기 */
		margin: auto;
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
