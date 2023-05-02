import { createStore } from 'vuex'

// Create a new store instance.
const store = createStore({
    state() {
        return {
            member: {

            }
        }
    },
    mutations: {
        setMember(state, member) {
            state.member = member;
        }
    }

})
export default store;