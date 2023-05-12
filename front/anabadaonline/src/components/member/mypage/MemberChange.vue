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
					<span class="id">{{ myData.memberId }}</span>
				</div>

				<div class="changeName">이름</div>
				<div class="nameBox" readonly>
					<span class="name">{{ myData.memberName }}</span>
				</div>
			</div>
		</div>

		<div class="changeImg">
			<div class="change">
				<div class="changeImgText">프로필 이미지 변경</div>
				<div class="imagePreView">
					<img id="img" />
				</div>
				<label class="imageChangeSelect" for="productImages">
					이미지 등록
					<input
						id="productImages"
						type="file"
						@change="onInputImage"
						accept="image/*"
						multiple="multiple"
						style="width: 0px; height: 0px"
					/>
				</label>
			</div>
		</div>
		<form>
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
				<input class="addrBox" readonly v-model="myData.memberAddr" />
				<button class="findAddrButton" type="button" @click="search()">주소 찾기</button>
			</div>

			<div class="changeAddr">
				<div class="change">
					<div class="changeAddrText">상세 주소 변경</div>
					<input class="addrDetailBox" />
				</div>
			</div>

			<div class="changeWishAddr">
				<div class="change">
					<div class="changeAddrText">거래 희망지 변경</div>
					<input class="addrDetailBox" />
				</div>
			</div>

			<div class="yesAndNoButton">
				<div class="change">
					<button class="checkButton">수정하기</button>
					<button class="noButton" onclick="history.go(-1)">취소하기</button>
				</div>
			</div>
		</form>
	</div>
</template>
<script>
	import axios from '@/axios';
	import { all } from 'axios';
	export default {
		name: '',
		components: {},
		props: {},
		data() {
			// quickfix to have components available to pass as props
			return {
				addr: '',
				myData: '',
			};
		},
		methods: {
			onInputImage(e) {
				this.productImages = e.target.files;
				console.log(this.productImages);

				//이미지 미리보기
				const image = document.getElementById('img');
				const reader = new FileReader();
				reader.onload = (e) => {
					image.src = e.target.result;
					image.width = 120;
					image.height = 120;
					document.querySelector('.imagePreView').style.backgroundColor = 'white';
				};
				reader.readAsDataURL(this.productImages.item(0));
			},
			search() {
				//@click을 사용할 때 함수는 이렇게 작성해야 한다.
				new window.daum.Postcode({
					oncomplete: (data) => {
						//function이 아니라 => 로 바꿔야한다.
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var roadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 참고 항목 변수

						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName;
						}
						// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						// document.getElementById('post').value = data.zonecode;
						this.addr = roadAddr;
						// document.getElementById('detailaddr').value =
						// 	data.jibunAddress;
					},
				}).open();
			},
			getMypage() {
				axios.get('member/idNameImage').then((res) => {
					this.myData = res.data;
					console.log(this.myData);
				});
			},
		},
		mounted() {
			this.getMypage();
		},
		components: { all },
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
		margin-top: 40px;
	}

	.changeHeaderText {
		color: #0075ff;
		font: 700 28px 'Roboto', sans-serif;
		margin-top: 40px;
	}

	.changeId .changeName .changePw .changeAddrText .changeImgText {
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
		margin-bottom: 20px;
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

	.changeImg {
		display: flex;
		justify-content: center;
		align-items: center;
		margin-top: 40px;
	}

	.imagePreView {
		background: #d9d9d9;
		width: 120px;
		height: 120px;
		margin-top: 10px;
	}

	.imageChangeSelect {
		background: #0075ff;
		border-radius: 5px;
		width: 70px;
		height: 40px;
		border: none;
		color: white;
		cursor: pointer;
		text-align: center;
		cursor: pointer;
	}

	.changePwAndPwCheck {
		display: flex;
		justify-content: center;
		align-items: center;
		margin-top: 40px;
	}

	.pwBox {
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		height: 40px;
		width: 693px;
		margin-bottom: 10px;
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
		margin-top: 30px;
	}

	.changeAddrContainer {
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.changeWishAddr {
		display: flex;
		justify-content: center;
		align-items: center;
		margin-top: 30px;
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
		margin-top: 80px;
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
		margin-top: 10px;
		margin-bottom: 90px;
	}
</style>
