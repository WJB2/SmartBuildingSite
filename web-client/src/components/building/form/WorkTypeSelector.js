import React, { PureComponent } from 'react';
import { Select } from 'antd';

import workTypeService from '../../../services/building/WorkTypeService';

const { Option } = Select;

class WorkTypeSelector extends PureComponent {
  state = {
    dataSource: [],
  };

  async componentWillMount() {
    const { queryParams } = this.props;

    const result = await workTypeService.findWorkTypeList(queryParams ? queryParams :{});

    this.setState({
      dataSource: result,
    });
  }

  render() {
    return (
      <Select {...this.props}>
        {this.state.dataSource.map(item => {
          return <Option key={item.name}>{item.name}</Option>;
        })}
      </Select>
    );
  }
}

export default WorkTypeSelector;
