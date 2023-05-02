<template>
    <div class="title">인기 매물</div>
    <div class="productFlex">
        <div class="flexItem">
            <div class="productImg"></div>

            <div class="productName">상품이름</div>

            <div class="price">가격</div>

            <div class="area">지역</div>

            <!-- <div class="interest">관심</div> -->

            <!-- <div class="chat">채팅</div>

			<div class="chatCount">0</div> -->

            <!-- <div class="interestCount">0</div> -->
        </div>
        <div class="flexItem">
            <div class="productImg"></div>

            <div class="productName">상품이름</div>

            <div class="price">가격</div>

            <div class="area">지역</div>

            <!-- <div class="interest">관심</div> -->

            <!-- <div class="chat">채팅</div>

			<div class="chatCount">0</div> -->

            <!-- <div class="interestCount">0</div> -->
        </div>
        <div class="flexItem">
            <div class="productImg"></div>

            <div class="productName">상품이름</div>

            <div class="price">가격</div>

            <div class="area">지역</div>

            <!-- <div class="interest">관심</div> -->

            <!-- <div class="chat">채팅</div>

			<div class="chatCount">0</div> -->

            <!-- <div class="interestCount">0</div> -->
        </div>
        <div class="flexItem">
            <div class="productImg"></div>

            <div class="productName">상품이름</div>

            <div class="price">가격</div>

            <div class="area">지역</div>

            <!-- <div class="interest">관심</div> -->

            <!-- <div class="chat">채팅</div>

			<div class="chatCount">0</div> -->

            <!-- <div class="interestCount">0</div> -->
        </div>
        <div class="flexItem">
            <div class="productImg"></div>

            <div class="productName">상품이름</div>

            <div class="price">가격</div>

            <div class="area">지역</div>

            <!-- <div class="interest">관심</div> -->

            <!-- <div class="chat">채팅</div>

			<div class="chatCount">0</div> -->

            <!-- <div class="interestCount">0</div> -->
        </div>
        <div class="flexItem">
            <div class="productImg"></div>

            <div class="productName">상품이름</div>

            <div class="price">가격</div>

            <div class="area">지역</div>

            <!-- <div class="interest">관심</div> -->

            <!-- <div class="chat">채팅</div>

			<div class="chatCount">0</div> -->

            <!-- <div class="interestCount">0</div> -->
        </div>
        <div class="flexItem">
            <div class="productImg"></div>

            <div class="productName">상품이름</div>

            <div class="price">가격</div>

            <div class="area">지역</div>

            <!-- <div class="interest">관심</div> -->

            <!-- <div class="chat">채팅</div>

			<div class="chatCount">0</div> -->

            <!-- <div class="interestCount">0</div> -->
        </div>
        <div class="flexItem">
            <div class="productImg"></div>

            <div class="productName">상품이름</div>

            <div class="price">가격</div>

            <div class="area">지역</div>

            <!-- <div class="interest">관심</div> -->

            <!-- <div class="chat">채팅</div>

			<div class="chatCount">0</div> -->

            <!-- <div class="interestCount">0</div> -->
        </div>
    </div>
</template>
<script>
/* Code generated with AutoHTML Plugin for Figma */
import One8Px from './One8Px.vue';
import axios from '@/axios.js';

export default {
    name: 'Off',
    components: {
        One8Px,
    },
    props: {},
    data() {
        // quickfix to have components available to pass as props
        return {
            One8Px,
            productList: '',
        };
    },
    methods: {
        product() {
            axios.get('/product/list').then((response) => {
                console.dir(response.data);
                this.product = response.data.list;
            });
        },
    },
};
</script>
<style scoped>
.productFlex {
    margin: auto;
    display: flex;
    width: 60%;
    justify-content: center;
    align-items: center;
    flex-wrap: wrap;
}

.flexItem {
    margin-right: 70px;
    margin-bottom: 40px;
}

.title {
    color: #000000;
    text-align: center;
    font: 600 24px 'Roboto', sans-serif;
    margin: 50px auto;
    left: 825px;
    top: 161px;
    width: 270px;
    height: 39px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.rectangle-20 {
    position: absolute;
    left: 385px;
    top: 285px;
    overflow: visible;
}

.productImg {
    background: #d9d9d9;
    border-radius: 20px;
    width: 160px;
    height: 160px;

    left: 384px;
    top: 285px;
}

.productName {
    color: #000000;
    text-align: left;
    font: 300 16px 'Roboto', sans-serif;

    left: 384px;
    top: 445px;
    width: 160px;
    height: 23px;

    align-items: center;
    justify-content: flex-start;
}

.price {
    color: #000000;
    text-align: left;
    font: 600 16px 'Roboto', sans-serif;

    left: 387px;
    top: 473px;
    width: 157px;
    height: 23px;

    align-items: center;
    justify-content: flex-start;
}

.area {
    color: #000000;
    text-align: left;
    font: 400 16px 'Roboto', sans-serif;

    left: 387px;
    top: 501px;
    width: 157px;
    height: 23px;

    align-items: center;
    justify-content: flex-start;
}

.interest {
    color: #797979;
    text-align: left;
    font: 400 13px 'Roboto', sans-serif;
    position: absolute;
    left: 386.67px;
    top: 524px;
    width: 38.67px;
    height: 23px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
}

.chat {
    color: #797979;
    text-align: left;
    font: 400 13px 'Roboto', sans-serif;
    position: absolute;
    left: 470.67px;
    top: 524px;
    width: 40px;
    height: 23px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
}

.chatCount {
    color: #797979;
    text-align: left;
    font: 400 13px 'Roboto', sans-serif;
    position: absolute;
    left: 520px;
    top: 524px;
    width: 24px;
    height: 23px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
}

.interestCount {
    color: #797979;
    text-align: left;
    font: 400 13px 'Roboto', sans-serif;
    position: absolute;
    left: 425.33px;
    top: 524px;
    width: 40px;
    height: 23px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
}

.rectangle-202 {
    background: #ffffff;
    width: 160px;
    height: 260px;
    position: absolute;
    left: 385px;
    top: 630px;
}

.rectangle-162 {
    background: #d9d9d9;
    border-radius: 20px;
    width: 160px;
    height: 160px;
    position: absolute;
    left: 384px;
    top: 630px;
}

.rectangle-203 {
    background: #ffffff;
    width: 160px;
    height: 260px;
    position: absolute;
    left: 705px;
    top: 285px;
}

.rectangle-163 {
    background: #d9d9d9;
    border-radius: 20px;
    width: 160px;
    height: 160px;
    position: absolute;
    left: 703px;
    top: 285px;
}

.rectangle-204 {
    position: absolute;
    left: 700.5px;
    top: 630px;
    overflow: visible;
}

.rectangle-164 {
    background: #d9d9d9;
    border-radius: 20px;
    width: 160px;
    height: 160px;
    position: absolute;
    left: 700px;
    top: 630px;
}
</style>
