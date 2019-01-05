import React, { Component } from 'react';
import { connect } from 'dva';
import { Card } from 'antd';

import BuildingSitePane from './BuildingSitePane';
import BuildingSiteTable from './BuildingSiteTable';
import BuildingSiteForm from './BuildingSiteForm';

import styles from './BuildingSiteIndex.less';

@connect(models => ({
  buildingSite: models['building/buildingSite'],
  loading: models.loading.models.buildingSite,
}))
class BuildingSiteIndex extends Component {
  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'building/buildingSite/queryBuildingSitePageAsync',
    });
  }

  handleBuildingSiteAdd() {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/buildingSite/updateState',
      payload: {
        formVisible: true,
        formType: 'ADD',
      },
    });
  }

  handleBuildingSiteSearch(payload) {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/buildingSite/queryBuildingSitePageAsync',
      payload,
    });
  }

  handleBuildingSiteQueryParamsChange(values) {
    const { dispatch, buildingSite } = this.props;

    dispatch({
      type: 'building/buildingSite/updateState',
      payload: {
        params: { ...buildingSite.params, ...values },
      },
    });
  }

  handleBuildingSiteEdit(id) {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/buildingSite/editBuildingSiteAction',
      payload: {
        id,
      },
    });
  }

  handleBuildingSiteDelete(id) {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/buildingSite/deleteBuildingSiteByIdAsync',
      payload: {
        id,
      },
    });
  }


  handleFormSubmit(formValues) {
    const { dispatch, buildingSite } = this.props;

    if (buildingSite.formType === 'ADD') {
      dispatch({
        type: 'building/buildingSite/addBuildingSiteAsync',
        payload: formValues,
      });
    } else if (buildingSite.formType === 'EDIT') {
      dispatch({
        type: 'building/buildingSite/editBuildingSiteAsync',
        payload: formValues,
      });
    }
  }

  handleFormCancel() {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/buildingSite/updateState',
      payload: {
        formVisible: false,
        formType: null,
      },
    });
  }



  render() {
    const { dispatch, buildingSite } = this.props;

    const {
      formType,
      formVisible,
      currentBuildingSite,
      data,
    } = buildingSite;

    const paneConfigs = {
      onAdd: this.handleBuildingSiteAdd.bind(this),
      onSearch: this.handleBuildingSiteSearch.bind(this),
      onParamsChange: this.handleBuildingSiteQueryParamsChange.bind(this),
    };

    const formConfigs = {
      onSubmit: this.handleFormSubmit.bind(this),
      onCancel: this.handleFormCancel.bind(this),
      formType,
      formVisible,
      currentBuildingSite,
    };

    const tableConfigs = {
      dataSource: data.list,
      pagination: {
        ...data.pagination,
        onChange: page => {
          dispatch({
            type: 'building/buildingSite/queryBuildingSitePageAsync',
            payload: {
              page,
            },
          });
        },
      },
      onEdit: this.handleBuildingSiteEdit.bind(this),
      onDelete: this.handleBuildingSiteDelete.bind(this),
    };

    return (
      <div>
        <Card>
          <div className={styles.buildingSiteIndex}>
            <BuildingSitePane {...paneConfigs} />
            <BuildingSiteTable {...tableConfigs} />
          </div>
        </Card>

        {formVisible && <BuildingSiteForm {...formConfigs} />}
      </div>
    );
  }
}

export default BuildingSiteIndex;
