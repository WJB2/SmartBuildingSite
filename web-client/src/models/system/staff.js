import React from 'react';
import {message} from 'antd';

import ObjectUtils from './../../utils/ObjectUtils';

import staffService from './../../services/system/StaffService';
import UserService from "../../services/user/UserService";

export default {
  namespace: 'system/staff',

  state: {
    formType: 'ADD',
    formVisible: false,
    currentStaff: null,
    passwordFormVisible:false,
    params: {},
    data: {
      list: [],
      pagination: {},
    },
  },

  effects: {
    *addStaffAsync({ payload }, { call, put }) {
      yield call(staffService.addStaff, payload);

      yield put({
        type: 'mergeState',
      });

      yield put({
        type: 'queryStaffPageAsync',
      });

      yield put({
        type: 'mergeState',
        payload: {
          formType: undefined,
          formVisible: false,
          currentStaff: null,
        },
      });

      message.success("人员新建成功");
    },

    *editStaffAction({ payload }, { call, put }) {
      const result = yield call(staffService.findStaffById, payload);

      yield put({
        type: 'mergeState',
        payload: {
          formType: 'EDIT',
          formVisible: true,
          currentStaff: result,
        },
      });
    },

    *editStaffAsync({ payload }, { select, call, put }) {
      const currentStaff = yield select(state => state['system/staff'].currentStaff);

      yield call(staffService.editStaff, { id: currentStaff.id, ...payload });

      yield put({
        type: 'mergeState',
        payload: {
          formType: undefined,
          formVisible: false,
          currentStaff: null,
        },
      });

      yield put({
        type: 'queryStaffPageAsync',
      });

      message.success("人员修改成功");
    },

    *deleteStaffByIdAsync({ payload }, { call, put }) {
      yield call(staffService.deleteStaffById, payload);

      yield put({
        type: 'mergeState',
      });

      yield put({
        type: 'queryStaffPageAsync',
      });

      message.success("人员删除成功");
    },

    *changePasswordAsync({ payload }, { select, call, put }){
      const {currentStaff} = yield select(state => state['system/staff']);

      delete payload.confirmPassword;

      yield call(UserService.adminEditUserPassword, {
        ...payload,
        userId : currentStaff.userId
      });

      yield put({
        type : 'updateState',
        payload : {
          passwordFormVisible:false,
        }
      });

      message.success("人员密码修改成功");
    },

    *queryStaffPageAsync({ payload }, { select, call, put }) {
      const params = yield select(state => state['system/staff'].params);
      const pageInfo = yield call(staffService.findStaffPage, {
        ...params,
        ...payload,
        deletedFlag:false,
      });

      pageInfo.list.forEach((item, index)=>{
        item.rowNo = index + 1 + (pageInfo.pageNum-1)*pageInfo.pageSize;
      });

      yield put({
        type: 'mergeState',
        payload: {
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
      if (!payload) {
        return;
      }
      return ObjectUtils.mergeDeep(state, payload);
    },
  },
};
