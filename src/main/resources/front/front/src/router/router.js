import VueRouter from 'vue-router'

//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Messages from '../pages/messages/list'
import Forum from '../pages/forum/list'
import ForumAdd from '../pages/forum/add'
import ForumDetail from '../pages/forum/detail'
import MyForumList from '../pages/forum/myForumList'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import ziyuanfenleiList from '../pages/ziyuanfenlei/list'
import ziyuanfenleiDetail from '../pages/ziyuanfenlei/detail'
import ziyuanfenleiAdd from '../pages/ziyuanfenlei/add'
import ziyuanxinxiList from '../pages/ziyuanxinxi/list'
import ziyuanxinxiDetail from '../pages/ziyuanxinxi/detail'
import ziyuanxinxiAdd from '../pages/ziyuanxinxi/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'
import aboutusList from '../pages/aboutus/list'
import aboutusDetail from '../pages/aboutus/detail'
import aboutusAdd from '../pages/aboutus/add'
import systemintroList from '../pages/systemintro/list'
import systemintroDetail from '../pages/systemintro/detail'
import systemintroAdd from '../pages/systemintro/add'
import discussziyuanxinxiList from '../pages/discussziyuanxinxi/list'
import discussziyuanxinxiDetail from '../pages/discussziyuanxinxi/detail'
import discussziyuanxinxiAdd from '../pages/discussziyuanxinxi/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'messages',
					component: Messages
				},
				{
					path: 'forum',
					component: Forum
				},
				{
					path: 'forumAdd',
					component: ForumAdd
				},
				{
					path: 'forumDetail',
					component: ForumDetail
				},
				{
					path: 'myForumList',
					component: MyForumList
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'ziyuanfenlei',
					component: ziyuanfenleiList
				},
				{
					path: 'ziyuanfenleiDetail',
					component: ziyuanfenleiDetail
				},
				{
					path: 'ziyuanfenleiAdd',
					component: ziyuanfenleiAdd
				},
				{
					path: 'ziyuanxinxi',
					component: ziyuanxinxiList
				},
				{
					path: 'ziyuanxinxiDetail',
					component: ziyuanxinxiDetail
				},
				{
					path: 'ziyuanxinxiAdd',
					component: ziyuanxinxiAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
				{
					path: 'aboutus',
					component: aboutusList
				},
				{
					path: 'aboutusDetail',
					component: aboutusDetail
				},
				{
					path: 'aboutusAdd',
					component: aboutusAdd
				},
				{
					path: 'systemintro',
					component: systemintroList
				},
				{
					path: 'systemintroDetail',
					component: systemintroDetail
				},
				{
					path: 'systemintroAdd',
					component: systemintroAdd
				},
				{
					path: 'discussziyuanxinxi',
					component: discussziyuanxinxiList
				},
				{
					path: 'discussziyuanxinxiDetail',
					component: discussziyuanxinxiDetail
				},
				{
					path: 'discussziyuanxinxiAdd',
					component: discussziyuanxinxiAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
