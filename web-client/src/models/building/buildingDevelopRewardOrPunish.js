import React from 'react';
import {message} from 'antd';


import ObjectUtils from './../../utils/ObjectUtils';
import DevelopRewardOrPunishService from "../../services/building/BuildingDevelopRewardOrPunishService";

export default {
  namespace:'building/buildingDevelopRewardOrPunish',
  state:{
    index:0,
    formVisible:false,
    params:{},
    params2:{},
    data: {
      list: [],
      pagination: {},
    },
    data2:{
      list:[],
      pagination:{},
    },
    formType:'add',
    buttonVisible:false,
    currentRewardOrPunish:null,
  },
  effects:{
    *addDevelopRewardOrPunishAsync({ payload }, { call, put }) {
      yield call(DevelopRewardOrPunishService.addDevelopRewardOrPunish, payload);

      yield put({
        type: 'queryTable2DevelopRewardOrPunishAsync',
        payload:{
          payload
        }
      });

      yield put({
        type:'queryTableDevelopRewardOrPunish',
        payload:{
          payload
        }
      })

      yield put({
        type: 'mergeState',
        payload: {
          formType: undefined,
          formVisible: false,
        },
      });

      message.success("奖惩记录添加成功");
    },

    *editDevelopRewardOrPunishAction({ payload }, { call, put }) {//点击编辑出现对应表单
      const result = yield call(DevelopRewardOrPunishService.getDevelopRewardOrPunishById, payload);

      yield put({
        type: 'mergeState',
        payload: {
          formType:'edit',
          formVisible: true,
          currentRewardOrPunish: result,
        },
      });
    },

    *editDevelopRewardOrPunishAsync({ payload }, { select, call, put }) {//点击确定提交表单
      const currentRewardOrPunish = yield select(state=>state['building/buildingDevelopRewardOrPunish'].currentRewardOrPunish);

      yield call(DevelopRewardOrPunishService.editDevelopRewardOrPunishById, { id: currentRewardOrPunish.id, ...payload });

      yield put({
        type:'queryTable2DevelopRewardOrPunishAsync',

      });

      yield put({
        type: 'mergeState',
        payload: {
          formType: undefined,
          formVisible: false,
          currentRewardOrPunish: null,
        },
      });

      yield put({
        type: 'queryTableBuildingDeveloperPageAsync',
      });

      message.success("奖惩记录修改成功");
    },

    *deleteDevelopRewardOrPunishAsync({ payload }, { call, put }) {
      yield call(DevelopRewardOrPunishService.deleteDevelopRewardOrPunishById, payload);

      yield put({
        type: 'queryTable2DevelopRewardOrPunishAsync',
      });

      message.success("奖惩记录删除成功");
    },

    *queryTableDevelopRewardOrPunishAsync({ payload }, { select, call, put }) {

      const params =yield select(state=>state['building/buildingDevelopRewardOrPunish'].params);

      const pageInfo = yield call(DevelopRewardOrPunishService.getDevelopRewardOrPunishPage, {
        ...params,
        ...payload,
        approved:true,
      });
      pageInfo.list.forEach((item, index)=>{
        item.rowNo = index + 1;
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

    *queryTable2DevelopRewardOrPunishAsync({ payload }, { select, call, put }) {

      const params =yield select(state=>state['building/buildingDevelopRewardOrPunish'].params2);

      const pageInfo = yield call(DevelopRewardOrPunishService.getDevelopRewardOrPunishPage, {
        ...params,
        ...payload,
      });

      pageInfo.list.forEach((item, index)=>{
        item.rowNo = index + 1;
      });

      yield put({
        type: 'mergeState',
        payload: {
          data2: {
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

  reducers:{
    updateState(state, { payload }) {
      return { ...state, ...payload };
    },
    mergeState(state, { payload }) {
      return ObjectUtils.mergeDeep(state, payload);
    },
  },
  subscription:{},
}
