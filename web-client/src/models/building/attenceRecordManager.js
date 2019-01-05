
import  moment from 'moment';
import attenceRecordService from "../../services/building/AttenceRecordService";
import ObjectUtils from "../../utils/ObjectUtils";

export default {

  namespace: 'building/attenceRecordManager',

  state : {
    beginDate : moment().startOf('month'),
    endDate : moment().endOf('month'),
    buildingDeveloperId: null,
    data : []
  },

  effects : {
    *initAttenceRecord({payload}, {select, call, put}){
      yield put({
        type : 'queryAttenceRecordAsync'
      })
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
        buildingDeveloperId : payload.buildingDeveloperId?payload.buildingDeveloperId:attenceRecord.buildingDeveloperId,
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
