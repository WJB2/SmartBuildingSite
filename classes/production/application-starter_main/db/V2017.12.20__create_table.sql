








-- 创建产品类别信息表
drop table if exists sys_category;
create table sys_category(
	id varchar(32) not null primary key,
	tenant_id varchar(32) not null,
	name varchar(40) not null,
	name_pinyin varchar(40) null,
	name_abbr varchar(40) null,
	name_abbr_pinyin varchar(40) null,
	name_alias varchar(40) null,
	name_alias_pinyin varchar(40) null,
	deleted_flag numeric(2) not null,
	sort_no numeric(10) not null,
	created_by_id varchar(32) null,
	created_org_id varchar(32) null,
	created_time timestamp null,
	updated_by_id varchar(32) null,
	updated_org_id varchar(32) null,
	updated_time timestamp null,
	deleted_by_id varchar(32) null,
	deleted_org_id varchar(32) null,
	deleted_time timestamp null
);

-- 创建产品信息表
drop table if exists sys_goods;
create table sys_goods(
	id varchar(32) not null primary key,
	tenant_id varchar(32) not null,
	name varchar(40) not null,
	name_pinyin varchar(40) null,
	name_abbr varchar(40) null,
	name_abbr_pinyin varchar(40) null,
	name_alias varchar(40) null,
	name_alias_pinyin varchar(40) null,
	deleted_flag numeric(2) not null,
	unit_name varchar(10) null,
	sort_no numeric(10) not null,
	category_id varchar(32) not null,
	created_by_id varchar(32) null,
	created_org_id varchar(32) null,
	created_time timestamp null,
	updated_by_id varchar(32) null,
	updated_org_id varchar(32) null,
	updated_time timestamp null,
	deleted_by_id varchar(32) null,
	deleted_org_id varchar(32) null,
	deleted_time timestamp null
);

drop table if exists sys_purchasing_order;
create table sys_purchasing_order(
	id varchar(32) not null primary key,
	tenant_id varchar(32) not null,
	org_id varchar(32) not null,
	order_date timestamp not null,
	sales_volume numeric(10, 2) not null,
	gross_profit numeric(10, 2) not null,
	status numeric(2,0) not null,
	created_by_id varchar(32) null,
	created_org_id varchar(32) null,
	created_time timestamp null,
	updated_by_id varchar(32) null,
	updated_org_id varchar(32) null,
	updated_time timestamp null
);

drop table if exists sys_purchasing_order_line;
create table sys_purchasing_order_line(
	id varchar(32) not null primary key,
	tenant_id varchar(32) not null,
	order_id varchar(32) not null,
	goods_id varchar(32) not null,
	amount numeric(10, 2) not null,
	price numeric(10, 2) not null,
	created_by_id varchar(32) null,
	created_org_id varchar(32) null,
	created_time timestamp null,
	updated_by_id varchar(32) null,
	updated_org_id varchar(32) null,
	updated_time timestamp null
);

drop table if exists sys_purchasing_note;
create table sys_purchasing_note(
	id varchar(32) not null primary key,
	tenant_id varchar(32) not null,
	order_date timestamp not null,
	status numeric(2,0) not null,
	created_time timestamp null,
	updated_by_id varchar(32) null,
	updated_org_id varchar(32) null,
	updated_time timestamp null
);


drop table if exists sys_goods_price_note;
create table sys_goods_price_note(
	id varchar(32) not null primary key,
	tenant_id varchar(32) not null,
	order_date timestamp not null,
	status numeric(2,0) not null,
	created_by_id varchar(32) null,
	created_org_id varchar(32) null,
	created_time timestamp null,
	updated_by_id varchar(32) null,
	updated_org_id varchar(32) null,
	updated_time timestamp null
);

drop table if exists sys_goods_price_note_line;
create table sys_goods_price_note_line(
	id varchar(32) not null primary key,
	tenant_id varchar(32) not null,
	note_id varchar(32) not null,
	goods_id varchar(32) not null,
	price numeric(10, 2) not null,
	created_by_id varchar(32) null,
	created_org_id varchar(32) null,
	created_time timestamp null,
	updated_by_id varchar(32) null,
	updated_org_id varchar(32) null,
	updated_time timestamp null
);














drop table if exists idx_index;
create table idx_index(
	id varchar(32) not null primary key,
	tenant_id varchar(32) not null,
	parent_id varchar(32) null,
	name varchar(40) not null,
	name_pinyin varchar(40) null,
	name_abbr varchar(40) null,
	name_abbr_pinyin varchar(40) null,
	name_alias varchar(40) null,
	name_alias_pinyin varchar(40) null,
	deleted_flag numeric(2) not null,
	sort_no numeric(10) not null,
	weight numeric(10, 2) null,
	device_type numeric(10, 0) not null,
    appraisal_procedure  numeric(10, 0) not null,
	created_by_id varchar(32) null,
	created_org_id varchar(32) null,
	created_time timestamp null,
	updated_by_id varchar(32) null,
	updated_org_id varchar(32) null,
	updated_time timestamp null,
	deleted_by_id varchar(32) null,
	deleted_org_id varchar(32) null,
	deleted_time timestamp null
);

