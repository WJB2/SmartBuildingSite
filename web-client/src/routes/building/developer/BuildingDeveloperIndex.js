import React, { Component } from 'react';
import { connect } from 'dva';
import { Card } from 'antd';

import BuildingDeveloperPane from './BuildingDeveloperPane';
import BuildingDeveloperTable from './BuildingDeveloperTable';
import BuildingDeveloperForm from './BuildingDeveloperForm';

import styles from './BuildingDeveloperIndex.less';

@connect(models => ({
  buildingDeveloper: models['building/buildingDeveloper'],
  loading: models.loading.models.buildingDeveloper,
}))
class BuildingDeveloperIndex extends Component {
  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'building/buildingDeveloper/queryBuildingDeveloperPageAsync',
    });
  }

  handleBuildingDeveloperAdd() {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/buildingDeveloper/updateState',
      payload: {
        formVisible: true,
        formType: 'ADD',
      },
    });
  }

  handleBuildingDeveloperSearch(payload) {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/buildingDeveloper/queryBuildingDeveloperPageAsync',
      payload,
    });
  }

  handleBuildingDeveloperQueryParamsChange(values) {
    const { dispatch, buildingDeveloper } = this.props;

    dispatch({
      type: 'building/buildingDeveloper/updateState',
      payload: {
        params: { ...buildingDeveloper.params, ...values },
      },
    });
  }

  handleBuildingDeveloperEdit(id) {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/buildingDeveloper/editBuildingDeveloperAction',
      payload: {
        id,
      },
    });
  }

  handleBuildingDeveloperDelete(id) {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/buildingDeveloper/deleteBuildingDeveloperByIdAsync',
      payload: {
        id,
      },
    });
  }

  handleFormSubmit(formValues) {
    const { dispatch, buildingDeveloper } = this.props;

    if (buildingDeveloper.formType === 'ADD') {
      dispatch({
        type: 'building/buildingDeveloper/addBuildingDeveloperAsync',
        payload: formValues,
      });
    } else if (buildingDeveloper.formType === 'EDIT') {
      dispatch({
        type: 'building/buildingDeveloper/editBuildingDeveloperAsync',
        payload: formValues,
      });
    }
  }

  handleFormCancel() {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/buildingDeveloper/updateState',
      payload: {
        formVisible: false,
        formType: null,
      },
    });
  }

  render() {
    const { dispatch, buildingDeveloper } = this.props;

    const {
      formType,
      formVisible,
      currentBuildingDeveloper,
      data,
    } = buildingDeveloper;

    const paneConfigs = {
      onAdd: this.handleBuildingDeveloperAdd.bind(this),
      onSearch: this.handleBuildingDeveloperSearch.bind(this),
      onParamsChange: this.handleBuildingDeveloperQueryParamsChange.bind(this),
    };

    const formConfigs = {
      onSubmit: this.handleFormSubmit.bind(this),
      onCancel: this.handleFormCancel.bind(this),
      formType,
      formVisible,
      currentBuildingDeveloper,
    };

    const tableConfigs = {
      dataSource: data.list,
      pagination: {
        ...data.pagination,
        onChange: page => {
          dispatch({
            type: 'building/buildingDeveloper/queryBuildingDeveloperPageAsync',
            payload: {
              page,
            },
          });
        },
      },
      onEdit: this.handleBuildingDeveloperEdit.bind(this),
      onDelete: this.handleBuildingDeveloperDelete.bind(this),
    };

    return (
      <div>
        <Card>
          <div className={styles.buildingDeveloperIndex}>
            <BuildingDeveloperPane {...paneConfigs} />
            <BuildingDeveloperTable {...tableConfigs} />
          </div>
        </Card>

        {formVisible && <BuildingDeveloperForm {...formConfigs} />}
      </div>
    );
  }
}

export default BuildingDeveloperIndex;
