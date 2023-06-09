<template>
	<div class="form">
		<div class="flexContainer">
			<div class="chatList">
				<div class="listHeader">채팅 목록</div>
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
</style>
