import ObjectUtils from "../../utils/ObjectUtils";
import {message} from 'antd';
import wechatService from './../../services/system/WechatService';

export default {
  namespace: 'system/wechat',

  state: {
    wxAppInfo:{},
  },

  effects: {
    *addWxAppInfo({payload}, {select, put, call}){

      yield call(wechatService.addWxAppInfo, payload);

      message.success("数据保存成功");
    },

    *editWxAppInfo({payload}, {select, put, call}){
      yield call(wechatService.editWxAppInfo, payload);

      message.success("数据保存成功");
    },

    *findWxAppInfo({payload}, {select, put, call}){

      const wxAppInfo = yield call(wechatService.findWxAppInfo, payload);

      yield put({
        type:'updateState',
        payload: {
          wxAppInfo
        }
      })
    },
  },

  reducers: {
    updateState(state, { payload }) {
      return { ...state, ...payload };
    },

    mergeState(state, { payload }) {
      if (!payload) {
        return;
      }
      return ObjectUtils.mergeDeep(state, payload);
    },
  }
}
