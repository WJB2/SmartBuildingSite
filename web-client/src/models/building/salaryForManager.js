import paymentService from "../../services/building/PaymentService";
import moment from 'moment';
import ObjectUtils from "../../utils/ObjectUtils";
import buildingSiteService from "../../services/building/BuildingSiteService";
import attenceRecordService from "../../services/building/AttenceRecordService";
import {message} from "antd";

export default {

  namespace: 'building/salaryForManager',

  state : {
    year : (new Date()).getFullYear(),
    data: [],
    yearMonth: null,
    buildingSiteId: null,
    importFormVisible:false,
    detailViewerVisible : false,
    salaryDetails : [],
    checkRecordDetails : []
  },

  effects : {
    *initSalaryReport({ payload }, { select, call, put }){

      yield put({
        type : 'querySalaryForManager',
        payload:{
          deletedFlag:false,
        }
      });
    },

    *payoffSalary({ payload }, { select, call, put }){
      const data = yield call(paymentService.payoffPayments, payload);

      yield put({
        type : 'querySalaryForManager',
        payload : {
          importFormVisible:false,
        }
      });

      message.success("工资发放结果导入成功");
    },

    *viewDetail({ payload }, { select, call, put }){
      const salaryForManager = yield select(state => state['building/salaryForManager']);
      const global = yield select(state => state['global']);

      const salaryDetails = yield call(paymentService.findBuildingSitePaymentDetail, {
        year : parseInt(payload.yearMonth/100),
        month : payload.yearMonth%100,
        buildingSiteId : payload.buildingSiteId,
        buildingDeveloperId:payload.buildingDeveloperId
      });

      salaryDetails.forEach((item, index)=>{
        item.rowNo = index + 1;
      });

      const checkRecordDetails = yield call(attenceRecordService.findBuildingSiteWithCheckDetail, {
        buildingDeveloperId:payload.buildingDeveloperId,
        buildingSiteId : payload.buildingSiteId,
        beginDate: moment(payload.yearMonth + '01', 'YYYYMMDD').startOf('month').format("YYYY-MM-DD 00:00:00"),
        endDate: moment(payload.yearMonth + '01', 'YYYYMMDD').endOf('month').format("YYYY-MM-DD 23:59:59")
      });

      yield put({
        type : 'updateState',
        payload : {
          salaryDetails : salaryDetails,
          checkRecordDetails: checkRecordDetails.length>0?checkRecordDetails[0].employees:[],
          ...payload
        }
      });
    },

    *querySalaryForManager({ payload }, { select, call, put }){

      yield put({
        type : 'updateState',
        payload
      });

      const salaryForManager = yield select(state => state['building/salaryForManager']);

      const result = yield call(paymentService.findPaymentReport, {
        year : salaryForManager.year,
        deletedFlag:false,
      });

      result.forEach((item, index)=>{
        item.rowNo = index + 1;
      });


      yield put({
        type : 'updateState',
        payload : {
          ...(payload?payload:{}),
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
