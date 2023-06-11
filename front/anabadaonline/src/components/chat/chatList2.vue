<template>
	<div class="form">
		<div class="flexContainer">
			<div class="chatList">
				<div class="listHeader">채팅 목록</div>

				<div class="chatTarget">
					<div class="userImage">
						<img src="@/assets/userImage.jpg" />
						<!--대화상대 이미지 들어갈자리-->
					</div>
					<div class="userInfo">
						<div class="userInfoBox1">
							<div class="userName">유저이름 들어갈자리</div>
							<div class="region">지역 들어갈 자리</div>
						</div>
						<div class="productName">상품이름 들어갈 자리</div>
					</div>

					<div class="productImage">
						<img src="@/assets/userImage.jpg" />
						<!--상품 이미지 들어갈자리-->
					</div>
				</div>
			</div>
			<!-- -------------여기부터 채팅내용 들어갈 자리---------------->
			<div class="chatContent">
				<div class="userName2">XX님 과의 채팅</div>
				<div class="productBox">
					<div class="productImage2"><img src="@/assets/userImage.jpg" /></div>
					<div class="productBox2">
						<div class="productName2">상품 이름</div>
						<div class="productPrice">상품 가격</div>
					</div>
				</div>
				<div class="chatBox"><img src="@/assets/chatBubble.jpg" /></div>
			</div>
		</div>
	</div>
</template>

<script>
	export default {};
</script>

<style scoped>
	.form {
		background-color: #f2f3f6;
		width: 100%;
		height: 100%;
	}

	.flexContainer {
		display: flex;
		flex-direction: row;
		overflow: hidden;
		width: 100%;
		height: 60rem;
		justify-content: center;
	}

	.chatList {
		display: flex;
		flex-direction: column;
	}
	.chatContent {
		display: flex;
		flex-direction: column;
		width: 50rem;
		height: 60rem;
		background-color: #ffffff;
	}

	.listHeader {
		font-size: 20px;
		font-weight: bold;
		background: #ffffff;
		color: black;
		font-family: 'Roboto', sans-serif;
		height: 78px;
		width: 330px;
		display: flex;
		align-items: center;
		justify-content: center;
		border: 1.5px solid #eaebee;
	}

	.chatBox {
		border-bottom: none;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.chatTarget {
		display: flex;
		flex-direction: row;
		align-items: center;
		height: 78px;
		width: 330px;
		background-color: #ffffff;
		border: 1.5px solid #eaebee;
		border-top: none;
		cursor: pointer;
	}

	.userImage img {
		width: 40px;
		height: 40px;
		background-color: #ffffff;
		border-radius: 50%;
		border: 1px solid #dcdee3;
	}

	.productImage img {
		width: 40px;
		height: 40px;
		background-color: #ffffff;
		border: 1px solid #dcdee3;
		border-radius: 4px;
	}

	.productImage2 img {
		width: 40px;
		height: 40px;
		background-color: #ffffff;
		border: 1px solid #dcdee3;
		border-radius: 4px;
	}

	.productImage {
		margin-left: auto;
	}

	.productImage2 {
	}

	.userInfo {
		display: flex;
		flex-direction: column;
		margin-left: 8px;
		overflow: hidden;
	}

	.userName {
		font-weight: bold;
		font-size: 13px;
		letter-spacing: -0.02em;
		text-overflow: ellipsis;
		white-space: nowrap;
		overflow: hidden;
	}

	.productName {
		font-size: 13px;
		letter-spacing: -0.02em;
		text-overflow: ellipsis;
		white-space: nowrap;
		overflow: hidden;
	}

	.region {
		margin-left: 10px;
		font-size: 12px;
		letter-spacing: -0.02em;
		text-overflow: ellipsis;
		white-space: nowrap;
		overflow: hidden;
		color: #878b93;
	}

	.userInfoBox1 {
		display: flex;
		flex-direction: row;
	}

	.userName2 {
		height: 78px;
		width: 100%;
		display: flex;
		align-items: center;
		border: 1.5px solid #eaebee;
		justify-content: center;
	}

	.productBox {
		height: 78px;
		width: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: row;
		border: 1.5px solid #eaebee;
		border-top: none;
	}

	.productBox2 {
		margin-left: 36px;
	}

	.productName2 {
		font-size: 16px;
		letter-spacing: -0.02em;
	}

	.productPrice {
		font-size: 16px;
		letter-spacing: -0.02em;
		font-weight: bold;
	}
</style>
