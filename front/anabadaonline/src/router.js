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
        path: '/auction',
        name: 'Auction',
        component: () => import('@/components/Auction.vue'),
    },
    {
        path: '/chat',
        name: 'Chat',
        component: () => import('@/components/chat.vue'),
    },
    {
        path: '/memberExit',
        name: 'MemberExit',
        component: () => import('@/components/MemberExit.vue'),
    },
    {
        path: '/productRegistrationPage',
        name: 'ProductRegistrationPage',
        component: () => import('@/components/ProductRegistrationPage.vue'),
    },
    {
        path: '/jjim',
        name: 'Jjim',
        component: () => import('@/components/Jjim.vue'),
    },
    {
        path: '/productDt',
        name: 'ProductDt',
        component: () => import('@/components/ProductDt.vue'),
    },
    {
        path: '/memberChange',
        name: 'MemberChange',
        component: () => import('@/components/MemberChange.vue'),
    },
    {
        path: '/evaluation',
        name: 'Evaluation',
        component: () => import('@/components/Evaluation.vue'),
    },
    {
        path: '/auctionBid',
        name: 'AuctionBid',
        component: () => import('@/components/AuctionBid.vue'),
    },
    {
        path: '/successfulBid',
        name: 'SuccessfulBid',
        component: () => import('@/components/SuccessfulBid.vue'),
    },
    {
        path: '/acountRegister',
        name: 'AcountRegister',
        component: () => import('@/components/AcountRegister.vue'),
    },
    {
        path: '/transactionDt1',
        name: 'TransactionDt1',
        component: () => import('@/components/TransactionDt1.vue'),
    },
    {
        path: '/transactionDt2',
        name: 'TransactionDt2',
        component: () => import('@/components/TransactionDt2.vue'),
    },
    {
        path: '/memberJoin',
        name: 'MemberJoin',
        component: () => import('@/components/MemberJoin.vue'),
    },
]


export default routes;