import React from 'react';
import {message} from "antd";

import ObjectUtils from './../../utils/ObjectUtils';

import buildingSiteService from './../../services/building/BuildingSiteService';

export default {
  namespace: 'building/buildingSite',

  state: {
    formType: 'ADD',
    formVisible: false,
    currentBuildingSite: null,
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
    *addBuildingSiteAsync({ payload }, { call, put }) {
      yield call(buildingSiteService.addBuildingSite, payload);

      yield put({
        type: 'mergeState',
      });

      yield put({
        type: 'queryBuildingSitePageAsync',
      });

      yield put({
        type: 'mergeState',
        payload: {
          formType: undefined,
          formVisible: false,
          currentBuildingSite: null,
        },
      });

      message.success("工地信息添加成功");
    },

    *editBuildingSiteAction({ payload }, { call, put }) {
      const result = yield call(buildingSiteService.findBuildingSiteById, payload);

      yield put({
        type: 'mergeState',
        payload: {
          formType: 'EDIT',
          formVisible: true,
          currentBuildingSite: result,
        },
      });
    },

    *editBuildingSiteAsync({ payload }, { select, call, put }) {
      const currentBuildingSite = yield select(state => state['building/buildingSite'].currentBuildingSite);

      yield call(buildingSiteService.editBuildingSite, { id: currentBuildingSite.id, ...payload });

      yield put({
        type: 'mergeState',
        payload: {
          formType: undefined,
          formVisible: false,
          currentBuildingSite: null,
        },
      });

      yield put({
        type: 'queryBuildingSitePageAsync',
      });

      message.success("工地信息修改成功");
    },

    *deleteBuildingSiteByIdAsync({ payload }, { call, put }) {
      yield call(buildingSiteService.deleteBuildingSiteById, payload);

      yield put({
        type: 'mergeState',
      });

      yield put({
        type: 'queryBuildingSitePageAsync',
      });

      message.success("工地信息删除成功");
    },

    *queryBuildingSitePageAsync({ payload }, { select, call, put }) {
      const params = yield select(state => state['building/buildingSite'].params);

      if(!params.buildingDeveloperId){
        const global = yield select(state => state['global']);
        params.buildingDeveloperId=global.currentPosition.orgId;
      }

      const pageInfo = yield call(buildingSiteService.findBuildingSitePage, {
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
