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
        }
    },
}
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

.id {
    color: #000000;
    text-align: left;
    font: 400 16px 'Roboto', sans-serif;

    width: 398px;
    height: 37px;

}

.text {
    color: #0075ff;
    text-align: left;
    font: 700 28px 'Roboto', sans-serif;

    width: 398px;
    height: 37px;
    margin-bottom: 30px;
    /* display: flex;
    align-items: center;
    justify-content: flex-start; */
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