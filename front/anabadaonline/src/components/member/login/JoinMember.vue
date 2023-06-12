<template>
	<div class="flexContainer">
		<h1 class="anabadaLogo"></h1>
		<form class="formSection needs-validation" novalidate @submit.prevent="submitForm">
			<div class="mb-3">
				<label for="formFileSm" class="form-label imageText">프로필 이미지 선택</label>
				<input class="form-control form-control-sm" id="formFileSm" type="file" @change="onInputImage" />
			</div>
			<div class="formList1">
				<div class="form_item id">
					<!-- <label for="id"></label> -->
					<input type="text" class="input" maxlength="15" :placeholder="idPlaceholder" v-model="id" />
				</div>
				<div class="form_item pw">
					<!-- <label for="pw">비밀번호</label> -->
					<input
						type="password"
						class="input"
						v-model="pw"
						maxlength="20"
						placeholder="비밀번호(8~15자 대소문자 특수문자 포함)"
					/>
					<div class="invalid-feedback">비밀번호를 다시 입력하세요</div>
				</div>
			</div>
			<div class="formList2">
				<div class="form_item name">
					<input type="text" class="input" v-model="name" maxlength="10" placeholder="이름" />
				</div>
				<div class="form_item birth">
					<input type="text" class="input" id="birth" placeholder="생년월일" maxlength="8" v-model="birth" />
				</div>
			</div>
			<div class="formList3">
				<div class="form_item addr">
					<input type="text" class="input" v-model="addr" placeholder="주소" readonly />
					<div class="post">
						<button type="button" class="btn btn-light btn-sm" @click="search()">주소 찾기</button>
					</div>
				</div>
				<div class="form_item detailaddr">
					<input type="text" id="detailaddr" class="input" v-model="detailaddr" placeholder="상세주소" />
				</div>
				<div class="form_item wishaddr">
					<!-- <label for="wishaddr">거래희망지</label> -->
					<input
						type="text"
						id="wishaddr"
						class="input"
						v-model="Wishaddr"
						placeholder="거래희망지 필수입니다."
					/>
				</div>
			</div>

			<div class="imageContainer">
				<button type="submit" class="join btn btn-primary">회원가입</button>
				<button class="cancel btn btn-danger" @click="goLogin">취소하기</button>
			</div>
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
				image: null,
				idPlaceholder: '아이디',
				pwPlaceholder: '비밀번호(8~15자 대소문자 특수문자 포함)',
			};
		},
		methods: {
			submitForm() {
				let form = new FormData();
				form.append('id', this.id);
				form.append('pw', this.pw);
				form.append('name', this.name);
				form.append('birth', this.birth);
				form.append('detailAddr', this.detailaddr);
				form.append('addr', this.addr);
				form.append('wishAddr', this.Wishaddr);
				if (this.image != null) {
					form.append('image', this.image);
				}

				axios
					.post('/member', form, {
						header: { 'Content-Type': 'multipart/form-data' },
					})
					.then((response) => {
						if (response.status == 200) {
							this.$swal({
								icon: 'success',
								title: '회원가입에 성공했습니다!',
							});
							// 로그인 하면 토큰 발급
							this.$router.push('./login');
						} else {
							alert('잘못된 정보입니다.');
						}
					})
					.catch((error) => {
						let message = error.response.data.message;
						console.log(error.response);
						if (error.status == 400) {
						} else {
							this.$swal({
								icon: 'error',
								title: '회원가입을 실패하였습니다.',
								text: message,
							});
						}
					});
			},
			onInputImage(e) {
				this.image = e.target.files[0];
			},
			goLogin() {
				this.$router.push('./login');
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
		},
	};
	// };
</script>

<style scoped>
	input:focus {
		outline: 0;
	}

	.flexContainer {
		display: flex;
		flex-direction: column;
		position: relative;
		height: 100%;
		width: 500px;
		margin: 0 auto;
	}

	.anabadaLogo {
		padding: 25px 120px 25px 20px;
	}

	.formSection {
		margin-right: 20px;
		margin-left: 20px;
		padding-right: 0;
		padding-left: 0;
	}

	.form_item {
		display: flex;
		align-items: center;
		position: relative;
		box-sizing: border-box;
		max-width: 100%;
		min-height: 50px;
		padding: 0 10px 0 45px;
	}

	.input {
		flex: 1 1 auto;
		box-sizing: border-box;
		width: 100%;
		font-size: 16px;
		line-height: 22px;
		color: #222;
		border: none;
	}

	.formList1 {
		margin-bottom: 20px;
		padding-top: 2px;
		margin-top: 20px;
		border: 0.5px solid #00a5ff;
		border-radius: 6px;
		display: block;
	}

	.formList2 {
		margin-bottom: 20px;
		padding-top: 2px;
		border: 0.5px solid #00a5ff;
		border-radius: 6px;
		display: block;
	}

	.formList3 {
		margin-bottom: 20px;
		padding-top: 2px;
		border: 0.5px solid #00a5ff;
		border-radius: 6px;
		display: block;
	}

	.text {
		color: #0075ff;
		text-align: left;
		font: 700 28px 'Roboto', sans-serif;
		margin-top: 50px;
		margin-bottom: 50px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.id {
		color: #000000;
		font: 600 16px 'Roboto', sans-serif;
		border-bottom: 0.5px solid #00a5ff;
	}

	.pw {
		color: #000000;
		font: 600 16px 'Roboto', sans-serif;
		display: flex;
		align-items: center;
	}

	.name {
		color: #000000;
		font: 600 16px 'Roboto', sans-serif;
		border-bottom: 0.5px solid #00a5ff;
	}

	.birth {
		color: #000000;
		font: 600 16px 'Roboto', sans-serif;
	}

	.post {
		color: #000000;
		flex: none;
		padding-right: 4px;
	}

	.postcodeText {
		margin-right: 10px;
	}

	#post {
		width: 80px;
		cursor: default;
	}

	.addr {
		color: #000000;
		border-bottom: 0.5px solid #00a5ff;
	}

	.detailaddr {
		color: #000000;
		border-bottom: 0.5px solid #00a5ff;
	}

	.wishaddr {
		color: #000000;
	}

	#wishaddr {
		width: 400px;
		font: 400 16px 'Roboto', sans-serif;
		border-right: 0;
		border-top: 0;
		border-left: 0;
		padding-top: 10px;
	}

	.image {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		display: flex;
		align-items: center;
		justify-content: flex-start;
		margin-top: 50px;
	}

	.imageText {
		margin-right: 10px;
		color: #6c757d;
	}

	.imageContainer {
		text-align: center;
	}

	.join {
		margin-top: 50px;
		margin-right: 20px;
		text-align: center;
	}

	.btn-primary {
		background-color: #00a5ff;
		border: none;
	}
	.btn-primary:hover {
		background-color: #0087cf;
	}

	.cancel {
		margin-top: 50px;
		margin-left: 20px;
		text-align: center;
	}

	.btn-danger {
		background-color: rgb(255, 80, 80);
		border: none;
	}
	.btn-danger:hover {
		background-color: rgb(224, 54, 54);
		border: none;
	}
</style>
