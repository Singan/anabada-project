<template>
	<body>
		<div class="chattingPageContainer">
			<div class="chat_list_wrap">
				<div class="chatListContainer">
					<div class="header">채팅 목록</div>
					<div class="list">
						<div class="profile_td">
							<!--Img-->
							<img :src="this.$store.getters.getMember.image" />
						</div>
						<div class="chat_td">
							<!--Email & Preview-->
							<div class="email">
								<a class="emailText" target="chatting">kotkkio@gmail.com</a>
							</div>
							<div class="chat_preview">안녕하세요~</div>
						</div>
						<div class="time_td">
							<!--Time & Check-->
							<div class="time">2016.09.29 17:54</div>
							<div class="check"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="chatContainer">
				<div class="oppoInfoBox">
					<span class="oppoInfo">xxx님과의 채팅</span>
				</div>
				<div class="chattingMainBox">
					<div class="bubble receiver">안녕하세요~</div>
					<div class="bubble sender">
						나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는
						빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~
					</div>
					<div class="bubble receiver">안녕하세요~</div>
					<div class="bubble sender">
						나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는
						빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~
					</div>
					<div class="bubble receiver">안녕하세요~</div>
					<div class="bubble sender">
						나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는
						빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~
					</div>
					<div class="bubble receiver">안녕하세요~</div>
					<div class="bubble sender">
						나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는
						빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~나는 빡빡이다~
					</div>
				</div>
				<div class="chatController">
					<input class="chatInputBox" />
					<div class="sendButton">
						<img class="sendicon" src="@/assets/send.png" />
					</div>
				</div>
			</div>
		</div>
	</body>
</template>
<script>
	import axios from '@/axios';
	export default {
		data() {
			// quickfix to have components available to pass as props
			return {
				myData: '',
			};
		},
		methods: {},
	};
	// const chatContainer = document.getElementById('chat-container');
	// function scrollChatToBottom() {
	// 	chatContainer.scrollTop = chatContainer.scrollHeight;
	// } //채팅이 새로 왔을때 채팅창을 맨 밑으로 내리는 함수
	// scrollChatToBottom(); //채팅이 새로 왔을때 채팅창을 맨 밑으로 내리는 함수 호출
</script>
<style scoped>
	.chattingPageContainer {
		display: flex;
		width: 100%;
		align-items: flex-start;
		justify-content: center;
		margin: auto;
	}
	.chat_list_wrap {
		width: 500px;
		list-style: none;
		display: flex;
		align-content: flex-start;
		flex-direction: column;
		margin-top: 12px;
	}

	.chatListContainer {
		display: flex;
		flex-direction: column;
		width: 95%;
	}
	.chat_list_wrap .header {
		margin-top: 2em;
		font-size: 14px;
		padding: 15px 0;
		background: #0075ff;
		color: white;
		text-align: center;
		font-family: 'Roboto', sans-serif;
	}
	.chat_list_wrap {
		padding: 5px;
	}

	.list {
		margin-top: 2em;
		width: 450px;
		padding: 0 10px;
		display: flex;
		border-bottom: 1px solid#e1e1e1;
		justify-items: center;
	}
	.chat_list_wrap .list ul {
		width: 100%;
		list-style: none;
		margin-top: 3px;
	}
	.chat_list_wrap .list ul li {
		padding-top: 2em;
		padding-bottom: 2em;
		border-bottom: 1px solid #e5e5e5;
	}

	.profile_td {
		width: 50px;
		padding-right: 11px;
	}
	.chat_list_wrap .profile_td img {
		width: 50px;
		height: auto;
	}
	.chat_td .email {
		font-size: 12px;
		font-weight: bold;
		cursor: pointer;
	}
	.emailText {
		color: inherit;
	}
	.time_td {
		margin-left: 175px;
		width: 90px;
		text-align: center;
	}
	.chat_preview {
		margin-bottom: 2em;
	}

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
		flex-direction: column-reverse;
		align-items: flex-end;
		flex-wrap: wrap;
		background-color: white;
		width: 500px;
		height: 500px;
		overflow-y: scroll;
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
		max-width: 70%;
		margin-bottom: 10px;
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
