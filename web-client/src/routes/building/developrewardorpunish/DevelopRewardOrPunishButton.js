import { Table,Button,Form,Input} from 'antd';
import React,{PureComponent,Fragment} from 'react';
const FormItem = Form.Item;
import styles from './DevelopRewardOrPunishIndex.less';
import {connect} from "dva/index";

@Form.create({})
class DevelopRewardOrPunishButton extends PureComponent{
  handleAdd(){
    const {onAdd}=this.props;
    if(onAdd){
      onAdd();
    }
  }

  render() {
    return (
      <div>
        <Button type="primary"  onClick={this.handleAdd.bind(this)}>
          新增奖惩信息
        </Button>
      </div>
    );
  }
}
export default DevelopRewardOrPunishButton;
