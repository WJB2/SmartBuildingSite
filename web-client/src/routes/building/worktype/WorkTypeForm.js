import React, { PureComponent } from 'react';

import { Modal, Form, Input, InputNumber } from 'antd';

import BuildingSiteSelector from './../../../components/building/form/BuildingSiteSelector';

const FormItem = Form.Item;

@Form.create({})
class WorkTypeForm extends PureComponent {
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
    const { formType, currentWorkType, global } = this.props;
    const { getFieldDecorator } = this.props.form;

    const workType = formType === 'EDIT' ? currentWorkType : {};

    const modalConfig = {
      title: formType === 'EDIT' ? '编辑工种信息' : '新建工种信息',
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
          <FormItem {...formItemLayout} label="所属工地">
            {getFieldDecorator('buildingSiteId', {
            rules: [{ required: true, message: '请选择所属工地' }],
              initialValue: workType.buildingSiteId,
          })(<BuildingSiteSelector disabled={formType === 'EDIT'} queryParams={{buildingDeveloperId:global.currentPosition.orgId, deletedFlag:false }} placeholder="请选择所属工地" />)}
        </FormItem>

          <FormItem {...formItemLayout} label="工种名称">
            {getFieldDecorator('name', {
              rules: [{ required: true, message: '请输入工种名称' }],
              initialValue: workType.name,
            })(<Input placeholder="请输入工种名称" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="显示顺序">
            {getFieldDecorator('sortNo', {
            rules: [{ required: false, message: '请输入显示顺序' }],
              initialValue: workType.sortNo?workType.sortNo:50,
          })(<InputNumber style={{width:'100%'}} placeholder="请输入显示顺序" />)}
        </FormItem>

      </Form>

      </Modal>
    );
  }
}

export default WorkTypeForm;
