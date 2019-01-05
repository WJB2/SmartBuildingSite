import React, {Component, Fragment} from 'react';
import {connect} from 'dva';

import {Table, Divider, Menu, Dropdown, Icon, Button, Tabs, Row, Col, Input} from 'antd';
import YearSelect from './../../../components/building/form/YearSelect';
import SalaryImportForm from './SalaryImportForm';
import SalaryDetailTable from './SalaryDetailTable';
const ButtonGroup = Button.Group;

const {TabPane} = Tabs;

@connect(models => ({
  salaryForDeveloper: models['building/salaryForDeveloper'],
  global: models['global'],
  loading: models.loading.models.employee,
}))
class SalaryForDeveloper extends Component {

  componentWillMount(){
     const {dispatch} = this.props;

     dispatch({
       type : 'building/salaryForDeveloper/initBuildingSiteList'
     });
  }

  render() {

    const months = ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'];

    const {salaryForDeveloper, dispatch, global} = this.props;

    const {year, month, data, buildingSiteList, buildingSiteId, importFormVisible} = salaryForDeveloper;


    const formConfigs = {
      onCancel: ()=>{
        dispatch({
          type : 'building/salaryForDeveloper/updateState',
          payload : {
            importFormVisible:false
          }
        });
      },
      onSubmit:(values)=>{
        dispatch({
          type : 'building/salaryForDeveloper/createSalary',
          payload : values
        });
      }
    }

    return (
      <div>
        <div>
          <Row gutter={{md: 8, lg: 24, xl: 48}}>
            <Col span={2}>
              请选择年份：
            </Col>
            <Col span={4}>
              <YearSelect onChange={(value)=>{
                dispatch({
                  type : 'building/salaryForDeveloper/querySalaryForDeveloper',
                  payload : {
                    year: value
                  }
                });
              }} value={year}/>
            </Col>
          </Row>
          <Row style={{marginTop:8}}>
            <Col span={2}>薪资所属年月：</Col>
            <Col span={22}>
              <ButtonGroup>
                {
                  months.map((monthStr, index) => {
                    return (<Button key={index+1} type={month===index+1?'primary':'normal'} onClick={()=>{
                      dispatch({
                        type : 'building/salaryForDeveloper/querySalaryForDeveloper',
                        payload : {
                          month: index+1
                        }
                      });
                    }}>{year + '年' + monthStr}</Button>);
                  })
                }
              </ButtonGroup>
            </Col>
          </Row>
          <Row style={{marginTop: 8}}>
            <Col span={2}>所属工地：</Col>
            <Col span={22}>
              <ButtonGroup>
                {
                  buildingSiteList.map((item, index) => {
                    return (<Button key={index} type={buildingSiteId===item.id?'primary':'normal'} onClick={()=>{
                      dispatch({
                        type : 'building/salaryForDeveloper/querySalaryForDeveloper',
                        payload : {
                          buildingSiteId: item.id
                        }
                      });
                    }}>{item.name}</Button>);
                  })
                }
              </ButtonGroup>
            </Col>
          </Row>
          <Row style={{marginTop: 8}}>
            <Col span={24}>
              <Button icon="file-excel" type="primary" onClick={
                ()=>{
                  dispatch({
                    type: 'building/salaryForDeveloper/updateState',
                    payload : {
                      importFormVisible:true
                    }
                  });
                }
              }>
                导入
              </Button>
            </Col>
          </Row>
        </div>
        <div style={{marginTop: 16}}>
          <SalaryDetailTable dataSource={data}/>
        </div>
        {importFormVisible&&<SalaryImportForm buildingDeveloperId={global.currentPosition.orgId} {...formConfigs} />}
      </div>
    );
  }
}

export default SalaryForDeveloper;
