import React, { Component } from 'react';

import moment from 'moment';

import { Modal, Form, Input, DatePicker, Upload, Button } from 'antd';
import BuildingSiteSelector from './../../../components/building/form/BuildingSiteSelector';
import StaffSelector from './../../../components/system/form/StaffSelector';

const FormItem = Form.Item;

@Form.create({})
class SalaryBankImportForm extends Component {

  state = {
    fileList:[]
  };

  handleFormSubmit(e) {
    e.preventDefault();
    const { onSubmit } = this.props;

    this.props.form.validateFieldsAndScroll((err, values) => {

      const formData = new FormData();


      if (!err && onSubmit && this.state.fileList.length>0) {

        formData.append("buildingSiteId", values.buildingSiteId);
        formData.append("yearMonth", parseInt(values.yearMonth.format("YYYYMM")));
        formData.append('file', this.state.fileList[0]);

        onSubmit(formData);
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
    const { formType, buildingDeveloperId, buildingSiteId, yearMonth } = this.props;
    const { getFieldDecorator } = this.props.form;

    const modalConfig = {
      title: '上传工资发放要求',
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

    const uploadConfigs = {
      multiple:false,
      beforeUpload: (file) => {
        this.setState({
          fileList: [file]
        });
        return false;
      },
      fileList : this.state.fileList
    };

    return (
      <Modal {...modalConfig}>
        <Form>
            <FormItem {...formItemLayout} label="所属工地">
              {getFieldDecorator('buildingSiteId', {
                initialValue: buildingSiteId,
              rules: [{ required: true, message: '请选择所属工地' }],
            })(<BuildingSiteSelector disabled={true} queryParams={{buildingDeveloperId:buildingDeveloperId}} placeholder="请选择所属工地" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="所属月份">
            {getFieldDecorator('yearMonth', {
              rules: [{ required: true, message: '请选择所属月份' }],
              initialValue : moment(yearMonth + '01', 'YYYYMMDD')
            })(<DatePicker.MonthPicker disabled={true} placeholder="请选择所属月份" />)}
          </FormItem>

          <FormItem {...formItemLayout} label="Excel文件">
            {getFieldDecorator('file', {
              rules: [{ required: true, message: '请选择Excel文件' }],
            })(<div><Upload {...uploadConfigs} placeholder="请选择Excel文件">
                <Button>请选择Excel文件</Button>
            </Upload>

            </div>)}
          </FormItem>
      </Form>

      </Modal>
    );
  }
}

export default SalaryBankImportForm;
