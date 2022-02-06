<template>
  <div id="app-wrapper">
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
    <zt-player></zt-player>
  </div>
  <router-view />
</template>

<script>
// 这里依然使用vue2的语法
import ztPlayer from "zt-player";
export default {
  components: {
    ztPlayer,
  },
};
</script>

<style lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
