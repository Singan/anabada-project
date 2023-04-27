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
		component: () => import('@/components/member/login/LoginPage.vue'),
	},
	{
		path: '/mypage',
		name: 'MyPage',
		component: () => import('@/components/member/mypage/MyPage.vue'),
	},
	{
		path: '/product',
		name: 'Product',
		component: () => import('@/components/product/Product.vue'),
	},
	{
		path: '/auction',
		name: 'Auction',
		component: () => import('@/components/auction/Auction.vue'),
	},
	{
		path: '/chat',
		name: 'Chat',
		component: () => import('@/components/chat/chat.vue'),
	},
	{
		path: '/memberExit',
		name: 'MemberExit',
		component: () => import('@/components/member/mypage/MemberExit.vue'),
	},
	{
		path: '/jjim',
		name: 'Jjim',
		component: () => import('@/components/member/mypage/Jjim.vue'),
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
	},
	{
		path: '/evaluation',
		name: 'Evaluation',
		component: () => import('@/components/transaction/Evaluation.vue'),
	},
	{
		path: '/auctionBid',
		name: 'AuctionBid',
		component: () => import('@/components/auction/AuctionBid.vue'),
	},
	{
		path: '/successfulBid',
		name: 'SuccessfulBid',
		component: () => import('@/components/transaction/SuccessfulBid.vue'),
	},
	{
		path: '/acountRegister',
		name: 'AcountRegister',
		component: () =>
			import('@/components/member/mypage/AcountRegister.vue'),
	},
	{
		path: '/transactionDt1',
		name: 'TransactionDt1',
		component: () => import('@/components/transaction/TransactionDt1.vue'),
	},
	{
		path: '/transactionDt2',
		name: 'TransactionDt2',
		component: () => import('@/components/transaction/TransactionDt2.vue'),
	},
	{
		path: '/JoinMember',
		name: 'JoinMember',
		component: () => import('@/components/member/login/JoinMember.vue'),
	},
];

export default routes;
