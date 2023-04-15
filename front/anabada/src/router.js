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
    },
    {
        path: '/mypage',
        name: 'MyPage',
        component: () => import('@/components/MyPage.vue'),
    },
    {
        path: '/product',
        name: 'ProductInsert',
        component: () => import('@/components/ProductRegistrationPage.vue'),
    },
    {
        path: '/off',
        name: 'Off',
        component: () => import('@/components/Off.vue'),
    },
]


export default routes;