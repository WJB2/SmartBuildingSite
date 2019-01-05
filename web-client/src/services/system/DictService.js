import { stringify } from 'qs';

import request from "../../utils/HttpInvoker";

async function addDictEntry(params) {
  return request('/api/global/dict/entry', {
    method: 'POST',
    body: params,
  });
}

async function editDictEntry(params) {
  return request(`/api/global/dict/entry/${params.id}`, {
    method: 'PUT',
    body: params,
  });
}

async function deleteDictEntryById(params) {
  return request(`/api/global/dict/entry/${params.id}`, {
    method: 'DELETE',
  });
}

async function findDictEntryById(params) {
  return request(`/api/global/dict/entry/${params.id}`, {
    method: 'GET',
  });
}

async function findDictEntryList(params) {
  return request(`/api/global/dict/entry/list?${stringify(params)}`, {
    method: 'GET',
  });
}

async function findDictEntryPage(params) {
  return request(`/api/global/dict/entry/page?${stringify(params)}`, {
    method: 'GET',
  });
}


async function addDictItem(params) {
  return request('/api/system/dict/item', {
    method: 'POST',
    body: params,
  });
}

async function editDictItem(params) {
  return request(`/api/system/dict/item/${params.id}`, {
    method: 'PUT',
    body: params,
  });
}

async function deleteDictItemById(params) {
  return request(`/api/system/dict/item/${params.id}`, {
    method: 'DELETE',
  });
}

async function findDictItemById(params) {
  return request(`/api/system/dict/item/${params.id}`, {
    method: 'GET',
  });
}

async function findDictItemList(params) {
  return request(`/api/system/dict/item/list?${stringify(params)}`, {
    method: 'GET',
  });
}

async function findDictItemPage(params) {
  return request(`/api/system/dict/item/page?${stringify(params)}`, {
    method: 'GET',
  });
}

export default {
  addDictEntry,
  editDictEntry,
  deleteDictEntryById,
  findDictEntryById,
  findDictEntryList,
  findDictEntryPage,

  addDictItem,
  editDictItem,
  deleteDictItemById,
  findDictItemById,
  findDictItemList,
  findDictItemPage
};
