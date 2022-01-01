const IS_PROD = process.env.NODE_ENV === "dproduction";
// 消除控制台打印
const path = require("path");
const UglifyJsPlugin = require("uglifyjs-webpack-plugin");
const CompressionWebpackPlugin = require("compression-webpack-plugin");

function resolve(dir) {
  return path.join(__dirname, dir);
}
//websocket协议 是否加密
const socketProtocal="wss";
// 项目的主要配置文件

module.exports = {
  publicPath: IS_PROD ? "/" : "/",
  // 相对于打包路径index.html的路径
  outputDir: process.env.outputDir || "dist",
  // 'dist', 生产环境构建文件的目录
  assetsDir: "static",
  // 相对于outputDir的静态资源(js、css、img、fonts)目录
  transpileDependencies: ["vuetify"],
  // publicPath: "./",
  devServer: {
    host: "0.0.0.0",
    port: "8082",
    proxy: {
      "/api": {
        target: IS_PROD
          ? "https://api.zerotower.cn"
          : "http://zerotower.cn:10000",
        // target: "http://api.zerotower.cn",
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          "^/api": ""
        }
      },
      "/socket": {
        target:  IS_PROD ? `ws://zerotower.cn:10000` : "ws://localhost:10000", //websocket目标接口地址
        changeOrigin: true, //是否允许跨域
        pathRewrite: {
          "^/socket": "" //重写,
        },
        ws: true //开启ws, 如果是http代理此处可以不用设置
      }
    },
    disableHostCheck: true
  },
  configureWebpack: config => {
    if (IS_PROD) {
      config.plugins.push(
        //gzip压缩
        new CompressionWebpackPlugin({
          filename: "[path].gz[query]",
          algorithm: "gzip",
          test: /\.js$|\.html$|\.css/, // 匹配的文件名
          threshold: 10240, // 文件超过10k，进行gzip压缩
          minRatio: 0.8,
          deleteOriginalAssets: false // 是否删除原文件
        })
      );
      //清除控制台
      config.plugins.push(
        new UglifyJsPlugin({
          uglifyOptions: {
            compress: {
              // warningsFilter: false,
              drop_debugger: true, // console
              drop_console: true,
              pure_funcs: ["console.log"] // 移除console
            }
          },
          sourceMap: false,
          parallel: true
        })
      );
    }
  },
  chainWebpack: config => {
    config.resolve.alias
      .set("@", resolve("src"))
      .set("@/assets", resolve("src/assets"))
      .set("@/views", resolve("src/views"));
  }
};
