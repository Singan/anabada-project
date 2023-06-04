<template>
	<div class="chattingPageContainer">
		<div class="chat_list_wrap">
			<div class="chatListContainer">
				<div class="header">채팅 목록</div>
				<div
					class="list"
					v-for="chat in chatList"
					:key="chat.successNo"
					@click="(isClick = true), (memberName = chat.memberName), (successNo = chat.successNo)"
				>
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
		<ChatContainer v-if="isClick" :memberNo="memberNo" :memberName="memberName"></ChatContainer>
	</div>
</template>
<script>
	import axios from '@/axios';
	import ChatContainer from '@/components/chat/ChatContainer.vue';
	export default {
		components: { ChatContainer },
		data() {
			// quickfix to have components available to pass as props
			return {
				myData: '',
				chatList: [],
				isClick: false,
				memberName: '',
				memberNo: '',
				successNo: '',
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
				console.log(this.chatList);
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
</style>
