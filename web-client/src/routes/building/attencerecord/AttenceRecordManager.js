import React, {Component, Fragment} from 'react';
import {connect} from 'dva';
import moment from 'moment';

import {Table, Divider, Menu, Dropdown, Icon, Button, Tabs, Row, Col, Input, Alert, Badge} from 'antd';

import AttenceRecordTable from './AttenceRecordTable';
import AttenceRecordManagerPane from './AttenceRecordManagerPane';

const ButtonGroup = Button.Group;

const {TabPane} = Tabs;

@connect(models => ({
  attenceRecordManager: models['building/attenceRecordManager'],
  global: models['global'],
}))
class  AttenceRecordManager extends Component {

  render(){

    const {attenceRecordManager, dispatch} = this.props;

    const {beginDate, endDate, buildingDeveloperId, data} = attenceRecordManager;

    return (
      <div>
        <div >
          <AttenceRecordManagerPane onParamsChange={(values)=>{
            dispatch({
              type : 'building/attenceRecordManager/queryAttenceRecordAsync',
              payload: values['period']?{
                beginDate : values['period'][0],
                endDate : values['period'][1]
              }:values
            });
          }}
            buildingDeveloperId={buildingDeveloperId}
            beginDate={beginDate}
            endDate={endDate}
          />
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
          <Tabs>
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

export default AttenceRecordManager;
