import ObjectUtils from './../../utils/ObjectUtils';
import attenceDeviceService from './../../api/building/AttenceDeviceService';
import {Toast} from 'vant';

export default{

    namespaced:true,

    state:{
        formType:'ADD',
        formVisible:false,
        currentAttenceDevice:null,
        params:{
            deletedFlag:false,
        },
        permissionData:[],
        data:{
            list:[],
            pagination:{},
        },
        list:[],
    },

    getters:{
       
    },

    actions:{
        async addAttenceDeviceAsync({commit,dispatch},data){//增加设备
            
            await attenceDeviceService.addAttenceDevice(data);

            dispatch('queryAttenceDevicePageAsync');

            commit('updateState',{
                formType:undefined,
                formVisible:false,
            });
            
            Toast.success('添加设备信息成功');

        },

        async editAttenceDeviceAction({commit},data){//点击列表

            const result=await attenceDeviceService.findAttenceDeviceById(data);

            commit('updateState',{
                formType:'EDIT',
                formVisible:true,
                currentAttenceDevice:result,
            });

        },

        async editAttenceDeviceAsync({commit,state},data){//点击编辑表单的修改

            await attenceDeviceService.editAttenceDevice({id:state.currentEmployee.id,...data});

            commit('updateState',{
                formType:undefined,
                formVisible:false,
                currentEmployee:null,
            });

            dispatch('queryAttenceDevicePageAsync')

            Toast.success('修改设备信息成功');

        },

        async deleteAttenceDeviceByIdAsync({commit,state,dispatch},data){


            await attenceDeviceService.deleteAttenceDeviceById(data);

            dispatch('queryAttenceDevicePageAsync');

            commit('updateState',{
                formType:undefined,
                currentAttenceDevice:null,
                formVisible:false
            });

            Toast.success('删除人员信息成功');
        },

        async queryAttenceDevicePageAsync({commit,state},data){//加载设备列表
            console.log(data);

            if(!state.params.buildingDeveloperId){
                state.params.buildingDeveloperId=localStorage.getItem('currentPositionId');
            }


            const pageInfo=await attenceDeviceService.findAttenceDevicePage({
                ...state.params,
                ...data
            })

            
            commit('updateState',{
                list:pageInfo.list,
            });

        }
    },

    mutations:{
        updateState(state,payload){
            Object.assign(state,payload);
        }
    },


}