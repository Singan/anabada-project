<template>
	<form @submit.prevent="productRegister">
		<div class="box">
			<div class="productLayout"></div>
			<div class="--">
				<div class="--2">상품 등록하기</div>
			</div>

			<div class="rectangle-26"></div>

			<input
				id="productImages"
				class="productSelect"
				type="file"
				@change="onInputImage"
				accept="image/*"
				multiple="multiple"
				style="background-color: blue"
			/>

			<div class="_20px">
				<div class="_20px2">상품명</div>
			</div>

			<div class="group-10">
				<div class="rectangle-23">
					<div class="nameText" id="name">
						<input
							v-model="name"
							type="text"
							placeholder="상품명을 입력해주세요"
							style="border: none; outline: none"
						/>
					</div>
				</div>
			</div>

			<div class="group-18">
				<div class="_20px3">
					<div class="_20px2">상품 카테고리</div>
				</div>

				<div class="group-11">
					<div class="rectangle-232"></div>

					<div class="categoryText">
						<select v-model="selectCategory">
							<option
								v-for="item in categoryList"
								:key="item.categoryNo"
								:value="item.categoryNo"
							>
								{{ item.categoryName }}
							</option>
						</select>
					</div>
				</div>
			</div>

			<div class="_20px4">
				<div class="_20px2">상품 설명</div>
			</div>

			<div class="group-112">
				<div class="rectangle-233">
					<label for="detail" id="detail"></label>
					<textarea
						class="form-control"
						id="detail"
						placeholder="상품 설명을 입력하세요"
						rows="7"
						cols="50"
						style="border: none; outline: none"
					></textarea>
				</div>
			</div>

			<div class="group-17">
				<div class="useageBox">
					<div class="usingDate">
						상품 사용기간
						<div>
							사용기간 : {{ picked }}
							<div class="radioSelect">
								<input
									type="radio"
									id="one"
									value="미사용"
									v-model="picked"
									checked
								/>
								<label for="one">미사용</label>

								<input
									type="radio"
									id="two"
									value="0 ~ 4주"
									v-model="picked"
								/>
								<label for="two">0 ~ 4주</label>

								<input
									type="radio"
									id="three"
									value="4 ~ 8주"
									v-model="picked"
								/>
								<label for="three">4 ~ 8주</label>

								<input
									type="radio"
									id="four"
									value="8주 이상"
									v-model="picked"
								/>
								<label for="four">8주 이상</label>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="--7">
				<div class="rectangle-25"></div>

				<button class="_18px3">등록하기</button>
			</div>
		</div>
	</form>
</template>
<script>
	import axios from '@/axios.js';
	import result from '@/common/token.js';
	export default {
		name: '',
		components: {},
		props: {},
		data() {
			return {
				picked: '미사용',
				categoryList: '',
				name: '',
				// usingDate:'',
				detail: '',
				// price:'',
				selectCategory: '',
				productImages: null,
			};
		},

		methods: {
			goProductDt() {
				this.$router.push('./ProductDt');
			},

			category() {
				axios.get('/category').then((response) => {
					console.log(response.data);
					this.categoryList = response.data;
					console.log(this.categoryList);
				});
			},

			productRegister() {
				let form = new FormData();
				form.append('name', this.name);
				//form.append("usingDate", this.usingDate)
				form.append('detail', this.detail);
				//form.append("price", this.price)
				form.append('categoryNo', this.selectCategory);
				if (this.productImages) {
					form.append('productImages', this.productImages);
				}
				console.log(this.selectCategory);
				axios.defaults.headers.common['x-auth-token'] = result.token;
				axios
					.post('/product', form, {
						header: {
							'Content-Type': 'multipart/form-data',
						},
					})
					.then((response) => {
						console.log(response);
					});
			},
			onInputImage(e) {
				this.productImages = e.target.files;
				console.log(this.productImages);
			},
		},

		created() {
			this.category();
		},
	};
