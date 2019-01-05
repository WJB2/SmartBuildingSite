import React, { PureComponent } from 'react';

import { Modal, Form, Input, InputNumber } from 'antd';
import StaffSelector from './../../../components/system/form/StaffSelector';

const FormItem = Form.Item;

@Form.create({})
class BuildingSiteForm extends PureComponent {
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
    const { formType, currentBuildingSite } = this.props;
    const { getFieldDecorator } = this.props.form;

    const buildingSite = formType === 'EDIT' ? currentBuildingSite : {};

    const modalConfig = {
      title: formType === 'EDIT' ? '编辑工地信息' : '新建工地信息',
      visible: true,
      onOk: this.handleFormSubmit.bind(this),
      onCancel: this.handleCancel.bind(this),
    };

    const formItemLayout = {
      labelCol: {
        xs: { span: 6 },
        sm: { span: 6 },
      },
      wrapperCol: {
        xs: { span: 18 },
        sm: { span: 18 },
      },
    };

    return (
      <Modal {...modalConfig}>
        <Form>
          <FormItem {...formItemLayout} label="工地名称">
            {getFieldDecorator('name', {
              rules: [{ required: true, message: '请输工地工地名称' }],
              initialValue: buildingSite.name,
            })(<Input placeholder="请输工地名称" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="工地地址">
            {getFieldDecorator('address', {
              rules: [{ required: false, message: '请输入工地地址' }],
              initialValue: buildingSite.address,
            })(<Input placeholder="请输入工地地址" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="工地负责人">
            {getFieldDecorator('adminStaffId', {
              rules: [{ required: false, message: '请选择工地负责人' }],
              initialValue: buildingSite.adminStaffId,
            })(<StaffSelector placeholder="请选择工地负责人" />)}
          </FormItem>
      </Form>

      </Modal>
    );
  }
}

export default BuildingSiteForm;
