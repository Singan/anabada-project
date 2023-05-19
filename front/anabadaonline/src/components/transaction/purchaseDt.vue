<template>
	<div class="form">
		<div class="deal">거래 내역</div>

		<div class="historyText">
			<div class="purchaseText">구매 내역</div>
			<div class="salesText">판매 내역</div>
		</div>

		<div class="productInfo">
			<img class="productPicture" />
			<div class="productDetail">
				<div class="detailND">아이폰 14프로</div>
				<div class="detailPrice">1,200,000 원</div>
				<div class="detailND">경기도 성남시 1달전 판매완료</div>
			</div>
			<select class="sortList">
				<option>가격순</option>
				<option>거래순</option>
			</select>
		</div>
	</div>
</template>

<script>
	export default {};
</script>

<style scoped>
	.form {
		background: #ffffff;
		width: 800px;
		height: 800px;
		display: flex;
		flex-direction: column;
		margin: 80px auto 0;
		overflow: hidden;
	}

	.form > * {
		margin-bottom: 20px;
		padding: 30px;
	}

	.deal {
		color: #000000;
		font: 700 24px 'Roboto', sans-serif;
	}

	.historyText {
		display: flex;
		gap: 150px;
		justify-content: center;
	}

	.purchaseText {
		color: #0075ff;
		font: 700 24px 'Roboto', sans-serif;
	}

	.salesText {
		color: #000000;
		font: 700 24px 'Roboto', sans-serif;
	}

	.productInfo {
		display: flex;
	}

	.productPicture {
		background: #d9d9d9;
		border-radius: 10px;
		width: 160px;
		height: 160px;
	}

	.productDetail {
		margin-left: 20px;
		display: flex;
		flex-direction: column;
		justify-content: center;
	}

	.detailND {
		color: #000000;
		font: 500 16px 'Roboto', sans-serif;
		margin-bottom: 10px;
	}

	.detailPrice {
		color: #000000;
		font: 700 16px 'Roboto', sans-serif;
		margin-bottom: 10px;
	}

	.sortList {
		margin-left: 150px;
		font-size: 16px;
		border-style: solid;
		text-align: center;
		width: 140px;
		height: 25px;
	}
</style>
