import React, { PureComponent } from 'react';
import OrganizationTreeSelector from './../../../components/system/form/OrganizationTreeSelector';
import BuildingSiteSelector from './../../../components/building/form/BuildingSiteSelector';
import BuildingDevelopRewardOrPunishSelector from './../../../components/building/form/BuildingDevelopRewardOrPunishSelector';
import { Modal, Form, Input,Radio,Select} from 'antd';
const RadioGroup = Radio.Group;
const Option=Select.Option;
const FormItem=Form.Item;
@Form.create({})
class DevelopRewardOrPunishForm extends React.Component {

  handleFormSubmit(e) {//提交表单
    e.preventDefault();
    const { onSubmit } = this.props;

    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err && onSubmit) {
        onSubmit(values);
      }
    });
  }

  handleCancel(e) {//取消表单
    e.preventDefault();
    const { onCancel } = this.props;
    if (onCancel) {
      onCancel();
    }
  }

  render() {
    const { getFieldDecorator } = this.props.form;
    const { global,currentRewardOrPunish,formType }=this.props;

    const rewardOrPunish = formType === 'edit' ? currentRewardOrPunish : {};
    const modalConfig = {
      title: formType==='edit'?'编辑奖惩':'新建奖惩',
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
              initialValue: rewardOrPunish.buildingSiteId,
            })(<BuildingSiteSelector  queryParams={{buildingDeveloperId:global.currentPosition.orgId }} placeholder="请选择所属工地" />)}
          </FormItem>
          <FormItem{...formItemLayout} label="人员姓名">
            {getFieldDecorator('name', {
              rules: [{ message:'',},{required: true, message: '请填写姓名',}],
              initialValue: rewardOrPunish.name,
            })(<Input />)}
          </FormItem>
          <FormItem{...formItemLayout} label="身份证号">
            {getFieldDecorator('idCardNo',{
              rules: [{ message: 'idnumber',}, {required: true, message: '请填写身份证号',}],
              initialValue: rewardOrPunish.idCardNo,})(<Input />)}
          </FormItem>
          <FormItem{...formItemLayout} label="奖惩类型">
            {getFieldDecorator('type',{
              rules: [{ message:'',}, {required: true, message: '请填写你的当前职位',}],
              initialValue: rewardOrPunish.type,})(
              <RadioGroup><Radio value="REWARD">奖励</Radio><Radio value="PUNISH">惩罚</Radio></RadioGroup>)}
          </FormItem>
          <FormItem{...formItemLayout} label="奖惩内容">
            {getFieldDecorator('content',{
            rules: [{ message: 'content',},{required: true, message: '请填写你的奖惩内容',}],
              initialValue: rewardOrPunish.content,})(<textarea value=""/>)}
          </FormItem>
        </Form>
      </Modal>
    );
  }
}
export default DevelopRewardOrPunishForm;


