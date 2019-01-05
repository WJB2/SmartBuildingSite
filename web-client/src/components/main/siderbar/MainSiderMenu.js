import React, { PureComponent } from 'react';
import { Menu, Icon } from 'antd';

import styles from './MainSiderMenu.less';

const { SubMenu } = Menu;
const MenuItem = Menu.Item;

class MainSiderMenu extends PureComponent {

  onMenuFavoriteChange(menu){
    const {onMenuFavoriteChange} = this.props;

    onMenuFavoriteChange({
      isFavorite:!menu.extraAttrs.isFavorite,
      menuId : menu.id
    });
  }

  loopMenus(menus, collapsed) {

    return menus.map(menu => {
      if (menu.children && menu.children.length > 0) {
        return (
          <SubMenu
            key={menu.id}
            title={
              <span>
                {menu.icon ? <Icon type={menu.icon} /> : null}
                <span>{menu.name}</span>
              </span>
            }
          >
            {this.loopMenus(menu.children, collapsed)}
          </SubMenu>
        );
      } else {
        return (
          <MenuItem key={menu.id} menu={menu}>
            <div style={{overflow:'hidden'}}>
              {menu.icon ? <Icon type={menu.icon} /> : null}
              <span>{menu.name}</span>
              {
                !collapsed &&
                <div style={{position: 'absolute', top: 0, right: 5}} onClick={(e)=>{
                  e.preventDefault();

                  this.onMenuFavoriteChange(menu);
                }}>
                  {menu.extraAttrs && menu.extraAttrs.isFavorite ? <Icon type={'minus'} optype={'FAVORITE'}/> :
                    <Icon type={'plus'} optype={'FAVORITE'}/>}
                </div>
              }
            </div>
          </MenuItem>
        );
      }
    });
  }

  render() {
    const { menus, onMenuClick, collapsed  } = this.props;

    return (
      <div
        style={{
          position: 'absolute',
          width: '100%',
          height: '100%',
          overflow: 'auto',
          overflowX: 'hidden',
          paddingRight:1
        }}
      >
        <Menu
          mode="inline"
          className={styles.menu}
          inlineCollapsed={collapsed}
          inlineIndent={12}
          onClick={(e) => {
            const {item} = e;
            const { menu } = item.props;

            onMenuClick(menu);
          }}
        >
          {this.loopMenus(menus, collapsed)}
        </Menu>
      </div>
    );
  }
}

export default MainSiderMenu;
