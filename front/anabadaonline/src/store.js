import { createStore } from 'vuex';

// Create a new store instance.
const store = createStore({
	state() {
		return {
			member: null,
			socket: null,
		};
	},
	mutations: {
		setMember(state, member) {
			state.member = member;
		},
		setSocket(state, socket) {
			state.socket = socket;
		},
	},
	getters: {
		getMember(state) {
			return state.member;
		},
		getSocket(state) {
			return state.socket;
		},
	},
});
export default store;
