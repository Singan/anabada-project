import { createStore } from 'vuex'

// Create a new store instance.
const store = createStore({
    state() {
        return {
            member: null
        }
    },
    mutations: {
        setMember(state, member) {
            state.member = member;
        }
    },
    getters: {
        getMember(state) {
            return state.member;
        }
    }

})
export default store;