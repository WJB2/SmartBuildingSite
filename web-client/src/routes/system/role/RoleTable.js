import React, { PureComponent, Fragment } from 'react';
import { Table, Divider, Menu, Dropdown, Icon } from 'antd';

import styles from './RoleIndex.less';

class RoleTable extends PureComponent {
  state = {};

  render() {
    const { onEdit, onDelete, onAuthorize } = this.props;

    const menu = (record)=>{
      return (
        <Menu>
          <Menu.Item key="edit">
            <a onClick={
              ()=>{
                onEdit(record.id)
              }
            }>编辑</a>
          </Menu.Item>
          <Menu.Item key="delete">
            <a onClick={
              ()=>{
                onDelete(record.id)
              }
            }>删除</a>
          </Menu.Item>
        </Menu>
      );
    };

    const columns = [
      {
        title: '序号',
        dataIndex: 'rowNo',
        key: 'rowNo',
        width:60
      },
      {
        title: '角色编码',
        dataIndex: 'code',
        key: 'code',
        width:80,
      },
      {
        title: '角色名称',
        dataIndex: 'name',
        key: 'name',
        width:80,
      },
      {
        title: '角色简称',
        dataIndex: 'nameAbbr',
        key: 'nameAbbr',
        width:80,
      },
      {
        title: '角色别名',
        dataIndex: 'nameAlias',
        key: 'nameAlias',
        width:80,
      },
      {
        title: '操作',
        width:80,
        render: (val, record) => (
          <Fragment>
            <a
              onClick={() => {
                onAuthorize(record.id);
              }}
            >
              授权
            </a>
            <Divider type="vertical" />
            <Dropdown overlay={menu(record)}>
              <span className="ant-dropdown-link" href="#">
                更多<Icon type="down" />
              </span>
            </Dropdown>
          </Fragment>
        ),
      },
    ];

    const tableConfigs = {
      columns,
      rowKey: 'id',
      ...this.props,
      scroll:1600
    };

    return (
      <div className={styles.roleTable}>
        <Table {...tableConfigs} />
      </div>
    );
  }
}

export default RoleTable;
