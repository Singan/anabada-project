<template>
	<div class="form">
		<div class="flexContainer">
			<div class="chatList">
				<div class="listHeader">채팅 목록</div>

				<div class="chatTarget" @click="chatMessageList(chat)" v-for="chat in chatList" :key="chat.successNo">
					<div class="userImage">
						<img :src="chat.memberImage" />
					</div>
					<div class="userInfo">
						<div class="userInfoBox1">
							<div class="userName">{{ chat.memberName }}</div>
							<div class="region">{{ chat.memberWishAddr }}</div>
						</div>
						<div class="productName">{{ chat.productName }}</div>
					</div>

					<div class="productImage">
						<!--상품 이미지 들어갈자리-->
						<img :src="chat.productThumbnail" />
					</div>
				</div>
			</div>
			<!-- -------------여기부터 채팅관련 들어갈 자리---------------->

			<div v-if="!isClick" class="chatContent2">
				<div class="chatBox"><img src="@/assets/chatBubble.jpg" /></div>
			</div>

			<div class="chatContent justify-content-end" v-if="isClick">
				<div class="userName2">
					{{ selectChat.memberName }}님과의 채팅
					<button type="button" class="btn-close" aria-label="Close" @click="isClick = false"></button>
				</div>
				<div class="productBox">
					<div class="productImage2"><img :src="selectChat.productThumbnail" /></div>
					<div class="productBox2">
						<div class="productName2">상품명 : {{ selectChat.productName }}</div>
						<div class="productPrice">낙찰 가격 : {{ selectChat.bidPrice }}원</div>
					</div>
				</div>

				<!-- 채팅 -->
				<div class="chatting flex-grow-1" ref="messages">
					<div
						class="message"
						:class="[message.memberNo != $store.getters.getMember.no ? 'receiver' : 'sender']"
						v-for="message in messageList"
					>
						<span class="timestamp" v-if="message.memberNo == $store.getters.getMember.no">{{
							message.createDateTime
						}}</span>
						<span class="msg">{{ message.message }}</span>
						<span class="timestamp" v-if="message.memberNo != $store.getters.getMember.no">{{
							message.createDateTime
						}}</span>
					</div>
				</div>

				<!-- 채팅 내용 입력창 + 보내기 버튼 -->
				<div class="chatControll">
					<textarea class="chatInput" v-model="message"> </textarea>
				</div>
				<div class="container-fluid d-flex justify-content-center">
					<button class="sendButton" @click="send">전송</button>
				</div>
			</div>
		</div>
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
			messageList: {
				// 배열 내부를 검사하여, 알려준다.
				deep: true,

				handler() {
					this.$nextTick(() => {
						let messages = this.$refs.messages;
						messages.scrollTo({ top: messages.scrollHeight, behavior: 'smooth' });
					});
				},
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
				selectChat: '',
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

				if (this.isSocket) {
					this.sub();
				}
			},
			async chatMessageList(chat) {
				this.isClick = true;
				this.memberName = chat.memberName;
				this.successNo = chat.successNo;
				this.selectChat = chat;
				const res = await axios.get('/chat/content?successNo=' + chat.successNo);
				this.messageList = res.data;
			},
			async sub() {
				this.chatList.forEach((chat) => {
					this.subList.push(this.socket.subscribe('/chat/' + chat.successNo, this.recevieFunc));
				});
			},
			send() {
				if (this.message == '' || this.message == null) {
					return;
				}
				let obj = {
					message: this.message,
					successNo: this.successNo,
					memberName: this.$store.getters.getMember.name,
					memberImage: this.$store.getters.getMember.image,
					memberNo: this.$store.getters.getMember.no,
				};
				this.socket.send(obj, '/chat');
				this.message = '';
			},
			recevieFunc(res) {
				if (this.successNo == res.successNo) {
					this.messageList.push(res);
				}
			},
		},
	};
</script>

<style scoped>
	.form {
		background-color: #f2f3f6;
		width: 100%;
		height: 100%;
		overflow: auto;
	}
	.message_wrap {
	}
	.flexContainer {
		display: flex;
		flex-direction: row;
		width: 100%;
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
		min-height: 78px;
		width: 100%;
		display: flex;
		align-items: center;
		border: 1.5px solid #eaebee;
		justify-content: center;
		font-size: 20px;
	}

	.productBox {
		min-height: 78px;
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
		justify-content: flex-start;
		margin-left: 10px;
		margin-top: 15px;
	}
	.receiver > .msg {
		background-color: #eaeaea;

		font-size: 18px;
		padding: 10px 14px;
		border-radius: 2px 20px 20px;
	}
	.sender {
		margin-top: 15px;
		justify-content: flex-end;
		color: black;
	}
	.sender > .msg {
		font-size: 18px;
		border-radius: 20px 2px 20px 20px;
		padding: 10px 14px;
		margin-right: 15px;

		font-weight: 500;
		background-color: #d4e1f4;
	}
	.chatControll {
		display: flex;
		flex-direction: column;
		align-items: center;
		border-radius: 8px;
		min-height: 125px;
		border: 1px solid;
		overflow: auto;
		margin: 16px;
	}

	.chatInput {
		margin: 12px 12px 0px;
		border-radius: 5px;
		width: 95%;
		height: 100%;
		overflow-y: auto;
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
	.msg {
		/* width: max-content; */
		word-break: break-all;
		white-space: pre-wrap;
		max-width: 75%;
		display: inline-block;
	}
	.chatting {
		display: flex;
		flex-direction: column;
	}

	.message_wrap {
		display: flex;
		flex-direction: column;
		align-items: flex-start;
	}

	.message {
		display: flex;
		padding: 5px;
		margin-bottom: 10px;
		max-width: 100%;
		border-radius: 5px;
	}

	.timestamp {
		font-size: 12px;
		color: #999999;
		margin-top: 5px;
		align-self: flex-end;
	}
	.sender .timestamp {
		text-align: left;
	}

	.receiver .timestamp {
		text-align: left;
	}
	.sender > .msg {
		text-align: left;
	}
</style>
