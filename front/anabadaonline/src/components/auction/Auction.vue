<template>
	<div class="titleBox">
		<div
			v-for="item in categoryList"
			:key="item.categoryNo"
			:value="item.categoryNo"
			class="title"
			@click="product(item.categoryNo)"
		>
			{{ item.categoryName }}
		</div>
	</div>
	<div class="productFlex">
		<div class="flexItem" v-for="item in productList" :key="item.productNo">
			<router-link :to="'/ProductDt?productNo=' + item.productNo">
				<img class="productImg" :src="item.productImage" />
			</router-link>

			<div class="productName">{{ item.productName }}</div>

			<div class="price">등록 가격 : {{ item.productPrice }} 원</div>
		</div>
	</div>
</template>
<script>
	import axios from '@/axios.js';

	export default {
		name: 'Off',
		components: {},
		props: {},
		data() {
			return {
				productList: '',
				categoryList: '',

				categoryNo: '',
			};
		},
		methods: {
			category() {
				axios.get('/category').then((response) => {
					console.log(response.data);
					this.categoryList = response.data;
				});
			},
			product(key) {
				axios.get('/product/list?categoryNo=' + key).then((response) => {
					console.dir(response.data);
					this.productList = response.data;
				});
			},
		},
		mounted() {},

		created() {
			this.product(1);
			this.category();
		},
	};
</script>
<style scoped>
	.productFlex {
		margin: auto;
		display: flex;
		width: 60%;
		align-items: center;
		flex-wrap: wrap;
		justify-content: flex-start;
	}

	.flexItem {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		width: 20%;
		margin: 5%;
	}

	.titleBox {
		color: #010101;
		text-align: center;
		background-color: skyblue;
		font: 600 18px 'Roboto', sans-serif;
		margin: 50px auto;
		width: 60%;
		height: 39px;
		display: flex;
		align-items: center;
		justify-content: center;
	}
	.title {
		border: 1px black solid;
		width: 100%;
		height: calc(100% - 1px);
		justify-content: center;
		align-items: center;
		display: flex;
	}
	.productImg {
		background: #d9d9d9;
		border-radius: 20px;
		width: 100%;
		height: 100%;
	}

	.productName {
		color: #000000;
		text-align: center;
		font: 300 16px 'Roboto', sans-serif;
		width: 100%;
		height: 100%;
	}

	.price {
		color: #000000;
		text-align: center;
		font: 600 16px 'Roboto', sans-serif;

		width: 100%;
		height: 100%;
	}
</style>
