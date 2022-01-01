import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

//路由全部改为懒加载
const routes = [
  {
    path: "/",
    component: () => import("../views/home/Home"),
    meta: {
      title: "ZeroTower的技术小屋"
    }
  },
  {
    path: "/articles/:articleId",
    component: () => import("../views/article/Article")
  },
  {
    path: "/archives",
    component: () => import("../views/archive/Archive"),
    meta: {
      title: "归档"
    }
  },
  {
    path: "/tags",
    component: () => import("../views/tag/Tag"),
    meta: {
      title: "标签"
    }
  },
  {
    path: "/categories",
    component: () => import("../views/category/Category"),
    meta: {
      title: "分类"
    }
  },
  {
    path: "/categories/*",
    component: () => import("../components/ArticleList")
  },
  {
    path: "/links",
    component: () => import("../views/link/Link"),
    meta: {
      title: "友链列表"
    }
  },
  {
    path: "/resume",
    component: () => import("../views/resume/Resume"),
    meta: {
      title: "我的简历"
    }
  },
  {
    path: "/about",
    component: () => import("../views/about/About"),
    meta: {
      title: "关于我"
    }
  },
  {
    path: "/message",
    component: () => import("@/views/message/Message"),
    meta: {
      title: "留言板"
    }
  },
  {
    path: "/tags/*",
    component: () => import("../components/ArticleList")
  },
  {
    path: "/user",
    component: () => import("../views/user/User"),
    meta: {
      title: "个人中心"
    }
  },
  {
    path: "/oauth/login/qq",
    component: () => import("../components/OauthLogin")
  },
  {
    path: "/oauth/login/weibo",
    component: () => import("../components/OauthLogin")
  },
  {
    path: "/oauth/login/github",
    component: () => import("../components/OauthLogin")
  },
  {
    path: "/oauth/login/gitee",
    component: () => import("../components/OauthLogin")
  }
  //找不到返回主页
  // {
  //   path: "*",
  //   redirect: "/"
  // }
];

const router = new VueRouter({
  mode: "history",
  // base: process.env.BASE_URL,
  routes
});

export default router;
