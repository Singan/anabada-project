<template>
	<div class="title">회원 탈퇴</div>

	<div class="flex">
		<div class="explan">회원 탈퇴를 위해 비밀번호를 입력해주세요</div>

		<div class="pwBox">
			<div class="pw-re">
				<div class="pwText">비밀번호를 입력하세요</div>
			</div>
		</div>

		<div class="exitBox">
			<div class="_18px"></div>
			<div class="blueBox">탈퇴 하기</div>
		</div>

		<div class="cancelBox">
			<div class="_18px3">
				<div class="buttonText"></div>
			</div>
			<div class="redBox">취소 하기</div>
		</div>
	</div>
</template>
<script>
	/* Code generated with AutoHTML Plugin for Figma */

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
	.flex {
		background: #ffffff;
		width: 700px;
		overflow: hidden;
		display: flex;
		flex-direction: column;
		flex-wrap: wrap;
		padding: auto;
		margin: auto auto;
	}

	.title {
		color: #0075ff;
		text-align: center;
		font: 700 28px 'Roboto', sans-serif;
		margin: auto;
	}

	.explan {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		width: 315px;
		height: 30px;
		align-items: center;
		justify-content: flex-start;
		margin-top: 80px;
	}

	.pw-re {
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		width: 630px;
		height: 40px;
	}

	.pwBox {
		width: 487.98px;
		height: 26.67px;
		left: 663.2px;
		top: 264.67px;
		display: flex;
	}

	.pwText {
		color: #797979;
		text-align: left;
		font: 400 12px 'Roboto', sans-serif;

		left: 0px;
		top: 0px;
		width: 487.98px;
		height: 26.67px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.exitBox {
		width: 630px;
		height: 40px;
		margin-top: 300px;
		display: flex;
	}

	.blueBox {
		background: #0075ff;
		border-radius: 5px;
		width: 630px;
		height: 40px;

		left: 660px;
		top: 862px;
	}

	._18px {
		width: 107.23px;
		height: 30px;

		left: 921.38px;
		top: 867px;
	}

	.cancelBox {
		width: 630px;
		height: 40px;

		display: flex;
	}

	.redBox {
		background: #ff4545;
		border-radius: 5px;
		width: 630px;
		height: 40px;

		left: 660px;
		top: 922px;
	}

	._18px3 {
		width: 107.23px;
		height: 30px;

		left: 921.38px;
		top: 927px;
	}
</style>
