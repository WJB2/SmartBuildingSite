import React, { PureComponent } from 'react';
import {Card} from 'antd';
import styles from './DevelopRewardOrPunishIndex.less';

import DevelopRewardOrPunishPane from './DevelopRewardOrPunishPane';
import DevelopRewardOrPunishTable from "./DevelopRewardOrPunishTable";
import DevelopRewardOrPunishTable2 from "./DevelopRewardOrPunishTable2";
import DevelopRewardOrPunishForm from './DevelopRewardOrPunishForm';
import DevelopRewardOrPunishButton from './DevelopRewardOrPunishButton';
import {connect} from "dva/index";

@connect(models => ({
  buildingDevelopRewardOrPunish: models['building/buildingDevelopRewardOrPunish'],
  global: models['global'],
  loading: models.loading.models.buildingDevelopRewardOrPunish,
}))

class DevelopRewardOrPunishIndex extends PureComponent {

  componentDidMount() { //在初始化渲染执行之后立即调用
    const { dispatch }=this.props;
    console.log(dispatch);
    dispatch({
      type: 'building/buildingDevelopRewardOrPunish/queryTableDevelopRewardOrPunishAsync',
    });
  }

  handleDevelopRewardOrPunishChangeIndex(e){//根据index显示对应表格
    const { dispatch,global }=this.props;
    dispatch({
      type:'building/buildingDevelopRewardOrPunish/updateState',
      payload: {
        index: e.target.value,
      },
    });
    if(e.target.value===0){
      dispatch({
        type:'building/buildingDevelopRewardOrPunish/queryTableDevelopRewardOrPunishAsync',
      });
    }else if(e.target.value===1){
      dispatch({
        type:'building/buildingDevelopRewardOrPunish/queryTable2DevelopRewardOrPunishAsync',
        payload:{
          staffId:global.currentPosition.staffId,
        },
      });
    }
  }

  handleDevelopRewardOrPunishAdd() {//点击新增奖惩信息出现对应表单
    const { dispatch } = this.props;
    dispatch({
      type: 'building/buildingDevelopRewardOrPunish/updateState',
      payload: {
        formVisible: true,
        formType: 'add',
      },
    });
  }

  handleDevelopRewardOrPunishParamsChange(values) {//表格一参数改变
    const { dispatch, buildingDevelopRewardOrPunish} = this.props;

    dispatch({
      type: 'building/buildingDevelopRewardOrPunish/updateState',
      payload: {
        params: { ...buildingDevelopRewardOrPunish.params, ...values },
      },
    });
  }

  handleDevelopRewardOrPunishParams2Change(values){//表格二参数改变
    const {dispatch,buildingDevelopRewardOrPunish}=this.props;
    dispatch({
      type:'building/buildingDevelopRewardOrPunish/updateState',
      payload:{
        params2:{...buildingDevelopRewardOrPunish.params2,...values},
      },
    });
  }

  handleDevelopRewardOrPunishSearchTable1() {//表格一根据人員姓名身份证查询
    const { dispatch } = this.props;
    console.log("11")
    dispatch({
      type: 'building/buildingDevelopRewardOrPunish/queryTableDevelopRewardOrPunishAsync',
    });
  }

  handleDevelopRewardOrPunishSearchTable2(){//表格二根據人员身份证查询
    const {dispatch}=this.props;
    console.log("22")
    dispatch({
      type:'building/buildingDevelopRewardOrPunish/queryTable2DevelopRewardOrPunishAsync',
    })
  }

  handleDevelopRewardOrPunishEdit(id) {
    const { dispatch } = this.props;

    dispatch({
      type: 'building/buildingDevelopRewardOrPunish/editDevelopRewardOrPunishAction',
      payload: {
        id,
      },
    });
  }

  handleDevelopRewardOrPunishDelete(id) {//删除表格对应数据
    const { dispatch } = this.props;
    dispatch({
      type: 'building/buildingDevelopRewardOrPunish/deleteDevelopRewardOrPunishAsync',
      payload: {
        id,
      },
    });
  }

  handleDevelopRewardOrPunishFormCancel() { //点击取消取消菜单
    const { dispatch } = this.props;
    dispatch({
      type: 'building/buildingDevelopRewardOrPunish/updateState',
      payload: {
        formVisible: false,
        formType: null,
      },
    });
  }


  handleDevelopRewardOrPunishSubmit(formValues) {//点击提交提交菜单
    const { dispatch, buildingDevelopRewardOrPunish,global } = this.props;

    if (buildingDevelopRewardOrPunish.formType === 'add') {
      dispatch({
        type: 'building/buildingDevelopRewardOrPunish/addDevelopRewardOrPunishAsync',
        payload: formValues,
      });
    } else if (buildingDevelopRewardOrPunish.formType === 'edit') {
      dispatch({
        type: 'building/buildingDevelopRewardOrPunish/editDevelopRewardOrPunishAsync',
        payload: formValues,
      });
    }
  }
  render() {
    const {buildingDevelopRewardOrPunish, global,dispatch} = this.props;

    const {
      index,
      formType,
      formVisible,
      data,
      data2,
      currentRewardOrPunish,
    } = buildingDevelopRewardOrPunish;

    const paneConfigs = {
      onChangeTableIndex: this.handleDevelopRewardOrPunishChangeIndex.bind(this),
    };

    const buttonConfigs={
      onAdd:this.handleDevelopRewardOrPunishAdd.bind(this),
    };

    const formConfigs = {
      onSubmit: this.handleDevelopRewardOrPunishSubmit.bind(this),
      onCancel: this.handleDevelopRewardOrPunishFormCancel.bind(this),
      formType,
      formVisible,
      global:global,
      currentRewardOrPunish,
    };

    const table1Configs={
      onSearch:this.handleDevelopRewardOrPunishSearchTable1.bind(this),
      onParamsChange: this.handleDevelopRewardOrPunishParamsChange.bind(this),
      dataSource: data.list,
      pagination: {
        ...data.pagination,
        onChange: page => {
          dispatch({
            type: 'building/buildingDevelopRewardOrPunish/queryTableDevelopRewardOrPunishAsync',
            payload: {
              page,
            },
          });
        },
      },
    };

    const table2Configs = {
      onAdd:this.handleDevelopRewardOrPunishAdd.bind(this),
      onSearch:this.handleDevelopRewardOrPunishSearchTable2.bind(this),
      onEdit:this.handleDevelopRewardOrPunishEdit.bind(this),
      onDelete:this.handleDevelopRewardOrPunishDelete.bind(this),
      onParamsChange: this.handleDevelopRewardOrPunishParams2Change.bind(this),
      dataSource: data2.list,
      pagination: {
        ...data2.pagination,
        onChange: page => {
          dispatch({
            type: 'building/buildingDevelopRewardOrPunish/queryTable2DevelopRewardOrPunishAsync',
            payload: {
              page,
            },
          });
        },
      },
    };

    return (
      <div>
        <Card>
          <div>
            <DevelopRewardOrPunishPane {...paneConfigs} />
            <div className={styles.developRewardOrPunishButton}>
              {(index===0)?<div className={styles.developRewardOrPublishButtonH}>
                <DevelopRewardOrPunishButton {...buttonConfigs} />
                </div>:(<DevelopRewardOrPunishButton {...buttonConfigs} />)}
              {(index===0)?<DevelopRewardOrPunishTable {...table1Configs} />:<DevelopRewardOrPunishTable2 {...table2Configs}/> }
            </div>
          </div>
        </Card>
        {formVisible && <DevelopRewardOrPunishForm {...formConfigs} />}
      </div>
    );
  }
}

export default DevelopRewardOrPunishIndex;
