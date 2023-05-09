<template>
    <div class="form">
        <div>
            <img class="leftButton" src="@/assets/left.jpg" />
            <img class="productPicture" />
            <img class="rightButton" src="@/assets/right.jpg" />
        </div>

        <div class="userInfo">
            <img class="userImage" src="@/assets/userImage.jpg" />
            <div class="box1">
                <div class="sellerName">
                    판매자 이름 : {{ seller.memberName }}
                </div>
                <div class="mainDeal">판매자 주 거래지 :</div>
            </div>

            <div class="box2">
                <!-- <div class="mainDeal">방사능 지수 0</div> -->
                <div class="jjim">
                    <img class="starImage" src="@/assets/star.jpg" />찜하기
                </div>
            </div>
        </div>

        <div class="line"></div>

        <div class="prouductInfo">
            <div class="productNamePrice">
                상품 이름 : {{ seller.productName }}
            </div>
            <div class="productTime">
                등록 시간 : {{ seller.productInsertTime }}
            </div>
            <div class="productNamePrice">
                상품 등록 가격 : {{ seller.productPrice }}원
            </div>
            <div class="productExplain">
                상품 설명 : {{ seller.productDetail }}
            </div>
            <div class="productExplain">
                상품 사용기간 : {{ seller.productUseDate }}
            </div>
        </div>

        <div class="productStatus">
            <a class="productText1">찜 0</a>
            <a class="productText1">조회 {{ seller.productVisit }}</a>
        </div>
        <input v-model="testInput" type="number" style="width: 100%; height: auto;" />
        <button style="width: 100%; height: auto;" @click="send">입찰 테스트 버튼임</button>
        <button class="auctionText" @click="bidStart">경매 참여</button>

        <div class="line"></div>
        <BidList v-if="check"></BidList>

        <div class=" box3">
            <div class="actionProduct">인기경매 상품</div>
            <a class="others">다른 상품 보러가기</a>
        </div>
        <div class="box4">
            <div class="product1">
                <div class="productImage"></div>
                <div class="productName">상품 이름</div>
                <div class="productPrice">가격</div>
                <div class="productRegion">지역</div>
            </div>

            <div class="product2">
                <div class="productImage"></div>
                <div class="productName">상품 이름</div>
                <div class="productPrice">가격</div>
                <div class="productRegion">지역</div>
            </div>

            <div class="product3">
                <div class="productImage"></div>
                <div class="productName">상품 이름</div>
                <div class="productPrice">가격</div>
                <div class="productRegion">지역</div>
            </div>
        </div>

        <div class="box5">
            <div class="product4">
                <div class="productImage"></div>
                <div class="productName">상품 이름</div>
                <div class="productPrice">가격</div>
                <div class="productRegion">지역</div>
            </div>

            <div class="product5">
                <div class="productImage"></div>
                <div class="productName">상품 이름</div>
                <div class="productPrice">가격</div>
                <div class="productRegion">지역</div>
            </div>

            <div class="product6">
                <div class="productImage"></div>
                <div class="productName">상품 이름</div>
                <div class="productPrice">가격</div>
                <div class="productRegion">지역</div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from '@/axios.js';
import BidList from './BidList.vue';
import socket from '@/common/socket';

var temporaryData = {
    seller1: [
        {
            productNo: 0,
            memberName: 'string',
            productName: 'string',
            productDetail: 'string',
            productPrice: 0,
            productUseDate: 'string',
        },
    ],
};

export default {
    name: '',
    components: { BidList },
    props: {},
    data() {
        return {
            seller: '',
            temporaryData: '',
            productNo: this.$route.query.productNo,
            check: false,
            socket: '',
            stompClient: '',
            resultObj: {},
            testInput: 0,
        };
    },

    //axios 통신
    methods: {
        sellerInfo() {
            axios
                .get('/product?productNo=' + this.productNo)
                .then((response) => {
                    console.log(response.data);
                    this.seller = response.data;
                    console.log(this.seller);
                });
        },
        bidStart() {
            this.check = true;
        },
        recevieFunc(resObj) {
            console.log("recevieFunc 콜백")
            this.seller.productPrice = resObj.price;
        },
        send() {
            let msgObj = {
                bidPrice: this.testInput,
                productNo: this.productNo,
            }
            socket.send(msgObj, "/bid")

        }
    },

    mounted() {
        this.sellerInfo();
        socket.connect("/product/" + this.productNo, this.recevieFunc);

    },


};
</script>

