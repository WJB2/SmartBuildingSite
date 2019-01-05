import React, { PureComponent, Fragment } from 'react';
import { Table, Divider, Menu, Dropdown, Icon } from 'antd';
import moment from 'moment';

import styles from './EmployeeIndex.less';

class EmployeeTable extends PureComponent {
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
        title: '工号',
        dataIndex: 'code',
        key: 'code',
        width:60,
      },
      {
        title: '姓名',
        dataIndex: 'name',
        key: 'name',
        width:80,
      },
      {
        title: '身份证号',
        dataIndex: 'idCardNo',
        key: 'idCardNo',
        width:140,
      },
      {
        title: '开发商信息',
        dataIndex: 'buildingDeveloper',
        key: 'buildingDeveloper',
        width:150,
        render:(val)=>{
          return val?val.name:''
        }
      },
      {
        title: '工地信息',
        dataIndex: 'buildingSite',
        key: 'buildingSite',
        width:150,
        render:(val)=>{
          return val?val.name:''
        }
      },
      {
        title: '联系地址',
        dataIndex: 'address',
        key: 'address',
        width:100,
      },
      {
        title: '手机号',
        dataIndex: 'mobile',
        key: 'mobile',
        width:80
      },
      {
        title: '入职日期',
        dataIndex: 'registerTime',
        key: 'registerTime',
        width:80,
        render: (val, record) =>{
          return moment(val, 'YYYYMMDDHHmmssSSS').format('YYYY-MM-DD');
        }
      },
      {
        title: '状态',
        width:60,
        align:"center",
        dataIndex: 'unregisted',
        key: 'unregisted',
        render:(val, record)=>{
          return val?'离职':'在职'
        }
      },
      {
        title: '离职日期',
        dataIndex: 'unregisterTime',
        key: 'unregisterTime',
        width:80,
        align:"center",
      },
      {
        title: '操作',
        width:100,
        align:"center",
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
              <span className="ant-dropdown-link" >
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

export default EmployeeTable;
