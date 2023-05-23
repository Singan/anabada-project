<template>
	<body>
		<div class="chat_list_wrap">
			<div class="chatListContainer">
				<div class="header">채팅 목록</div>
				<div class="list">
					<div class="profile_td">
						<!--Img-->
						<!-- <img src="./images/profile_preview.png" /> -->
					</div>
					<div class="chat_td">
						<!--Email & Preview-->
						<div class="email">kkotkkio@gmail.com</div>
						<div class="chat_preview">안녕하세요~</div>
					</div>
					<div class="time_td">
						<!--Time & Check-->
						<div class="time">2016.09.29 17:54</div>
						<div class="check">
							<!-- <p></p> -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</template>
<style scoped>
	* {
		margin: 0;
		padding: 0;
	}
	body {
		font-size: 12px;
	}
	.chat_list_wrap {
		list-style: none;
		display: flex;
		flex-direction: column;
		align-items: center;
	}

	.chatListContainer {
		width: 70rem;
	}
	.chat_list_wrap .header {
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
		padding: 0 16px;
		display: flex;
		border-bottom: 1px solid#e1e1e1;
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
	.chat_list_wrap .list ul li table {
		width: 100%;
	}
	.profile_td {
		width: 50px;
		padding-right: 11px;
	}
	.chat_list_wrap .list ul li table td.profile_td img {
		width: 50px;
		height: auto;
	}
	.chat_td .email {
		font-size: 12px;
		font-weight: bold;
	}
	.time_td {
		width: 90px;
		text-align: center;
	}
	.chat_preview {
		margin-bottom: 2em;
	}
	.chat_list_wrap .list ul li table td.time_td .time {
		padding-bottom: 4px;
	}
	.chat_list_wrap .list ul li table td.time_td .check p {
		width: 5px;
		height: 5px;
		margin: 0 auto;
		-webkit-border-radius: 50%;
		-moz-border-radius: 50%;
		border-radius: 50%;
		background: #e51c23;
	}
</style>
