import React, { PureComponent, Fragment } from 'react';
import { Table, Divider, Menu, Dropdown, Icon } from 'antd';

import styles from './WorkTypeIndex.less';

class WorkTypeTable extends PureComponent {
  state = {};

  render() {
    const { onEdit, onDelete } = this.props;

    const menu = (record)=>{
      return <Menu>
        <Menu.Item key="delete">
          <a onClick={() => {
            onDelete(record.id);
          }}>删除</a>
        </Menu.Item>
      </Menu>
    };

    const columns = [
      {
        title: '工种名称',
        dataIndex: 'name',
        key: 'name',
        width:60
      },
      {
        title: '显示顺序',
        dataIndex: 'sortNo',
        key: 'sortNo',
        width:80
      },
      {
        title: '操作',
        width:40,
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
      <div className={styles.workTypeTable}>
        <Table {...tableConfigs} />
      </div>
    );
  }
}

export default WorkTypeTable;
