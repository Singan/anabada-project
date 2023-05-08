<template>
	<div class="form">
		<div class="user1">
			<div class="userImage"></div>
			<dib class="userName">홍길동</dib>
			<div class="userPrice">xxx,xxx원</div>
		</div>

		<div class="line"></div>

		<div class="user2">
			<div class="userImage"></div>
			<dib class="userName">김세진</dib>
			<div class="userPrice">xxx,xxx원</div>
		</div>

		<div class="line"></div>

		<div class="user3">
			<div class="userImage"></div>
			<dib class="userName">석성희</dib>
			<div class="userPrice">xxx,xxx원</div>
		</div>

		<div class="bidBox">
			<div class="inputPrice">
				<input
					class="textSize"
					type="text"
					placeholder="ex)xxx,xxx,xxx 원"
					style="border: none; outline: none"
				/>
			</div>
			<button class="bid">입찰</button>
		</div>
	</div>
</template>

<script>
	import axios from '@/axios.js';

	export default {
		name: '',
		components: {},
		props: {},
		data() {
			return {
				auction: '',
			};
		},

		methods: {
			auctionList() {
				axios.get('/bid').then((response) => {
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

<style>
	.form {
		background: #ffffff;
		border-radius: 5px;
		width: 560px;
		height: 330px;
		display: flex;
		flex-direction: column;
		align-items: center;
		overflow: hidden;
		margin: 100px auto 0;
		box-shadow: 4px 4px 8px 0px rgba(216, 216, 216, 0.25);
		border: 5px solid black;
	}

	.form > * {
		margin-bottom: 10px;
		padding: 5px;
	}

	.userImage {
		background: #d9d9d9;
		border-radius: 50%;
		width: 20px;
		height: 20px;
		align-items: flex-start;
	}
	.userName {
		color: #000000;
		font: 400 12px 'Roboto', sans-serif;
		margin-left: 10px;
	}

	.userPrice {
		color: #000000;
		font: 400 11px 'Roboto', sans-serif;
		margin-left: 320px;
		font-weight: bold;
	}

	.line {
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
		width: 440px;
		margin-top: 30px;
	}

	.user2 {
		display: flex;
		width: 440px;
	}

	.user3 {
		display: flex;
		width: 440px;
	}

	.bidBox {
		display: flex;
		width: 440px;
		margin-top: 70px;
		gap: 30px;
	}

	.inputPrice {
		color: #797979;
		text-align: left;
		font: 300 10px 'Roboto', sans-serif;
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1px;
		width: 370px;
		height: 20px;
		align-items: center;
		display: flex;
	}

	.textSize {
		width: 370px;
		height: 10px;
	}

	.bid {
		background: #0075ff;
		border-radius: 5px;
		width: 40px;
		height: 22px;
		color: #ffffff;
		font: 400 10px 'Roboto', sans-serif;
		border: none;
	}
</style>
