import React, {PureComponent} from 'react';
import {Form, Input, Modal, Tag, DatePicker} from "antd";

const FormItem = Form.Item;

@Form.create({})
class ClockingRepairForm  extends PureComponent {

  handleFormSubmit(e) {
    e.preventDefault();
    const { onSubmit, employees, attenceRule } = this.props;

    if(employees&&employees[0]){
      this.props.form.validateFieldsAndScroll((err, values) => {
        if (!err && onSubmit) {
          onSubmit({
            ...values,
            checkInTime: values.checkInTime?values.checkInTime.format('YYYY-MM-DD HH:mm:ss.SSS'):null,
            breakCheckInTime: values.breakCheckInTime?values.breakCheckInTime.format('YYYY-MM-DD HH:mm:ss.SSS'):null,
            breakCheckOutTime: values.breakCheckOutTime?values.breakCheckOutTime.format('YYYY-MM-DD HH:mm:ss.SSS'):null,
            checkOutTime: values.checkOutTime?values.checkOutTime.format('YYYY-MM-DD HH:mm:ss.SSS'):null,
            buildingDeveloperId: employees[0].buildingDeveloperId,
            buildingSiteId: employees[0].buildingSiteId
          });
        }
      });
    }
  }

  handleCancel(e) {
    e.preventDefault();
    const { onCancel } = this.props;
    if (onCancel) {
      onCancel();
    }
  }

  render(){

    const { employees, attenceRule } = this.props;
    const { getFieldDecorator } = this.props.form;

    const employeeList = employees||[];

    const modalConfig = {
      title: '人员考勤补卡',
      visible: true,
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

    console.log(attenceRule)

    return (
      <Modal {...modalConfig}>
        <Form>
          <FormItem {...formItemLayout} label="打卡人">
            {getFieldDecorator('employeeIdList', {
              rules: [{ required: true, message: '请选择打卡人' }],
              initialValue: employeeList.map((employee)=>{
                return employee.id;
              })
            })(
              <div>
                {
                  employees?employeeList.map((employee)=>{
                    return (<Tag>{employee.name}</Tag>);
                  }):<span></span>
                }
              </div>
            )}
          </FormItem>

          <FormItem {...formItemLayout} label="上班打卡时间">
            {getFieldDecorator('checkInTime', {
              rules: [{ required: true, message: '上班打卡时间' }],
            })(<DatePicker showTime format="YYYY-MM-DD HH:mm:ss" placeholder="请输入上班打卡时间" />)}
          </FormItem>

          {
            attenceRule.breakEnabled && <FormItem {...formItemLayout} label="休息开始时间">
              {getFieldDecorator('breakCheckOutTime', {
                rules: [{ required: true, message: '休息开始时间' }],
              })(<DatePicker showTime format="YYYY-MM-DD HH:mm:ss" placeholder="请输入休息开始时间" />)}
            </FormItem>
          }

          {
            attenceRule.breakEnabled && <FormItem {...formItemLayout} label="休息结束时间">
              {getFieldDecorator('breakCheckInTime', {
                rules: [{ required: true, message: '休息开始时间' }],
              })(<DatePicker showTime format="YYYY-MM-DD HH:mm:ss" placeholder="请输入休息结束时间" />)}
            </FormItem>
          }

          <FormItem {...formItemLayout} label="下班打卡时间">
            {getFieldDecorator('checkOutTime', {
              rules: [{ required: true, message: '下班打卡时间' }],
            })(<DatePicker showTime format="YYYY-MM-DD HH:mm:ss" placeholder="请输入下班打卡时间" />)}
          </FormItem>
        </Form>
      </Modal>
    );
  }
}

export default ClockingRepairForm;
