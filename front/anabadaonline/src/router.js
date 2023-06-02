// 라우트 정보
// 라우팅을 해주면서 #을 제거해주면서, path에 입력한 경로에 매핑된 컴포넌트를 보여준다.
import token from './common/token';
const requireAuth = () => (to, from, next) => {
	if (token.is()) {
		return next();
	}
	next('/login');
};

const routes = [
	{
		path: '/',
		name: 'Main',
		component: () => import('@/components/MainPage.vue'),
	},
	{
		path: '/login',
		name: 'Login',
		component: () => import('@/components/member/login/LoginPage.vue'),
	},
	{
		path: '/mypage',
		name: 'MyPage',
		component: () => import('@/components/member/mypage/MyPage.vue'),
		beforeEnter: requireAuth(),
	},
	{
		path: '/product',
		name: 'Product',
		component: () => import('@/components/product/Product.vue'),
		beforeEnter: requireAuth(),
	},
	{
		path: '/auction',
		name: 'Auction',
		component: () => import('@/components/auction/Auction.vue'),
	},
	{
		path: '/memberExit',
		name: 'MemberExit',
		component: () => import('@/components/member/mypage/MemberExit.vue'),
		beforeEnter: requireAuth(),
	},

	{
		path: '/productDt',
		name: 'ProductDt',
		component: () => import('@/components/product/ProductDt.vue'),
	},
	{
		path: '/memberChange',
		name: 'MemberChange',
		component: () => import('@/components/member/mypage/MemberChange.vue'),
		beforeEnter: requireAuth(),
	},

	{
		path: '/successfulBid/:successBidNo',
		name: 'SuccessfulBid',
		component: () => import('@/components/transaction/SuccessfulBid.vue'),
		props: true,
		beforeEnter: requireAuth(),
	},

	{
		path: '/acountRegister',
		name: 'AcountRegister',
		component: () => import('@/components/member/mypage/AcountRegister.vue'),
		beforeEnter: requireAuth(),
	},

	{
		path: '/my/history/buy',
		name: 'buyHistory',
		props: { check: 'buy' },
		component: () => import('@/components/transaction/MyHistory.vue'),
		beforeEnter: requireAuth(),
	},
	{
		path: '/my/history/sales',
		name: 'salesHistory',
		props: { check: 'sales' },
		component: () => import('@/components/transaction/MyHistory.vue'),
		beforeEnter: requireAuth(),
	},
	{
		path: '/my/history/bid',
		name: 'bidHistory',
		props: { check: 'bid' },
		component: () => import('@/components/transaction/MyHistory.vue'),
		beforeEnter: requireAuth(),
	},
	{
		path: '/JoinMember',
		name: 'JoinMember',
		component: () => import('@/components/member/login/JoinMember.vue'),
	},

	{
		path: '/BidList',
		name: '/BidList',
		component: () => import('@/components/product/BidList.vue'),
	},
	{
		path: '/Payment',
		name: '/Payment',
		component: () => import('@/components/member/mypage/Payment.vue'),
		beforeEnter: requireAuth(),
	},
	{
		path: '/my/chatting',
		name: '/chatList',
		component: () => import('@/components/chat/chatList.vue'),
		beforeEnter: requireAuth(),
	},
];

export default routes;
