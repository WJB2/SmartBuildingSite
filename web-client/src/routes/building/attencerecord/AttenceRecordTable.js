import React, {PureComponent, Fragment} from 'react';
import {connect} from 'dva';
import moment from 'moment';

import {Table, Divider, Menu, Dropdown, Icon, Button, Tabs, Row, Col, Input, Badge } from 'antd';

const ButtonGroup = Button.Group;

const {TabPane} = Tabs;

class AttenceRecordTable extends PureComponent {

  render(){

    const {beginDate, endDate, dataSource} = this.props;

    const dropdown = (record)=>{

      if(!record){
        return null;
      }

      return (
        <div style={{padding:16, backgroundColor:'#fff', border:'1px solid '}}>
          <div>
            <span>上班打卡时间:</span>
            <span>{record.checkInTime&&moment(record.checkInTime, 'YYYYMMDDHHmmssSSS').format('HH:mm:ss')}</span>
          </div>
          {
            record.breakEnabled&&<div>
              <span>休息开始时间:</span>
              <span>{record.breakCheckOutTime&&moment(record.breakCheckOutTime, 'YYYYMMDDHHmmssSSS').format('HH:mm:ss')}</span>
            </div>
          }
          {
            record.breakEnabled&&<div>
              <span>休息结束时间:</span>
              <span>{record.breakCheckInTime&&moment(record.breakCheckInTime, 'YYYYMMDDHHmmssSSS').format('HH:mm:ss')}</span>
            </div>
          }
          <div>
            <span>下班打卡时间:</span>
            <span>{record.checkOutTime&&moment(record.checkOutTime, 'YYYYMMDDHHmmssSSS').format('HH:mm:ss')}</span>
          </div>
        </div>
      );
    }

    dataSource.forEach((row, idx)=>{
      row.rowNo = idx+1;
    });

    const columns = [{
      title : '序号',
      dataIndex : 'rowNo',
      key : 'rowNo',
      width:80,
      fixed : 'left',
      align:"center"
    }, {
      title : '人员姓名',
      dataIndex : 'employee',
      key : 'employeeName',
      width:100,
      fixed : 'left',
      render:(val)=>{
        return val.name;
      }
    }, {
      title : '身份证号',
      dataIndex : 'employee',
      key : 'employeeIdCardNo',
      width:200,
      render:(val)=>{
        return val.idCardNo;
      }
    }];

    const beginDateTemp = moment(beginDate);

    while(beginDateTemp.isBefore(endDate)){
      const key = beginDateTemp.format('YYYYMMDD');
      columns.push({
        title : beginDateTemp.format("D号"),
        dataIndex : 'checkRecords',
        key : key,
        width:80,
        render:(val)=>{
          if(!val[key]){
            return (<Badge status="default"  />);
          }else if(val[key].checkStatus=='LOST'){
            return (<Dropdown overlay={dropdown(val[key])}><Badge status="error" /></Dropdown>);
          }else if(val[key].checkStatus=='ABNORMAL'){
            return (<Dropdown overlay={dropdown(val[key])}><Badge status="warning" /></Dropdown>);
          } else {
            return (<Dropdown overlay={dropdown(val[key])}><Badge status="success" /></Dropdown>);
          }
        }
      })

      beginDateTemp.add(1, 'days')
    }
    return <Table columns={columns} scroll={{
      x: 320 + 80*31
    }} dataSource={dataSource} pagination={false}/>
  }
}

export default AttenceRecordTable;
