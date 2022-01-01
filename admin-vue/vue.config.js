const IS_PROD = process.env.NODE_ENV === "production";
// 消除控制台打印
const path = require("path");
const UglifyJsPlugin = require("uglifyjs-webpack-plugin");
const CompressionWebpackPlugin = require("compression-webpack-plugin");

function resolve(dir) {
  return path.join(__dirname, dir);
}
// 项目的主要配置文件

module.exports = {
  publicPath: "/",
  // 相对于打包路径index.html的路径
  outputDir: process.env.outputDir || "dist",
  // 'dist', 生产环境构建文件的目录
  assetsDir: "static",
  devServer: {
    host: "0.0.0.0",
    port: "8080",
    proxy: {
      "/api": {
        target: IS_PROD
          ? "https://www.zerotower.cn:10000"
          : "https://api.zerotower.cn",
        // target: "http://api.zerotower.cn",
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          "^/api": ""
        }
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
