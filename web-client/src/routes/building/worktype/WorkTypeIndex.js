import React, { Component } from 'react';
import { connect } from 'dva';
import { Card } from 'antd';

import WorkTypePane from './WorkTypePane';
import WorkTypeTable from './WorkTypeTable';
import WorkTypeForm from './WorkTypeForm';

import styles from './WorkTypeIndex.less';

@connect(models => ({
  workType: models['building/workType'],
  global: models['global'],
  loading: models.loading.models.workType,
}))
class WorkTypeIndex extends Component {
  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'building/workType/queryWorkTypePageAsync',
    });
  }

  handleWorkTypeAdd() {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/workType/updateState',
      payload: {
        formVisible: true,
        formType: 'ADD',
      },
    });
  }

  handleWorkTypeSearch() {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/workType/queryWorkTypePageAsync',
    });
  }

  handleWorkTypeQueryParamsChange(values) {
    const { dispatch, workType } = this.props;

    dispatch({
      type: 'building/workType/updateState',
      payload: {
        params: { ...workType.params, ...values },
      },
    });
  }

  handleWorkTypeEdit(id) {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/workType/editWorkTypeAction',
      payload: {
        id,
      },
    });
  }

  handleWorkTypeDelete(id) {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/workType/deleteWorkTypeByIdAsync',
      payload: {
        id,
      },
    });
  }


  handleFormSubmit(formValues) {
    const { dispatch, workType } = this.props;

    if (workType.formType === 'ADD') {
      dispatch({
        type: 'building/workType/addWorkTypeAsync',
        payload: formValues,
      });
    } else if (workType.formType === 'EDIT') {
      dispatch({
        type: 'building/workType/editWorkTypeAsync',
        payload: formValues,
      });
    }
  }

  handleFormCancel() {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/workType/updateState',
      payload: {
        formVisible: false,
        formType: null,
      },
    });
  }

  render() {
    const { dispatch, workType, global } = this.props;

    const {
      formType,
      formVisible,
      currentWorkType,
      data,
    } = workType;

    const paneConfigs = {
      onAdd: this.handleWorkTypeAdd.bind(this),
      onSearch: this.handleWorkTypeSearch.bind(this),
      onParamsChange: this.handleWorkTypeQueryParamsChange.bind(this),
      buildingDeveloperId:global.currentPosition.orgId,
    };

    const formConfigs = {
      onSubmit: this.handleFormSubmit.bind(this),
      onCancel: this.handleFormCancel.bind(this),
      formType,
      formVisible,
      currentWorkType,
      global,
    };

    const tableConfigs = {
      dataSource: data.list,
      pagination: {
        ...data.pagination,
        onChange: page => {
          dispatch({
            type: 'building/workType/queryWorkTypePageAsync',
            payload: {
              page,
            },
          });
        },
      },
      onEdit: this.handleWorkTypeEdit.bind(this),
      onDelete: this.handleWorkTypeDelete.bind(this),
    };

    return (
      <div>
        <Card>
          <div className={styles.workTypeIndex}>
            <WorkTypePane {...paneConfigs} />
            <WorkTypeTable {...tableConfigs} />
          </div>
        </Card>

        {formVisible && <WorkTypeForm {...formConfigs} />}
      </div>
    );
  }
}

export default WorkTypeIndex;
