import React from 'react';
import {message} from 'antd';

import ObjectUtils from './../../utils/ObjectUtils';

import organizationService from './../../services/system/OrganizationService';

import SystemPermissionConst from './../../const/SystemPermissionConst';

export default {
  namespace: 'system/organization',

  state: {
    formType: 'ADD',
    formVisible: false,
    currentOrganization: null,
    params: {},
    rootId : null,
    treeData: [],
    data: {
      list: [],
      pagination: {},
    },
  },

  effects: {
    *addOrganizationAsync({ payload }, { call, put }) {
      yield call(organizationService.addOrganization, payload);

      yield put({
        type: 'mergeState',
      });

      yield put({
        type: 'queryOrganizationTreeAsync',
      });

      yield put({
        type: 'queryOrganizationPageAsync',
      });

      yield put({
        type: 'mergeState',
        payload: {
          formType: undefined,
          formVisible: false,
          currentOrganization: null,
        },
      });

      message.success("组织机构新建成功");
    },

    *editOrganizationAction({ payload }, { call, put }) {
      const result = yield call(organizationService.findOrganizationById, payload);

      yield put({
        type: 'mergeState',
        payload: {
          formType: 'EDIT',
          formVisible: true,
          currentOrganization: result,
        },
      });
    },

    *editOrganizationAsync({ payload }, { select, call, put }) {
      const currentOrganization = yield select(
        state => state['system/organization'].currentOrganization
      );

      yield call(organizationService.editOrganization, {
        id: currentOrganization.id,
        ...payload,
      });

      yield put({
        type: 'mergeState',
        payload: {
          formType: undefined,
          formVisible: false,
          currentOrganization: null,
        },
      });

      yield put({
        type: 'queryOrganizationPageAsync',
      });

      yield put({
        type: 'queryOrganizationTreeAsync',
      });

      message.success("组织机构编辑成功");
    },

    *deleteOrganizationByIdAsync({ payload }, { call, put }) {
      yield call(organizationService.deleteOrganizationById, payload);

      yield put({
        type: 'queryOrganizationTreeAsync',
      });

      yield put({
        type: 'queryOrganizationPageAsync',
      });

      message.success("组织机构删除成功");
    },

    *changeOrganizationRootNode({ payload }, { select, call, put }){
      yield put({
        type: 'updateState',
        payload: {
          rootId: payload.id,
        },
      });

      yield put({
        type: 'queryOrganizationPageAsync',
        payload:{

        },
      });
    },

    *queryOrganizationPageAsync({ payload }, { select, call, put }) {
      const {params, rootId} = yield select(state => state['system/organization']);
      const pageInfo = yield call(organizationService.findOrganizationPage, {
        ...params,
        rootId,
        ...payload,
        deletedFlag:false,
      });

      pageInfo.list.forEach((item, index)=>{
        item.rowNo = index + 1 + (pageInfo.pageNum-1)*pageInfo.pageSize;
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

    *queryOrganizationTreeAsync({ payload }, { select, call, put }) {
      const params = yield select(state => state['system/organization'].params);
      const treeData = yield call(organizationService.findAuthorizedOrganizationTree, {
        ...params,
        ...payload,
        permissionWildcard: SystemPermissionConst.SYS_ORG_VIEW,
        deletedFlag:false
      });

      yield put({
        type: 'mergeState',
        payload: {
          treeData,
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
