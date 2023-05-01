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
				방사능 지수
				<div style="margin-top: 10px; color: #0075ff">찜하기</div>
			</div>
		</div>
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

	.userImage {
		border-radius: 50%;
		border-style: solid;
		border-color: #000000;
		border-width: 1.5px;
		width: 40px;
		height: 40px;
	}
</style>
