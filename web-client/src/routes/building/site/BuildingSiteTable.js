import React, { PureComponent, Fragment } from 'react';
import { Table, Divider, Menu, Dropdown, Icon } from 'antd';

import styles from './BuildingSiteIndex.less';

class BuildingSiteTable extends PureComponent {
  state = {};

  render() {
    const { onEdit, onDelete } = this.props;

    const menu = (record)=>{
      return <Menu>
        <Menu.Item key="delete">
          <a onClick={() => {
            onDelete(record.id);
          }}>{record.deletedFlag?'还原':'删除'}</a>
        </Menu.Item>
      </Menu>
    };

    const columns = [
      {
        title: '工地名称',
        dataIndex: 'name',
        key: 'name',
        width:100
      },
      {
        title: '所属开发商',
        dataIndex: 'buildingDeveloper',
        key: 'buildingDeveloper',
        render: (val, record)=>{
          return val?val.name:'';
        },
        width:100
      },
      {
        title: '工地地址',
        dataIndex: 'address',
        key: 'address',
        width:100
      },
      {
        title: '负责人',
        dataIndex: 'adminStaff',
        key: 'adminStaff',
        render:(val, record)=>{
          return val?val.name:'';
        },
        width:80
      },
      {
        title: '操作',
        align:"center",
        width:60,
        render: (val, record) => (
          <Fragment>
            <a
              onClick={() => {
                onEdit(record.id);
              }}
            >
              编辑
            </a>
            <Divider type="vertical" />
            <Dropdown overlay={menu(record)}>
              <a className="ant-dropdown-link" href="#">
                更多<Icon type="down" />
              </a>
            </Dropdown>
          </Fragment>
        ),
      },
    ];

    const tableConfigs = {
      columns,
      rowKey: 'id',
      ...this.props,
      scroll:{
        x:1600
      },
      rowClassName:(record, idx)=>{
        return record.deletedFlag?styles.deletedRow:'';
      }
    };

    return (
      <div className={styles.buildingSiteTable}>
        <Table {...tableConfigs} />
      </div>
    );
  }
}

export default BuildingSiteTable;
