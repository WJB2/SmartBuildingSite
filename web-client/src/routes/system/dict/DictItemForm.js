import React, { PureComponent } from 'react';

import { Modal, Form, Input } from 'antd';

const FormItem = Form.Item;

@Form.create({})
class DictItemForm extends PureComponent {

  handleFormSubmit(e) {
    e.preventDefault();
    const { onSubmit, currentDictItem } = this.props;

    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err && onSubmit) {
        onSubmit({
          ...values,
          id: currentDictItem&&currentDictItem.id?currentDictItem.id:null
        });
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

  render(){

    const { formType, currentDictItem, entry } = this.props;
    const { getFieldDecorator } = this.props.form;

    const dictItem = formType === 'EDIT' ? currentDictItem : {};

    const modalConfig = {
      title: formType === 'EDIT' ? '编辑词义' : '新建词义',
      visible: true,
      maskClosable: false,
      onOk: this.handleFormSubmit.bind(this),
      onCancel: this.handleCancel.bind(this),
    };

    const formItemLayout = {
      labelCol: {
        xs: { span: 8 },
        sm: { span: 6 },
      },
      wrapperCol: {
        xs: { span: 16 },
        sm: { span: 18 },
      },
    };

    return (
      <Modal {...modalConfig}>
        <Form>
          <FormItem {...formItemLayout} label="词条">
            {getFieldDecorator('entryId', {
              rules: [{ required: true }],
              initialValue: entry.id,
            })(<div>{entry.name}</div>)}
          </FormItem>

          <FormItem {...formItemLayout} label="词义代码">
            {getFieldDecorator('value', {
              rules: [{ required: true, message: '请输入词义代码' }],
              initialValue: dictItem.value,
            })(<Input placeholder="请输入词义代码" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="词义文本">
            {getFieldDecorator('text', {
              rules: [{ required: true, message: '请输入词义文本' }],
              initialValue: dictItem.text,
            })(<Input placeholder="请输入词义文本" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="描述">
            {getFieldDecorator('description', {
              rules: [{  message: '请输入词义描述' }],
              initialValue: dictItem.description,
            })(<Input.TextArea placeholder="请输入词义描述" />)}
          </FormItem>
        </Form>
      </Modal>
    );
  }
}

export default DictItemForm;
