import ObjectUtils from './../../utils/ObjectUtils';

import workTypeService from './../../services/building/WorkTypeService';
import {message} from "antd";

export default {
  namespace: 'building/workType',

  state: {
    formType: 'ADD',
    formVisible: false,
    currentWorkType: null,
    params: {},
    permissionData: [],
    data: {
      list: [],
      pagination: {},
    },
  },

  effects: {
    *addWorkTypeAsync({ payload }, { call, put }) {
      yield call(workTypeService.addWorkType, payload);

      yield put({
        type: 'mergeState',
      });

      yield put({
        type: 'queryWorkTypePageAsync',
      });

      yield put({
        type: 'mergeState',
        payload: {
          formType: undefined,
          formVisible: false,
          currentWorkType: null,
        },
      });

      message.success("工种信息新建成功");
    },

    *editWorkTypeAction({ payload }, { call, put }) {
      const result = yield call(workTypeService.findWorkTypeById, payload);

      yield put({
        type: 'mergeState',
        payload: {
          formType: 'EDIT',
          formVisible: true,
          currentWorkType: result,
        },
      });
    },

    *editWorkTypeAsync({ payload }, { select, call, put }) {
      const currentWorkType = yield select(state => state['building/workType'].currentWorkType);

      yield call(workTypeService.editWorkType, { id: currentWorkType.id, ...payload });

      yield put({
        type: 'mergeState',
        payload: {
          formType: undefined,
          formVisible: false,
          currentWorkType: null,
        },
      });

      yield put({
        type: 'queryWorkTypePageAsync',
      });

      message.success("工种信息修改成功");
    },

    *deleteWorkTypeByIdAsync({ payload }, { call, put }) {
      yield call(workTypeService.deleteWorkTypeById, payload);

      yield put({
        type: 'mergeState',
      });

      yield put({
        type: 'queryWorkTypePageAsync',
      });

      message.success("工种信息删除成功");
    },

    *queryWorkTypePageAsync({ payload }, { select, call, put }) {
      const params = yield select(state => state['building/workType'].params);

      if(!params.buildingDeveloperId){
        const global = yield select(state => state['global']);
        params.buildingDeveloperId=global.currentPosition.orgId;
      }

      const pageInfo = yield call(workTypeService.findWorkTypePage, {
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
