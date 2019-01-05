import React, {PureComponent} from 'react';
import {connect} from 'dva';

import {Table, Divider, Menu, Dropdown, Icon, Button, Tabs, Row, Col, Input, Form, TimePicker, Switch} from 'antd';

const FormItem = Form.Item;

@Form.create({})
class SettingForm extends PureComponent{

  handleFormSubmit(e){
    e.preventDefault();
    const { onSubmit, wxAppInfo } = this.props;

    this.props.form.validateFieldsAndScroll((err, values) => {

      if(!err && onSubmit){
        delete values.url;
        onSubmit({
          ...values,
          id:wxAppInfo.id?wxAppInfo.id:null,
        });
      }
    });
  }

  render(){

    const {wxAppInfo} = this.props;
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

    const wxAppId = this.props.form.getFieldValue('appId');

    return (
      <Form>
        <FormItem {...formItemLayout} label="APP_ID">
          {getFieldDecorator('appId', {
            rules: [{ required: true }],
            initialValue: wxAppInfo.appId,
          })(<Input />)}
        </FormItem>

        <FormItem {...formItemLayout} label="APP_SECRET">
          {getFieldDecorator('appSecret', {
            rules: [{ required: true }],
            initialValue: wxAppInfo.appSecret,
          })(<Input />)}
        </FormItem>

        <FormItem {...formItemLayout} label="微信网址">
          {getFieldDecorator('url', {
            rules: [{ required: false }],
          })(<div>{wxAppId?'http://47.96.146.23/api/wechat/mp/' + wxAppId + '.html':''}</div>)}
        </FormItem>

        <FormItem style={{textAlign:'right'}}>
          <Button type={'primary'} onClick={this.handleFormSubmit.bind(this)}>保存</Button>
        </FormItem>
      </Form>
    );
  }
}

@connect(models => ({
  wechat: models['system/wechat'],
  global: models['global'],
}))
class WechatIndex extends PureComponent {


  onSubmit(payload){

    if(!payload.id){
      this.props.dispatch({
        type: 'system/wechat/addWxAppInfo',
        payload,
      });
    }else{
      this.props.dispatch({
        type: 'system/wechat/editWxAppInfo',
        payload,
      });
    }
  }

  componentDidMount(){
    this.props.dispatch({
      type:'system/wechat/findWxAppInfo',
      payload:{

      }
    });
  }

  render(){

    const {wechat} = this.props;

    return (
      <div style={{display:'flex', flexDirection:'vertical', justifyContent:'center', alignItems:'center', width:'100%', height:'100%'}}>
        <div style={{width:480, marginLeft:'auto', marginRight:'auto'}}>
          <SettingForm wxAppInfo={wechat.wxAppInfo} onSubmit={this.onSubmit.bind(this)} />
        </div>
      </div>
    );
  }
}

export  default WechatIndex;
