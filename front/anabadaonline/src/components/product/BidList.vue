<template>
	<div class="form1">
		<div class="user1">
			<div class="ListImage"></div>
			<div class="userName">{{ testData.list[0].memberName }}</div>
			<div class="userPrice">{{ testData.list[0].price }}원</div>
		</div>

		<div class="listLine"></div>

		<div class="user2">
			<div class="ListImage"></div>
			<dib class="userName">{{ testData.list[1].memberName }}</dib>
			<div class="userPrice">{{ testData.list[1].price }}원</div>
		</div>

		<div class="listLine"></div>

		<div class="user3">
			<div class="ListImage"></div>
			<dib class="userName">{{ testData.list[2].memberName }}</dib>
			<div class="userPrice">{{ testData.list[2].price }}원</div>
		</div>

		<div class="bidBox">
			<input
				class="textSize"
				type="text"
				placeholder="ex)xxx,xxx,xxx 원"
			/>
			<button class="bid">입찰</button>
		</div>
	</div>
</template>

<script>
	var testData = {
		length: 3,
		list: [
			{
				memberName: 'user1',
				price: 11,
			},
			{
				memberName: 'user2',
				price: 22,
			},
			{
				memberName: 'user3',
				price: 33,
			},
		],
	};
	import axios from '@/axios.js';
	import socket from '@/common/socket';
	export default {
		name: '',
		components: {},
		props: {},
		data() {
			return {
				auction: '',
				testData,
				productNo: this.$route.query.productNo,
			};
		},

		methods: {
			auctionList() {
				axios
					.get('/bid?productNo=' + this.productNo)
					.then((response) => {
						console.log(response.data);
						this.auction = response.data;
						console.log(this.auction);
					});
			},
		},

		created() {
			this.auctionList();
		},
	};
</script>

<style scoped>
	.form1 {
		overflow: scroll;
		background: #ffffff;
		border-radius: 5px;
		width: 97%;
		height: 400px;
		display: flex;
		flex-direction: column;
		align-items: center;
		box-shadow: 5px 5px 2px 0px rgba(0, 117, 255, 0.25);
		border: 1px solid rgba(0, 0, 0, 0.2);
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
		align-items: flex-start;
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

	.user1 {
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		width: 440px;
		margin-top: 30px;
	}

	.user2 {
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		width: 440px;
	}

	.user3 {
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		width: 440px;
	}

	.bidBox {
		width: 440px;
		margin-top: 25px;
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
		width: 440px;
		height: 30px;
		color: #ffffff;
		font: 400 18px 'Roboto', sans-serif;
		border: none;
	}
</style>
