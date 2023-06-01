import { createStore } from 'vuex';
import socket from '@/common/socket';

// Create a new store instance.
const store = createStore({
	state() {
		return {
			image: '',
			name: '',
			no: '',
			socket: socket,
			mysub: [],
		};
	},
	mutations: {
		setMember(state, member) {
			state.image = member.image;
			state.name = member.name;
			state.no = member.no;
		},
		setSocket(state, socket) {
			state.socket = socket;
		},
		removeMember(state) {
			state.image = '';
			state.name = '';
			state.no = '';
			state.mysub.forEach((sub) => {
				console.log('언서브 실행');
				sub.unsubscribe();
			});
			state.mysub = [];
		},
		addMysub(state, sub) {
			state.mysub.push(sub);
		},
	},
	getters: {
		getMember({ image, name, no }) {
			return {
				image,
				name,
				no,
			};
		},
		getSocket(state) {
			return state.socket;
		},
		getMysub(state) {
			return state.mysub;
		},
	},
});
export default store;
