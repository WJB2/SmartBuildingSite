import React from 'react';
import {message} from "antd";

import ObjectUtils from "../../utils/ObjectUtils";
import buildingSiteService from "../../services/building/BuildingSiteService";
import attenceRuleService from "../../services/building/AttenceRuleService";

export default {
  namespace: 'building/clockingSetting',

  state:{
    buildingSiteId : null,
    buildingSiteList:[],
    attenceRuleList:[],
  },

  effects: {
    *initBuildingSiteList({ payload }, { select, call, put }){
      const global = yield select(state => state['global']);

      const buildingSiteList = yield buildingSiteService.findBuildingSiteList({
        buildingDeveloperId:global.currentPosition.orgId,
        deletedFlag:false,
      });

      yield put({
        type : 'updateState',
        payload: {
          buildingSiteList,
          buildingSiteId : buildingSiteList.length>0?buildingSiteList[0].id:null
        }
      });

      yield put({
        type : 'query'
      });
    },

    *saveSetting({ payload }, { select, call, put }){

      yield call(attenceRuleService.saveAttenceRule, payload);

      yield put({
        type : 'query'
      });

      message.success("考勤设置保存成功");
    },

    *query({ payload }, { select, call, put }){
      const global = yield select(state => state['global']);

      const result = yield call(attenceRuleService.findAttenceRuleList,{
        buildingDeveloperId:global.currentPosition.orgId
      });

      yield put({
        type : 'updateState',
        payload:{
          attenceRuleList : result
        }
      })
    }
  }
  ,

  reducers: {
    updateState(state, { payload }) {
      return { ...state, ...payload };
    },

    mergeState(state, { payload }) {
      return ObjectUtils.mergeDeep(state, payload);
    },
  }
}
