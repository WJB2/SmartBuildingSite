import React from 'react';
import {message} from 'antd';

import ObjectUtils from './../../utils/ObjectUtils';

import buildingDeveloperService from './../../services/building/BuildingDeveloperService';

export default {
  namespace: 'building/buildingDeveloper',

  state: {
    formType: 'ADD',
    formVisible: false,
    currentBuildingDeveloper: null,
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
    *addBuildingDeveloperAsync({ payload }, { call, put }) {
      yield call(buildingDeveloperService.addBuildingDeveloper, payload);

      yield put({
        type: 'mergeState',
      });

      yield put({
        type: 'queryBuildingDeveloperPageAsync',
      });

      yield put({
        type: 'mergeState',
        payload: {
          formType: undefined,
          formVisible: false,
          currentBuildingDeveloper: null,
        },
      });

      message.success("开发商信息添加成功");
    },

    *editBuildingDeveloperAction({ payload }, { call, put }) {
      const result = yield call(buildingDeveloperService.findBuildingDeveloperById, payload);

      yield put({
        type: 'mergeState',
        payload: {
          formType: 'EDIT',
          formVisible: true,
          currentBuildingDeveloper: result,
        },
      });
    },

    *editBuildingDeveloperAsync({ payload }, { select, call, put }) {
      const currentBuildingDeveloper = yield select(state => state['building/buildingDeveloper'].currentBuildingDeveloper);

      yield call(buildingDeveloperService.editBuildingDeveloper, { id: currentBuildingDeveloper.id, ...payload });

      yield put({
        type: 'mergeState',
        payload: {
          formType: undefined,
          formVisible: false,
          currentBuildingDeveloper: null,
        },
      });

      yield put({
        type: 'queryBuildingDeveloperPageAsync',
      });

      message.success("开发商信息修改成功");
    },

    *deleteBuildingDeveloperByIdAsync({ payload }, { call, put }) {
      yield call(buildingDeveloperService.deleteBuildingDeveloperById, payload);

      yield put({
        type: 'mergeState',
      });

      yield put({
        type: 'queryBuildingDeveloperPageAsync',
      });

      message.success("开发商信息删除成功");
    },

    *queryBuildingDeveloperPageAsync({ payload }, { select, call, put }) {
      const params = yield select(state => state['building/buildingDeveloper'].params);
      const pageInfo = yield call(buildingDeveloperService.findBuildingDeveloperPage, {
        ...params,
        ...payload,
      });

      yield put({
        type: 'mergeState',
        payload: {
          params : {
            ...(payload?payload:{})
          },
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
