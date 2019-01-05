import ObjectUtils from './../../utils/ObjectUtils';

import employeeService from './../../services/building/EmployeeService';
import {message} from "antd";

export default {
  namespace: 'building/employee',

  state: {
    formType: 'ADD',
    formVisible: false,
    importFormVisible: false,
    currentEmployee: null,
    params: {
      deletedFlag:false,
    },
    permissionData: [],
    data: {
      list: [],
      pagination: {},
    },
  },

  effects: {
    *addEmployeeAsync({ payload }, { call, put }) {
      yield call(employeeService.addEmployee, payload);

      yield put({
        type: 'mergeState',
      });

      yield put({
        type: 'queryEmployeePageAsync',
      });

      yield put({
        type: 'mergeState',
        payload: {
          formType: undefined,
          formVisible: false,
          currentEmployee: null,
        },
      });

      message.success("人员添加成功");
    },

    *importEmployee({ payload }, { select, call, put }){
      yield call(employeeService.importEmployee, payload);

      yield put({
        type : 'queryEmployeePageAsync',
        payload : {
          importFormVisible:false,
        }
      });

      message.success("人员信息导入成功");
    },

    *editEmployeeAction({ payload }, { call, put }) {
      const result = yield call(employeeService.findEmployeeById, payload);

      yield put({
        type: 'mergeState',
        payload: {
          formType: 'EDIT',
          formVisible: true,
          currentEmployee: result,
        },
      });
    },

    *editEmployeeAsync({ payload }, { select, call, put }) {
      const currentEmployee = yield select(state => state['building/employee'].currentEmployee);

      yield call(employeeService.editEmployee, { id: currentEmployee.id, ...payload });

      yield put({
        type: 'mergeState',
        payload: {
          formType: undefined,
          formVisible: false,
          currentEmployee: null,
        },
      });

      yield put({
        type: 'queryEmployeePageAsync',
      });

      message.success("人员修改成功");
    },

    *deleteEmployeeByIdAsync({ payload }, { call, put }) {
      yield call(employeeService.deleteEmployeeById, payload);

      yield put({
        type: 'mergeState',
      });

      yield put({
        type: 'queryEmployeePageAsync',
      });

      message.success("人员删除成功");
    },

    *queryEmployeePageAsync({ payload }, { select, call, put }) {
      const params = yield select(state => state['building/employee'].params);

      if(!params.buildingDeveloperId){
        const global = yield select(state => state['global']);
        params.buildingDeveloperId=global.currentPosition.orgId;
      }

      const pageInfo = yield call(employeeService.findEmployeePage, {
        ...params,
        ...payload,
      });

      yield put({
        type: 'mergeState',
        payload: {
          params: payload?payload:{},
          ...payload,
          data: {
            list: pageInfo.list,
            pagination: {
              total: pageInfo.total,
              current: pageInfo.pageNum,
              pageSize: pageInfo.pageSize,
            },
          },
        },
      });
    },
  },

  reducers: {
    updateState(state, { payload }) {
      return { ...state, ...payload };
    },

    mergeState(state, { payload }) {
      return ObjectUtils.mergeDeep(state, payload);
    },
  },
};
