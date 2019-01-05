import Vue from 'vue';
import Vuex from 'vuex';

import authenticationService from './../api/user/AuthenticationService';
import staffAuthorizationService from './../api/system/StaffAuthorizationService';
import employee from './building/employee';
import attenceDevice from './modules/attenceDevice'
import authentication from './user/authentication';

Vue.use(Vuex);

export default new Vuex.Store({
    namespaced:true,

    modules:{
      employee,
      attenceDevice,
      authentication
    },
    state:{

      initialized:false,//是否初始化完成
      currentPosition:null,//当前人员岗位
      currentUser:null,//当前用户
      
    },
    getters:{

    },
    actions:{
      async initialize({commit},payload){//初始化信息
        console.log('初始化开始')

        const user=await authenticationService.getCurrentUser({});

        const position=await staffAuthorizationService.getCurrentPosition({});
      
        const modules=await staffAuthorizationService.findAuthorizedModuleList({});

        commit('updateState',{
          initialized:true,
          currentPosition:position,
        });

        console.log(position)

        localStorage.setItem('currentPositionId',position.orgId)

        console.log('初始化结束')

      },
    },
    mutations:{
      updateState(state,payload){
        Object.assign(state,payload);
      }
    },
})