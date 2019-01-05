import React, { PureComponent } from 'react';
import { Select } from 'antd';

import dictService from '../../../services/system/DictService';

const { Option } = Select;

class DictItemSelector extends PureComponent {
  state = {
    dataSource: [],
  };

  async componentWillMount() {
    const {entryCode} = this.props;

    const result = await dictService.findDictItemList({
      entryCode: entryCode
    });

    this.setState({
      dataSource: result,
    });
  }

  render() {
    return (
      <Select allowClear={true} {...this.props}>
        {this.state.dataSource.map(item => {
          return <Option key={item.value}>{item.text}</Option>;
        })}
      </Select>
    );
  }
}

export default DictItemSelector;
