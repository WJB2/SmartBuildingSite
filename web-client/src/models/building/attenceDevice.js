import React from 'react';
import {message} from 'antd';

import ObjectUtils from './../../utils/ObjectUtils';

import attenceDeviceService from './../../services/building/AttenceDeviceService';

export default {
  namespace: 'building/attenceDevice',

  state: {
    formType: 'ADD',
    formVisible: false,
    currentAttenceDevice: null,
    params: {},
    permissionData: [],
    data: {
      list: [],
      pagination: {},
    },
  },

  effects: {
    *addAttenceDeviceAsync({ payload }, { call, put }) {
      yield call(attenceDeviceService.addAttenceDevice, payload);

      yield put({
        type: 'mergeState',
      });

      yield put({
        type: 'queryAttenceDevicePageAsync',
      });

      yield put({
        type: 'mergeState',
        payload: {
          formType: undefined,
          formVisible: false,
          currentAttenceDevice: null,
        },
      });

      message.success("考勤设备添加成功");
    },

    *editAttenceDeviceAction({ payload }, { call, put }) {
      const result = yield call(attenceDeviceService.findAttenceDeviceById, payload);

      yield put({
        type: 'mergeState',
        payload: {
          formType: 'EDIT',
          formVisible: true,
          currentAttenceDevice: result,
        },
      });
    },

    *editAttenceDeviceAsync({ payload }, { select, call, put }) {
      const currentAttenceDevice = yield select(state => state['building/attenceDevice'].currentAttenceDevice);

      yield call(attenceDeviceService.editAttenceDevice, { id: currentAttenceDevice.id, ...payload });

      yield put({
        type: 'mergeState',
        payload: {
          formType: undefined,
          formVisible: false,
          currentAttenceDevice: null,
        },
      });

      yield put({
        type: 'queryAttenceDevicePageAsync',
      });

      message.success("考勤设备信息修改成功");
    },

    *deleteAttenceDeviceByIdAsync({ payload }, { call, put }) {
      yield call(attenceDeviceService.deleteAttenceDeviceById, payload);

      yield put({
        type: 'mergeState',
      });

      yield put({
        type: 'queryAttenceDevicePageAsync',
      });

      message.success("考勤设备信息删除成功");
    },

    *queryAttenceDevicePageAsync({ payload }, { select, call, put }) {
      const params = yield select(state => state['building/attenceDevice'].params);

      if(!params.buildingDeveloperId){
        const global = yield select(state => state['global']);
        params.buildingDeveloperId=global.currentPosition.orgId;
      }

      const pageInfo = yield call(attenceDeviceService.findAttenceDevicePage, {
        ...params,
        ...payload,
        deletedFlag:false,
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
