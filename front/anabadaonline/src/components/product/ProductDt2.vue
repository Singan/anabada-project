<template>
	<form class="form">
		<div class="productPicture"></div>

		<div class="userInfo">
			<img class="userImage" src="@/assets/userImage.jpg" />
			<div class="box1">
				<div class="sellerName">판매자 이름 :</div>
				<div class="mainDeal">판매자 주 거래지 :</div>
			</div>

			<div class="box2">
				<div class="mainDeal">방사능 지수 0</div>
				<div class="jjim">
					<img class="starImage" src="@/assets/star.jpg" />찜하기
				</div>
			</div>
		</div>

		<div class="line"></div>

		<div class="prouductInfo">
			<div class="productNamePrice">상품 이름 :</div>
			<div class="productTime">등록 시간 :</div>
			<div class="productNamePrice">상품 가격 : xx원</div>
			<div class="productExplain">상품 설명 :</div>
		</div>

		<div class="myInfo">
			<a class="myInfoText">관심 0</a>
			<a class="myInfoText">채팅 0</a>
			<a class="myInfoText">조회 0</a>
			<a class="myInfoText">경매 참여 0</a>
		</div>

		<div class="line"></div>

		<div class="actionProduct">인기경매 상품</div>
	</form>
</template>

<script>
	export default {};
</script>

<style>
	.form {
		width: 100%;
		background: #ffffff;
		width: 700px;
		height: 2000px;
		display: flex;
		flex-direction: column;
		margin: 100px auto 0;
		overflow: hidden;
	}

	.form > * {
		margin-bottom: 20px;
		padding: 5px;
	}

	.productPicture {
		background: #d9d9d9;
		border-radius: 20px;
		width: 580px;
		height: 400px;
		left: 670px;
		top: 160px;
	}

	.userInfo {
		display: flex;
		flex-direction: row;
	}

	.box1 {
		margin-left: 20px;
		display: flex;
		flex-direction: column;
		gap: 15px;
	}
	.box2 {
		margin-left: 280px;
		display: flex;
		flex-direction: column;
		gap: 8px;
	}

	.sellerName {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
		font-weight: bold;
	}

	.mainDeal {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
	}

	.hackNumber {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
	}

	.jjim {
		color: #0075ff;
		font: 20px 'Roboto', sans-serif;
	}

	.userImage {
		border-radius: 50%;
		border-style: solid;
		border-color: #000000;
		border-width: 1.5px;
		width: 40px;
		height: 40px;
	}

	.starImage {
		width: 18px;
		height: 18px;
		margin-top: 5px;
	}

	.line {
		border-style: solid;
		border-color: #d9d9d9;
		border-width: 1px 0 0 0;
		width: 580px;
		height: 0px;
	}

	.productInfo {
		display: flex;
		flex-direction: column;
		gap: px;
	}

	.myInfoText {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
		opacity: 0.4;
	}

	.productNamePrice {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
		font-weight: bold;
		margin-bottom: 30px;
	}

	.productTime {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
		opacity: 0.4;
		margin-bottom: 30px;
	}

	.productExplain {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
		margin-bottom: 30px;
	}

	.myInfo {
		display: flex;
		gap: 50px;
	}
</style>
