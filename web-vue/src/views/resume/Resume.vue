<template>
  <div>
    <!--      封面图-->
    <!-- banner -->
    <div class="resume-banner banner">
      <h1 class="banner-title">简历</h1>
    </div>
    <!-- 简历内容 -->
    <v-card class="blog-container">
      <div class="my-wrapper">
        <v-avatar size="110">
          <img
            class="author-avatar"
            src="https://www.static.talkxj.com/avatar/blogger.jpg"
            alt="用户头像"
          />
        </v-avatar>
      </div>
      <div class="resume-content markdown-body" v-html="resumeContent" />
    </v-card>
  </div>
</template>

<script>
//  个人组件
export default {
  name: "Resume",
  data() {
    return {
      resumeContent: ""
    };
  }
};
</script>

<style scoped>
.resume-banner {
  background: #49b1f5 url(http://zerotower.oss-cn-beijing.aliyuncs.com/cover/wallhaven-w8j39r.jpg) no-repeat
    center center;
}
.resume-title {
  color: #344c67;
  font-size: 21px;
  font-weight: bold;
  line-height: 2;
}
.resume-content {
  word-break: break-word;
  line-height: 1.8;
  font-size: 14px;
}
.my-wrapper {
  text-align: center;
}
.author-avatar {
  transition: all 0.5s;
}
.author-avatar:hover {
  transform: rotate(360deg);
}
</style>
