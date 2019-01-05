import React, { PureComponent } from 'react';

import { Modal, Form, Input, InputNumber, DatePicker,Select} from 'antd';

import BuildingSiteSelector from './../../../components/building/form/BuildingSiteSelector';
import WorkTypeSelector from './../../../components/building/form/WorkTypeSelector';
import DictItemSelector from './../../../components/system/form/DictItemSelector';

const FormItem = Form.Item;
const Option = Select.Option;
@Form.create({})
class EmployeeForm extends PureComponent {
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
    const { formType, currentEmployee, global } = this.props;
    const { getFieldDecorator } = this.props.form;

    const employee = formType === 'EDIT' ? currentEmployee : {};

    const modalConfig = {
      title: formType === 'EDIT' ? '编辑工人信息' : '新建工人信息',
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
              initialValue: employee.buildingSiteId,
          })(<BuildingSiteSelector disabled={formType === 'EDIT'} queryParams={{buildingDeveloperId:global.currentPosition.orgId, deletedFlag:false }} placeholder="请选择所属工地" />)}
        </FormItem>

          <FormItem {...formItemLayout} label="员工工号">
            {getFieldDecorator('code', {
              rules: [{ required: true, message: '请输入员工工号' }],
              initialValue: employee.code,
              max:8
            })(<Input placeholder="请输入员工工号" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="工人姓名">
            {getFieldDecorator('name', {
              rules: [{ required: true, message: '请输入工人姓名' }],
              initialValue: employee.name,
            })(<Input placeholder="请输入工人姓名" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="身份证号">
            {getFieldDecorator('idCardNo', {
              rules: [{ required: true,
                len:18, message: '请输入身份证号' }],
              initialValue: employee.idCardNo,
            })(<Input placeholder="请输入身份证号" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="开户行">
            {getFieldDecorator('bank', {
            rules: [{ required: true, message: '请选择开户行' }],
              initialValue: employee.bank,
          })(<DictItemSelector entryCode={'BANK'} />)}
        </FormItem>

          <FormItem {...formItemLayout} label="银行账户号">
            {getFieldDecorator('bankNo', {
            rules: [{ required: true, message: '请输入银行账户号' }],
              initialValue: employee.bankNo,
          })(<Input placeholder="请输入银行账户号" />)}
        </FormItem>

          <FormItem {...formItemLayout} label="工种">
            {getFieldDecorator('workType', {
              rules: [{ required: false, message: '请选择工种' }],
              initialValue: employee.workType,
            })(<WorkTypeSelector queryParams={{buildingDeveloperId:global.currentPosition.orgId }} placeholder="请选择工种" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="手机号">
            {getFieldDecorator('mobile', {
            rules: [{ required: false, message: '请输入手机号' }],
              initialValue: employee.mobile,
          })(<Input placeholder="请输入手机号" />)}
        </FormItem>

        <FormItem {...formItemLayout} label="联系地址">
          {getFieldDecorator('address', {
          rules: [{ required: false, message: '请输入联系地址' }],
            initialValue: employee.address,
        })(<Input placeholder="请输入联系地址" />)}
      </FormItem>

      </Form>

      </Modal>
    );
  }
}

export default EmployeeForm;
