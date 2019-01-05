import React, { PureComponent, Fragment } from 'react';
import { Table, Divider } from 'antd';

import styles from './PostIndex.less';

class PostTable extends PureComponent {
  state = {};

  render() {
    const { onEdit, onDelete } = this.props;

    const columns = [
      {
        title: '序号',
        dataIndex: 'rowNo',
        key: 'rowNo',
        width:60,
      },
      {
        title: '工作职位编码',
        dataIndex: 'code',
        key: 'code',
        width:80,
      },
      {
        title: '工作职位名称',
        dataIndex: 'name',
        key: 'name',
        width:80,
      },
      {
        title: '工作职位简称',
        dataIndex: 'nameAbbr',
        key: 'nameAbbr',
        width:80,
      },
      {
        title: '工作职位别名',
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
    }
    ;

    return (
      <div className={styles.postTable} {...this.props}>
        <Table {...tableConfigs} />
      </div>
    );
  }
}

export default PostTable;
