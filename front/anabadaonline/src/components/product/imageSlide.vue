<template>
	<div class="slider">
		<div class="slide" v-for="(image, index) in images" :key="index" v-show="index === currentIndex">
			<img :src="image" alt="slide" />
		</div>

		<button class="prev" @click="prevSlide">&lt;</button>
		<button class="next" @click="nextSlide">&gt;</button>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				images: [
					'https://picsum.photos/id/237/200/300',
					'https://picsum.photos/id/238/200/300',
					'https://picsum.photos/id/239/200/300',
					'https://picsum.photos/id/240/200/300',
				],
				currentIndex: 0,
			};
		},
		methods: {
			prevSlide() {
				this.currentIndex = this.currentIndex === 0 ? this.images.length - 1 : this.currentIndex - 1;
			},
			nextSlide() {
				this.currentIndex = (this.currentIndex + 1) % this.images.length;
			},
		},
	};
</script>

<style>
	.slider {
		position: relative;
		width: 100%;
		height: 300px;
	}
	.slide {
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		opacity: 0;
		transition: opacity 1s ease;
	}
	.slide img {
		width: 100%;
		height: 100%;
		object-fit: cover;
	}
	.slide:first-child {
		opacity: 1;
	}
	.prev,
	.next {
		position: absolute;
		top: 50%;
		transform: translateY(-50%);
		background-color: transparent;
		color: white;
		font-size: 2rem;
		border: none;
		cursor: pointer;
		outline: none;
	}
	.prev {
		left: 10px;
	}
	.next {
		right: 10px;
	}
</style>
