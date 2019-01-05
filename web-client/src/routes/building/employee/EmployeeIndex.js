import React, { Component } from 'react';
import { connect } from 'dva';
import { Card } from 'antd';

import EmployeePane from './EmployeePane';
import EmployeeTable from './EmployeeTable';
import EmployeeForm from './EmployeeForm';
import EmployeeImportForm from './EmployeeImportForm';

import styles from './EmployeeIndex.less';

@connect(models => ({
  employee: models['building/employee'],
  global: models['global'],
  loading: models.loading.models.employee,
}))
class EmployeeIndex extends Component {
  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'building/employee/queryEmployeePageAsync',
    });
  }

  handleEmployeeAdd() {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/employee/updateState',
      payload: {
        formVisible: true,
        formType: 'ADD',
      },
    });
  }

  handleEmployeeSearch(params) {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/employee/queryEmployeePageAsync',
      payload:params?params:{}
    });
  }

  handleEmployeeQueryParamsChange(values) {

    const { dispatch, employee } = this.props;

    dispatch({
      type: 'building/employee/updateState',
      payload: {
        params: { ...employee.params, ...values },
      },
    });
  }

  handleEmployeeEdit(id) {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/employee/editEmployeeAction',
      payload: {
        id,
      },
    });
  }

  handleEmployeeDelete(id) {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/employee/deleteEmployeeByIdAsync',
      payload: {
        id,
      },
    });
  }


  handleFormSubmit(formValues) {
    const { dispatch, employee } = this.props;

    if (employee.formType === 'ADD') {
      dispatch({
        type: 'building/employee/addEmployeeAsync',
        payload: formValues,
      });
    } else if (employee.formType === 'EDIT') {
      dispatch({
        type: 'building/employee/editEmployeeAsync',
        payload: formValues,
      });
    }
  }

  handleFormCancel() {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/employee/updateState',
      payload: {
        formVisible: false,
        formType: null,
      },
    });
  }

  render() {
    const { dispatch, employee, global } = this.props;

    const {
      formType,
      formVisible,
      importFormVisible,
      currentEmployee,
      data,
    } = employee;

    const paneConfigs = {
      onAdd: this.handleEmployeeAdd.bind(this),
      onSearch: this.handleEmployeeSearch.bind(this),
      onParamsChange: this.handleEmployeeQueryParamsChange.bind(this),
      buildingDeveloperId:global.currentPosition.orgId,
      onUpload:()=>{
        dispatch({
          type : 'building/employee/updateState',
          payload : {
            importFormVisible:true
          }
        });
      }
    };

    const formConfigs = {
      onSubmit: this.handleFormSubmit.bind(this),
      onCancel: this.handleFormCancel.bind(this),
      formType,
      formVisible,
      currentEmployee,
      global,
    };

    const tableConfigs = {
      dataSource: data.list,
      pagination: {
        ...data.pagination,
        onChange: page => {
          dispatch({
            type: 'building/employee/queryEmployeePageAsync',
            payload: {
              page,
            },
          });
        },
      },
      onEdit: this.handleEmployeeEdit.bind(this),
      onDelete: this.handleEmployeeDelete.bind(this),
    };

    return (
      <div>
        <Card>
          <div className={styles.employeeIndex}>
            <EmployeePane {...paneConfigs} />
            <EmployeeTable {...tableConfigs} />
          </div>
        </Card>

        {formVisible && <EmployeeForm {...formConfigs} />}
        {importFormVisible && <EmployeeImportForm
          buildingDeveloperId={global.currentPosition.orgId}
          onSubmit={(values)=>{
            dispatch({
              type : 'building/employee/importEmployee',
              payload : values
            });
          }}
          onCancel={()=>{
            dispatch({
              type : 'building/employee/updateState',
              payload : {
                importFormVisible:false
              }
            });
          }}/>}
      </div>
    );
  }
}

export default EmployeeIndex;
