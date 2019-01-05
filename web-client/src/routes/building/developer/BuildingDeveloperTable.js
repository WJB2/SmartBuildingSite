import React, { PureComponent, Fragment } from 'react';
import { Table, Divider, Menu, Dropdown, Icon } from 'antd';

import styles from './BuildingDeveloperIndex.less';

class BuildingDeveloperTable extends PureComponent {
  state = {};

  render() {
    const { onEdit, onDelete } = this.props;

    const menu = (record) => {
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
        title: '统一社会信用代码',
        dataIndex: 'creditCode',
        key: 'creditCode',
        width:100,
      },
      {
        title: '开发商名称',
        dataIndex: 'name',
        key: 'name',
        width:100,
      },
      {
        title: '法人姓名',
        dataIndex: 'corporation',
        key: 'corporation',
        width:100,
      },
      {
        title: '法人手机号',
        dataIndex: 'corporationMobile',
        key: 'corporationMobile',
        width:100,
      },
      {
        title: '管理员用户',
        dataIndex: 'adminStaff',
        key: 'adminStaff',
        width:100,
        render:(val)=>{
          return val?val.name:'';
        }
      },
      {
        title: '联系电话',
        dataIndex: 'telephone',
        key: 'telephone',
        width:100,
      },
      {
        title: '传真号码',
        dataIndex: 'fax',
        key: 'fax',
        width:100,
      },
      {
        title: '联系地址',
        dataIndex: 'address',
        key: 'address',
        width:100,
      },
      {
        title: '银行账户号',
        dataIndex: 'bankNo',
        key: 'bankNo',
        width:100,
      },
      {
        title: '操作',
        width:100,
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
      scroll:{
        x:1600
      },
      rowClassName:(record, idx)=>{
        return record.deletedFlag?styles.deletedRow:'';
      }
    };

    return (
      <div className={styles.buildingDeveloperTable}>
        <Table {...tableConfigs} />
      </div>
    );
  }
}

export default BuildingDeveloperTable;
