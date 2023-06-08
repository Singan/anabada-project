<template>
	<div class="form1">
		<div class="bidBox" v-if="$token.is()">
			<input
				class="textSize"
				type="number"
				placeholder="이 전보다 낮은 금액을 입력 시 입찰에 실패합니다."
				v-model="bidPrice"
			/>
			<button class="bid" type="button" @click="send">입찰</button>
		</div>
		<div class="user" v-for="(bid, index) in bidList" :key="index">
			<div class="ListImage"><img :src="bid.memberImage" /></div>
			<div class="userName">{{ bid.memberName }}</div>
			<div class="userPrice">{{ bid.price }}원</div>
			<div class="bidTime">{{ bid.bidTime }}</div>
		</div>
	</div>
</template>

<script>
	import axios from '@/axios.js';
	export default {
		inject: ['socket'],
		props: {
			memberNo: {
				type: Number,
			},
			productName: {
				type: String,
			},
			leftTime: {},
			isSocket: {
				type: Boolean,
			},
			productPrice:{
				type:Number
			}
		},
		name: '',
		components: {},
		watch: {
			isSocket: function (isSocket) {
				if (isSocket) {
					this.sub = this.socket.subscribe('/product/' + this.productNo, this.recevieFunc);
				}
			},
		},
		data() {
			return {
				bidList: '',
				productNo: this.$route.query.productNo,
				bidPrice: '',
				myMemberNo: this.$store.getters.getMember.no,
				sub: '',
			};
		},

		methods: {
			auctionList() {
				axios.get('/bid?productNo=' + this.productNo).then((response) => {
					this.bidList = response.data.list;
				});
			},

			recevieFunc(resObj) {
				this.bidList.unshift(resObj);
			},
			send() {
				if (this.leftTime == 0) {
					this.$swal({
						icon: 'info',
						title: '경매가 끝난 상품입니다.',
					});
					return;
				}
				const lastBid = this.bidList[this.bidList.length - 1];
				console.log("프로덕트프라이스 , ",this.productPrice)
				if (this.productPrice>this.bidPrice) {
					this.$swal({
						icon: 'info',
						title: '등록가격보다 낮은 금액은 입력할 수 없습니다.',
					});
				} else if (!lastBid || lastBid.price < this.bidPrice) {
					let msgObj = {
						bidPrice: this.bidPrice,
						productNo: this.productNo,
						memberImage: this.$store.getters.getMember.image,
						productName: this.productName,
					};
					this.bidPrice = '';
					this.socket.send(msgObj, '/bid');
				} else {
					alert('입찰 금액은 현재 최고 입찰금액보다 높아야 합니다.');
				}
			},
		},
		unmounted() {
			this.sub.unsubscribe();
		},
		created() {
			this.auctionList();
			console.log(this.isSocket);
			if (this.isSocket) {
				this.sub = this.socket.subscribe('/product/' + this.productNo, this.recevieFunc);
			}
		},
	};
</script>

<style scoped>
	input[type='number']::-webkit-outer-spin-button,
	input[type='number']::-webkit-inner-spin-button {
		-webkit-appearance: none;
		margin: 0;
	}
	.form1 {
		overflow: scroll;
		background: #ffffff;
		border-radius: 5px;
		width: 100%;
		height: 900px;
		display: flex;
		flex-direction: column;
		align-items: center;
		box-shadow: 5px 5px 2px 0px rgba(0, 117, 255, 0.25);
		border: 1px solid rgba(0, 0, 0, 0.2);
		align-items: center;
	}

	.form1 > * {
		margin-bottom: 10px;
		padding: 5px;
	}

	.ListImage {
		background: #d9d9d9;
		border-radius: 50%;
		width: 40px;
		height: 40px;
	}
	.ListImage > img {
		width: 100%;
		height: 100%;
	}
	.userName {
		color: #000000;
		font: 400 20px 'Roboto', sans-serif;
		flex: 1;
		margin-left: 10px;
		margin-bottom: 5px;
	}

	.userPrice {
		color: #000000;
		font: 400 15px 'Roboto', sans-serif;
		margin-left: 180px;
		flex: 1;
		font-weight: bold;
	}

	.listLine {
		border-style: solid;
		border-color: #dddddd;
		border-width: 1px 0 0 0;
		width: 430px;
		height: 0px;
		transform-origin: 0 0;
		transform: rotate(0deg) scale(1, 1);
	}

	.user {
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		width: 90%;
		margin-top: 30px;
		border-bottom: 1px dashed black;
	}

	.bidBox {
		width: 440px;
		margin-top: 25px;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	.textSize {
		text-align: left;
		font: 18px 'Roboto', sans-serif;
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1px;
		width: 430px;
		height: 50px;
		align-items: center;
	}

	input::placeholder {
		font-size: 18px;
	}

	.textSize:focus {
		outline: none;
	}

	.bid {
		margin-top: 15px;
		background: #0075ff;
		border-radius: 5px;
		text-align: center;
		width: 100px;
		height: 30px;
		color: #ffffff;
		font: 400 18px 'Roboto', sans-serif;
		border: none;
		letter-spacing: 5px;
		cursor: pointer;
		box-shadow: 1px 1px 1px 1px rgba(0.5, 0.5, 0.5, 0.5);
	}
	.bid:active {
		box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0);
		transform: translateY(4px);
	}
</style>
