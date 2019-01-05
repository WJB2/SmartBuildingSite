import React, { PureComponent } from 'react';
import {Card} from 'antd';
import styles from './RewardOrPunishIndex.less';
import RewardOrPunishPane from './RewardOrPunishPane';
import RewardOrPunishTable from "./RewardOrPunishTable";
import RewardOrPunishTable2 from './RewardOrPunishTable2';
import RewardOrPunishTable3 from './RewardOrPunishTable3';
import RewardOrPunishAuditForm from './RewardOrPunishAuditForm';
import RewardOrPunishButton from './RewardOrPunishButton';

import {connect} from "dva/index";

@connect(models => ({
  buildingRewardOrPunish: models['building/buildingRewardPunish'],
  global: models['global'],
  loading: models.loading.models.buildingRewardPunish,
}))

class RewardOrPunishIndex extends PureComponent {

  componentDidMount() { //在初始化渲染执行之后立即调用
    const { global,dispatch,buildingRewardOrPunish }=this.props;

    dispatch({
      type: 'building/buildingRewardPunish/queryTableRewardOrPunishAsync',
    });
  }

  handleRewardOrPunishChangeIndex(e){//根据index显示对应表格(成功)
    const { global,dispatch }=this.props;
    dispatch({
      type:'building/buildingRewardPunish/updateState',
      payload: {
        index: e.target.value,
      },
    });
    console.log(e.target.value);
    if(e.target.value===0){
      dispatch({
        type:'building/buildingRewardPunish/queryTableRewardOrPunishAsync',
      });
    }else if(e.target.value === 1){
      dispatch({
        type:'building/buildingRewardPunish/queryTable2RewardOrPunishAsync',
      });
    }else if(e.target.value === 2){
      dispatch({
        type:'building/buildingRewardPunish/queryTable3RewardOrPunishAsync',
      });
    }
  }

  handleRewardOrPunishParamsChange(values) {//表格一参数改变
    const { dispatch, buildingRewardOrPunish} = this.props;

    dispatch({
      type: 'building/buildingRewardPunish/updateState',
      payload: {
        params: { ...buildingRewardOrPunish.params, ...values },
      },
    });
  }

  handleRewardOrPunishParams2Change(values) {//表格二参数改变
    const { dispatch, buildingRewardOrPunish} = this.props;

    dispatch({
      type: 'building/buildingRewardPunish/updateState',
      payload: {
        params2: { ...buildingRewardOrPunish.params2, ...values },
      },
    });
  }

  handleRewardOrPunishParams3Change(values) {//表格三参数改变
    const { dispatch, buildingRewardOrPunish} = this.props;

    dispatch({
      type: 'building/buildingRewardPunish/updateState',
      payload: {
        params3: { ...buildingRewardOrPunish.params3, ...values },
      },
    });
  }

  handleRewardOrPunishSearchTable1() {//表格一根据姓名查找
    const { dispatch } = this.props
    dispatch({
      type: 'building/buildingRewardPunish/queryTableRewardOrPunishAsync',
    });
  }

  handleRewardOrPunishSearchTable2(){//表格二根据姓名查找
    const { dispatch } = this.props
    dispatch({
      type: 'building/buildingRewardPunish/queryTable2RewardOrPunishAsync',
    });
  }

  handleRewardOrPunishSearchTable3(){//表格三根据姓名查找
    const { dispatch } = this.props
    dispatch({
      type: 'building/buildingRewardPunish/queryTable3RewardOrPunishAsync',
    });
  }

  handleRewardOrPunishAudit(id){//点击审批出现对应表单
    const { dispatch } = this.props;
    dispatch({
      type: 'building/buildingRewardPunish/auditRewardOrPunishAction',
      payload: {
        id
      },
    });
  }

