import React, { PureComponent } from 'react';

import { Modal, Form, Input, InputNumber } from 'antd';
import OrganizationTreeSelector from './../../../components/system/form/OrganizationTreeSelector';
import StaffSelector from './../../../components/system/form/StaffSelector';

const FormItem = Form.Item;

@Form.create({})
class BuildingDeveloperForm extends PureComponent {
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
    const { formType, currentBuildingDeveloper } = this.props;
    const { getFieldDecorator } = this.props.form;

    const buildingDeveloper = formType === 'EDIT' ? currentBuildingDeveloper : {};

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
            <FormItem {...formItemLayout} label="关联组织机构">
              {getFieldDecorator('orgId', {
              rules: [{ required: true, message: '请选择关联组织机构' }],
                initialValue: buildingDeveloper.orgId,
            })(<OrganizationTreeSelector placeholder="请选择关联组织机构" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="法人姓名">
            {getFieldDecorator('corporation', {
              rules: [{ required: false, message: '请输入法人姓名' }],
              initialValue: buildingDeveloper.corporation,
            })(<Input placeholder="请输入法人姓名" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="法人手机号">
            {getFieldDecorator('corporationMobile', {
              rules: [{ required: false, message: '请输入法人手机号' }],
              initialValue: buildingDeveloper.corporationMobile,
            })(<Input placeholder="请输入法人手机号" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="银行账户号">
            {getFieldDecorator('bankNo', {
              rules: [{ required: true, message: '请输入银行账户号' }],
              initialValue: buildingDeveloper.bankNo,
            })(<Input placeholder="请输入银行账户号" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="管理员用户">
              {getFieldDecorator('adminStaffId', {
              rules: [{ required: true, message: '请选择管理员用户' }],
                initialValue: buildingDeveloper.adminStaffId,
            })(<StaffSelector placeholder="请选择管理员用户" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="联系电话">
            {getFieldDecorator('telephone', {
            rules: [{ required: false, message: '请输入联系电话' }],
              initialValue: buildingDeveloper.telephone,
          })(<Input placeholder="请输入联系电话" />)}
        </FormItem>

          <FormItem {...formItemLayout} label="传真号码">
            {getFieldDecorator('fax', {
            rules: [{ required: false, message: '请输入传真号码' }],
              initialValue: buildingDeveloper.fax,
          })(<Input placeholder="请输入传真号码" />)}
        </FormItem>

        <FormItem {...formItemLayout} label="联系地址">
          {getFieldDecorator('address', {
          rules: [{ required: false, message: '请输入联系地址' }],
            initialValue: buildingDeveloper.address,
        })(<Input placeholder="请输入联系地址" />)}
      </FormItem>

      </Form>

      </Modal>
    );
  }
}

export default BuildingDeveloperForm;
