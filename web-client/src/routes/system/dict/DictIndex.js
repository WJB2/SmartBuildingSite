import React, {Component} from 'react';

import {Row, Col, Table, Button, Input, Icon, Divider, Popconfirm} from 'antd';
import {connect} from "dva";

import DictItemForm from './DictItemForm';

@connect(models => ({
  dict: models['system/dict'],
}))
class DictIndex extends Component{

  componentDidMount(){
    const {dispatch} = this.props;

    dispatch({
      type : 'system/dict/initialize',
      payload: {}
    });
  }

  onRowSelect(entry){
    const {dispatch} = this.props;

    dispatch({
      type : 'system/dict/queryItemPageAsync',
      payload: {
        entryId : entry.id,
        currentEntry: entry
      }
    });
  }

  onDictItemSubmit(payload){
    const {dispatch} = this.props;

    if(payload.id){
      dispatch({
        type : 'system/dict/editDictItemAsync',
        payload
      });
    }else{
      dispatch({
        type : 'system/dict/addDictItemAsync',
        payload
      });
    }
  }

  handleAddDictItemAction(item){
    this.props.dispatch({
      type: 'system/dict/updateState',
      payload: {
        currentEntry:item,
        itemFormType: 'ADD',
        itemFormVisible: true,
      }
    });
  }

  handleEditDictItemAction(item){
    this.props.dispatch({
      type: 'system/dict/updateState',
      payload: {
        currentItem: item,
        itemFormType: 'EDIT',
        itemFormVisible: true,
      }
    });
  }

  handleDictItemEditCancelAction(){
    this.props.dispatch({
      type: 'system/dict/updateState',
      payload: {
        itemFormVisible: false,
        itemFormType: null
      }
    });
  }

  handleDeleteDictItemAction(record){
    this.props.dispatch({
      type: 'system/dict/deleteDictItemByIdAsync',
      payload: record
    });
  }

  entryColumns = [{
    title: '序号',
    dataIndex: 'rowNo',
    key: 'rowNo'
  }, {
    title: '词条编码',
    dataIndex: 'code',
    key: 'code'
  }, {
    title: '词条名称',
    dataIndex: 'name',
    key: 'name'
  }, {
    title : '操作',
    dataIndex : 'operate',
    key: 'operate',
    render:(val, record)=>{
      return (
        <span>
          {/*<Icon type={'edit'} style={{cursor:'pointer'}} />*/}
          {
            record.opType!='CONST'&&<Divider type={'vertical'}/>
          }
          {
            record.opType!='CONST'&&<span onClick={()=>{
              this.handleAddDictItemAction(record)
            }}><Icon type={'plus'} style={{cursor:'pointer'}}/></span>
          }
        </span>
      );
    }
  }];

  itemColumns = [{
    title: '序号',
    dataIndex: 'rowNo',
    key: 'rowNo'
  }, {
    title: '词义值',
    dataIndex: 'value',
    key: 'value'
  }, {
    title: '词义文本',
    dataIndex: 'text',
    key: 'text'
  }, {
    title: '描述',
    dataIndex: 'description',
    key: 'description'
  }, {
    title : '操作',
    dataIndex : 'operate',
    key: 'operate',
    render:(val, record)=>{
      return (
        <span>
          {
            record.opType!='CONST'&&<span onClick={()=>{
              this.handleEditDictItemAction(record)
            }}><Icon type={'edit'} style={{cursor:'pointer'}} /></span>
          }

          {
            record.opType=='NO_LIMIT'&&<Divider type={'vertical'}/>
          }
          {
            record.opType=='NO_LIMIT'&&
            <Popconfirm title={'你确定要删除该词条吗?'} onConfirm={()=>{this.handleDeleteDictItemAction(record)}}>
              <Icon type={'delete'} style={{cursor:'pointer'}}/>
            </Popconfirm>
          }
        </span>
      );
    }
  }];


  render(){

    const {dict} = this.props;

    const {currentEntry, entryFormVisible, entryFormType, entryParams, entryData, entryDataLoading,
      currentItem, itemFormVisible, itemFormType, itemParams, itemData, itemDataLoading} = dict;

    return (
      <div style={{width:'100%', height:'100%'}}>
        <Row gutter={24}>
          <Col span={12}>
            <div style={{height:'auto', overflow:'hidden'}}>
              <div style={{float:'right', height:'auto',  zoom:1}}>
                <Input.Search />
              </div>
            </div>
            <Table loading={entryDataLoading}
                   columns={this.entryColumns}
                   dataSource={entryData.list}
                   pagination={entryData.pagination}
                   onRow={
                     (record)=>{
                       return {
                         onClick:()=>{
                           this.onRowSelect(record);
                         }
                       };
                     }
                   }/>
          </Col>

          <Col span={12}>
            <div style={{height:'auto', overflow:'hidden'}}>
              <div style={{float:'right'}}>
                <Input.Search />
              </div>
            </div>
            <Table loading={itemDataLoading} columns={this.itemColumns} dataSource={itemData.list} pagination={itemData.pagination}/>
          </Col>
        </Row>
        {
          itemFormVisible &&
            <DictItemForm entry={currentEntry} currentDictItem={currentItem} formType={itemFormType}
                          onSubmit={this.onDictItemSubmit.bind(this)}
                          onCancel={this.handleDictItemEditCancelAction.bind(this)}/>
        }
      </div>
    );
  }
}

export default DictIndex;
