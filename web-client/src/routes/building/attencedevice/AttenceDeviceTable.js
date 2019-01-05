import React, { PureComponent, Fragment } from 'react';
import { Table, Divider, Menu, Dropdown, Icon, Badge } from 'antd';

import styles from './AttenceDeviceIndex.less';

class AttenceDeviceTable extends PureComponent {
  state = {};

  render() {
    const { onEdit, onDelete } = this.props;

    const menu = (record)=>{
      return  <Menu>
          <Menu.Item key="delete">
            <a onClick={() => {
              onDelete(record.id);
            }}>删除</a>
          </Menu.Item>
        </Menu>
    };

    const columns = [
      {
        title: '所属开发商',
        dataIndex: 'buildingDeveloper',
        key: 'buildingDeveloper',
        width:80,
        render: (val, record)=>{
          return val?val.name:'';
        }
      },
      {
        title: '所属工地',
        dataIndex: 'buildingSite',
        key: 'buildingSite',
        width:80,
        render: (val, record)=>{
          return val?val.name:'';
        }
      },
      {
        title: '序列号',
        dataIndex: 'sn',
        key: 'sn',
        width:80,
      },
      {
        title: '设备型号',
        dataIndex: 'type',
        key: 'type',
        width:80,
      },
      {
        title: '在线标记',
        dataIndex: 'onlineFlag',
        key: 'onlineFlag',
        width:80,
        render(val){
          return <Badge status={val?'processing':'error'} text={val?'在线':'离线'} />
        }
      },
      {
        title: '备注信息',
        dataIndex: 'remark',
        key: 'remark',
        width:100,
      },
      {
        title: '操作',
        width:80,
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
      }
    };

    return (
      <div className={styles.attenceDeviceTable}>
        <Table {...tableConfigs} />
      </div>
    );
  }
}

export default AttenceDeviceTable;
