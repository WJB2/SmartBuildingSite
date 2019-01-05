import React, {Component, Fragment} from 'react';
import qs from 'qs';
import {connect} from 'dva';
import moment from 'moment';

import {Table, Divider, Menu, Dropdown, Icon, Button, Tabs, Row, Col, Input, DatePicker, Form, Alert, Badge} from 'antd';

import AttenceRecordTable from './AttenceRecordTable';
import AttenceRecordPane from './AttenceRecordPane';

const ButtonGroup = Button.Group;
const FormItem = Form.Item;

const {TabPane} = Tabs;

@connect(models => ({
  attenceRecord: models['building/attenceRecord'],
  global: models['global'],
}))
class  AttenceRecord extends Component {

  componentWillMount(){
    const {dispatch} = this.props;

    dispatch({
      type : 'building/attenceRecord/initAttenceRecord'
    });
  }

  render(){

    const {attenceRecord, dispatch} = this.props;

    const {beginDate, endDate, data, buildingSiteId} = attenceRecord;

    return (
      <div>
        <div >
          <AttenceRecordPane onParamsChange={(values)=>{
            dispatch({
              type : 'building/attenceRecord/queryAttenceRecordAsync',
              payload: {
                beginDate : values['period'][0],
                endDate : values['period'][1]
              }
            });
          }} beginDate={beginDate} endDate={endDate} onExport={()=>{
            const params = {
              beginDate: beginDate.format('YYYY-MM-DD 00:00:00'),
              endDate: endDate.format('YYYY-MM-DD 23:59:59'),
              buildingSiteId: buildingSiteId?buildingSiteId:data[0]&&data[0].buildingSite.id
            }
            window.open(`/api/building/attence-record/salary-report?${qs.stringify(params)}`)
          }}/>
        </div>
        <div style={{marginTop:16}}>
          <Alert message={
            <span>
              状态说明：&nbsp;&nbsp;&nbsp;&nbsp;
              <Badge status={'success'}/>正常出勤 &nbsp;&nbsp;&nbsp;&nbsp;
              <Badge status={'warning'}/>迟到或早退 &nbsp;&nbsp;&nbsp;&nbsp;
              <Badge status={'error'}/>缺卡 &nbsp;&nbsp;&nbsp;&nbsp;
              <Badge status={'default'}/>缺勤 &nbsp;&nbsp;&nbsp;&nbsp;
            </span>
          } type={"success"}/>
        </div>
        <div style={{marginTop:16}}>
          <Tabs activeKey={buildingSiteId?buildingSiteId:data[0]&&data[0].buildingSite.id} onTabClick={(key)=>{
            dispatch({
              type: 'building/attenceRecord/updateState',
              payload : {
                buildingSiteId:key
              }
            })
          }}>
          {
            data.map((item)=>{
              return (
                <TabPane key={item.buildingSite.id} tab={item.buildingSite.name}>
                  <AttenceRecordTable beginDate={beginDate} endDate={endDate} dataSource={item.employees} />
                </TabPane>
              )
            })
          }
          </Tabs>
        </div>
      </div>
    );
  }
}

export default AttenceRecord;
