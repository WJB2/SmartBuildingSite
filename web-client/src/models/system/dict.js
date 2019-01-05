import ObjectUtils from "../../utils/ObjectUtils";

import dictService from "../../services/system/DictService"

export default {

  namespace : 'system/dict',

  state:{
    currentEntry:null,
    entryFormVisible:false,
    entryFormType: null,
    entryParams:{},
    entryDataLoading:true,
    entryData: {
      list:[],
      pagination:{},
    },

    currentItem:null,
    itemFormVisible:false,
    itemFormType: null,
    itemParams:{},
    itemDataLoading:false,
    itemData: {
      list:[],
      pagination:{},
    }
  },

  effects : {

    *initialize({payload}, {select, put, call}){
      yield put({
        type : 'queryEntryPageAsync',
        payload: {}
      });
    },

    *queryEntryPageAsync({payload}, {select, put, call}){
      const entryParams = yield select(state=>state['system/dict'].entryParams);

      const pageInfo = yield call(dictService.findDictEntryPage, {
          ...entryParams,
        ...payload
      });

      pageInfo.list.forEach((item, index)=>{
        item.rowNo = index + 1 + (pageInfo.pageNum-1)*pageInfo.pageSize;
      });

      yield put({
        type: 'mergeState',
        payload: {
          entryDataLoading:false,
          entryParams:{
            ...entryParams,
            ...payload,
          },
          entryData: {
            list: pageInfo.list,
            pagination: {
              total: pageInfo.total,
              current: pageInfo.pageNum,
              pageSize: pageInfo.pageSize,
            },
          },
        }
      });
    },

    *addDictItemAsync({payload}, {select, put, call}){
      yield call(dictService.addDictItem, payload);

      yield put({
        type: 'updateState',
        payload: {
          itemFormVisible: false
        }
      });

      yield put({
        type : 'queryItemPageAsync',
        payload: {}
      });
    },

    *editDictItemAsync({payload}, {select, put, call}){
      yield call(dictService.editDictItem, payload);

      yield put({
        type: 'updateState',
        payload: {
          itemFormVisible: false
        }
      });

      yield put({
        type : 'queryItemPageAsync',
        payload: {}
      });
    },

    *deleteDictItemByIdAsync({payload}, {select, put, call}){
      yield call(dictService.deleteDictItemById, payload);

      yield put({
        type : 'queryItemPageAsync',
        payload: {}
      });
    },

    *queryItemPageAsync({payload}, {select, put, call}){

      const {itemParams} = yield select(state=>state['system/dict']);

      yield put({
        type : 'updateState',
        payload : {
          itemDataLoading: true,
        }
      });

      const pageInfo = yield call(dictService.findDictItemPage, {
        ...itemParams,
        ...payload
      });

      pageInfo.list.forEach((item, index)=>{
        item.rowNo = index + 1 + (pageInfo.pageNum-1)*pageInfo.pageSize;
      });

      yield put({
        type: 'mergeState',
        payload: {
          itemDataLoading:false,
          ...payload,
          itemParams:{
            ...itemParams,
            ...payload,
          },
          itemData: {
            list: pageInfo.list,
            pagination: {
              total: pageInfo.total,
              current: pageInfo.pageNum,
              pageSize: pageInfo.pageSize,
            },
          },
        }
      });
    },
  },

  reducers : {
    updateState(state, { payload }) {
      return { ...state, ...payload };
    },

    mergeState(state, { payload }) {

      return ObjectUtils.mergeDeep(state, payload);
    },
  },

}
