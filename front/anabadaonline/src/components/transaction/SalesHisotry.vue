<template>
	<div class="productInfo" v-for="item in salesList">
		<img class="productPicture" :src="item.productThumbnail" />
		<div class="productDetail">
			<div class="detailPrice">상품명 : {{ item.productName }}</div>
			<div class="detailPrice">등록 가격 :{{ item.productPrice }}</div>
			<div class="detailPrice">상품 등록 시간 : {{ item.createDateTime }}</div>
			<template v-if="item.isBidComplete">
				<div class="detailPrice">경매 낙찰자 : {{ item.memberName }}</div>
				<div class="detailPrice">경매 낙찰 가격 : {{ item.bidPrice }}</div>
				<div class="detailPrice">경매 낙찰 시간 : {{ item.bidTime }}</div>
			</template>
		</div>
		<div v-if="item.isBidComplete" class="successGo" @click="goSuccess(item.successNo)">낙찰 바로가기</div>
	</div>
</template>
<script>
	import axios from '@/axios';
	export default {
		name: '',
		components: {},
		props: {},
		data() {
			// quickfix to have components available to pass as props
			return {
				page: 0,
				totalPage: 0,
				size: 4,
				salesList: [],
				loading: false, // 데이터 로딩 상태 추가
				debounceTimer: null, // 딜레이 타이머 변수 추가
				totalCountPage: 0,
			};
		},
		methods: {
			goSuccess(successBidNo) {
				this.$router.push({ name: 'SuccessfulBid', params: { successBidNo } });
			},
			async productSalesList() {
				const result = await axios.get(`/mypage/sales?page=${this.page}&size=${this.size}`);
				this.salesList.push(...result.data.list);
				this.totalCountPage = result.data.totalCountPage;
				this.page++;
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
					this.productSalesList();
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

		async created() {
			await this.productSalesList();
		},
	};
</script>
<style scoped>
	.productPicture {
		background: #d9d9d9;
		border-radius: 10px;
		max-width: 160px;
		height: 160px;
		flex: 1;
	}

	.productDetail {
		margin-left: 20px;
		display: flex;
		flex-direction: column;
		justify-content: center;
		flex: 2;
		width: fit-content;
	}

	.detailPrice {
		color: #000000;
		font: 700 16px 'Roboto', sans-serif;
		margin-bottom: 5px;
		text-overflow: ellipsis;
		white-space: nowrap;
		overflow: hidden;
		max-width: 450px;
		flex: 2;
	}

	.productInfo {
		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.successGo {
		box-shadow: inset 0px 1px 0px 0px #bbdaf7;
		background: linear-gradient(to bottom, #79bbff 5%, #378de5 100%);
		background-color: #00a5ff;
		border-radius: 6px;
		border: 1px solid #00a5ff;
		cursor: pointer;
		color: #ffffff;
		font-family: Arial;
		font-size: 15px;
		font-weight: bold;
		padding: 6px 24px;
		text-decoration: none;
		min-width: 145px;
		text-shadow: 0px 1px 0px #00a5ff;
	}
	.successGo:hover {
		background: linear-gradient(to bottom, #00a5ff 5%, #00a5ff 100%);
		background-color: #00a5ff;
	}
</style>
