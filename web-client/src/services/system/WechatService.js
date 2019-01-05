import { stringify } from 'qs';
import request from './../../utils/HttpInvoker';

async function addWxAppInfo(params) {
  return request('/api/wechat/wx-app-info', {
    method: 'POST',
    body: params,
  });
}

async function editWxAppInfo(params) {
  return request(`/api/wechat/wx-app-info/${params.id}`, {
    method: 'PUT',
    body: params,
  });
}

async function findWxAppInfo(params) {
  return request(`/api/wechat/wx-app-info`, {
    method: 'GET',
  });
}


export default {
  addWxAppInfo,
  editWxAppInfo,
  findWxAppInfo,
}
