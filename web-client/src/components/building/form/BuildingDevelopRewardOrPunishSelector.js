import React, { PureComponent } from 'react';
import { Select } from 'antd';

import buildingDevelopRewardOrPunishService from '../../../services/building/BuildingDevelopRewardOrPunishService';

const { Option } = Select;

class BuildingDevelopRewardOrPunishSelector extends PureComponent {
  state = {
    dataSource: [],
  };

  async componentWillMount() {
    const { queryParams } = this.props;

    const result = await buildingDevelopRewardOrPunishService.getDevelopRewardOrPunishList();

    this.setState({
      dataSource: result,
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

export default BuildingDevelopRewardOrPunishSelector;