<style scoped>
.form {
    width: 100%;
    background: #ffffff;
    width: 700px;
    height: 3000px;
    display: flex;
    flex-direction: column;
    margin: 100px auto 0;
    overflow: hidden;
}

.form>* {
    margin-bottom: 20px;
    padding: 5px;
}

.productPicture {
    background: #d9d9d9;
    border-radius: 20px;
    width: 580px;
    height: 400px;
}

.leftButton {
    width: 40px;
    height: 40px;
    margin-bottom: 180px;
}

.rightButton {
    width: 40px;
    height: 40px;
    margin-bottom: 180px;
}

.userInfo {
    display: flex;
    flex-direction: row;
    margin-top: 30px;
}

.box1 {
    margin-left: 20px;
    display: flex;
    flex-direction: column;
    gap: 15px;
}

.box2 {
    margin-left: 280px;
    display: flex;
    flex-direction: column;
    gap: 8px;
}

.sellerName {
    color: #000000;
    font: 14px 'Roboto', sans-serif;
    font-weight: bold;
}

.mainDeal {
    color: #000000;
    font: 14px 'Roboto', sans-serif;
}

.hackNumber {
    color: #000000;
    font: 14px 'Roboto', sans-serif;
}

.jjim {
    color: #0075ff;
    font: 20px 'Roboto', sans-serif;
}

.userImage {
    border-radius: 50%;
    border-style: solid;
    border-color: #000000;
    border-width: 1.5px;
    width: 40px;
    height: 40px;
}

.starImage {
    width: 18px;
    height: 18px;
    margin-top: 5px;
}

.line {
    border-style: solid;
    border-color: #d9d9d9;
    border-width: 1px 0 0 0;
    width: 100%;
    height: 0px;
    margin-bottom: 5px;
}

.productText1 {
    color: #000000;
    font: 14px 'Roboto', sans-serif;
}

.auctionText {
    color: #000000;
    font: 14px 'Roboto', sans-serif;
    opacity: 0.4;
}

.productNamePrice {
    color: #000000;
    font: 14px 'Roboto', sans-serif;
    font-weight: bold;
    margin-bottom: 30px;
}

.productTime {
    color: #000000;
    font: 14px 'Roboto', sans-serif;
    opacity: 0.4;
    margin-bottom: 30px;
}

.productExplain {
    color: #000000;
    font: 14px 'Roboto', sans-serif;
    margin-bottom: 30px;
}

.productStatus {
    display: flex;
    gap: 50px;
}

.box3 {
    gap: 280px;
    display: flex;
    flex-direction: row;
    gap: 400px;
}

.others {
    color: #0075ff;
    font: 12px 'Roboto', sans-serif;
}

.actionProduct {
    color: #000000;
    font: 14px 'Roboto', sans-serif;
    font-weight: bold;
}

.box4 {
    display: flex;
    flex-direction: row;
    gap: 60px;
}

.box5 {
    display: flex;
    flex-direction: row;
    gap: 60px;
}

.productImage {
    background: #d9d9d9;
    border-radius: 20px;
    width: 160px;
    height: 160px;
}

.productName {
    color: #000000;
    font: 300 16px 'Roboto', sans-serif;
    font-weight: light;
}

.productPrice {
    color: #000000;
    font: 16px 'Roboto', sans-serif;
    font-weight: bold;
}

.productRegion {
    color: #000000;
    font: 16px 'Roboto', sans-serif;
}

.arrow {
    width: 0;
    height: 0;
    border-top: 20px solid transparent;
    border-bottom: 20px solid transparent;
    border-right: 20px solid black;
}
</style>