</script>
<style scoped>
	.box * {
		box-sizing: border-box;
	}
	.form-control {
		margin: 5px 0px 0px 30px;
	}
	.box {
		background: #f1f1f1;
		width: 1920px;
		height: 1024px;

		overflow: hidden;
	}

	.productLayout {
		background: #ffffff;
		width: 700px;
		height: 900px;
		position: absolute;
		left: 610px;
		top: 61px;
	}

	.group-46 {
		width: 540px;
		height: 778px;
		position: static;
	}

	.-- {
		width: 200px;
		height: 40px;
		position: absolute;
		left: 690px;
		top: 92px;
	}

	.--2 {
		color: #0075ff;
		text-align: left;
		font: 700 28px 'Roboto', sans-serif;
		position: absolute;
		left: 0px;
		top: 4px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.productSelect {
		border-radius: 10px;
		width: 90px;
		height: 30px;
		position: absolute;
		left: 945px;
		top: 300px;
	}

	._20px {
		width: 60px;
		height: 23px;
		position: absolute;
		left: 760px;
		top: 347px;
	}

	.usingDate {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		position: absolute;
		left: 0px;
		top: 2px;
		display: block;
		align-items: center;
		justify-content: flex-start;
	}

	.radioSelect {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		left: 0px;
		top: 2px;
		display: block;
		align-items: center;
		justify-content: flex-start;
		margin: 10px 0px 0px 0px;
	}

	.pick1 {
		width: 110px;
		height: 23px;
		position: absolute;
		left: 900px;
		top: 535px;
	}

	.group-10 {
		width: 470px;
		height: 30px;
		position: static;
	}

	.rectangle-23 {
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		width: 470px;
		height: 30px;
		position: absolute;
		left: 760px;
		top: 380px;
	}

	.nameText {
		color: #797979;
		text-align: left;
		font: 300 12px 'Roboto', sans-serif;
		position: absolute;
		left: 0px;
		top: 2px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
		margin: 2px 0px 0px 5px;
	}

	.group-18 {
		width: 470px;
		height: 63px;
		position: static;
	}

	._20px3 {
		width: 110px;
		height: 23px;
		position: absolute;
		left: 760px;
		top: 440px;
	}

	.group-11 {
		width: 470px;
		height: 30px;
		position: static;
	}

	.rectangle-232 {
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		width: 470px;
		height: 30px;
		position: absolute;
		left: 760px;
		top: 473px;
	}

	.categoryText {
		width: 170px;
		height: 19px;
		position: absolute;
		left: 773px;
		top: 479px;
	}

	._20px4 {
		width: 110px;
		height: 23px;
		position: absolute;
		left: 760px;
		top: 626px;
	}

	.group-112 {
		width: 470px;
		height: 121px;
		position: static;
	}

	.rectangle-233 {
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		width: 470px;
		height: 121px;
		position: absolute;
		left: 760px;
		top: 659px;
	}

	.sub----4 {
		width: 170px;
		height: 76.63px;
		position: absolute;
		left: 773px;
		top: 683.2px;
	}

	.detail {
		color: #797979;
		text-align: left;
		font: 300 12px 'Roboto', sans-serif;
		position: absolute;
		left: 0px;
		top: 31px;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.vector-6 {
		position: absolute;
		left: 1195px;
		top: 483px;
		overflow: visible;
	}

	.group-17 {
		width: 460px;
		height: 63px;
		position: static;
	}

	.useageBox {
		width: 500px;
		height: 100px;
		position: absolute;
		left: 760px;
		top: 533px;
	}

	.group-13 {
		width: 70px;
		height: 30px;
		position: static;
	}

	.ellipse-13 {
		border-radius: 50%;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		width: 20px;
		height: 20px;
		position: absolute;
		left: 775px;
		top: 571px;
	}

	.--3 {
		width: 40px;
		height: 30px;
		position: absolute;
		left: 805px;
		top: 566px;
	}

	.---- {
		color: #000000;
		text-align: center;
		font: 400 12px 'Roboto', sans-serif;
		position: absolute;
		left: 0px;
		top: 0px;
		width: 40px;
		height: 30px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.group-14 {
		width: 70px;
		height: 30px;
		position: static;
	}

	.ellipse-132 {
		border-radius: 50%;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		width: 20px;
		height: 20px;
		position: absolute;
		left: 900px;
		top: 571px;
	}

	.--4 {
		width: 40px;
		height: 30px;
		position: absolute;
		left: 930px;
		top: 566px;
	}

	.group-15 {
		width: 70px;
		height: 30px;
		position: static;
	}

	.ellipse-133 {
		border-radius: 50%;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		width: 20px;
		height: 20px;
		position: absolute;
		left: 1025px;
		top: 571px;
	}

	.--5 {
		width: 40px;
		height: 30px;
		position: absolute;
		left: 1055px;
		top: 566px;
	}

	.group-16 {
		width: 70px;
		height: 30px;
		position: static;
	}

	.ellipse-134 {
		border-radius: 50%;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		width: 20px;
		height: 20px;
		position: absolute;
		left: 1150px;
		top: 571px;
	}

	.--6 {
		width: 40px;
		height: 30px;
		position: absolute;
		left: 1180px;
		top: 566px;
	}

	.--7 {
		width: 470px;
		height: 40px;
		position: static;
	}

	.rectangle-25 {
		background: #0075ff;
		border-radius: 5px;
		width: 470px;
		height: 40px;
		position: absolute;
		left: 760px;
		top: 830px;
	}

	._18px3 {
		width: 80px;
		height: 30px;
		position: absolute;
		left: 955px;
		top: 835px;
	}

	._18px4 {
		color: #ffffff;
		text-align: center;
		font: 700 18px 'Roboto', sans-serif;
		position: absolute;
		left: 0px;
		top: 0px;
		width: 80px;
		height: 30px;
		display: flex;
		align-items: center;
		justify-content: center;
	}

	.rectangle-26 {
		background: #d9d9d9;
		width: 120px;
		height: 120px;
		position: absolute;
		left: 930px;
		top: 167px;
	}
</style>
