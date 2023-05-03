<template>
	<form class="form">
		<div class="box"></div>

		<div class="userInfo">
			<img class="userImage" src="@/assets/userImage.jpg" />

			<div class="commonText1" style="margin-left: 20px">
				판매자 이름 &nbsp; :
				<div class="commonText2" style="margin-top: 10px">
					판매자 주 거래지 &nbsp; :
				</div>
			</div>

			<div class="commonText2" style="margin-left: 250px">
				방사능 지수 &nbsp;:

				<div style="margin-top: 10px; color: #0075ff">
					<img class="starImage" src="@/assets/star.jpg" />찜하기
				</div>
			</div>
		</div>

		<div class="line"></div>

		<div class="prouductInfo">
			<div class="commonText1" style="margin-bottom: 30px">상품 이름</div>
			<div class="commonText3" style="margin-bottom: 30px">등록 시간</div>
			<div class="commonText1" style="margin-bottom: 30px">
				상품 가격 xx원
			</div>
			<div class="commonText2" style="margin-bottom: 30px">상품 설명</div>
		</div>

		<div class="myInfo">
			<div class="commonText3">관심</div>
			<div class="commonText3">채팅</div>
			<div class="commonText3">조회</div>
			<div class="commonText3">관심</div>
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

	.box {
		background: #d9d9d9;
		border-radius: 20px;
		width: 580px;
		height: 400px;
		left: 670px;
		top: 160px;
	}

	.userInfo {
		display: flex;
	}

	.commonText1 {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
		font-weight: bold;
	}

	.commonText2 {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
	}
	.commonText3 {
		color: #000000;
		font: 14px 'Roboto', sans-serif;
		opacity: 0.4;
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
		width: 22px;
		height: 22px;
		margin-top: 5px;
	}

	.line {
		border-style: solid;
		border-color: #d9d9d9;
		border-width: 1px 0 0 0;
		width: 580px;
		height: 0px;
	}
</style>
