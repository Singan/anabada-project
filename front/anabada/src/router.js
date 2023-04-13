// 라우트 정보
// 라우팅을 해주면서 #을 제거해주면서, path에 입력한 경로에 매핑된 컴포넌트를 보여준다.


const routes = [
    {
        path: '/',
        name: 'Main',
        component: () => import('@/components/MainPage.vue'),
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('@/components/LoginPage.vue'),
    }
]


export default routes;