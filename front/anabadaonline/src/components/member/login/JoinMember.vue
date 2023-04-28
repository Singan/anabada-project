<template>
	<div class="flexContainer">
		<form @submit.prevent="submitForm">
			<div class="text">본인 정보를 입력해 주세요</div>
			<div class="id">
				<label for="id">아이디</label>
				<input type="text" id="id" v-model="id" />
			</div>
			<div class="pw">
				<label for="pw">비밀번호</label>
				<input type="password" id="pw" v-model="pw" />
			</div>
			<div class="name">
				<label for="name">이름</label>
				<input type="text" id="name" v-model="name" />
			</div>
			<div class="birth">
				<label for="birth">생년월일</label>
				<input type="date" id="birth" v-model="birth" />
			</div>
			<div class="post">
				<label for="addr">우편번호 찾기</label>
				<input type="text" id="post" v-model="addr" readonly />
				<button @click="search()">찾기</button>
			</div>
			<div class="addr">
				<label for="addr">주소</label>
				<input type="text" id="addr" v-model="addr" />
			</div>
			<div class="detailaddr">
				<label for="detailaddr">상세주소</label>
				<input type="text" id="detailaddr" v-model="detailaddr" />
			</div>
			<div class="Wishaddr">
				<label for="Wishaddr">거래희망지</label>
				<input type="text" id="Wishaddr" v-model="Wishaddr" />
			</div>
			<div class="image">
				<label for="image">프로필 이미지</label>
				<input id="image" type="file" @change="onInputImage" />
			</div>

			<button type="submit" class="join">회원가입</button>
			<button class="cancel" @click="goLogin">취소하기</button>
		</form>
	</div>
</template>

<script>
	import axios from '@/axios';
	export default {
		name: 'SignupForm',
		data() {
			return {
				id: '',
				pw: '',
				name: '',
				birth: '',
				detailaddr: '',
				addr: '',
				Wishaddr: '',
				image: '',
			};
		},
		methods: {
			submitForm() {
				let form = new FormData();
				form.append('id', this.id);
				form.append('pw', this.pw);
				form.append('name', this.name);
				// form.append("birth",this.birth)
				form.append('detailAddr', this.detailaddr);
				form.append('addr', this.addr);
				form.append('wishAddr', this.Wishaddr);

				axios
					.post('/member', form, {
						header: { 'Content-Type': 'multipart/form-data' },
					})
					.then((response) => {
						console.log(response);
						if (response.status == 200) {
							// 로그인 하면 토큰 발급
							this.$router.push('./login');
						}
					});
			},
			onInputImage(e) {
				this.image = e.target.files[0];
			},
			goLogin() {
				this.$router.push('./login');
			},
		},

		name: 'Address',
		methods: {
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
						if (
							data.bname !== '' &&
							/[동|로|가]$/g.test(data.bname)
						) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (
							data.buildingName !== '' &&
							data.apartment === 'Y'
						) {
							extraRoadAddr +=
								extraRoadAddr !== ''
									? ', ' + data.buildingName
									: data.buildingName;
						}
						// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('post').value = data.zonecode;
						document.getElementById('addr').value = roadAddr;
						document.getElementById('detailaddr').value =
							data.jibunAddress;

						// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
						if (roadAddr !== '') {
							document.getElementById('extraAddress').value =
								extraRoadAddr;
						} else {
							document.getElementById('extraAddress').value = '';
						}

						var guideTextBox = document.getElementById('guide');
						// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
						if (data.autoRoadAddress) {
							var expRoadAddr =
								data.autoRoadAddress + extraRoadAddr;
							guideTextBox.innerHTML =
								'(예상 도로명 주소 : ' + expRoadAddr + ')';
							guideTextBox.style.display = 'block';
						} else if (data.autoJibunAddress) {
							var expJibunAddr = data.autoJibunAddress;
							guideTextBox.innerHTML =
								'(예상 지번 주소 : ' + expJibunAddr + ')';
							guideTextBox.style.display = 'block';
						} else {
							guideTextBox.innerHTML = '';
							guideTextBox.style.display = 'none';
						}
					},
				}).open();
			},
		},
	};
	// };
</script>

<style scoped>
	input:focus {
		outline: 0;
	}
	.id {
		color: #000000;
		text-align: left;
		font: 600 16px 'Roboto', sans-serif;
		position: absolute;
		top: 211px;
		height: 40px;
		width: 30%;
		left: 34%;
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
	}

	#id {
		font: 400 16px 'Roboto', sans-serif;
		border-right: 0;
		border-top: 0;
		border-left: 0;
		padding-top: 3%;
	}

	.text {
		color: #0075ff;
		text-align: left;
		font: 700 28px 'Roboto', sans-serif;
		position: absolute;
		top: 150px;
		width: 80%;
		height: 37px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.pw {
		color: #000000;
		text-align: left;
		font: 600 16px 'Roboto', sans-serif;
		position: absolute;
		top: 273px;
		width: 30%;
		left: 34%;
		height: 37px;
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		padding-top: 15px;
	}

	#pw {
		font: 400 16px 'Roboto', sans-serif;
		border-right: 0;
		border-top: 0;
		border-left: 0;
		padding-top: 3%;
	}

	.name {
		color: #000000;
		text-align: left;
		font: 600 16px 'Roboto', sans-serif;
		position: absolute;
		top: 337px;
		height: 37px;
		width: 10%;
		left: 34%;
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		padding-top: 30px;
	}

	#name {
		font: 400 16px 'Roboto', sans-serif;
		border-right: 0;
		border-top: 0;
		border-left: 0;
		padding-top: 3%;
	}

	.birth {
		color: #000000;
		text-align: left;
		font: 600 16px 'Roboto', sans-serif;
		position: absolute;
		top: 401px;
		width: 10%;
		left: 34%;
		height: 37px;
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		padding-top: 30px;
	}

	.post {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		position: absolute;
		top: 465px;
		width: 500px;
		left: 34%;
		display: flex;
		padding-top: 25px;
	}

	#post {
		padding-left: 20px;
	}

	.addr {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		position: absolute;
		left: 648px;
		top: 530px;
		width: 10%;
		left: 34%;
		height: 37px;
		display: flex;
		justify-content: flex-start;
	}

	.detailaddr {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		position: absolute;
		left: 648px;
		top: 600px;
		width: 398px;
		height: 37px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.Wishaddr {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		position: absolute;
		left: 648px;
		top: 593px;
		width: 398px;
		height: 37px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.image {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		position: absolute;
		left: 648px;
		top: 657px;
		width: 398px;
		height: 37px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.join {
		width: 8.5rem;
		height: 50px;
		line-height: 50px;
		background-color: white;
		border-radius: 5px;
		margin: 0 auto;
		color: #0075ff;
		position: absolute;
		left: 648px;
		top: 727px;
	}

	.cancel {
		width: 8.5rem;
		height: 50px;
		line-height: 50px;
		background-color: white;
		border-radius: 5px;
		margin: 0 auto;
		color: #e20303;
		position: absolute;
		left: 1000px;
		top: 727px;
	}
</style>
