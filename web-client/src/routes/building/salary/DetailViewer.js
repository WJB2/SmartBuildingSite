import React, { PureComponent } from 'react';

import moment from 'moment';

import { Modal, Form, Input, DatePicker, Upload, Button, Tabs, Alert,Badge} from 'antd';

import SalaryDetailTable from './SalaryDetailTable';
import AttenceRecordTable from './../attencerecord/AttenceRecordTable';

const {TabPane} = Tabs;

class DetailViewer extends PureComponent{

  render(){

    const {salaryDetails, checkRecordDetails, beginDate, endDate} = this.props;

    const modalConfig = {
      title: '明细记录',
      visible: true,
      width:'80%',
      okBtn: 'hidden',
      onCancel:()=>{
        this.props.onCancel();
      }
    };

    return (
      <Modal {...modalConfig}>
        <Tabs>
          <TabPane key={1} tab="工资发放明细">
            <SalaryDetailTable dataSource={salaryDetails} pagination={false}/>
          </TabPane>
          <TabPane key={2} tab="员工考勤明细">
            <div style={{marginBottom:16}}>
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

            <AttenceRecordTable beginDate={beginDate} endDate={endDate} dataSource={checkRecordDetails} pagination={false}/>
          </TabPane>
        </Tabs>
      </Modal>
    );
  }
}

export default DetailViewer;
