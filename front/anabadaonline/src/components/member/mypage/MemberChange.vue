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

				<div class="--5">
					<div class="----">이름</div>
				</div>
			</div>
		</div>

		<div class="----------">
			<div class="rectangle-272"></div>

			<div class="--6">
				<div class="----2">홍길동</div>
			</div>

			<div class="--7">
				<div class="Text">비밀번호 변경</div>
			</div>

			<div class="rectangle-273"></div>

			<div class="----3">현재 비밀번호를 입력하세요</div>

			<div class="--9">
				<div class="Text">주소 변경</div>
			</div>

			<div class="----------2">
				<div class="rectangle-274"></div>

				<div class="--10"></div>

				<div class="-------">경기도 성남시</div>
			</div>

			<div class="----------3">
				<div class="rectangle-275"></div>

				<div class="--11"></div>

				<div class="-----">주소 찾기</div>
			</div>

			<div class="rectangle-276"></div>

			<div class="--12"></div>

			<div class="-------------">새 비밀번호를 입력하세요</div>

			<div class="rectangle-277"></div>

			<div class="--13"></div>

			<div class="------------------">
				새 비밀번호를 한 번더 입력하세요
			</div>

			<div class="--14">
				<div class="rectangle-25"></div>

				<div class="_18px">
					<div class="_18px2">수정 하기</div>
				</div>
			</div>

			<div class="-----2">
				<div class="rectangle-252"></div>

				<div class="_18px3">
					<div class="_18px2" @click="goMypage">취소</div>
				</div>
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

	.changeId {
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

	.--4 {
		width: 487.98px;
		height: 26.67px;
		position: absolute;
		left: 673.2px;
		top: 216.67px;
	}

	.----2 {
		color: #797979;
		text-align: left;
		font: 400 12px 'Roboto', sans-serif;
		position: absolute;
		left: 0px;
		top: 0px;
		width: 487.98px;
		height: 26.67px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.name {
		width: 370px;
		height: 30px;
		position: absolute;
		left: 660px;
		top: 280px;
	}

	.nameBox {
		background: #e1e1e1;
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		width: 630px;
		height: 40px;
		position: absolute;
		left: 660px;
		top: 322px;
	}

	.--6 {
		width: 487.98px;
		height: 26.67px;
		position: absolute;
		left: 673.2px;
		top: 328.67px;
	}

	.--7 {
		width: 370px;
		height: 30px;
		position: absolute;
		left: 660px;
		top: 390px;
	}

	.rectangle-273 {
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		width: 630px;
		height: 40px;
		position: absolute;
		left: 660px;
		top: 430px;
	}

	.--8 {
		width: 487.98px;
		height: 26.67px;
		position: absolute;
		left: 663.2px;
		top: 436.67px;
	}

	.----3 {
		color: #797979;
		text-align: left;
		font: 400 12px 'Roboto', sans-serif;
		position: absolute;
		left: 670px;
		top: 437px;
		width: 487.98px;
		height: 26.67px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.--9 {
		width: 370px;
		height: 30px;
		position: absolute;
		left: 660px;
		top: 620px;
	}

	.----------2 {
		width: 530px;
		height: 30px;
		position: static;
	}

	.rectangle-274 {
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		width: 530px;
		height: 30px;
		position: absolute;
		left: 660px;
		top: 660px;
	}

	.--10 {
		width: 410.53px;
		height: 20px;
		position: absolute;
		left: 662.69px;
		top: 665px;
	}

	.------- {
		color: #797979;
		text-align: left;
		font: 400 12px 'Roboto', sans-serif;
		position: absolute;
		left: 668.41px;
		top: 665.25px;
		width: 410.53px;
		height: 20px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.----------3 {
		width: 70px;
		height: 30px;
		position: static;
	}

	.rectangle-275 {
		background: #0075ff;
		border-radius: 5px;
		width: 70px;
		height: 30px;
		position: absolute;
		left: 1220px;
		top: 660px;
	}

	.--11 {
		background: #0075ff;
		width: 54.22px;
		height: 20px;
		position: absolute;
		left: 1220.36px;
		top: 665px;
	}

	.----- {
		color: #ffffff;
		text-align: center;
		font: 400 12px 'Roboto', sans-serif;
		position: absolute;
		left: 1220px;
		top: 665.25px;
		width: 70px;
		height: 20.25px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.rectangle-276 {
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		width: 630px;
		height: 40px;
		position: absolute;
		left: 660px;
		top: 490px;
	}

	.--12 {
		width: 487.98px;
		height: 26.67px;
		position: absolute;
		left: 663.2px;
		top: 496.67px;
	}

	.------------- {
		color: #797979;
		text-align: left;
		font: 400 12px 'Roboto', sans-serif;
		position: absolute;
		left: 670px;
		top: 497px;
		width: 487.98px;
		height: 26.67px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.rectangle-277 {
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		width: 630px;
		height: 40px;
		position: absolute;
		left: 660px;
		top: 550px;
	}

	.--13 {
		width: 487.98px;
		height: 26.67px;
		position: absolute;
		left: 663.2px;
		top: 556.67px;
	}

	.------------------ {
		color: #797979;
		text-align: left;
		font: 400 12px 'Roboto', sans-serif;
		position: absolute;
		left: 670px;
		top: 557px;
		width: 487.98px;
		height: 26.67px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.--14 {
		width: 630px;
		height: 40px;
		position: static;
	}

	.rectangle-25 {
		background: #0075ff;
		border-radius: 5px;
		width: 630px;
		height: 40px;
		position: absolute;
		left: 650px;
		top: 902px;
	}

	._18px {
		width: 107.23px;
		height: 30px;
		position: absolute;
		left: 911.38px;
		top: 907px;
	}

	._18px2 {
		color: #ffffff;
		text-align: center;
		font: 700 18px 'Roboto', sans-serif;
		position: absolute;
		left: 0px;
		top: 0px;
		width: 107.23px;
		height: 30px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.-----2 {
		width: 630px;
		height: 40px;
		position: static;
	}

	.rectangle-252 {
		background: #ff4545;
		border-radius: 5px;
		width: 630px;
		height: 40px;
		position: absolute;
		left: 650px;
		top: 962px;
	}

	._18px3 {
		width: 107.23px;
		height: 30px;
		position: absolute;
		left: 911.38px;
		top: 967px;
	}
</style>
