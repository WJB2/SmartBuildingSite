import React, {PureComponent, Fragment} from 'react';
import moment from 'moment';
import {Table, Divider, Menu, Dropdown, Icon, Modal} from 'antd';

import depositTypeEnum from './../../../enum/building/DepositTypeEnum';

import styles from './DepositIndex.less';

class DepositDetailTable extends PureComponent {
  state = {};

  render() {
    const columns = [
      {
        title: '操作日期',
        dataIndex: 'createdTime',
        key: 'createdTime',
        width:80,
        render: (val) => {
          return val ? moment(val, 'YYYYMMDDHHmmssSSS').format('YYYY-MM-DD') : ''
        }
      },
      {
        title: '期初余额',
        dataIndex: 'initDeposit',
        key: 'initDeposit',
        width:80,
      },
      {
        title: '操作类型',
        dataIndex: 'depositType',
        key: 'depositType',
        width:80,
        render: (val) => {
          return depositTypeEnum.getDisplayText(val);
        }
      },
      {
        title: '操作金额',
        dataIndex: 'deposit',
        key: 'deposit',
        width:80,
      },
      {
        title: '期末余额',
        dataIndex: 'endDeposit',
        key: 'endDeposit',
        width:80,
        render:(val, record)=>{
          if(record.depositType==='DEPOSIT'){
            return record.initDeposit + record.deposit;
          }else{
            return record.initDeposit - record.deposit;
          }
        }
      },
    ];

    const tableConfigs = {
      columns,
      rowKey: 'id',
      ...this.props,

    };

    return (
      <Modal width={1000} visible={true} onCancel={this.props.onCancel}>
        <div className={styles.depositTable}>
          <Table {...tableConfigs} />
        </div>
      </Modal>
    );
  }
}

export default DepositDetailTable;
