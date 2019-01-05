import React, {Component, Fragment} from 'react';
import {connect} from 'dva';

import {Table, Divider, Button, Tabs} from 'antd';
import ClockingRepairForm from "./ClockingRepairForm";

const {TabPane} = Tabs;

@connect(models => ({
  manualClocking: models['building/manualClocking'],
  global: models['global'],
}))
class ManualClocking extends Component {

  componentWillMount(){
    const {dispatch} = this.props;

    dispatch({
      type : 'building/manualClocking/findEmployeeForManualClocking'
    });
  }

  handleClocking(record){
    const {dispatch} = this.props;

    dispatch({
      type : 'building/manualClocking/clocking',
      payload : {
        employee : record
      }
    });
  }

  handleRepairClockingAction(recordList){

    const {dispatch} = this.props;

    if(recordList){
      dispatch({
        type : 'building/manualClocking/updateState',
        payload: {
          repairFormVisible: true,
          selectedItems: recordList,
          selectedItemKeys: recordList.map((item)=>{
            return item.id
          })
        }
      });
    }
  }

  onRepairClockingSubmit(payload){
    const {dispatch} = this.props;

    dispatch({
      type : 'building/manualClocking/repairClockingAsync',
      payload,
    });
  }

  render(){

    const {manualClocking, global, dispatch} = this.props;

    const {data, repairFormVisible, selectedItems, selectedItemKeys, buildingSiteId} = manualClocking;

    let currentRule = {};

    if(data&&data.length>0){
      const siteId = buildingSiteId||data[0].buildingSite.id;
      for(let i=0; i<data.length; i++){
        if(data[i].buildingSite.id===siteId){
          currentRule = data[i].rule
        }
      }
    }

    const columns = [{
      title : '序号',
      dataIndex : 'rowNo',
      key : 'rowNo',
      width:80,
      align:"center",
    }, {
      title : '姓名',
      dataIndex : 'name',
      key : 'name',
      width:180,
    }, {
      title : '身份证号',
      dataIndex : 'idCardNo',
      key : 'idCardNo',
      width:240,
    }, {
      title : '操作',
      dataIndex : 'op',
      key : 'op',
      align:"center",
      render: (val, record) => (
        <span>
          <Fragment>
            <a
              onClick={() => {
                this.handleClocking(record);
              }}
            >
              打卡
            </a>
          <Divider type={'vertical'}/>
            <a
              onClick={() => {
                this.handleRepairClockingAction([record]);
              }}
            >
              补卡
            </a>
          </Fragment>
        </span>
      ),
    }];

    const rowSelection = {
      selectedRowKeys: selectedItemKeys,
      onChange: (selectedRowKeys, selectedRows) => {
        this.props.dispatch({
          type : 'building/manualClocking/updateState',
          payload: {
            selectedItemKeys: selectedRowKeys,
            selectedItems: selectedRows,
          }
        });
      },
    };

    return (
      <div>
        <div>
          <Button type={'primary'} onClick={()=>{
            this.handleRepairClockingAction(selectedItems);
          }}>补卡</Button>
        </div>
        <Tabs onChange={(activeKey)=>{
          dispatch({
            type : 'building/manualClocking/updateState',
            payload: {
              buildingSiteId: activeKey
            }
          });
        }}>
          {
            data.map((item) => {
              return (<TabPane key={item.buildingSite.id} tab={item.buildingSite.name}>
                <Table rowSelection={rowSelection} columns={columns} dataSource={item.employees} pagination={false} />
              </TabPane>)
            })
          }
        </Tabs>
        {
          repairFormVisible && <ClockingRepairForm
            onSubmit={this.onRepairClockingSubmit.bind(this)}
            employees={selectedItems}
            attenceRule={currentRule}
            onCancel={()=>{
              this.props.dispatch({
                type : 'building/manualClocking/updateState',
                payload: {
                  repairFormVisible: false,
                }
              });
            }}
          />
        }
      </div>
    );
  }
}

export default ManualClocking;
