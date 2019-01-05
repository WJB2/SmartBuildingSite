import React, { PureComponent } from 'react';
import { Form, Input, Button, Row, Col } from 'antd';

import styles from './DepositIndex.less';

const FormItem = Form.Item;

@Form.create({
  onValuesChange: (props, values) => {
    props.onParamsChange(values);
  },
})
class DepositPane extends PureComponent {
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
    const { getFieldDecorator } = this.props.form;

    return (
      <div>
        <div className={styles.depositIndexForm}>
          <Form onSubmit={this.handleSearch} layout="inline">
            <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
              <Col md={6} sm={24}>
                <FormItem label="开发商关键字">{getFieldDecorator('depositText')(<Input />)}</FormItem>
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
        <div className={styles.depositIndexOperator}>
          <Button icon="plus" type="primary" onClick={this.handleAdd.bind(this)}>
            新增
          </Button>
        </div>
      </div>
    );
  }
}

export default DepositPane;
