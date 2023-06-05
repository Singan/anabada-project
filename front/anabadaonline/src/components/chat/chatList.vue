<template>
	<div class="chattingPageContainer">
		<div class="chat_list_wrap">
			<div class="chatListContainer">
				<div class="header">채팅 목록</div>
				<div class="list" v-for="chat in chatList" :key="chat.successNo" @click="chatMessageList(chat)">
					<div class="profile_td">
						<img :src="chat.memberImage" />
					</div>
					<div class="chat_td">
						<div class="email">
							<a class="emailText" target="chatting">{{ chat.memberName }}</a>
						</div>
						<div class="chat_preview" v-if="chat.lastMessage != null">{{ chat.lastMessage }}</div>
					</div>
					<div class="time_td" v-if="chat.lastMessage != null">
						<div class="time">{{ chat.lastMessageTime }}</div>
						<div class="check"></div>
					</div>
				</div>
			</div>
		</div>
		<template v-if="isClick">
			<div class="chatContainer">
				<div class="oppoInfoBox">
					<span class="oppoInfo">{{ memberName }}님과의 채팅</span>
				</div>
				<div class="chattingMainBox">
					<div
						class="bubble"
						:class="[message.memberNo == $store.getters.getMember.memberNo ? 'receiver' : '', 'sender']"
						v-for="message in messageList"
					>
						{{ message.message }}
					</div>
				</div>
				<div class="chatController">
					<input class="chatInputBox" v-model="message" />
					<div class="sendButton" @click="send">
						<img class="sendicon" src="@/assets/send.png" />
					</div>
				</div>
			</div>
		</template>
	</div>
</template>
<script>
	import axios from '@/axios';
	export default {
		inject: ['socket'],
		watch: {
			isSocket: function (isSocket) {
				if (isSocket) {
					console.log('와치');
					this.sub();
				}
			},
		},
		props: {
			isSocket: {
				type: Boolean,
			},
		},
		data() {
			// quickfix to have components available to pass as props
			return {
				myData: '',
				chatList: [],
				isClick: false,
				memberName: '',
				memberNo: '',
				successNo: '',
				message: '',
				subList: [],
				messageList: [],
			};
		},
		mounted() {
			this.getMyChatList();
		},
		updated() {},
		methods: {
			async getMyChatList() {
				const response = await axios.get('/chat/list');
				this.chatList = response.data;
				console.log(this.isSocket);
				console.log(this.socket);
				if (this.isSocket) {
					console.log('소켓 서브스크라이브');
					this.sub();
				}
			},
			async chatMessageList(chat) {
				console.log('데이터 가져오기');
				this.isClick = true;
				this.memberName = chat.memberName;
				this.successNo = chat.successNo;
				const res = await axios.get('/chat/content?successNo=' + chat.successNo);
				this.messageList = res.data;
				console.log(this.messageList);
			},
			async sub() {
				this.chatList.forEach((chat) => {
					this.subList.push(this.socket.subscribe('/chat/' + chat.successNo, this.recevieFunc));
				});
			},
			send() {
				let obj = {
					message: this.message,
					successNo: this.successNo,
					memberName: this.$store.getters.getMember.name,
					memberImage: this.$store.getters.getMember.image,
					memberNo: this.$store.getters.getMember.memberNo,
				};
				this.socket.send(obj, '/chat');
			},
			recevieFunc(res) {
				if (this.successNo == res.successNo) {
					this.messageList.push(res);
				}
			},
		},
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
