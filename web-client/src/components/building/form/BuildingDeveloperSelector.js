import React, { PureComponent } from 'react';
import { Select } from 'antd';

import buildingDeveloperService from '../../../services/building/BuildingDeveloperService';

const { Option } = Select;

class RoleSelector extends PureComponent {
  state = {
    dataSource: [],
  };

  async componentWillMount() {
    //const result = await buildingDeveloperService.findBuildingDeveloperList();

    this.setState({
      dataSource: [],
    });
  }

  render() {
    return (
      <Select {...this.props}>
        {this.state.dataSource.map(item => {
          return <Option key={item.id}>{item.name}</Option>;
        })}
      </Select>
    );
  }
}

export default RoleSelector;
