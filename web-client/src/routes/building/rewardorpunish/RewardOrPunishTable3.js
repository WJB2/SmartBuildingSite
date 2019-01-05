import { Table,Button,Form,Input } from 'antd';
import React,{PureComponent,Fragment} from 'react';
const FormItem = Form.Item;
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

  handleAdd(){
    const {onAdd}=this.props;
    if(onAdd){
      onAdd();
    }
  }

  render() {
    const { onView } = this.props;
    const columns = [
      {
        title: '序号',
        dataIndex: 'rowNo',
        key: 'rowNo',
        table:60,
      },
      {
        title: '人员姓名',
        dataIndex: 'name',
        key: 'name',
        table:80,
      },
      {
        title: '身份证号',
        dataIndex: 'idCardNo',
        key: 'idCardNo',
        table:80,
      },
      {
        title: '奖惩类型',
        dataIndex: 'type',
        key: 'type',
        table:80,
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
        table:80,
      },
      {
        title: '审批结果',
        dataIndex: 'approved',
        key: 'approved',
        table:80,
        render:(approved)=>{
          if(approved){
            return '同意';
          }
          if(!approved){
            return '不同意';
          }
          return '';
        }
      },
      {
        title: '审批意见',
        dataIndex: 'auditRemark',
        key: 'auditRemark',
        table:80,
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
          <FormItem label="人员姓名">
            <Input placeholder="请写入人员姓名" />
          </FormItem>
          <FormItem label="身份证号">
            <Input placeholder="请写入身份证号" />
          </FormItem>
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
