import React, { PureComponent } from 'react';
import { Select } from 'antd';
import moment from 'moment';

const { Option } = Select;

class YearSelect extends PureComponent{

  render(){
    const years = [];

    const year = (new Date()).getFullYear();

    for(let i=0; i<10; i++){
      years.push(year-i);
    }

    return (
      <Select {...this.props} style={{width:'100%'}}>
        {
          years.map((year)=>{
            return <Option key={year} value={year}>{year}</Option>
          })
        }
      </Select>
    );
  }
}

export default YearSelect;
