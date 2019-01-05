import axios from 'axios';
// import loginStore from '../store/modules/login';
import router from '../router';
import reqwest from 'reqwest';

axios.defaults.timeout=5000;
axios.defaults.headers.post['Content-Type']='application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.headers.common['X-Requested-With']='XMLHttpRequest';

// function checkStatus(response){
//     if(response.status>=200 && response.status<300){
//         return response;
//     }
//     const errortext=response.status;
//     const error=new Error();
//     error.name=errortext;
//     error.response=response;
//     throw error;
// }

//request拦截器Post传参序列化
axios.interceptors.request.use(
    config=>{
        // console.log('发送请求');
        //判断是否存在token,如果存在的话,则每个http header都加上token
        // if(loginStore.state.token){
        //     config.headers.common['Access-Token']=`token ${loginStore.state.token}`;
        // }
        return config;
    },
    err=>{
        return Promise.reject(err)
    }
)

axios.interceptors.response.use(function(response){
    const responseData=response.data;
    return responseData;
},function(error){
    console.log('失败收到响应')
    console.log(error)
    console.log(error.response);
    if(error.response){
        switch(error.response.status){
            case 401:
                break
            case 404:
                console.log(404);
                break
            case 500:
                console.log(500);
                break
        }
    }
    return Promise.reject(error.response.data);
});

export default function request(url,options){

    const defaultOptions={//默认设置
        credentials:'include',
    };

    const newOptions={...defaultOptions,...options};

    if(newOptions.multipartForm){//如果是文件上传 header设置为上传文件专有
        newOptions.headers={
            ...newOptions.headers,
        }
    }else{
        if(newOptions.method==='POST'||newOptions.method==="PUT"){
            newOptions.headers={
                Accept:'application/json',
                'Content-Type':'application/json;charset=utf-8',
                ...newOptions.headers,
            };
            newOptions.body=JSON.stringify(newOptions.body);
        }
    }

    if(newOptions.multipartForm){
        return reqwest({
            url:url,
            method:newOptions.method?newOptions.method:'POST',
            processData:false,
            data:newOptions.body
        })
    }else{
        return axios({
                method:newOptions.method,
                url:url,
                data:newOptions.body,
                headers:newOptions.headers
            })
            .catch(error=>{
                console.log(error)
            })
    }
}