<template>
	<div class="dropdown d-flex justify-content-center align-items-center" style="height: 200px">
		<button
			class="btn btn-outline-info dropdown-toggle"
			type="button"
			id="categoryDropdown"
			data-bs-toggle="dropdown"
			aria-expanded="false"
		>
			{{ selectedCategory }}
		</button>
		<ul class="dropdown-menu" aria-labelledby="categoryDropdown">
			<li v-for="item in categoryList">
				<router-link
					:key="item.categoryNo"
					:value="item.categoryNo"
					:to="{ path: '/auction', query: { category: item.categoryNo } }"
					class="dropdown-item"
					@click="selectedCategory = item.categoryName"
				>
					{{ item.categoryName }}
				</router-link>
			</li>
		</ul>
	</div>
	<!-- <div class="titleBox">
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
	</div> -->
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
				categoryList: [],
				selectedCategory: '의류',
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
					const selectedCategory = this.categoryList.find(
						(item) => item.categoryNo === Number(this.categoryNo),
					);
					this.selectedCategory = selectedCategory ? selectedCategory.categoryName : '';
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
		background-color: #81f7f3;
		width: 100%;
		margin: 5px;
		height: calc(100% - 1px);
		justify-content: center;
		align-items: center;
		display: flex;
		border-radius: 30px;
		box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.2);
	}
	.title:active {
		transform: translateY(4px);
	}
	.select {
		background-color: #bef781;
	}
</style>
