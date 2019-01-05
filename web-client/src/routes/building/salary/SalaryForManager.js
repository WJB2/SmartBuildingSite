import React, {Component, Fragment} from 'react';
import {connect} from 'dva';
import moment from 'moment';
import qs from 'qs';

import {Table, Divider, Menu, Dropdown, Icon, Button, Tabs, Row, Col, Input, Badge,Alert} from 'antd';
import YearSelect from './../../../components/building/form/YearSelect';
import SalaryBankImportForm from './SalaryBankImportForm';
import DetailViewer from "./DetailViewer";
import SecurityUtils from "../../../utils/SecurityUtils";

const ButtonGroup = Button.Group;

const {TabPane} = Tabs;

@connect(models => ({
  salaryForManager: models['building/salaryForManager'],
  global: models['global'],
  loading: models.loading.models.employee,
}))
class SalaryForManager extends Component {

  componentWillMount() {
    const {dispatch} = this.props;

    dispatch({
      type: 'building/salaryForManager/initSalaryReport'
    });
  }

  render() {

    const months = ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'];

    const {salaryForManager, global, dispatch} = this.props;

    const {year, data, importFormVisible, buildingSiteId, yearMonth, detailViewerVisible,
      salaryDetails,
      checkRecordDetails} = salaryForManager;

    const firstDayOfMonth = moment(yearMonth+'01', 'YYYYMMDD');

    const onViewDetail = (payload) => {
      dispatch({
        type: 'building/salaryForManager/viewDetail',
        payload: {
          yearMonth: payload.year * 100 + payload.month + '',
          buildingSiteId: payload.buildingSiteId,
          buildingDeveloperId: payload.buildingDeveloperId,
          detailViewerVisible: true
        }
      })
    };

    const onExportSalary = (payload) => {

      payload.yearMonth = payload.year * 100 + payload.month + '';

      window.open(`/api/building-site/payment/excel?${qs.stringify(payload)}`)
    };

    const onImportSalary = (payload) => {
      dispatch({
        type: 'building/salaryForManager/updateState',
        payload: {
          yearMonth: payload.year * 100 + payload.month + '',
          buildingSiteId: payload.buildingSiteId,
          importFormVisible: true
        }
      })
    };

    const menuClick = (key, payload) => {

      if (key === 'viewDetail') {
        onViewDetail(payload);
      } else if (key === 'exportSalary') {
        onExportSalary(payload);
      } else if (key === 'importSalary') {
        onImportSalary(payload);
      }
    }

    const menu = (payload) => {
      return (
        <Menu onClick={({item, key, keyPath}) => {
          menuClick(key, payload)
        }}>
          <Menu.Item key="viewDetail">
            <a>查看明细</a>
          </Menu.Item>
          {
            (SecurityUtils.isPermit('bdi:salary:payb:export', global))&&<Menu.Item key="exportSalary">
              <a>导出工资发放需求</a>
            </Menu.Item>
          }
          {
            (SecurityUtils.isPermit('bdi:salary:payb:import', global))&&<Menu.Item key="importSalary">
              <a>上传工资发放记录</a>
            </Menu.Item>
          }
        </Menu>
      );
    };

    const columns = [{
      title: '序号',
      dataIndex: 'rowNo',
      key: 'rowNo',
      width:60
    }, {
      title: '开发商名称',
      dataIndex: 'buildingDeveloper',
      key: 'buildingDeveloperName',
      width:150,
      render: (val) => {
        return val ? val.name : '';
      }
    }, {
      title: '社会统一信用代码',
      dataIndex: 'buildingDeveloper',
      key: 'buildingDeveloperCreditCode',
      width:150,
      render: (val) => {
        return val ? val.code : '';
      }
    }, {
      title: '所属工地',
      dataIndex: 'buildingSite',
      key: 'buildingSiteName',
      width:150,
      render: (val) => {
        return val ? val.name : '';
      }
    }]

    months.map((month, index) => {
      columns.push({
        title: month,
        dataIndex: 'monthReports',
        key: (year * 100 + index + 1) + "",
        render: (val, record) => {
          const reportItem = val['' + (year * 100 + index + 1)];

          if (reportItem && reportItem.grossSalary) {
            return <Dropdown overlay={menu({year: year, month: index + 1, buildingSiteId: record.buildingSite.id, buildingDeveloperId:record.buildingDeveloper.id})}>
              <span>{reportItem.paidSalary && reportItem.paidSalary >= reportItem.grossSalary ?
                <Badge status="success"/> : <Badge status="error"/>}{reportItem.grossSalary}</span>
            </Dropdown>
          } else {
            return '';
          }
        }
      })
    });

    return (
      <div>
        <div>
          <Row gutter={{md: 8, lg: 24, xl: 48}}>
            <Col span={2}>
              请选择年份：
            </Col>
            <Col span={4}>
              <YearSelect onChange={(value) => {
                dispatch({
                  type: 'building/salaryForManager/querySalaryForManager',
                  payload: {
                    year: value
                  }
                });
              }} value={year}/>
            </Col>
          </Row>
        </div>
        <div style={{marginTop:16}}>
          <Alert message={
            <span>
              状态说明：&nbsp;&nbsp;&nbsp;&nbsp;
              <Badge status={'success'}/>发放完成 &nbsp;&nbsp;&nbsp;&nbsp;
              <Badge status={'error'}/>发放未完成 &nbsp;&nbsp;&nbsp;&nbsp;
            </span>
          } type={"success"}/>
        </div>
        <div style={{marginTop: 16}}>
          <Table columns={columns} scroll={{x:1600}} dataSource={data}></Table>
        </div>
        {
          detailViewerVisible?<DetailViewer beginDate={firstDayOfMonth.startOf('month')}
          endDate={moment(firstDayOfMonth).endOf('month')} salaryDetails={salaryDetails}
          checkRecordDetails={checkRecordDetails}
          onCancel={()=>{
            dispatch({
              type : 'building/salaryForManager/updateState',
              payload : {
                detailViewerVisible:false
              }
            })
          }
          }/>:null
        }
        {importFormVisible ?
          <SalaryBankImportForm yearMonth={yearMonth}
                                buildingDeveloperId={global.currentPosition.orgId}
                                buildingSiteId={buildingSiteId}
                                onCancel={() => {
                                  dispatch({
                                    type: 'building/salaryForManager/updateState',
                                    payload: {
                                      importFormVisible: false
                                    }
                                  });
                                }}
                                onSubmit={(params) => {
                                  dispatch({
                                    type: 'building/salaryForManager/payoffSalary',
                                    payload: params
                                  });
                                }}

          /> : null}
      </div>
    );
  }
}

export default SalaryForManager;
