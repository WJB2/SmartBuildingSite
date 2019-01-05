import employeeService from './../../api/building/EmployeeService';
import ObjectUtils from '../../utils/ObjectUtils';
import {Toast} from 'vant';

export default{
    
    namespaced:true,

    state:{
        formType:'ADD',
        formVisible:false,
        importFormVisible:false,
        currentEmployee:null,
        params:{
            deletedFlag:false,
        },
        permiseeionData:[],
        data:{
            list:[],
            pagination:{},
        },
        list:[],
    },
    getters:{
        
    },
    actions:{
        async addEmployeeAsync({commit,dispatch},data){//增加人员信息

            await employeeService.addEmployee(data);

            dispatch('queryEmployeePageAsync');

            commit('updateState',{
                formType:undefined,
                formVisible:false,
            });
            
            Toast.success('添加人员信息成功');
        },

        async editEmployeeAction({commit,dispatch},data){//点击列表弹出编辑界面

            const result=await employeeService.findEmployeeById(data);

            commit('updateState',{
                formType:'EDIT',
                formVisible:true,
                currentEmployee:result,
            });

        },

        async editEmployeeAsync({commit,state,dispatch},data){//修改人员信息
            
            await employeeService.editEmployee({id:state.currentEmployee.id,...data});

            commit('updateState',{
                formType:undefined,
                formVisible:false,
                currentEmployee:null,
            });

            dispatch('queryEmployeePageAsync')

            Toast.success('修改人员信息成功');

        },

        async deleteEmployeeByIdAsync({commit,dispatch},data){

            await employeeService.deleteEmployeeById(data);

            dispatch('queryEmployeePageAsync');

            commit('updateState',{
                formType:undefined,
                currentEmployee:null,
                formVisible:false
            });

            Toast.success('删除人员信息成功');
            
        },
    
        async queryEmployeePageAsync({commit,state,rootState,rootGetters},data){
            console.log(rootState);

            if(!state.params.buildingDeveloperId){
                state.params.buildingDeveloperId=localStorage.getItem('currentPositionId');
            }

            const pageInfo=await employeeService.findEmployeePage({
                ...state.params,
                ...data,
            });

            console.log(pageInfo)

            commit('updateState',{
                list:pageInfo.list,
            });
        }
    },
    mutations:{
        updateState(state,payload){
            Object.assign(state,payload);
        },
        mergeState(state,payload){
            ObjectUtils.mergeDeep(state,payload)
        }
    },
}