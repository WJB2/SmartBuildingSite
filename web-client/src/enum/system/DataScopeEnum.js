const ALL = 'ALL';
const MY_ORGANIZATION_TREE = 'MY_ORGANIZATION_TREE';
const MY_ORGANIZATION = 'MY_ORGANIZATION';
const SELF = 'SELF';
const SPECIFIED = 'SPECIFIED';

function getDisplayText(value) {
  if (!value) {
    return '所有';
  }

  if (value === 'ALL') {
    return '所有';
  }

  if (value === 'MY_ORGANIZATION_TREE') {
    return '本部门及下级部门';
  }

  if (value === 'MY_ORGANIZATION') {
    return '本部门';
  }

  /*if (value === 'SELF') {
    return '自己创建的数据';
  }

  if (value === 'SPECIFIED') {
    return '指定';
  }*/
}

function toList(){

  return [ALL, MY_ORGANIZATION_TREE, MY_ORGANIZATION/*, SELF, SPECIFIED*/];
}

export default {
  ALL,
  MY_ORGANIZATION_TREE,
  MY_ORGANIZATION,
  /*SELF,
  SPECIFIED,*/

  toList,
  getDisplayText,
};
