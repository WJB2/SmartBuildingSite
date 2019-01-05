import React from 'react';
import {message} from "antd";

import ObjectUtils from './../../utils/ObjectUtils';

import depositService from './../../services/building/DepositService';

export default {
  namespace: 'building/deposit',

  state: {
    formType: 'ADD',
    formVisible: false,
    depositDetailViewerVisible:false,
    depositRecordPage:{
      list:[],
      pagination:{}
    },
    currentDeposit: null,
    params: {},
    permissionData: [],
    data: {
      list: [],
      pagination: {},
    },
  },

  effects: {
    *addDepositAsync({ payload }, { call, put }) {
      yield call(depositService.addDeposit, payload);

      yield put({
        type: 'queryDepositPageAsync',
        payload:{
          ...payload
        }
      });

      yield put({
        type: 'mergeState',
        payload: {
          formType: undefined,
          formVisible: false,
          currentDeposit: null,
        },
      });

      message.success("存款记录新建成功");
    },

    *editDepositAction({ payload }, { call, put }) {
      const result = yield call(depositService.findDepositById, payload);

      yield put({
        type: 'mergeState',
        payload: {
          formType: 'EDIT',
          formVisible: true,
          currentDeposit: result,
        },
      });
    },

    *viewDepositDetailAction({ payload }, { select, call, put }) {
      const pageInfo = yield call(depositService.findDepositRecordPage, payload);

      yield put({
        type: 'mergeState',
        payload: {
          depositDetailViewerVisible: true,
          depositRecordPage: {
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

    *deleteDepositByIdAsync({ payload }, { call, put }) {
      yield call(depositService.deleteDepositById, payload);

      yield put({
        type: 'mergeState',
      });

      yield put({
        type: 'queryDepositPageAsync',

      });

      message.success("存款记录删除成功");
    },

    *queryDepositPageAsync({ payload }, { select, call, put }) {
      const params = yield select(state => state['building/deposit'].params);
      const pageInfo = yield call(depositService.findDepositPage, {
        ...params,
        ...payload,
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
      return ObjectUtils.mergeDeep(state, payload);
    },
  },
};
