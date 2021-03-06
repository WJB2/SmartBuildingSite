import React from 'react';
import {message} from 'antd';

import  moment from 'moment';
import ObjectUtils from "../../utils/ObjectUtils";
import attenceRecordService from '../../services/building/AttenceRecordService';

export default {

  namespace: 'building/attenceRecord',

  state : {
    beginDate : moment().startOf('month'),
    endDate : moment().endOf('month'),
    buildingSiteId : null,
    data : []
  },

  effects : {
    *initAttenceRecord({payload}, {select, call, put}){
      yield put({
        type : 'queryAttenceRecordAsync'
      });
    },

    *queryAttenceRecordAsync({payload}, {select, call, put}){
      yield put({
        type : 'updateState',
        payload : payload
      });

      const attenceRecord = yield select((state)=>{
        return state['building/attenceRecord']
      });

      const global = yield select((state)=>{
        return state['global']
      });

      const result = yield call(attenceRecordService.findBuildingSiteWithCheckDetail, {
        buildingDeveloperId : global.currentPosition.orgId,
        beginDate: attenceRecord.beginDate.format("YYYY-MM-DD 00:00:00"),
        endDate: attenceRecord.endDate.format("YYYY-MM-DD 23:59:59")
      });

      yield put({
        type : 'updateState',
        payload: {
          data : result
        }
      });
    }
  },

  reducers: {
    updateState(state, { payload }) {
      return { ...state, ...payload };
    },

    mergeState(state, { payload }) {
      return ObjectUtils.mergeDeep(state, payload);
    },
  }
}
