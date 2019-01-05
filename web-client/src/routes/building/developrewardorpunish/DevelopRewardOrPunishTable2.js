import { Table,Form,Input,Button,Divider,} from 'antd';
import React,{ PureComponent,Fragment} from 'react';
import styles from './DevelopRewardOrPunishIndex.less';
import OrganizationTreeSelector from './../../../components/system/form/OrganizationTreeSelector';
const FormItem = Form.Item;
@Form.create({
  onValuesChange: (props, values) => {
    props.onParamsChange(values);
  },
})
class DevelopRewardOrPunishTable2 extends PureComponent {//不帶有新增按鈕的表格

  handleSearch() {
    const { onSearch } = this.props;
    if (onSearch) {
      onSearch();
    }
  }

  render() {
    const { onEdit, onDelete } = this.props;
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
        width:70
      },
      {
        title: '身份证号',
        dataIndex: 'idCardNo',
        key: 'idCardNo',
        width:80
      },
      {
        title: '奖惩类型',
        dataIndex: 'type',
        key: 'type',
        width:80,
        align:"center",
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
        width:80
      },
      {
        title: '操作',
        width:80,
        align:"center",
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
      scroll:{
        x:1600
      },
      ...this.props,
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


export default DevelopRewardOrPunishTable2;
