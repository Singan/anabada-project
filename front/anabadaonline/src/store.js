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
	},
});
export default store;
