import ObjectUtils from "../../utils/ObjectUtils";

import paymentService from './../../services/building/PaymentService';
import buildingSiteService from './../../services/building/BuildingSiteService';
import {message} from "antd";

export default {

  namespace: 'building/salaryForDeveloper',

  state : {
    year : (new Date()).getFullYear(),
    month : (new Date()).getMonth(),
    buildingSiteId: null,
    importFormVisible:false,
    buildingSiteList: [],
    data: []
  },

  effects : {

    *initBuildingSiteList({ payload }, { select, call, put }){
      const global = yield select(state => state['global']);
      const buildingSiteList = yield buildingSiteService.findBuildingSiteList({
        buildingDeveloperId:global.currentPosition.orgId,
        deletedFlag:false
      });

      yield put({
        type : 'updateState',
        payload: {
          buildingSiteList,
          buildingSiteId : buildingSiteList.length>0?buildingSiteList[0].id:null,
        }
      });

      yield put({
        type : 'querySalaryForDeveloper'
      });
    },

    *createSalary({ payload }, { select, call, put }){
      const data = yield call(paymentService.addPayments, payload);

      yield put({
        type : 'querySalaryForDeveloper',
        payload : {
          importFormVisible:false
        }
      });

      message.success("工资发放需求导入成功");
    },

    *querySalaryForDeveloper({ payload }, { select, call, put }){

      yield put({
        type : 'updateState',
        payload
      });

      const salaryForDeveloper = yield select(state => state['building/salaryForDeveloper']);

      const result = yield call(paymentService.findBuildingSitePaymentDetail, {
        yearMonth : salaryForDeveloper.year*100 + salaryForDeveloper.month,
        buildingSiteId : salaryForDeveloper.buildingSiteId
      });

      result.forEach((item, index)=>{
        item.rowNo = index + 1;
      });

      yield put({
        type : 'updateState',
        payload : {
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
