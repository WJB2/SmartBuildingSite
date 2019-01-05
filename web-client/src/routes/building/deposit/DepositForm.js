import React, { PureComponent } from 'react';

import { Modal, Form, Input, InputNumber, Radio } from 'antd';
import OrganizationTreeSelector from './../../../components/system/form/OrganizationTreeSelector';
import StaffSelector from './../../../components/system/form/StaffSelector';

const FormItem = Form.Item;
const RadioGroup = Radio.Group;

@Form.create({})
class DepositForm extends PureComponent {
  handleFormSubmit(e) {
    e.preventDefault();
    const { onSubmit } = this.props;

    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err && onSubmit) {
        onSubmit(values);
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
    const { formType, currentDeposit } = this.props;
    const { getFieldDecorator } = this.props.form;

    const deposit = formType === 'EDIT' ? currentDeposit : {};

    const modalConfig = {
      title: '新建单据信息',
      visible: true,
      onOk: this.handleFormSubmit.bind(this),
      onCancel: this.handleCancel.bind(this),
    };

    const formItemLayout = {
      labelCol: {
        xs: { span: 8 },
        sm: { span: 8 },
      },
      wrapperCol: {
        xs: { span: 16 },
        sm: { span: 16 },
      },
    };

    return (
      <Modal {...modalConfig}>
        <Form>
            <FormItem {...formItemLayout} label="开发商">
              {getFieldDecorator('buildingDeveloperId', {
              rules: [{ required: true, message: '请选择开发商' }],
                initialValue: deposit.buildingDeveloperId,
            })(<OrganizationTreeSelector placeholder="请选择开发商" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="款项操作类别">
            {getFieldDecorator('depositType', {
              rules: [{ required: true, message: '请选择款项操作类别' }],
              initialValue: deposit.depositType,
            })(
              <RadioGroup>
                <Radio value={'DEPOSIT'}>存款</Radio>
                <Radio value={'RETURN'}>退款</Radio>
                <Radio value={'USE'}>发放工资</Radio>
              </RadioGroup>
            )}
          </FormItem>

          <FormItem {...formItemLayout} label="外部流水号">
            {getFieldDecorator('outCredenceId', {
              rules: [{ required: false, message: '请输入外部流水号' }],
              initialValue: deposit.outCredenceId,
            })(<Input placeholder="请输入外部流水号" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="金额">
            {getFieldDecorator('money', {
              rules: [{ required: true, message: '请输入金额' }],
              initialValue: deposit.money,
            })(<InputNumber style={{width:'100%'}} placeholder="请输入金额" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="备注">
            {getFieldDecorator('remark', {
            rules: [{ required: false, message: '请输入备注' }],
              initialValue: deposit.remark,
          })(<Input placeholder="请输入备注" />)}
        </FormItem>

      </Form>

      </Modal>
    );
  }
}

export default DepositForm;
