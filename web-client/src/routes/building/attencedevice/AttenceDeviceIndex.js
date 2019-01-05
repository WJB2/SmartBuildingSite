import React, { Component } from 'react';
import { connect } from 'dva';
import { Card } from 'antd';

import AttenceDevicePane from './AttenceDevicePane';
import AttenceDeviceTable from './AttenceDeviceTable';
import AttenceDeviceForm from './AttenceDeviceForm';

import styles from './AttenceDeviceIndex.less';

@connect(models => ({
  attenceDevice: models['building/attenceDevice'],
  global: models['global'],
  loading: models.loading.models.attenceDevice,
}))
class AttenceDeviceIndex extends Component {
  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'building/attenceDevice/queryAttenceDevicePageAsync',
    });
  }

  handleAttenceDeviceAdd() {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/attenceDevice/updateState',
      payload: {
        formVisible: true,
        formType: 'ADD',
      },
    });
  }

  handleAttenceDeviceSearch() {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/attenceDevice/queryAttenceDevicePageAsync',
    });
  }

  handleAttenceDeviceQueryParamsChange(values) {
    const { dispatch, attenceDevice } = this.props;

    dispatch({
      type: 'building/attenceDevice/updateState',
      payload: {
        params: { ...attenceDevice.params, ...values },
      },
    });
  }

  handleAttenceDeviceEdit(id) {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/attenceDevice/editAttenceDeviceAction',
      payload: {
        id,
      },
    });
  }

  handleAttenceDeviceDelete(id) {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/attenceDevice/deleteAttenceDeviceByIdAsync',
      payload: {
        id,
      },
    });
  }

  handleAttenceDeviceAuthorize(attenceDeviceId) {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/attenceDevice/doAttenceDeviceAuthorizeAction',
      payload: {
        attenceDeviceId,
      },
    });
  }

  handleFormSubmit(formValues) {
    const { dispatch, attenceDevice } = this.props;

    if (attenceDevice.formType === 'ADD') {
      dispatch({
        type: 'building/attenceDevice/addAttenceDeviceAsync',
        payload: formValues,
      });
    } else if (attenceDevice.formType === 'EDIT') {
      dispatch({
        type: 'building/attenceDevice/editAttenceDeviceAsync',
        payload: formValues,
      });
    }
  }

  handleFormCancel() {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/attenceDevice/updateState',
      payload: {
        formVisible: false,
        formType: null,
      },
    });
  }

  render() {
    const { dispatch, attenceDevice, global } = this.props;

    const {
      formType,
      formVisible,
      currentAttenceDevice,
      data,
    } = attenceDevice;

    const paneConfigs = {
      onAdd: this.handleAttenceDeviceAdd.bind(this),
      onSearch: this.handleAttenceDeviceSearch.bind(this),
      onParamsChange: this.handleAttenceDeviceQueryParamsChange.bind(this),
      buildingDeveloperId:global.currentPosition.orgId,
    };

    const formConfigs = {
      onSubmit: this.handleFormSubmit.bind(this),
      onCancel: this.handleFormCancel.bind(this),
      formType,
      formVisible,
      currentAttenceDevice,
      global,
    };

    const tableConfigs = {
      dataSource: data.list,
      pagination: {
        ...data.pagination,
        onChange: page => {
          dispatch({
            type: 'building/attenceDevice/queryAttenceDevicePageAsync',
            payload: {
              page,
            },
          });
        },
      },
      onEdit: this.handleAttenceDeviceEdit.bind(this),
      onDelete: this.handleAttenceDeviceDelete.bind(this),
    };

    return (
      <div>
        <Card>
          <div className={styles.attenceDeviceIndex}>
            <AttenceDevicePane {...paneConfigs} />
            <AttenceDeviceTable {...tableConfigs} />
          </div>
        </Card>

        {formVisible && <AttenceDeviceForm {...formConfigs} />}
      </div>
    );
  }
}

export default AttenceDeviceIndex;
