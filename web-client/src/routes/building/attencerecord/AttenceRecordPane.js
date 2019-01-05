import React, { PureComponent } from 'react';
import { Form, Input, Button, Row, Col, DatePicker } from 'antd';

import styles from './AttenceRecordIndex.less';

const FormItem = Form.Item;

@Form.create({
  onValuesChange: (props, values) => {

    props.onParamsChange(values);
  },
})
class AttenceRecordPane extends PureComponent {
  handleAdd() {
    const { onExport } = this.props;

    if (onExport) {
      onExport();
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
    const {beginDate, endDate} = this.props;
    const { getFieldDecorator } = this.props.form;

    return (
      <div>
        <div className={styles.attenceRecordIndexForm}>
          <Form onSubmit={this.handleSearch} layout="inline">
            <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
              <Col md={6} sm={24}>
                <FormItem label="所属周期">{getFieldDecorator('period', {
                  initialValue:[beginDate, endDate]
                })(<DatePicker.RangePicker  />)}</FormItem>
              </Col>
            </Row>
          </Form>
        </div>
        <div className={styles.attenceRecordIndexOperator}>
          <Button icon="plus" type="primary" onClick={this.handleAdd.bind(this)}>
            导出工资发放表格
          </Button>
        </div>
      </div>
    );
  }
}

export default AttenceRecordPane;
