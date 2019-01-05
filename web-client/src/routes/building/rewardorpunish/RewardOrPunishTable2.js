import { Table,Button,Form,Input,Divider} from 'antd';
import React,{PureComponent,Fragment} from 'react';
const FormItem = Form.Item;
import styles from './RewardOrPunishIndex.less'
@Form.create({
  onValuesChange: (props, values) => {
    props.onParamsChange(values);
  },
})
class RewardOrPunishTable extends PureComponent {

  handleSearch() {
    const { onSearch } = this.props;

    if (onSearch) {
      onSearch();
    }
  }

  render() {
    const { onAudit, onDelete } = this.props;
    const { getFieldDecorator }=this.props.form;
    const columns = [
      {
        title: '序号',
        dataIndex: 'rowNo',
        key: 'rowNo',
        width:60,
        align:"center",
      },
      {
        title: '人员姓名',
        dataIndex: 'name',
        key: 'name',
        width:80,
      },
      {
        title: '身份证号',
        dataIndex: 'idCardNo',
        key: 'idCardNo',
        width:80,
      },
      {
        title: '奖惩类型',
        dataIndex: 'type',
        key: 'type',
        width:80,
        render:(val)=>{
          if(val==='PUNISH'){
            return '惩罚';
          }

          if(val==='REWARD'){
            return '奖励';
          }

          return '';
        },
      },
      {
        title: '奖惩内容',
        dataIndex: 'content',
        key: 'content',
        width:80,
      },
      {
        title: '操作',
        width:80,
        render: (val, record) => (
          <Fragment>
            <a
              onClick={() => {
                onAudit(record.id);
              }}
            >
              审批
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
      <div>
        <Form onSubmit={this.handleSearch} layout="inline" >
          <FormItem label="人员姓名">{getFieldDecorator('name')(<Input />)}</FormItem>
          <FormItem label="身份证号">{getFieldDecorator('IdCardNo')(<Input />)}</FormItem>
          <FormItem>
            <Button type="primary" onClick={this.handleSearch.bind(this)}>查询</Button>
          </FormItem>
        </Form>
        <Table  {...tableConfigs} />
      </div>
    );
  }
}


export default RewardOrPunishTable;
