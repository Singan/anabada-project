<template>
    <form @submit.prevent="submitForm" class="flexContainer">
        <div class="text">본인 정보를 입력해 주세요</div>
        <div class="id">
            <label for="id">id</label>
            <input type="text" id="id" v-model="id" />
        </div>
        <div class="pw">
            <label for="pw">pw</label>
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
        <div class="buttonContainer">
            <button type="submit" class="join">회원가입</button>
            <button class="cancel" @click="goLogin">취소하기</button>
        </div>
    </form>
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
                    // document.getElementById('detailaddr').value =
                    // 	data.jibunAddress;
                },
            }).open();
        },
    },
};
</script>
<style scoped>
.flexContainer {
    display: flex;
    width: 788px;
    height: 1040px;
    flex-direction: column;
    align-items: center;

    margin: auto;
}

.flexContainer>* {
    display: flex;
    align-items: center;
    justify-content: center;
}

.flexContainer>*>*:first-child {
    flex-grow: 1;
}

.flexContainer>*>* {
    flex-grow: 3;
}

.text {
    color: #0075ff;
    text-align: left;
    font: 700 28px 'Roboto', sans-serif;

    width: 398px;
    height: 37px;
    margin-top: 40px;
    margin-bottom: 30px;
    /* display: flex;
       align-items: center;
       justify-content: flex-start; */
}

.id {
    color: #000000;
    text-align: left;
    font: 400 16px 'Roboto', sans-serif;

    width: 398px;
    height: 37px;
}

.pw {
    color: #000000;
    text-align: left;
    font: 400 16px 'Roboto', sans-serif;

    width: 398px;
    height: 37px;
}

.name {
    color: #000000;
    text-align: left;
    font: 400 16px 'Roboto', sans-serif;

    width: 398px;
    height: 37px;
}

.birth {
    color: #000000;
    text-align: left;
    font: 400 16px 'Roboto', sans-serif;

    width: 398px;
    height: 37px;
}

.addr {
    color: #000000;
    text-align: left;
    font: 400 16px 'Roboto', sans-serif;

    width: 398px;
    height: 37px;
}

.detailaddr {
    color: #000000;
    text-align: left;
    font: 400 16px 'Roboto', sans-serif;

    width: 398px;
    height: 37px;
}

.Wishaddr {
    color: #000000;
    text-align: left;
    font: 400 16px 'Roboto', sans-serif;

    width: 398px;
    height: 37px;
}

.image {
    color: #000000;
    text-align: left;
    font: 400 16px 'Roboto', sans-serif;

    width: 398px;
    height: 37px;
}

.join {
    width: 8.5rem;
    height: 50px;
    line-height: 50px;
    background-color: white;
    border-radius: 5px;
    margin: 0 auto;
    color: #0075ff;
}

.cancel {
    width: 8.5rem;
    height: 50px;
    line-height: 50px;
    background-color: white;
    border-radius: 5px;
    margin: 0 auto;
    color: #e20303;
}
</style>
