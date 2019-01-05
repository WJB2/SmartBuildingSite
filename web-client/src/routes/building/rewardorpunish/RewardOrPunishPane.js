import React,{PureComponent} from 'react';
import {  Radio } from 'antd';

import styles from './RewardOrPunishIndex.less';

class RewardOrPunishPane extends PureComponent {


  handleChangeTable(e) {//根据index显示对应表格
    const { onChangeTableIndex }=this.props;

    if(onChangeTableIndex){
      onChangeTableIndex(e);
    }
  }

  render() {

    return (
      <div>
        <Radio.Group defaultValue={0} onChange={this.handleChangeTable.bind(this)} >
          <Radio.Button value={0}>奖惩列表查询</Radio.Button>
          <Radio.Button value={1}>待我审批的奖惩</Radio.Button>
          <Radio.Button value={2}>我已审批的奖惩</Radio.Button>
        </Radio.Group>
      </div>
    );
  }
}

export default RewardOrPunishPane;
