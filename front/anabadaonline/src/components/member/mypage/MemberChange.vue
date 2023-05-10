<template>
	<div class="changeHeader">
		<div class="changeContainer">
			<div class="change">
				<div class="changeHeaderText">회원 정보 수정</div>
			</div>
		</div>

		<div class="changeIdAndName">
			<div class="change">
				<div class="changeId">아이디</div>
				<div class="idBox" readonly>
					<span class="id">aaa</span>
				</div>

				<div class="changeName">이름</div>
				<div class="nameBox" readonly>
					<span class="name">홍길동</span>
				</div>
			</div>
		</div>

		<div class="changePwAndPwCheck">
			<div class="change">
				<div class="changePw">비밀번호 변경</div>
				<input id="beforePw" type="text" class="pwBox" placeholder="기존 비밀번호를 입력하세요" />
				<input id="beforePw" type="text" class="pwBox" placeholder="새 비밀번호를 입력하세요" />
				<input id="beforePw" type="text" class="pwBox" placeholder="새 비밀번호를 한번 더 입력하세요" />
			</div>
		</div>

		<div class="changeAddr">
			<div class="change">
				<div class="changeAddrText">주소 변경</div>
			</div>
		</div>
		<div class="changeAddrContainer">
			<input class="addrBox" readonly />
			<button class="findAddrButton">주소 찾기</button>
		</div>

		<div class="changeAddr">
			<div class="change">
				<div class="changeAddrText">상세 주소 변경</div>
				<input class="addrDetailBox" />
			</div>
		</div>

		<div class="yesAndNoButton">
			<div class="change">
				<button class="checkButton">수정하기</button>
				<button class="noButton">취소하기</button>
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
	.changeContainer {
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.change {
		width: 700px;
	}

	.changeIdAndName {
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.changeHeaderText {
		color: #0075ff;
		font: 700 28px 'Roboto', sans-serif;
	}

	.changeId .changeName .changePw .changeAddrText {
		color: #000000;
		font: 400 16px 'Roboto', sans-serif;
	}

	.idBox {
		background: #e1e1e1;
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		height: 40px;
	}

	.id {
		margin-left: 10px;
		color: #797979;
		font: 400 14px 'Roboto', sans-serif;
		position: relative;
		top: 7px;
	}

	.nameBox {
		background: #e1e1e1;
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		height: 40px;
	}

	.name {
		margin-left: 10px;
		color: #797979;
		font: 400 14px 'Roboto', sans-serif;
		position: relative;
		top: 7px;
	}

	.changePwAndPwCheck {
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.pwBox {
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		height: 40px;
		width: 693px;
	}

	.addrBox {
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		height: 40px;
		width: 600px;
		margin-right: 25px;
	}

	.addrDetailBox {
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		height: 40px;
		width: 693px;
	}

	.changeAddr {
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.changeAddrContainer {
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.yesAndNoButton {
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.findAddrButton {
		background: #0075ff;
		border-radius: 5px;
		width: 70px;
		height: 40px;
		border: none;
		color: white;
		cursor: pointer;
	}

	.checkButton {
		border-radius: 5px;
		border-style: solid;
		background-color: #0075ff;
		border-width: 1.5px;
		width: 700px;
		height: 40px;
		border: none;
		color: white;
		font: 500 16px 'Roboto', sans-serif;
		cursor: pointer;
	}

	.noButton {
		border-radius: 5px;
		border-style: solid;
		background-color: #ff4545;
		border-width: 1.5px;
		width: 700px;
		height: 40px;
		border: none;
		color: white;
		font: 500 16px 'Roboto', sans-serif;
		cursor: pointer;
	}
</style>
