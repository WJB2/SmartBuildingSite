import React, { PureComponent } from 'react';
import { Layout, Icon, Dropdown, Row, Col, Divider } from 'antd';

import styles from './MainHeader.less';
import PasswordForm from "./PasswordForm";
import {LOGO} from "../../../utils/GlobalConst";

const { Header } = Layout;

export default class MainHeader extends PureComponent {
  onModuleChange(module) {
    const { dispatch } = this.props;

    if (module.menuId) {
      dispatch({
        type: 'global/changeModule',
        payload: {
          module,
        },
      });
    }
  }

  render() {
    const { sidebarCollapsed, dispatch, modules, currentPosition, passwordFormVisible, onPasswordChange } = this.props;

    return (
      <Header className={styles.header}>
        <div className={styles.logo} style={{float:'left', width:220}}>{LOGO}</div>
        <div className={styles.left}>
          <div
            className={styles.item}
            onClick={() => {
              dispatch({
                type: 'global/updateState',
                payload: {
                  sidebarCollapsed: !sidebarCollapsed,
                },
              });
            }}
          >
            <Icon type={sidebarCollapsed ? 'menu-unfold' : 'menu-fold'} />
          </div>
          <div
            className={styles.item}
            onClick={() => {
              dispatch({
                type: 'global/goHome',
                payload: {
                  currentModule: null,
                },
              });
            }}
          >
            <Icon type="home" />
          </div>
          <Dropdown
            overlay={
              <div>
                <Row
                  style={{
                    width: 480,
                    backgroundColor: '#fff',
                    height: 480,
                    padding: 16,
                    border: '1px solid rgba(0, 0, 0, 0.15)',
                    borderRadius: 0,
                  }}
                >
                  {(modules?modules:[]).map(module => {
                    return (
                      <Col
                        span={4}
                        key={module.id}
                        style={{ textAlign: 'center', cursor: 'pointer' }}
                        onClick={() => {
                          this.onModuleChange(module);
                        }}
                      >
                        <div style={{ height: 50, paddingTop: 14 }}>
                          <Icon
                            type={module.icon}
                            style={{ fontSize: 28, color: 'rgb(27, 178, 243)' }}
                          />
                        </div>
                        <div>{module.name}</div>
                      </Col>
                    );
                  })}
                </Row>
              </div>
            }
            trigger={['click']}
          >
            <div className={styles.item}>
              <Icon type="appstore" />
            </div>
          </Dropdown>
        </div>

        <div style={{float:'left', display:'inline-block', marginLeft:50, color:'#000', fontSize:20, fontWeight:'bolder', letterSpacing:4}}>
          打造无欠薪城市
        </div>

        <div className={styles.right}>
          <Dropdown placement={"bottomCenter"} overlay={
            <div style={{backgroundColor:'#fff', padding:12}}>
              <div  style={{cursor:'pointer'}} onClick={() => {
                dispatch({
                  type: 'global/updateState',
                  payload: {
                    passwordFormVisible: true,
                  },
                });
              }}>
                <Icon type="user" style={{ fontSize: 16, color: 'rgb(27, 178, 243)' }}/>&nbsp;修改密码
              </div>
              <Divider style={{marginTop:12, marginBottom:12}}/>
              <div style={{ cursor:'pointer'}}
                onClick={() => {
                  dispatch({
                    type: 'user/authentication/logout',
                    payload: {
                      sidebarCollapsed: !sidebarCollapsed,
                    },
                  });
                }}
              >
                <Icon type="poweroff" style={{ fontSize: 16, color: 'rgb(27, 178, 243)' }}/>&nbsp;注销
              </div>
            </div>
          }>
            <div className={styles.item} style={{fontSize:12}}>
              {currentPosition.staff.name + '(' + currentPosition.org.name + ')'}
            </div>
          </Dropdown>

        </div>
        {passwordFormVisible && <PasswordForm onSubmit={(values)=>{
          onPasswordChange(values)
        }} onCancel={()=>{
          dispatch({
            type: 'global/updateState',
            payload: {
              passwordFormVisible: false,
            },
          });
        }}/>}
      </Header>
    );
  }
}
