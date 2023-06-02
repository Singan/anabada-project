<template>
	<div class="titleBox">
		<router-link
			v-for="item in categoryList"
			:key="item.categoryNo"
			:value="item.categoryNo"
			:to="{ path: '/auction', query: { category: item.categoryNo } }"
			class="title"
			:class="{ select: categoryNo == item.categoryNo }"
		>
			{{ item.categoryName }}
		</router-link>
	</div>
	<ProductList :categoryNo="categoryNo"></ProductList>
</template>
<script>
	import axios from '@/axios.js';
	import ProductList from './ProductList.vue';
	export default {
		name: 'Auction',
		components: { ProductList },
		props: {},
		data() {
			return {
				categoryList: '',
			};
		},
		computed: {
			categoryNo() {
				return this.$route.query.category || 1;
			},
		},
		methods: {
			category() {
				console.log('getCategory');
				axios.get('/category').then((response) => {
					this.categoryList = response.data;
				});
			},
		},

		created() {
			this.category();
		},
	};
</script>
<style scoped>
	.titleBox {
		color: #010101;
		text-align: center;
		font: 600 18px 'Roboto', sans-serif;
		margin: 50px auto;
		width: 60%;
		height: 39px;
		display: flex;
		align-items: center;
		justify-content: center;
	}
	.title {
		background-color: skyblue;
		border: 0.001px black solid;
		width: 100%;
		height: calc(100% - 1px);
		justify-content: center;
		align-items: center;
		display: flex;
		border-radius: 30px;
	}
	.select {
		background-color: greenyellow;
	}
</style>
