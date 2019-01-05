import ObjectUtils from "../../utils/ObjectUtils";

import attenceRecordService from './../../services/building/AttenceRecordService';

import { message } from 'antd';

export default {

  namespace: 'building/manualClocking',

  state : {
    success : false,
    repairFormVisible:false,
    selectedItems:[],
    selectedItemKeys:[],
    data: [],
    buildingSiteId:null,
  },

  effects : {
    *findEmployeeForManualClocking({ payload }, { select, call, put }){
      const data = yield call(attenceRecordService.findEmployeeForManualClocking, payload);

      data.forEach((item, idx)=>{
        item.employees.forEach((emp, index)=>{
          emp.rowNo=index+1;
        });
      });

      yield put({
        type : 'updateState',
        payload : {
          data: data
        }
      });
    },

    *clocking({ payload }, { select, call, put }){
      const employeeCopied = {...payload.employee};
      delete employeeCopied.rowNo;

      yield attenceRecordService.clocking(employeeCopied);

      message.success(payload.employee.name + "打卡成功")
    },

    *repairClockingAsync({ payload }, { select, call, put }){

      yield attenceRecordService.mannualRepairClocking(payload);

      yield put({
        type : 'updateState',
        payload: {
          repairFormVisible: false
        }
      });

      message.success("补卡成功");
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
