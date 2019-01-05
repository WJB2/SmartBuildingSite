import React, { PureComponent } from 'react';
import { Layout } from 'antd';

import { LOGO } from './../../../utils/GlobalConst';

import MainSiderMenu from './MainSiderMenu';
import MainFavoriteMenu from './MainFavoriteMenu';

import styles from './MainSiderBar.less';

const { Header, Sider, Content } = Layout;

class SiderBar extends PureComponent {
  render() {
    const {
      sidebarCollapsed,
      menus,
      favoriteMenus,
      onMenuClick,
      dispatch,
      currentModule,
      onMenuFavoriteChange,
    } = this.props;
    return (
      <Sider
        collapsed={sidebarCollapsed}
        collapsedWidth={80}
        collapsible
        width={220}
        className={styles.mainSiderBar}
        style={{ position: 'relative' }}
        trigger={null}
      >
        <Layout style={{height:'100%'}}>
          <Content className={styles.content}>
               {currentModule ? (
                    <MainSiderMenu
                    dispatch={dispatch}
                  collapsed={sidebarCollapsed}
                  menus={menus}
                  onMenuClick={onMenuClick}
                    collapsed={sidebarCollapsed}
                    onMenuFavoriteChange={onMenuFavoriteChange}
                />
              ) : (
                <MainFavoriteMenu
                favoriteMenus={favoriteMenus}
                dispatch={dispatch}
                collapsed={sidebarCollapsed}
                onMenuClick={onMenuClick}
                />
              )}
          </Content>
        </Layout>
      </Sider>
    );
  }
}

export default SiderBar;
