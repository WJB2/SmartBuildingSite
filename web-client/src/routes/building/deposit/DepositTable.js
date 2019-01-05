import React, { PureComponent, Fragment } from 'react';
import { Table, Divider, Menu, Dropdown, Icon } from 'antd';

import styles from './DepositIndex.less';

class DepositTable extends PureComponent {
  state = {};

  render() {
    const { onView } = this.props;

    const columns = [
      {
        title: '统一社会信用代码',
        dataIndex: 'buildingDeveloper',
        key: 'creditCode',
        width:100,
        render(val, record){
          return val?val.creditCode:'';
        }
      },
      {
        title: '开发商名称',
        dataIndex: 'buildingDeveloper',
        key: 'name',
        width:100,
        render(val, record){
          return val?val.name:'';
        }
      },
      {
        title: '法人姓名',
        dataIndex: 'buildingDeveloper',
        key: 'corporation',
        width:80,
        render(val, record){
          return val?val.corporation:'';
        }
      },
      {
        title: '法人手机号',
        dataIndex: 'buildingDeveloper',
        key: 'corporationMobile',
        width:100,
        render(val, record){
          return val?val.corporationMobile:'';
        }
      },
      {
        title: '联系电话',
        dataIndex: 'buildingDeveloper',
        key: 'telephone',
        width:100,
        render(val, record){
          return val?val.telephone:'';
        }
      },
      {
        title: '传真号码',
        dataIndex: 'buildingDeveloper',
        key: 'fax',
        width:100,
        render(val, record){
          return val?val.fax:'';
        }
      },
      {
        title: '押金金额',
        dataIndex: 'deposit',
        key: 'deposit',
        width:60,
      },
      {
        title: '操作',
        width:80,
        align:"center",
        render: (val, record) => (
          <Fragment>
            <a
              onClick={() => {
                onView(record.buildingDeveloper.orgId);
              }}
            >
              查看明细
            </a>
          </Fragment>
        ),
      },
    ];

    const tableConfigs = {
      columns,
      rowKey: 'id',
      scroll:{x:1600},
      ...this.props,
    };

    return (
      <div className={styles.buildingDeveloperTable}>
        <Table {...tableConfigs} />
      </div>
    );
  }
}

export default DepositTable;
