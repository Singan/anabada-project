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
		name: 'Auction',
		components: {},
		props: {},
		data() {
			return {
				productList: [],
				categoryList: '',
				size: 8,
				page: 0,
				totalCountPage: 0,
				loading: false, // 데이터 로딩 상태 추가
				debounceTimer: null, // 딜레이 타이머 변수 추가
			};
		},
		computed: {
			categoryNo() {
				return this.$route.query.category || 1;
			},
		},
		methods: {
			category() {
				axios.get('/category').then((response) => {
					this.categoryList = response.data;
				});
			},
			async product() {
				let response = await axios.get(
					'/product/list?categoryNo=' + this.categoryNo + '&&size=' + this.size + '&&page=' + this.page,
				);
				this.productList = response.data.list;
				this.totalCountPage = response.data.totalCountPage;
				this.loading = false; // 데이터 로딩 완료 후 상태 변경
			},
			scroll() {
				// 딜레이 시간 설정
				const delay = 200;

				// 딜레이 타이머가 존재할 경우 이전 타이머 제거
				if (this.debounceTimer) {
					clearTimeout(this.debounceTimer);
				}

				// 딜레이 후 로딩 함수 호출
				this.debounceTimer = setTimeout(() => {
					const scrollPosition = window.pageYOffset || document.documentElement.scrollTop;
					const windowHeight = window.innerHeight;
					const documentHeight = document.documentElement.scrollHeight;

					// 스크롤 위치 확인 및 로딩 상태 확인
					if (scrollPosition + windowHeight >= documentHeight && !this.loading) {
						this.loadMoreData();
					}
				}, delay);
			},

			async loadMoreData() {
				this.loading = true;

				// 추가 데이터 로딩
				if (this.page < this.totalCountPage) {
					console.log('데이터호출');
					this.page++;
					let response = await axios.get(
						'/product/list?categoryNo=' + this.categoryNo + '&&size=' + this.size + '&&page=' + this.page,
					);
					this.productList.push(...response.data.list);
				}

				// 로딩 상태 변경
				this.loading = false;
			},
			scrollToTop() {
				window.scrollTo({
					top: 0,
					behavior: 'smooth',
				});
			},
		},
		mounted() {
			// scroll 이벤트 핸들러 등록
			window.addEventListener('scroll', this.scroll);
			this.scrollToTop();
		},
		beforeUnmount() {
			// scroll 이벤트 핸들러 제거
			window.removeEventListener('scroll', this.scroll);
		},
		created() {
			this.category();
			this.product();
		},
	};
</script>
<style scoped>
	.productFlex {
		margin: auto;
		display: grid;
		width: 60%;
		row-gap: 50px;
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
