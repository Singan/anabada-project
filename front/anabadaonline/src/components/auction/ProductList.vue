<template>
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
	import axios from '@/axios';
	export default {
		watch: {
			categoryNo(categoryNo) {
				this.productList = [];
				this.page = 0;
				this.totalCountPage = 0;
				this.product();
			},
		},
		props: {
			categoryNo: {
				type: Number,
			},
		},
		data() {
			return {
				productList: [],
				size: 12,
				page: 0,
				totalCountPage: 0,
				loading: false, // 데이터 로딩 상태 추가
				debounceTimer: null, // 딜레이 타이머 변수 추가
			};
		},
		methods: {
			async product() {
				let response = await axios.get(
					'/product/list?categoryNo=' + this.categoryNo + '&&size=' + this.size + '&&page=' + this.page,
				);
				this.productList.push(...response.data.list);
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
					this.product();
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
			console.log(this.categoryNo);
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

	.productImg {
		background: #d9d9d9;
		border-radius: 10px;
		height: 160px;
		max-width: 160px;
		max-height: 100%;
		box-shadow: 0px 0px 1px 1px rgba(0.5, 0.5, 0.5, 0.5);
	}
	.productImg:active {
		transform: translateY(1px);
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
