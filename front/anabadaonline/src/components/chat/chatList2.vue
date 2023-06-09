<template>
	<div class="form">
		<div class="flexContainer">
			<div class="chatList"></div>
			<div class="chatContent"></div>
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
		height: 100vh;
	}

	.flexContainer {
		display: flex;
		flex-direction: row;
		overflow: hidden;
	}

	.chatList {
		display: flex;
		flex-direction: column;
	}
	.chatContent {
		display: flex;
		flex-direction: column;
	}
</style>
