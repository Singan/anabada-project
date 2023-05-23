<template>
	<div class="chatBackground">
		<div class="chatContainer">
			<div class="oppoInfoBox">
				<span class="oppoInfo">xxx님과의 채팅</span>
			</div>
			<div class="chattingMainBox"></div>
			<div class="chatController">
				<input class="chatInputBox" />
			</div>
		</div>
	</div>
</template>
<script>
	export default {
		name: '',
		components: {},
		props: {},
		data() {
			// quickfix to have components available to pass as props
			return {};
		},
	};
</script>
<style scoped>
	.chatBackground {
		background: #f1f1f1;
		width: 100%;
		height: 100%;
		position: relative;
	}

	.chatContainer {
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
	}

	.oppoInfoBox {
		background: #ffffff;
		width: 1200px;
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
		top: 20px;
	}

	.chattingMainBox {
		background-color: white;
		overflow: hidden;
		width: 1200px;
		height: 500px;
	}

	.chatController {
		background-color: white;
		width: 1200px;
		box-shadow: 0px -5px 4px 0px rgba(209, 209, 209, 0.25);
		height: 80px;
		text-align: center;
	}

	.chatInputBox {
		background: #d9ecff;
		border-radius: 5px;
		width: 580px;
		height: 40px;
		position: relative;
		border: none;
		top: 15px;
	}

	.chatSendButton {
		background: #0075ff;
		border-radius: 5px;
		width: 60px;
		height: 40px;
	}

	.myChattingBox {
		background: #d9ecff;
		width: 260px;
		height: 40px;
	}

	.oppoChattingBox {
		background: #1b83ff;
		width: 260px;
		height: 40px;
	}
</style>
