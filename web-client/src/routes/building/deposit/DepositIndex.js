import React, { Component } from 'react';
import { connect } from 'dva';
import { Card } from 'antd';

import DepositPane from './DepositPane';
import DepositTable from './DepositTable';
import DepositForm from './DepositForm';

import styles from './DepositIndex.less';
import DepositDetailTable from "./DepositDetailTable";

@connect(models => ({
  deposit: models['building/deposit'],
  loading: models.loading.models.deposit,
}))
class DepositIndex extends Component {
  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'building/deposit/queryDepositPageAsync',
    });
  }

  handleDepositAdd() {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/deposit/updateState',
      payload: {
        formVisible: true,
        formType: 'ADD',
      },
    });
  }

  handleDepositSearch() {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/deposit/queryDepositPageAsync',
    });
  }

  handleDepositQueryParamsChange(values) {
    const { dispatch, deposit } = this.props;

    dispatch({
      type: 'building/deposit/updateState',
      payload: {
        params: { ...deposit.params, ...values },
      },
    });
  }

  handleDepositView(buildingDeveloperId) {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/deposit/viewDepositDetailAction',
      payload: {
        buildingDeveloperId,
      },
    });
  }

  handleFormSubmit(formValues) {
    const { dispatch, deposit } = this.props;

    if (deposit.formType === 'ADD') {
      dispatch({
        type: 'building/deposit/addDepositAsync',
      });
    } else if (deposit.formType === 'EDIT') {
      dispatch({
        type: 'building/deposit/editDepositAsync',
        payload: formValues,
      });
    }
  }

  handleFormCancel() {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/deposit/updateState',
      payload: {
        formVisible: false,
        formType: null,
      },
    });
  }

  render() {
    const { dispatch, deposit } = this.props;

    const {
      formType,
      formVisible,
      currentDeposit,
      data,
      depositDetailViewerVisible,
      depositRecordPage,
    } = deposit;

    const paneConfigs = {
      onAdd: this.handleDepositAdd.bind(this),
      onSearch: this.handleDepositSearch.bind(this),
      onParamsChange: this.handleDepositQueryParamsChange.bind(this),
    };

    const formConfigs = {
      onSubmit: this.handleFormSubmit.bind(this),
      onCancel: this.handleFormCancel.bind(this),
      formType,
      formVisible,
      currentDeposit,
    };

    const tableConfigs = {
      dataSource: data.list,
      pagination: {
        ...data.pagination,
        onChange: page => {
          dispatch({
            type: 'building/deposit/queryDepositPageAsync',
            payload: {
              page,
            },
          });
        },
      },
      onView: this.handleDepositView.bind(this),
    };

    return (
      <div>
        <Card>
          <div className={styles.depositIndex}>
            <DepositPane {...paneConfigs} />
            <DepositTable {...tableConfigs} />
          </div>
        </Card>

        {formVisible && <DepositForm {...formConfigs} />}
        {depositDetailViewerVisible&& <DepositDetailTable
          dataSource={depositRecordPage.list}
          pagination={depositRecordPage.pagination}
          onCancel={
            ()=>{
              dispatch({
                type: 'building/deposit/updateState',
                payload: {
                  depositDetailViewerVisible:false
                },
              })
            }
          }/>}
      </div>
    );
  }
}

export default DepositIndex;