drop table if exists idx_check_record;
create table idx_check_record(
	id varchar(32) not null primary key,
	sanction_no varchar(64) null, 
	report_no varchar(64) null,
	agent varchar(64) null, 
	device_type numeric(10,0) not null,
	model varchar(64) null, 
	code varchar(64) null, 
	sn varchar(64) null,
	register_no varchar(64) null,
	appraisal_procedure numeric(10,0) null, 
	checked_date timestamp
);

drop table if exists idx_checked_index_item;
create table idx_checked_index_item(
	id varchar(32) not null primary key,
	record_id varchar(32) not null,
	index_id varchar(32) not null,
	weight numeric(10,2) null,
	level_1 numeric(10,4) null,
	level_2 numeric(10,4) null,
	level_3 numeric(10,4) null,
	level_4 numeric(10,4) null,
	summary varchar(1024) null
);

drop table if exists idx_device_info;
create table idx_device_info(
	id  VARCHAR(32) primary key NOT NULL,
record_id  VARCHAR(32) NOT NULL,
edqzl VARCHAR(32)  NULL,
kdxbcd  VARCHAR(64) NULL,
zjgzjb  VARCHAR(32)  NULL,
qsgd  VARCHAR(32)  NULL,
zyjgjcz  VARCHAR(32)  NULL,
qsjgZqssdBl  NUMERIC(10, 2) NULL,
qsjgZqsjdSd  NUMERIC(10, 2) NULL,
qsjgGzjb  VARCHAR(32)  NULL,
qsjgDjxhsl  NUMERIC(10, 2) NULL,
qsjgGl  NUMERIC(10, 2) NULL,
qsjgZs  NUMERIC(10, 2) NULL,
qsjgJsjxh  VARCHAR(32) NULL,
qsjgCdb  NUMERIC(10, 2) NULL,
qsjgJtzj  NUMERIC(10, 2) NULL,
qsjgZdqxhsl  NUMERIC(10, 2) NULL,
qsjgZdlj  NUMERIC(10, 2) NULL,
qsjgDdhlzj  NUMERIC(10, 2) NULL,
qsjgDghlzzl  NUMERIC(10, 2) NULL,
qsjgGssxhzj  NUMERIC(10, 2) NULL,
qsjgGsszl  NUMERIC(10, 2) NULL,
fqsjgFqssdBl  NUMERIC(10, 2) NULL,
fqsjgFqssdSd  NUMERIC(10, 2) NULL,
fqsjgGzjb  VARCHAR(32)  NULL,
fqsjgDjxhsl  NUMERIC(10, 2) NULL,
fqsjgGl  NUMERIC(10, 2) NULL,
fqsjgZs  NUMERIC(10, 2) NULL,
fqsjgJsjxh  VARCHAR(32)  NULL,
fqsjgCdb  NUMERIC(10, 2) NULL,
fqsjgZdqxhsl  NUMERIC(10, 2) NULL,
fqsjgZdlj  NUMERIC(10, 2) NULL,
fqsjgJtzj  NUMERIC(10, 2) NULL,
fqsjgDdhlzj  NUMERIC(10, 2) NULL,
fqsjgGssxh  NUMERIC(10, 2) NULL,
xcyxjgSd  NUMERIC(10, 2) NULL,
xcyxjgGzjb  VARCHAR(32) NULL,
xcyxjgDjxhsl  NUMERIC(10, 2) NULL,
xcyxjgGl  NUMERIC(10, 2) NULL,
xcyxjgZs  NUMERIC(10, 2) NULL,
xcyxjgJsjxh  VARCHAR(32) NULL,
xcyxjgCdb  NUMERIC(10, 2) NULL,
xcyxjgZdqxhsl  NUMERIC(10, 2) NULL,
xcyxjgZdlj  NUMERIC(10, 2) NULL,
xcyxjgXccltmzj  NUMERIC(10, 2) NULL,
xcyxjgSygdxh  NUMERIC(10, 2) NULL,
dcyxjgSd  NUMERIC(10, 2) NULL,
dcyxjgGzjb  VARCHAR(32) NULL,
dcyxjgDjxhsl  NUMERIC(10, 2) NULL,
dcyxjgGl  NUMERIC(10, 2) NULL,
dcyxjgZs  NUMERIC(10, 2) NULL,
dcyxjgJsjxh  VARCHAR(32) NULL,
dcyxjgZdqxhsl  NUMERIC(10, 2) NULL,
dcyxjgZdlj  NUMERIC(10, 2) NULL,
dcyxjgCdb  NUMERIC(10, 2) NULL,
dcyxjgXccltmzj  NUMERIC(10, 2) NULL,
dcyxjgSygdxh  NUMERIC(10, 2) NULL

);



drop table if exists idx_check_record_comment;
create table idx_check_record_comment(
	id  VARCHAR(32) primary key NOT NULL,
  record_id  VARCHAR(32) NOT NULL,
  little_danger_comment  VARCHAR(8000) NULL,
  danger_comment  VARCHAR(8000) NULL
);


