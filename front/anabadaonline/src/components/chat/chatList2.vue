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
						<div class="userName">유저이름 들어갈자리</div>
						<div class="productName">상품이름 들어갈 자리</div>
					</div>
					<div class="region">경기 성남시</div>
					<div class="productImage">
						<img src="@/assets/userImage.jpg" />
						<!--상품 이미지 들어갈자리-->
					</div>
				</div>
			</div>

			<div class="chatContent">
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
	}

	.listHeader {
		font-size: 16px;
		font-weight: bold;
		background: #ffffff;
		color: black;
		font-family: 'Roboto', sans-serif;
		height: 64px;
		width: 270px;
		display: flex;
		align-items: center;
		justify-content: center;
		border: 1.5px solid #eaebee;
	}

	.chatBox {
		width: 50rem;
		height: 60rem;
		background-color: #ffffff;
		border: 1.5px solid #eaebee;
		border-bottom: none;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.chatTarget {
		display: flex;
		flex-direction: row;
		align-items: center;
		height: 64px;
		width: 270px;
		background-color: #ffffff;
		border: 1.5px solid #eaebee;
		border-top: none;
		cursor: pointer;
		justify-content: space-between;
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

	.productImage {
		margin-left: 38px;
	}

	.region {
		font-size: 13px;
		letter-spacing: -0.02em;
	}
</style>
