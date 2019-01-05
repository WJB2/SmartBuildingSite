import React, {Component, Fragment} from 'react';
import {connect} from 'dva';

import {Table, Divider, Menu, Dropdown, Icon, Button, Tabs, Row, Col, Input} from 'antd';
import YearSelect from './../../../components/building/form/YearSelect';
import SalaryImportForm from './SalaryImportForm';

const ButtonGroup = Button.Group;

const {TabPane} = Tabs;

class SalaryDetailTable extends  Component{

  render(){
    const {dataSource} = this.props;

    const columns = [{
      title: '序号',
      dataIndex: 'rowNo',
      key: 'rowNo',
      table:60,
    }, {
      title: '姓名',
      dataIndex: 'name',
      key: 'name',
      table:80,
    }, {
      title: '身份证号',
      dataIndex: 'idCardNo',
      key: 'idCardNo',
      table:100,
    }, {
      title: '开户银行',
      dataIndex: 'bank',
      table:80,
      key: 'bank'
    }, {
      title: '银行卡号',
      dataIndex: 'bankNo',
      table:80,
      key: 'bankNo'
    }, {
      title: '工种',
      dataIndex: 'workType',
      table:80,
      key: 'workType'
    }, {
      title: '出勤天数',
      dataIndex: 'attenceDays',
      table:80,
      key: 'attenceDays'
    }, {
      title: '工资总额',
      dataIndex: 'money',
      table:80,
      key: 'money'
    }, {
      title: '发放状态',
      dataIndex: 'status',
      table:80,
      key: 'status',
      render: (val)=>{
        switch (val){
          case 'PAID':
            return <span style={{color:"green"}}>已发放</span>;
          case 'PROCESSING':
            return <span style={{color:"orange"}}>银行处理中</span>;
          default:
            return <span style={{color:"red"}}>待发放</span>;
        }
      }
    }];

    return <Table columns={columns} scroll={{x:1600}} dataSource={dataSource} pagination={false}></Table>;
  }
}

export default SalaryDetailTable;
