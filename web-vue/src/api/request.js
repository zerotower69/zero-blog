/**
 * @description 封装的axios
 * 封装其的注意事项
 * */

import axios from "axios"

const http = axios.create({
    baseURL: 'https://api.zerotower.cn',  //TODO:使用环境文件来配置
    timeout: '10000'
    // headers: headers
})