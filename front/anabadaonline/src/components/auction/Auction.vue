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
	<div class="productFlex" ref="productFlex">
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
		watch: {
			categoryNo(newCategoryNo) {
				(this.page = 0), (this.totalCountPage = 0);

				console.log('바뀜?');
				this.product();
			},
		},
		name: 'Auction',
		components: {},
		props: {},
		computed: {
			categoryNo() {
				return this.$route.query.category || 1;
			},
		},
		data() {
			return {
				productList: [],
				categoryList: '',
				size: 8,
				page: 0,
				totalCountPage: 0,
			};
		},
		methods: {
			category() {
				axios.get('/category').then((response) => {
					this.categoryList = response.data;
					this.product();
				});
			},
			async product() {
				let response = await axios.get(
					'/product/list?categoryNo=' + this.categoryNo + '&&size=' + this.size + '&&page=' + this.page,
				);
				this.productList = response.data.list;
				this.totalCountPage = response.data.totalCountPage;
			},
			scroll() {
				const element = this.$refs.productFlex;

				const scrollBottom = element.scrollTop + element.clientHeight;
				// 스크롤 위치 확인
				if (scrollBottom >= element.scrollHeight) {
					this.loadMoreData();
				}
			},

			async loadMoreData() {
				// 추가 데이터 로딩
				if (this.page < this.totalCountPage) {
					console.log('추가 데이터 가져오기');
					this.page++;
					let response = await axios.get(
						'/product/list?categoryNo=' + this.categoryNo + '&&size=' + this.size + '&&page=' + this.page,
					);
					this.productList.push(...response.data.list);
				}
			},
		},
		mounted() {
			// scroll 이벤트 핸들러 등록
			window.addEventListener('scroll', this.scroll);
		},
		beforeUnmount() {
			// scroll 이벤트 핸들러 제거
			window.removeEventListener('scroll', this.scroll);
		},
		created() {
			this.category();
		},
	};
</script>
<style scoped>
	.productFlex {
		margin: auto;
		display: grid;
		width: 60%;
		row-gap: 10px;
		/* row의 간격을 10px로 */
		column-gap: 20px;
		/* column의 간격을 20px로 */
		grid-template-columns: 1fr 1fr 1fr 1fr;
		grid-template-rows: auto;
		overflow: auto;
	}

	.flexItem {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

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
	.productImg {
		background: #d9d9d9;
		border-radius: 20px;
		width: 160px;
		height: 160px;
		max-width: 100%;
		max-height: 100%;
	}

	.productName {
		color: #000000;
		text-align: center;
		font: 300 16px 'Roboto', sans-serif;
		width: 100%;
	}

	.price {
		color: #000000;
		text-align: center;
		font: 600 16px 'Roboto', sans-serif;

		width: 100%;
	}
</style>
