import authenticationService from './../../api/user/AuthenticationService';
import {Toast} from 'vant';

export default{
    namespaced:true,

    state:{
        status:null,
        errorText:null,
        user:null,
        authenticated:false,
    },
    getters:{

    },
    actions:{
        async login({commit},data){

            try{
                const user=await authenticationService.login(data);
                
                if(!user){
                    throw new Error('你输入的密码有误')
                }

                console.log('发生错误1')

                window.localStorage.setItem("AUTHENTICATED",true);
                window.localStorage.setItem("USERNAME",user.username);

                console.log('发生错误2')

                commit('updateStorageData',{
                    authenticated:true,
                    status:true,
                    user:user,
                });
            }catch(e){
                Toast.fail(e.message);
            }

            

        },
        
        async logout({commit},data){
            
            await authenticationService.logout();

            window.localStorage.removeItem('USERNAME');
            window.localStorage.removeItem('AUTHENTICATED');
            window.localStorage.removeItem('currentPositionId');

            commit('updateStorageData',{
                authenticated:false,
                status:false,
                user:null,
            })

        }
    },
    mutations:{
        updateStorageData(state,payload){
            Object.assign(state,payload);
        }
    }
}