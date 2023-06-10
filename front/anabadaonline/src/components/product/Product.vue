<template>
	<div class="wrapper .container-fluid">
		<form @submit.prevent="productRegister" class="form">
			<div class="title">상품 등록하기</div>

			<div class="imagePreView">
				<img id="img" class="img-fluid" />
			</div>

			<div class="input-group justify-content-center">
				<label class="input-group-text" for="inputGroupFile01"> 이미지 등록 </label>
				<input
					type="file"
					id="inputGroupFile01"
					@change="onInputImage"
					accept="image/*"
					multiple="multiple"
					style="width: 0px; height: 0px"
				/>
			</div>
			<div class="commonText">상품명</div>
			<input
				type="text"
				class="form-control"
				placeholder="상품명을 입력해주세요"
				aria-label="Username"
				aria-describedby="basic-addon1"
				v-model="name"
			/>

			<div class="commonText">경매 시작 가격</div>
			<input
				type="number"
				class="form-control"
				placeholder="경매 시작 가격을 입력해주세요( , 없이 숫자만 입력)"
				aria-describedby="basic-addon1"
				v-model="price"
			/>
			<!-- <div class="priceText" id="price">
				<input
					class="textSize"
					type="number"
					placeholder="경매 시작 가격을 입력해주세요( , 없이 숫자만 입력)"
					style="border: none; outline: none"
                    v-model="price"

				/>
			</div> -->

			<div class="commonText">상품 카테고리</div>

			<div class="category">
				<select v-model="selectCategory" class="categorySelect">
					<option v-for="item in categoryList" :key="item.categoryNo" :value="item.categoryNo">
						{{ item.categoryName }}
					</option>
				</select>
			</div>

			<div class="commonText">상품 설명</div>

			<div class="rectangle-233">
				<textarea
					class="form-control"
					id="detail"
					placeholder="상품 설명을 입력하세요"
					v-model="detail"
					rows="7"
					cols="50"
					style="border: none; outline: none"
				></textarea>
			</div>
			<div class="usingDate commonText">상품 사용기간 <br /></div>
			<div class="commonText">사용기간 : {{ usingDate }}</div>

			<div class="radioSelect">
				<input type="radio" id="one" value="미사용" v-model="usingDate" checked />
				<label for="one">미사용</label>

				<input type="radio" id="two" value="0 ~ 4주" v-model="usingDate" />
				<label for="two">0 ~ 4주</label>

				<input type="radio" id="three" value="4 ~ 8주" v-model="usingDate" />
				<label for="three">4 ~ 8주</label>

				<input type="radio" id="four" value="8주 이상" v-model="usingDate" />
				<label for="four">8주 이상</label>
			</div>
			<label class="submit">
				등록하기
				<button class="_18px3" style="display: none">등록하기</button>
			</label>
		</form>
	</div>
</template>
<script>
	import axios from '@/axios.js';
	export default {
		name: '',
		components: {},
		props: {},
		data() {
			return {
				categoryList: '',
				name: '',
				price: '',
				usingDate: '미사용',
				detail: '',
				price: '',
				selectCategory: '',
				productImages: null,
				isChk: false,
			};
		},

		methods: {
			category() {
				axios.get('/category').then((response) => {
					console.log(response.data);
					this.categoryList = response.data;
				});
			},

			async productRegister() {
				if (this.isChk) {
					this.isChk = true;
					return;
				}
				let form = new FormData();
				form.append('name', this.name);
				form.append('price', this.price);
				form.append('usingDate', this.usingDate);
				form.append('detail', this.detail);
				form.append('price', this.price);
				form.append('categoryNo', this.selectCategory);
				if (this.productImages) {
					for (let i = 0; i < this.productImages.length; i++) {
						form.append('productImages', this.productImages[i]);
					}
				}

				const response = await axios
					.post('/product', form, {
						header: {
							'Content-Type': 'multipart/form-data',
						},
					})
					.catch((error) => {
						this.isChk = false;
						this.$swal({
							icon: 'error',
							title: '상품 등록에 실패하였습니다.',
						});
						return;
					});
				this.$emit('addSub', response.data.productNo);
				this.$router.push('./ProductDt?productNo=' + response.data.productNo);
			},

			onInputImage(e) {
				this.productImages = e.target.files;
				//이미지 미리보기
				const image = document.getElementById('img');
				const reader = new FileReader();
				reader.onload = (e) => {
					image.src = e.target.result;
				};
				reader.readAsDataURL(this.productImages.item(0));
			},
		},

		created() {
			this.category();
		},
	};
</script>
<style scoped>
	.wrapper {
		background-color: #f8f9fa;
	}
	.form-control {
		width: 50%;
	}

	.form {
		background: #ffffff;
		width: 700px;
		height: 1100px;
		display: flex;
		flex-direction: column;
		margin: 0 auto;
		justify-content: center;
		align-items: center;
		border: #000000;
		overflow: hidden;
	}

	.form > * {
		margin-bottom: 20px;
		padding: 5px;
	}

	.title {
		color: #0075ff;
		text-align: left;
		font: 700 28px 'Roboto', sans-serif;
		margin-top: 20px;
	}

	.productSelect {
		border-radius: 10px;
		width: 90px;
		height: 30px;
		text-align: center;
		line-height: 30px;
	}

	.usingDate {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		margin-bottom: 5px;
	}

	.radioSelect {
		color: #000000;
		text-align: left;
		font: 400 16px 'Roboto', sans-serif;
		display: flex;
		align-items: center;
		justify-content: flex-start;
	}

	.pick1 {
		width: 110px;
		height: 23px;
	}

	.nameText {
		color: #797979;
		text-align: left;
		font: 300 12px 'Roboto', sans-serif;

		align-items: center;
		justify-content: flex-start;
		margin: 2px 0px 0px 5px;
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		width: 470px;
		height: 30px;
	}

	.priceText {
		color: #797979;
		text-align: left;
		font: 300 12px 'Roboto', sans-serif;

		align-items: center;
		justify-content: flex-start;
		margin: 2px 0px 0px 5px;
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		width: 470px;
		height: 30px;
	}

	.textSize {
		width: 470px;
		height: 30px;
	}

	.category {
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		width: 470px;
		height: 30px;
	}

	.categorySelect {
		width: 100px;
		height: 100%;
		font-size: 18px;
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		text-align: center;
	}

	.rectangle-233 {
		border-radius: 5px;
		border-style: solid;
		border-color: #0075ff;
		border-width: 1.5px;
		width: 470px;
		height: 121px;
	}

	.detail {
		color: #797979;
		text-align: left;
		font: 300 12px 'Roboto', sans-serif;
	}

	.commonText {
		font-size: 18px;
		color: blue;
	}

	.submit {
		background: #0075ff;
		border-radius: 5px;
		width: 470px;
		height: 40px;
		text-align: center;
		line-height: 40px;
		color: #ffffff;
		font-size: 15;
	}

	.imagePreView {
		background: #d9d9d9;
		width: 200px;
	}
</style>
