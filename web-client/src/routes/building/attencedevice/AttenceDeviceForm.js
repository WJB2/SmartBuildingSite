import React, { PureComponent } from 'react';

import { Modal, Form, Input, InputNumber } from 'antd';

import BuildingSiteSelector from './../../../components/building/form/BuildingSiteSelector';

const FormItem = Form.Item;

@Form.create({})
class AttenceDeviceForm extends PureComponent {
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
    const { formType, currentAttenceDevice , global} = this.props;
    const { getFieldDecorator } = this.props.form;

    const attenceDevice = formType === 'EDIT' ? currentAttenceDevice : {};

    const modalConfig = {
      title: formType === 'EDIT' ? '编辑开发商信息' : '新建开发商信息',
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
          <FormItem {...formItemLayout} label="所属工地">
            {getFieldDecorator('buildingSiteId', {
            rules: [{ required: true, message: '请选择所属工地' }],
              initialValue: attenceDevice.buildingSiteId,
          })(<BuildingSiteSelector queryParams={{buildingDeveloperId:global.currentPosition.orgId, deletedFlag:false }} placeholder="请选择所属工地" />)}
        </FormItem>

          <FormItem {...formItemLayout} label="设备序列号">
            {getFieldDecorator('sn', {
              rules: [{ required: true, message: '请输入设备序列号' }],
              initialValue: attenceDevice.sn,
            })(<Input placeholder="请输入设备序列号" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="设备型号">
            {getFieldDecorator('type', {
            rules: [{ required: true, message: '请输入设备型号' }],
              initialValue: attenceDevice.type,
          })(<Input placeholder="请输入设备型号" />)}
        </FormItem>

          <FormItem {...formItemLayout} label="备注">
            {getFieldDecorator('remark', {
            rules: [{ required: false, message: '请输入备注信息' }],
              initialValue: attenceDevice.remark,
          })(<Input placeholder="请输入备注信息" />)}
        </FormItem>
      </Form>

      </Modal>
    );
  }
}

export default AttenceDeviceForm;
