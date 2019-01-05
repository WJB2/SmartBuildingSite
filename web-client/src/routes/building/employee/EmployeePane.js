import React, { PureComponent } from 'react';
import { Form, Input, Button, Row, Col, Switch } from 'antd';

import BuildingSiteSelector from './../../../components/building/form/BuildingSiteSelector';


import styles from './EmployeeIndex.less';

const FormItem = Form.Item;

@Form.create({
  onValuesChange: (props, values) => {
    props.onParamsChange(values);
  },
})
class EmployeePane extends PureComponent {
  handleAdd() {
    const { onAdd } = this.props;

    if (onAdd) {
      onAdd();
    }
  }

  handleSearch(payload) {
    const { onSearch } = this.props;

    if (onSearch) {
      onSearch(payload);
    }
  }

  handleReset() {
    const { onSearch } = this.props;

    this.props.form.resetFields();

    if (onSearch) {
      onSearch({});
    }
  }

  handleUpload(){
    const { onUpload } = this.props;

    if (onUpload) {
      onUpload({});
    }
  }

  render() {
    const {buildingDeveloperId, includeDeleted} = this.props;
    const { getFieldDecorator } = this.props.form;

    return (
      <div>

        <div className={styles.employeeIndexForm}>
          <Form onSubmit={this.handleSearch} layout="inline">
            <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
              {/*<Col md={6} sm={24}>
                  <FormItem label="所属开发商">{getFieldDecorator('buildingDeveloperId')(<OrganizationTreeSelector />)}</FormItem>
                </Col>*/}
                <Col md={6} sm={24}>
                  <FormItem label="所属工地">{getFieldDecorator('buildingSiteId')(<BuildingSiteSelector queryParams={{buildingDeveloperId:buildingDeveloperId, deletedFlag:false}}/>)}</FormItem>
                </Col>
              <Col md={6} sm={24}>
                <FormItem label="人员关键字">{getFieldDecorator('employeeText')(<Input />)}</FormItem>
              </Col>
              <Col md={6} sm={24}>
                <span className={styles.submit_buttons}>
                  <Button type="primary" onClick={()=>{
                    this.handleSearch();
                  }}>
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
        <div className={styles.employeeIndexOperator}>
          <Button icon="plus" type="primary" onClick={this.handleAdd.bind(this)}>
            新增
          </Button>
          <Button icon="plus" type="default" onClick={this.handleUpload.bind(this)}>
            导入
          </Button>

          <div style={{float:'right'}}>
            查看已删除人员: <Switch onChange={
              (checked)=>{
                this.handleSearch({
                  deletedFlag:checked?"":checked
                })
              }
            }  defaultChecked={false}/>
          </div>
        </div>
      </div>
    );
  }
}

export default EmployeePane;
