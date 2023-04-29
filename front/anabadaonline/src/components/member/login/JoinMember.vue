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
				<!-- <label for="addr" class="postcodeText">우편번호</label>
				<input type="text" id="post" v-model="addr" readonly /> -->
				<button @click="search()">주소 찾기</button>
			</div>
			<div class="addr">
				<label for="addr">주소</label>
				<input type="text" id="addr" v-model="addr" readonly />
			</div>
			<div class="detailaddr">
				<label for="detailaddr">상세주소</label>
				<input type="text" id="detailaddr" v-model="detailaddr" />
			</div>
			<div class="wishaddr">
				<label for="wishaddr">거래희망지</label>
				<input type="text" id="wishaddr" v-model="Wishaddr" />
			</div>
			<div class="image">
				<label for="image" class="imageText">프로필 이미지</label>
				<input id="image" type="file" @change="onInputImage" />
			</div>
			<div class="imageContainer">
				<button type="submit" class="join">회원가입</button>
				<button class="cancel" @click="goLogin">취소하기</button>
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
						// document.getElementById('post').value = data.zonecode;
						document.getElementById('addr').value = roadAddr;
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
		width: 788px;
		height: 1040px;
		flex-direction: column;
		align-items: center;
		margin: auto;
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
		text-align: left;
		font: 600 16px 'Roboto', sans-serif;
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
	}

	#id {
		font: 400 16px 'Roboto', sans-serif;
		border-right: 0;
		border-top: 0;
		border-left: 0;
		padding-top: 15px;
	}

	.pw {
		color: #000000;
		text-align: left;
		font: 600 16px 'Roboto', sans-serif;
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		margin-top: 50px;
	}

	#pw {
		font: 400 16px 'Roboto', sans-serif;
		border-right: 0;
		border-top: 0;
		border-left: 0;
		padding-top: 15px;
	}

	.name {
		color: #000000;
		text-align: left;
		font: 600 16px 'Roboto', sans-serif;
		margin-top: 15px;
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		margin-top: 50px;
	}

	#name {
		font: 400 16px 'Roboto', sans-serif;
		border-right: 0;
		border-top: 0;
		border-left: 0;
		padding-top: 15px;
	}

	.birth {
		color: #000000;
		text-align: left;
		font: 600 16px 'Roboto', sans-serif;
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		margin-top: 50px;
	}

	.post {
		color: #000000;
		text-align: left;
		font: 600 16px 'Roboto', sans-serif;
		display: flex;
		margin-top: 50px;
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
		text-align: left;
		font: 600 16px 'Roboto', sans-serif;
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		margin-top: 20px;
	}

	#addr {
		width: 500px;
		font: 400 16px 'Roboto', sans-serif;
		border-right: 0;
		border-top: 0;
		border-left: 0;
		cursor: default;
		padding-top: 10px;
	}

	.detailaddr {
		color: #000000;
		text-align: left;
		font: 600 16px 'Roboto', sans-serif;
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		margin-top: 50px;
	}

	#detailaddr {
		width: 500px;
		font: 400 16px 'Roboto', sans-serif;
		border-right: 0;
		border-top: 0;
		border-left: 0;
		padding-top: 10px;
	}

	.wishaddr {
		color: #000000;
		text-align: left;
		font: 600 16px 'Roboto', sans-serif;
		display: flex;
		flex-direction: column;
		justify-content: flex-start;
		margin-top: 50px;
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
	}

	.imageContainer {
		text-align: center;
	}

	.join {
		width: 6.5rem;
		height: 50px;
		background-color: #0075ff;
		border-radius: 5px;
		margin: 0 auto;
		color: white;
		cursor: pointer;
		border: none;
		margin-top: 50px;
		margin-right: 20px;
		text-align: center;
	}

	.cancel {
		width: 6.5rem;
		height: 50px;
		background-color: #e20303;
		border-radius: 5px;
		margin: 0 auto;
		color: white;
		cursor: pointer;
		border: none;
		margin-top: 50px;
		margin-left: 20px;
		text-align: center;
	}
</style>
