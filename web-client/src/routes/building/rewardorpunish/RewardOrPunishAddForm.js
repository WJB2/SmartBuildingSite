import React, { PureComponent } from 'react';
import BuildingSiteSelector from './../../../components/building/form/BuildingSiteSelector';
import { Modal, Form, Input,Radio,Button} from 'antd';
const RadioGroup = Radio.Group;
const FormItem=Form.Item;
@Form.create({})
class DevelopRewardOrPunishForm extends React.Component {

  handleFormSubmit(e) {
    e.preventDefault();
    const { onSubmit } = this.props;

    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err && onSubmit) {
        onSubmit(values);
        console.log(values);
      }
    });
  }
  handleCancel(e) {
    e.preventDefault();
    const { onCancel } = this.props;
    if (onCancel) {
      onCancel();
    }
  }

  render() {
    const {global}=this.props;
    const { getFieldDecorator } = this.props.form;
    const modalConfig = {
      title: '新建奖惩人员信息',
      visible: true,
      onOk: this.handleFormSubmit.bind(this),
      onCancel: this.handleCancel.bind(this),
    };

    const formItemLayout = {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 8 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 },
      },
    };
    return (
      <Modal {...modalConfig}>
        <Form onSubmit={this.handleFormSubmit}>
          <FormItem {...formItemLayout} label="所属工地">
            {getFieldDecorator('buildingSiteId', {
              rules: [{ required: true, message: '请选择工地' }],
              initialValue: null,
            })(<BuildingSiteSelector  queryParams={{buildingDeveloperId:global.currentPosition.orgId }} placeholder="请选择所属工地" />)}
          </FormItem>
          <FormItem{...formItemLayout} label="人员姓名">
            {getFieldDecorator('name', {rules: [{ message:'',
              }, {required: true, message: 'Please input your staffname!',}],})(<Input />)}
          </FormItem>
          <FormItem{...formItemLayout} label="身份证号">
            {getFieldDecorator('idCardNo', {rules: [{ message: 'idnumber',
              }, {required: true, message: 'Please input your idnumber!',}],})(<Input />)}
          </FormItem>
          <FormItem{...formItemLayout} label="当前职位">
            {getFieldDecorator('currentPositionId', {rules: [{ message:'',
              }, {required: true, message: 'Please input your staffname!',}],})(<Input />)}
          </FormItem>
          <FormItem{...formItemLayout} label="奖惩类型">
            {getFieldDecorator('type')(
              <RadioGroup><Radio value="REWARD">奖励</Radio><Radio value="PUNISH">惩罚</Radio></RadioGroup>)}
          </FormItem>

          <FormItem{...formItemLayout} label="奖惩内容">
            {getFieldDecorator('content', {
              rules: [{ message: 'content',
              }, {required: false, message: 'Please input rpcontent!',}],})(<textarea/>)}
          </FormItem>
        </Form>
      </Modal>
    );
  }
}
export default DevelopRewardOrPunishForm;


