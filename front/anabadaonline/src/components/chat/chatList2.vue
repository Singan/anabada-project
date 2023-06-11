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
				<div class="chatBox"><img src="@/assets/chatBubble.jpg" /></div>
			</div>
			<div class="chatContent2" v-if="showConversation">
				<div class="userName2">XX님과의 채팅</div>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				showConversation: false,
			};
		},

		methods: {
			showchatContent2() {
				this.showConversation = true;
			},
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
	}

	.chatContent2 {
		display: flex;
		flex-direction: column;
	}

	.listHeader {
		font-size: 16px;
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
		margin-left: auto;
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
		font-size: 18px;
	}
</style>
