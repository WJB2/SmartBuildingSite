import React, {Component, Fragment} from 'react';
import {connect} from 'dva';
import moment from 'moment';

import {Table, Divider, Menu, Dropdown, Icon, Button, Tabs, Row, Col, Input, Form, TimePicker, Switch} from 'antd';

const ButtonGroup = Button.Group;

const {TabPane} = Tabs;
const FormItem = Form.Item;


@Form.create({})
class SettingForm extends Component{

  handleFormSubmit(e) {
    e.preventDefault();
    const { onSubmit } = this.props;

    this.props.form.validateFieldsAndScroll((err, values) => {

      if (!err && onSubmit) {
        onSubmit({
          breakEnabled : values.breakEnabled,
          beginTime: values.beginTime.format("HH:mm"),
          breakBeginTime: values.breakBeginTime.format("HH:mm"),
          breakEndTime: values.breakEndTime.format("HH:mm"),
          endTime: values.endTime.format("HH:mm"),
          buildingSiteId:this.props.buildingSiteId,
          buildingDeveloperId:this.props.buildingDeveloperId});
      }
    });
  }

  render(){

    const {workPeriod} = this.props;

    const { getFieldDecorator, getFieldValue } = this.props.form;

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
      <Form>
        <FormItem {...formItemLayout} label="开启中间休息">
          {getFieldDecorator('breakEnabled', {
            rules: [{ required: true }],
            initialValue: workPeriod.breakEnabled,
          })(<Switch />)}
        </FormItem>

        <FormItem {...formItemLayout} label="上班时间">
          {getFieldDecorator('beginTime', {
            rules: [{ required: true, message: '请选择上班时间' }],
            initialValue: moment('2018-01-01 ' + workPeriod.beginTime + ':00', 'YYYY-MM-DD HH:mm:ss'),
          })(<TimePicker style={{width:'100%'}} format={'HH:mm'} placeholder="请选择上班时间" />)}
        </FormItem>

        <FormItem {...formItemLayout} label="休息开始时间">
          {getFieldDecorator('breakBeginTime', {
            rules: [{ required: false, message: '请选休息开始时间' }],
            initialValue: moment('2018-01-01 ' + workPeriod.breakBeginTime + ':00', 'YYYY-MM-DD HH:mm:ss'),
          })(<TimePicker disabled={!getFieldValue('breakEnabled')} style={{width:'100%'}} format={'HH:mm'} placeholder="请选择休息开始时间" />)}
        </FormItem>

        <FormItem {...formItemLayout} label="休息结束时间">
          {getFieldDecorator('breakEndTime', {
            rules: [{ required: false, message: '请选休息结束时间' }],
            initialValue: moment('2018-01-01 ' + workPeriod.breakEndTime + ':00', 'YYYY-MM-DD HH:mm:ss'),
          })(<TimePicker disabled={!getFieldValue('breakEnabled')} style={{width:'100%'}} format={'HH:mm'} placeholder="请选择休息开始时间" />)}
        </FormItem>

        <FormItem {...formItemLayout} label="下班时间">
          {getFieldDecorator('endTime', {
            rules: [{ required: true, message: '请选择下班时间' }],
            initialValue: moment('2018-01-01 ' + workPeriod.endTime + ':00', 'YYYY-MM-DD HH:mm:ss'),
          })(<TimePicker style={{width:'100%'}} format={'HH:mm'} placeholder="请选择下班时间" />)}
        </FormItem>

        <FormItem style={{textAlign:'right'}}>
          <Button type={'primary'} onClick={this.handleFormSubmit.bind(this)}>保存</Button>
        </FormItem>
      </Form>
    );
  }
}



@connect(models => ({
  clockingSetting: models['building/clockingSetting'],
  global: models['global'],
}))
class ClockingSetting extends Component {

  componentWillMount(){
    const {dispatch} = this.props;

    dispatch({
      type : 'building/clockingSetting/initBuildingSiteList',
    });
  }

  render(){

    const {clockingSetting, global, dispatch} = this.props;
    const {
      buildingSiteList,
      attenceRuleList
    } = clockingSetting;

    return (
      <div>
        <Tabs>
      {
        buildingSiteList.map((item)=>{

          const result = attenceRuleList.find((value, index, arr)=>{

            return value.buildingSiteId===item.id;
          });

          const workPeriod = result?result:{};

          return (
            <TabPane key={item.id} tab={item.name}>
              <div style={{width:480, marginLeft:'auto', marginRight:'auto'}}>
                <SettingForm
                  workPeriod={workPeriod}
                  buildingDeveloperId = {item.buildingDeveloperId}
                  buildingSiteId={item.id}
                  onSubmit={(values)=>{
                    dispatch({
                      type : 'building/clockingSetting/saveSetting',
                      payload : values
                    });
                  }}></SettingForm>
              </div>
            </TabPane>
          );
        })
      }
        </Tabs>
      </div>
    );
  }
}

export default ClockingSetting;