  handleRewardOrPunishFormCancel() { //点击取消取消菜单
    const { dispatch,buildingRewardOrPunish } = this.props;
    if(buildingRewardOrPunish.formType==="add"||buildingRewardOrPunish.formType==="audit"){
      dispatch({
        type: 'building/buildingRewardPunish/updateState',
        payload: {
          formVisible: false,
          formType: null,
        },
      });
    }
  }

  handleRewardOrPunishDelete(id){//点击删除删除对应单项
    const {dispatch,global}=this.props;
    dispatch({
      type:'building/buildingRewardPunish/deleteRewardOrPunishAsync',
      payload:{
        id
      }
    });
  }

  handleRewardOrPunishSubmit(formValues) {//点击提交提交菜单

    const { dispatch,global } = this.props;
      dispatch({
        type:'building/buildingRewardPunish/updateState',
        payload:{
          formType:null,
          formVisible:false,
        }
      });
      dispatch({
        type: 'building/buildingRewardPunish/auditRewardOrPunishAsync',
        payload: formValues,
      });
  }
  render() {

    const {buildingRewardOrPunish, global,dispatch} = this.props;
    const {
      index,
      formType,
      formVisible,
      data,
      data2,
      auditFormContent,
      data3,
    } = buildingRewardOrPunish;

    const {currentPositionId}=global.currentPosition.id;

    const paneConfigs = {
      onChangeTableIndex: this.handleRewardOrPunishChangeIndex.bind(this),
    };

    const auditformConfig={
      onSubmit: this.handleRewardOrPunishSubmit.bind(this),
      onCancel: this.handleRewardOrPunishFormCancel.bind(this),
      formType,
      global:global,
      auditFormContent,
    };

    const table1Configs = {
      onSearch:this.handleRewardOrPunishSearchTable1.bind(this),
      onParamsChange: this.handleRewardOrPunishParamsChange.bind(this),
      dataSource: data.list,
      pagination: {
        ...data.pagination,
        onChange: page => {
          dispatch({
            type: 'building/buildingRewardPunish/queryTable1RewardOrPunishAsync',
            payload: {
              page,
            },
          });
        },
      },
    };

    const table2Configs = {
      onAudit:this.handleRewardOrPunishAudit.bind(this),
      onSearch:this.handleRewardOrPunishSearchTable2.bind(this),
      onDelete:this.handleRewardOrPunishDelete.bind(this),
      onParamsChange: this.handleRewardOrPunishParams2Change.bind(this),
      onCancel:this.handleRewardOrPunishFormCancel.bind(this),
      dataSource: data2.list,
      pagination: {
        ...data2.pagination,
        onChange: page => {
          dispatch({
            type: 'building/buildingRewardPunish/queryTable2RewardOrPunishAsync',
            payload: {
              page,
            },
          });
        },
      },
    };

    const table3Configs = {
      onSearch:this.handleRewardOrPunishSearchTable3.bind(this),
      onParamsChange: this.handleRewardOrPunishParams3Change.bind(this),
      dataSource: data3.list,
      pagination: {
        ...data3.pagination,
        onChange: page => {
          dispatch({
            type: 'building/buildingRewardPunish/queryTable3RewardOrPunishAsync',
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
            <RewardOrPunishPane  {...paneConfigs}/>
            <div className={styles.RewardOrPunishButton}>
              {(index===0)?(<div className={styles.RewardOrPublishButtonH}><RewardOrPunishButton  /></div>)
                :(index===1?(<div className={styles.RewardOrPublishButtonH}><RewardOrPunishButton  /></div>):(<div className={styles.RewardOrPublishButtonH}><RewardOrPunishButton /></div>))}
            </div>
              {(index===0)?<RewardOrPunishTable {...table1Configs}/>:(index===1?<RewardOrPunishTable2 {...table2Configs}/>:<RewardOrPunishTable3 {...table3Configs} />)}
          </div>
        </Card>
        {formVisible && <RewardOrPunishAuditForm {...auditformConfig} />}
      </div>
    );

  }
}

export default RewardOrPunishIndex;
