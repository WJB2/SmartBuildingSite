import React from 'react';
import {message} from "antd";

import ObjectUtils from './../../utils/ObjectUtils';

import buildingRewardOrPunishService from './../../services/building/BuildingRewardOrPunishService';


export default {
  namespace:'building/buildingRewardPunish',
  state:{
    index:0,
    params:{},
    params2:{},
    params3:{},
    data: {
      list: [],
      pagination: {},
    },
    data2:{
      list:[],
      pagination:{},
    },
    data3:{
      list:[],
      pagination:{},
    },
    formType:'add',
    auditFormContent:null,
  },
  effects:{

    *addRewardOrPunishAsync({ payload }, { call, put }) {//增加奖惩人员名单

      yield call(buildingRewardOrPunishService.addRewardOrPunish, payload);

      yield put({
        type: 'queryTableRewardOrPunishAsync',
      });

      yield put({
        type: 'mergeState',
        payload: {
          formType: undefined,
          formVisible: false,
        },
      });

      message.success("奖惩记录添加成功");
    },

    *auditRewardOrPunishAction({payload},{call,put}){//点击审核出现对应表单并在表单上显示数据

      const result=yield call(buildingRewardOrPunishService.getRewardOrPunishById,payload);

      yield put({
        type:'mergeState',
        payload:{
          formType:'audit',
          formVisible:true,
          auditFormContent:result,
        },
      });

      yield put({
        type: 'queryTable2RewardOrPunishAsync',
      });

    },

    *auditRewardOrPunishAsync({payload},{call,put,select}){//审核表单点击确定提交
      const currentRewardOrPunish = yield select(state=>state['building/buildingRewardPunish'].auditFormContent);

      yield call(buildingRewardOrPunishService.auditRewardOrPunishById, { id: currentRewardOrPunish.id, ...payload ,});

      yield put({
        type:'queryTable2RewardOrPunishAsync',
        payload:{
          approved:false,
        },
      });

      yield put({
        type:'queryTable3RewardOrPunishAsync',
      });

      yield put({
        type: 'mergeState',
        payload: {
          formType: undefined,
          formVisible: false,
          auditFormContent: null,
        },
      });

      message.success("奖惩记录审核完成");

    },
    *deleteRewardOrPunishAsync({payload},{call,put}){

      yield call(buildingRewardOrPunishService.deleteRewardOrPunishById,payload);

      yield put({
        type:'queryTable2RewardOrPunishAsync',
      });

      message.success("奖惩记录删除完成");
    },

    *queryTableRewardOrPunishAsync({ payload }, { select, call, put }) {

      const params = yield select(state => state['building/buildingRewardPunish'].params);

      const pageInfo = yield call(buildingRewardOrPunishService.getRewardOrPunishPage, {
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
    *queryTable2RewardOrPunishAsync({ payload }, { select, call, put }) {

      const params2 = yield select(state => state['building/buildingRewardPunish'].params2);

      const pageInfo = yield call(buildingRewardOrPunishService.getRewardOrPunishPage, {
        ...params2,
        ...payload,
        unauditOnly:false,
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
    *queryTable3RewardOrPunishAsync({ payload }, { select, call, put }) {

      const params3 = yield select(state => state['building/buildingRewardPunish'].params3);

      const staffId=yield select(state=>state['global'].currentPosition.staffId);

      const pageInfo = yield call(buildingRewardOrPunishService.getRewardOrPunishPage, {
        ...params3,
        ...payload,
        auditBy:staffId,
        auditedOnly:true,
      });

      pageInfo.list.forEach((item, index)=>{
        item.rowNo = index + 1;
      });

      yield put({
        type: 'mergeState',
        payload: {
          data3: {
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
    mergeState(state,{payload}){
      return ObjectUtils.mergeDeep(state, payload);;
    },
  },
  subscription:{},
}
