import React, { PureComponent, Fragment } from 'react';
import { Table, Divider, Tag } from 'antd';

import styles from './PositionIndex.less';

class PositionTable extends PureComponent {
  state = {};

  render() {
    const { onEdit, onDelete } = this.props;

    const columns = [
      {
        title: '序号',
        dataIndex: 'rowNo',
        key: 'rowNo',
        width:60
      },
      {
        title: '用户名',
        dataIndex: 'staff',
        key: 'staffUsername',
        width:80,
        render: val => {
          return val ? val.username : '';
        },
      },
      {
        title: '人员姓名',
        dataIndex: 'staff',
        key: 'staffName',
        width:80,
        render: val => {
          return val ? val.name : '';
        },
      },
      {
        title: '所属组织机构',
        dataIndex: 'org',
        key: 'orgName',
        width:80,
        render: val => {
          return val ? val.name : '';
        },
      },
      {
        title: '担任工作职位',
        dataIndex: 'post',
        key: 'postName',
        width:80,
        render: val => {
          return val ? val.name : '';
        },
      },
      {
        title: '所属角色',
        dataIndex: 'role',
        width:80,
        key: 'role',
        render: val => {
          if (!val) {
            return '';
          } else {
            return val.map(item => {
              return <Tag>{item.name}</Tag>;
            });
          }
        },
      },
      {
        title: '操作',
        width:80,
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
            <a
              onClick={() => {
                onDelete(record.id);
              }}
            >
              删除
            </a>
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
      <div className={styles.positionTable}>
        <Table {...tableConfigs} />
      </div>
    );
  }
}

export default PositionTable;
