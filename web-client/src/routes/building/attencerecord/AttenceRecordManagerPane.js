import React, { PureComponent } from 'react';
import { Form, Input, Button, Row, Col, DatePicker } from 'antd';

import OrganizationTreeSelector from './../../../components/system/form/OrganizationTreeSelector';
import BuildingDeveloperSelector from './../../../components/building/form/BuildingDeveloperSelector';

import styles from './AttenceRecordIndex.less';

const FormItem = Form.Item;

@Form.create({
  onValuesChange: (props, values) => {

    props.onParamsChange(values);
  },
})
class AttenceRecordPane extends PureComponent {
  handleAdd() {
    const { onAdd } = this.props;

    if (onAdd) {
      onAdd();
    }
  }

  handleSearch() {
    const { onSearch } = this.props;

    if (onSearch) {
      onSearch();
    }
  }

  handleReset() {
    const { onSearch } = this.props;

    this.props.form.resetFields();

    if (onSearch) {
      onSearch({});
    }
  }

  render() {
    const {beginDate, endDate, buildingDeveloperId} = this.props;
    const { getFieldDecorator } = this.props.form;

    return (
      <div>
        <div className={styles.attenceRecordIndexForm}>
          <Form onSubmit={this.handleSearch} layout="inline">
            <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
              <Col md={6} sm={24}>
                <FormItem label="所属开发商">{getFieldDecorator('buildingDeveloperId',{
                  initialValue : buildingDeveloperId
                })(<OrganizationTreeSelector  />)}</FormItem>
              </Col>
              <Col md={6} sm={24}>
                <FormItem label="所属周期">{getFieldDecorator('period', {
                  initialValue:[beginDate, endDate]
                })(<DatePicker.RangePicker  />)}</FormItem>
              </Col>
              <Col md={6} sm={24}>
                <span className={styles.submit_buttons}>
                  <Button type="primary" onClick={this.handleSearch.bind(this)}>
                    查询
                  </Button>
                  <Button style={{ marginLeft: 8 }} onClick={this.handleReset.bind(this)}>
                    重置
                  </Button>
                </span>
              </Col>
            </Row>
          </Form>
        </div>
      </div>
    );
  }
}

export default AttenceRecordPane;
