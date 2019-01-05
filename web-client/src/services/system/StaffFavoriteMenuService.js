import { stringify } from 'qs';
import request from './../../utils/HttpInvoker';

async function addStaffFavoriteMenu(params) {
  return request(`/api/system/staff-favorite-menu/${params.menuId}`, {
    method: 'POST',
    body: params,
  });
}


async function deleteStaffFavoriteMenuByMenuId(params) {
  return request(`/api/system/staff-favorite-menu/${params.menuId}`, {
    method: 'DELETE',
  });
}

async function findStaffFavoriteMenuList(params) {
  return request(`/api/system/staff-favorite-menu/list?${stringify(params)}`, {
    method: 'GET',
  });
}

export default {
  addStaffFavoriteMenu,
  deleteStaffFavoriteMenuByMenuId,
  findStaffFavoriteMenuList
};
