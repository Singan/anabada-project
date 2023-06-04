<template>
	<div class="chatContainer">
		<div class="oppoInfoBox">
			<span class="oppoInfo">{{ memberName }}님과의 채팅</span>
		</div>
		<div class="chattingMainBox">
			<div class="bubble receiver">안녕하세요~</div>
			<div class="bubble sender">안녕하세요~</div>
			<div class="bubble receiver">아이폰 14pro 상품보고 연락드리는데요</div>
			<div class="bubble receiver">배터리 성능상태가 어느정도 되나요?</div>
			<div class="bubble sender">사용한지 얼마 안되서 상태가 정말 좋아요</div>
			<div class="bubble sender">95퍼 입니다.</div>
		</div>
		<div class="chatController">
			<input class="chatInputBox" />
			<div class="sendButton">
				<img class="sendicon" src="@/assets/send.png" />
			</div>
		</div>
	</div>
</template>
<script>
	export default {
		props: {
			memberName: {
				type: String,
			},
			memberNo: {
				type: Number,
			},
			SuccessNo: {
				type: Number,
			},
		},
	};
</script>
<style scoped>
	.chatContainer {
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
	}
	.oppoInfoBox {
		background: #ffffff;
		width: 500px;
		height: 60px;
		box-shadow: 0px 5px 4px 0px rgba(209, 209, 209, 0.25);
		text-align: center;
		position: relative;
		top: 40px;
	}

	.oppoInfo {
		color: #000000;
		text-align: center;
		font: 400 20px 'Roboto', sans-serif;
		position: relative;
		margin-bottom: 50px;
		top: 20px;
	}
	.chattingMainBox {
		display: flex;
		flex-direction: column;
		align-items: flex-end;
		background-color: white;
		margin-top: 60px;
		width: 500px;
		height: 550px;
		overflow-y: auto;
	}

	.chatController {
		display: flex;
		justify-content: flex-start;
		align-items: center;
		background-color: white;
		width: 500px;
		box-shadow: 0px -5px 4px 0px rgba(209, 209, 209, 0.25);
		height: 80px;
		text-align: center;
	}

	.chatInputBox {
		background: #d9ecff;
		border-radius: 5px;
		width: 420px;
		height: 40px;
		margin-left: 10px;
		position: relative;
		border: none;
	}
	.sendButton {
		background: #0075ff;
		width: 40px;
		height: 40px;
		position: relative;
		border-radius: 5px;
		margin: 10px;
	}
	.sendicon {
		width: 30px;
		height: 30px;
		margin: 5px;
	}
	.bubble {
		display: inline-block;
		max-width: 75%;
		margin-top: 15px;
		padding: 10px 15px;
		border-radius: 20px;
		font-size: 14px;
	}
	.bubble.sender {
		background-color: #eaeaea;
		align-self: flex-end;
		margin-right: 10px;
	}
	.bubble.receiver {
		background-color: #d4e1f4;
		align-self: flex-start;
		margin-left: 10px;
	}
</style>
