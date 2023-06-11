<template>
	<div class="form">
		<div class="flexContainer">
			<div class="chatList">
				<div class="listHeader">채팅 목록</div>

				<div class="chatTarget" @click="isClick = true">
					<div class="userImage">
						<!--대화상대 이미지 들어갈자리-->
						<img src="@/assets/userImage.jpg" />
					</div>
					<div class="userInfo">
						<div class="userInfoBox1">
							<div class="userName">유저이름 들어갈자리</div>
							<div class="region">지역 들어갈 자리</div>
						</div>
						<div class="productName">상품이름 들어갈 자리</div>
					</div>

					<div class="productImage">
						<!--상품 이미지 들어갈자리-->
						<img src="@/assets/userImage.jpg" />
					</div>
				</div>
			</div>
			<!-- -------------여기부터 채팅관련 들어갈 자리---------------->

			<div v-if="!isClick" class="chatContent2">
				<div class="chatBox"><img src="@/assets/chatBubble.jpg" /></div>
			</div>

			<div class="chatContent justify-content-end" v-if="isClick">
				<div class="userName2">XX님 과의 채팅</div>
				<div class="productBox">
					<div class="productImage2"><img src="@/assets/userImage.jpg" /></div>
					<div class="productBox2">
						<div class="productName2">상품 이름</div>
						<div class="productPrice">상품 가격</div>
					</div>
				</div>

				<!-- 채팅 -->
				<div class="chatting flex-column flex-grow-1">
					<div class="receiver">XX님이 보낸 메시지</div>
					<div class="sender">내가 보내는 메세지</div>
				</div>

				<!-- 채팅 내용 입력창 + 보내기 버튼 -->
				<div class="chatControll">
					<textarea class="chatInput"> </textarea>
					<button class="sendButton">전송</button>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				isClick: false,
			};
		},
	};
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
		border: 1.5px solid #eaebee;
		border-top: none;
		border-left: none;
	}
	.chatContent2 {
		display: flex;
		flex-direction: column;
		width: 50rem;
		height: 60rem;
		background-color: #ffffff;
		border: 1.5px solid #eaebee;
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
		width: 50rem;
		height: 60rem;
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
		font-size: 20px;
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

	.chatting {
		display: flex;
		flex-direction: column;
		overflow-y: auto;
		overflow-x: hidden;
	}

	.receiver {
		background-color: #eaeaea;
		align-self: flex-start;
		margin-left: 10px;
		margin-top: 15px;
		font-size: 18px;
		padding: 10px 14px;
		border-radius: 2px 20px 20px;
	}

	.sender {
		background-color: #d4e1f4;
		align-self: flex-end;
		margin-right: 15px;
		font-size: 18px;
		border-radius: 20px 2px 20px 20px;
		padding: 10px 14px;
		color: white;
		font-weight: 500;
	}

	.chatControll {
		display: flex;
		flex-direction: column;
		align-items: center;
		border-radius: 8px;
		height: 125px;
		border: 1px solid;
		margin: 16px;
	}

	.chatInput {
		margin: 12px 12px 0px;
		border-radius: 5px;
		width: 95%;
		height: 60%;
		outline: none;
		border: none;
	}

	.sendButton {
		border-radius: 4px;
		width: 300px;
		height: 32px;
		line-height: 150%;
		font-weight: 600;
		font-size: 22px;
		background-color: #d4e1f4;
		cursor: pointer;
		border: none;
		color: white;
		margin-bottom: 8px;
	}
</style>
