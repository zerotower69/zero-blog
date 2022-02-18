const IS_PROD = process.env.NODE_ENV === "production";

export default {
  //TODO:使用QQ和微博扫码实现三方登录
  QQ_APP_ID: "",
  QQ_REDIRECT_URI: "",
  WEIBO_APP_ID: "",
  WEIBO_REDIRECT_URI: "",
  TENCENT_CAPTCHA: "2066967644",
  //>>>>>> git>>>>>
  GITHUB_CLIENT_ID: IS_PROD ? "d29b8f0d52f5e64d2690" : "d9b3b47e3b883d6cd039",
  GITHUB_REDIRECT_URI: IS_PROD
    ? "https://www.wugenquan.cn/oauth/login/github"
    : "http://localhost:8082/oauth/login/github",
  GITHUB_AUTHORIZE_URI: "https://github.com/login/oauth/authorize",
  //>>>>>>>> gitee >>>>>>>>>>>>>>>>
  GITEE_CLIENT_ID: IS_PROD
    ? "5caf0de1b9836df85bf79e144b137fa17abf574256a849e2138bdf7257e98a4b"
    : "c49300cffd2854aee51f67ee7f02f59201644c3cc20ae87d6871881b2b86c9c8",
  GITEE_REDIRECT_URI: IS_PROD
    ? "https://www.wugenquan.cn/oauth/login/gitee"
    : "http://localhost:8082/oauth/login/gitee",
  GITEE_AUTHORIZE_URI: "https://gitee.com/oauth/authorize",
  //自己使用自己的网易云api服务
  MUSIC_URI: ""
};
