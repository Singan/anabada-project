<template>
	<div class="title">회원 탈퇴</div>

	<div class="flex">
		<div class="explan">회원 탈퇴를 위해 비밀번호를 입력해주세요</div>

		<div class="pwBox">
			<div class="pw-re">
				<input class="pwText" type="password" placeholder="비밀번호를 입력하세요" />
			</div>
		</div>

		<div class="exitBox">
			<div class="blueBox">탈퇴 하기</div>
		</div>

		<a href="./MyPage.vue" class="cancelBox">
			<div class="redBox">취소 하기</div>
		</a>
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
		margin: 30px auto;
	}

	.explan {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		width: 315px;
		height: 30px;
		align-items: center;
		justify-content: flex-start;
		margin-top: 30px;
	}

	.pw-re {
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		margin-top: 20px;
		width: 630px;
		height: 45px;
	}

	.pwBox {
		width: 630px;
		height: 26.67px;
		left: 663.2px;
		top: 264.67px;
		display: flex;
		justify-items: center;
	}

	.pwText {
		color: #797979;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		width: 487.98px;
		height: 40px;
		display: flex;
		align-items: center;
		border: none;
		border-radius: none;
		outline: none;
	}

	.exitBox {
		width: 630px;
		height: 40px;
		margin-top: 300px;
		margin-bottom: 10px;
		display: flex;
		background: #0075ff;
		border-radius: 5px;
		justify-content: center;
	}

	.blueBox {
		color: #ffffff;
		height: 40px;
		line-height: 40px;
		text-align: center;
		align-items: center;
	}

	.cancelBox {
		width: 630px;
		height: 40px;
		background: #ff4545;
		border-radius: 5px;
		display: flex;
	}

	.redBox {
		color: #ffffff;
		width: 630px;
		height: 40px;
		line-height: 40px;
		text-align: center;
		align-items: center;
	}
</style>
