import React, { PureComponent, Fragment } from 'react';
import { Table, Divider } from 'antd';

import styles from './OrganizationIndex.less';

class OrganizationTable extends PureComponent {
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
        title: '组织机构编码',
        dataIndex: 'code',
        key: 'code',
        width:160
      },
      {
        title: '组织机构名称',
        dataIndex: 'name',
        key: 'name',
        width:180
      },
      {
        title: '组织机构简称',
        dataIndex: 'nameAbbr',
        key: 'nameAbbr',
        width:80
      },
      {
        title: '组织机构别名',
        dataIndex: 'nameAlias',
        key: 'nameAlias',
        width:80
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
      <div className={styles.organizationTable} {...this.props}>
        <Table {...tableConfigs}/>
      </div>
    );
  }
}

export default OrganizationTable;
