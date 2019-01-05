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
      onCancel(e);
    }
  }

  render() {

    const {global,formType,auditFormContent}=this.props;
    const { getFieldDecorator} = this.props.form;
    const RewardOrPunish = formType === 'audit' ? auditFormContent : {};
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
          <FormItem{...formItemLayout} label="所属工地">
            {getFieldDecorator('buildingSiteId', {rules: [{ message: 'buildingSiteId',
              }, {required: true, message: 'Please input your idnumber!',}],
              initialValue: RewardOrPunish.buildingSiteId,
            })(<BuildingSiteSelector  disabled={true} queryParams={{buildingDeveloperId:global.currentPosition.orgId }} placeholder="请选择所属工地" />)}
          </FormItem>
          <FormItem{...formItemLayout} label="人员姓名">
            {getFieldDecorator('name', {rules: [{ message: 'name',
              }, {required: true, message: 'Please input your idnumber!',}],
              initialValue: RewardOrPunish.name,
            })(<Input disabled={true}/>)}
          </FormItem>
          <FormItem{...formItemLayout} label="身份证号">
            {getFieldDecorator('idCardNo', {rules: [{ message: 'idCardNo',
              }, {required: true, message: 'Please input your idnumber!',}],
              initialValue: RewardOrPunish.idCardNo,
            })(<Input disabled={true}/>)}
          </FormItem>
          <FormItem{...formItemLayout} label="奖惩类型">
            {getFieldDecorator('type', {rules: [{ message: 't',
              }, {required: true}],
              initialValue: RewardOrPunish.type,
            })(<RadioGroup><Radio value="REWARD">奖励</Radio><Radio value="PUNISH">惩罚</Radio></RadioGroup>)}
          </FormItem>
          <FormItem{...formItemLayout} label="奖惩内容">
            {getFieldDecorator('content', {rules: [{ message: '',
              },],
              initialValue: RewardOrPunish.content,
            })(<textarea disabled={true} value=""/>) }
            </FormItem>
          <FormItem{...formItemLayout} label="审批结果">
            {getFieldDecorator('approved', {rules: [{ message: '请选择你的审批结果',
              }, {required: true}],
            })(<RadioGroup><Radio value="true">同意</Radio><Radio value="false">拒绝</Radio></RadioGroup>)}
          </FormItem>
          <FormItem{...formItemLayout} label="审批意见">
            {getFieldDecorator('auditRemark', {rules: [{ message: '请选择你的审批结果',
            }, {required: true}],
          })(<textarea value=""/>)}</FormItem>
        </Form>
      </Modal>
    );
  }
}
export default DevelopRewardOrPunishForm;


