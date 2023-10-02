import axios from "axios";

// 设置默认值
axios.defaults.baseURL = "http://localhost:8000/api";
axios.defaults.timeout = 20000;

// get 请求方法，url 是请求的地址，params 是请求的 json 对象
function toGet(url,params) {
    return axios({
        url: url,
        method: "get",
        params: params
    });
}

// post 请求方法，url 是请求的地址，params 是请求的 json 对象
function doPost(url,params) {
    return axios({
        url: url,
        method: "post",
        params: params
    });
}

// 导出函数
export {toGet}

export {doPost}