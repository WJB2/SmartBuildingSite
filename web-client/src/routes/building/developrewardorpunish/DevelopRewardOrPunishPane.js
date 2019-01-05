import React,{PureComponent} from 'react';
import {  Radio } from 'antd';

class DevelopRewardOrPunishPane extends PureComponent {


  handleChangeTable(e) {//根据index显示对应表格
    const { onChangeTableIndex }=this.props;

    if(onChangeTableIndex){
      onChangeTableIndex(e);
    }
  }

  render() {

    return (
      <div>
        <Radio.Group defaultValue={0} type="primary" onChange={this.handleChangeTable.bind(this)} >
          <Radio.Button value={0}>奖惩列表查询</Radio.Button>
          <Radio.Button value={1}>我上报的奖惩</Radio.Button>
        </Radio.Group>
      </div>
    );
  }
}

export default DevelopRewardOrPunishPane;
