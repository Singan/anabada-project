<template>
	<div class="wrapper container-fluid overflow-auto">
		<form
			ref="form"
			@submit.prevent="productRegister"
			class="container-sm d-flex flex-column align-items-center justify-content-between"
		>
			<div class="title">상품 등록하기</div>

			<div
				class="imagePreView d-flex align-items-center justify-content-center"
				style="background-color: transparent"
			>
				<img id="img" class="img-fluid mx-auto" required />
			</div>

			<div class="input-group justify-content-center">
				<label class="input-group-text" for="inputGroupFile01" style="border-radius: 0"> 이미지 등록 </label>
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
				required
			/>

			<div class="commonText">경매 시작 가격</div>
			<input
				type="number"
				class="form-control"
				placeholder="경매 시작 가격을 입력해주세요( , 없이 숫자만 입력)"
				aria-describedby="basic-addon1"
				v-model="price"
				required
			/>
			<div class="commonText">상품 카테고리</div>

			<select class="form-select w-50" aria-label="select example" v-model="selectCategory" required>
				<option v-for="item in categoryList" :key="item.categoryNo" :value="item.categoryNo">
					{{ item.categoryName }}
				</option>
			</select>

			<div class="commonText">상품 설명</div>

			<textarea
				class="form-control border"
				id="detail"
				placeholder="상품 설명을 입력하세요"
				v-model="detail"
				rows="7"
				cols="50"
				style="border: none; outline: none"
			></textarea>
			<div class="usingDate commonText">상품 사용기간 <br /></div>
			<div class="commonText">사용기간 : {{ usingDate }}</div>
			<div>
				<div class="form-check form-check-inline">
					<input type="radio" id="one" value="미사용" class="form-check-input" v-model="usingDate" checked />
					<label for="one" class="form-check-label">미사용</label>
				</div>
				<div class="form-check form-check-inline">
					<input type="radio" id="two" value="0 ~ 4주" class="form-check-input" v-model="usingDate" />
					<label for="two" class="form-check-label">0 ~ 4주</label>
				</div>
				<div class="form-check form-check-inline">
					<input type="radio" id="three" value="4 ~ 8주" v-model="usingDate" class="form-check-input" />
					<label for="three" class="form-check-label">4 ~ 8주</label>
				</div>
				<div class="form-check form-check-inline">
					<input type="radio" id="four" value="8주 이상" v-model="usingDate" class="form-check-input" />
					<label for="four" class="form-check-label">8주 이상</label>
				</div>
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
				if (!this.regex()) {
					return;
				}
				let form = new FormData();
				form.append('name', this.name);
				form.append('price', this.price);
				form.append('usingDate', this.usingDate);
				form.append('detail', this.detail);
				form.append('price', this.price);
				form.append('categoryNo', this.selectCategory);
				for (let i = 0; i < this.productImages.length; i++) {
					form.append('productImages', this.productImages[i]);
				}
				try {
					const response = await axios.post('/product', form, {
						header: {
							'Content-Type': 'multipart/form-data',
						},
					});
					this.$emit('addSub', response.data.productNo);
					this.$router.push('./ProductDt?productNo=' + response.data.productNo);
				} catch (err) {
					this.isChk = false;
					console.log(err);
					this.$swal({
						icon: 'error',
						title: '상품 등록에 실패하였습니다.',
					});
					return;
				}
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
			regex() {
				if (this.$refs.form.checkValidity()) {
					console.log('오나?');
				}
				if (!this.productImages) {
					this.$swal({
						icon: 'error',
						title: '이미지는 하나 이상 등록해야 합니다.',
					});
					return false;
				}
				return true;
			},
		},

		created() {
			this.category();
		},
	};
</script>
<style scoped>
	.img-fluid {
		width: 400px;
		max-height: 400px;
		height: auto;
		object-fit: contain;
	}
	.wrapper {
		height: 100%;
		background-color: #f2f3f6;
	}
	.form-control {
		width: 50%;
	}
	.container-sm {
		background-color: white;
		min-height: 100%;
		overflow: auto;
	}
	form > * {
		margin-bottom: 20px;
		padding: 5px;
	}

	.title {
		color: #00a5ff;
		font: 500 2em 'Roboto', sans-serif;
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

	.commonText {
		font-size: 18px;
		color: #00a5ff;
	}

	.submit {
		background: #00a5ff;
		border-radius: 5px;
		width: 30%;
		text-align: center;
		color: #ffffff;
		font-size: 1.2rem;
		cursor: pointer;
		margin-bottom: 30px;
	}

	.submit:hover {
		background: #008edb;
	}
	.submit:active {
		box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0);
		transform: translateY(4px);
	}

	.imagePreView {
		background: #d9d9d9;
	}

	.input-group-text {
		border-radius: 0;
	}

	.input-group-text:hover {
		background-color: #eef1f3;
		cursor: pointer;
	}
</style>
