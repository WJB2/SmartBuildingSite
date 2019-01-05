import { Table,Form,Input,Button ,Pagination} from 'antd';
import React,{ PureComponent,Fragment} from 'react';
import styles from './DevelopRewardOrPunishIndex.less';
const FormItem = Form.Item;
@Form.create({
  onValuesChange: (props, values) => {
    props.onParamsChange(values);
  },
})
class DevelopRewardOrPunishTable extends PureComponent {//不帶有新增按鈕的表格

  handleSearch() {
    const { onSearch } = this.props;

    if (onSearch) {
      onSearch();
    }
  }

  render() {
    const { form } = this.props;
    const { getFieldDecorator} = form;
    const columns = [
      {
        title: '序号',
        dataIndex: 'rowNo',
        key: 'rowNo',
        width:50,
        align:"center",
      },
      {
        title: '人员姓名',
        dataIndex: 'name',
        key: 'name',
        width:90,
      },
      {
        title: '身份证号',
        dataIndex: 'idCardNo',
        key: 'idCardNo',
        width:150,
      },
      {
        title: '奖惩类型',
        dataIndex: 'type',
        key: 'type',
        width: 100,
        align:"center",
        render:(val)=>{
          if(val==='PUNISH'){
            return '惩罚';
          }

          if(val==='REWARD'){
            return '奖励';
          }

          return '';
        }
      },
      {
        title: '奖惩内容',
        dataIndex: 'content',
        key: 'content',
        width:100,
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
      <div>
        <Form onSubmit={this.handleSearch.bind(this)} layout="inline">
          <FormItem label="人员姓名">{getFieldDecorator('name')(<Input />)}</FormItem>
          <FormItem label="身份证号">{getFieldDecorator('IdCardNo')(<Input />)}</FormItem>
          <span className={styles.developRewardOrPublishIndexOperator}>
            <Button type="primary" htmlType="submit" onClick={this.handleSearch.bind(this)}>
              查询
            </Button>
          </span>
        </Form>
        <Table className={styles.developRewardOrPublishTable} {...tableConfigs} />
      </div>
    );
  }
}
export default DevelopRewardOrPunishTable;
