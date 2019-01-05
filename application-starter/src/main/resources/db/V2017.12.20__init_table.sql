
--插入默认模块数据
insert into gbl_module(id, name, icon, link_path, menu_id, sort_no)
values('18c7f44f72034ab3b27251afa1e39015', '系统管理', 'setting', '/system/user', 'fd0c794cf43e11e78c3f9a214cf093ae', 9999);

insert into gbl_module(id, name, icon, link_path, menu_id, sort_no)
values('ebd42f831dbb4fd2a671d6cccc1e7424', '智慧工地', 'home', '/home', '8e823449ed604746a470378ce2f8e1e1', 1);

-- 插入系统导航根菜单
insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, sort_no)
values('06489b04f43e11e78c3f9a214cf093ae', null, 'R', '系统导航菜单', 'XTDHCD', null, null, 1);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, sort_no)
values('fd0c794cf43e11e78c3f9a214cf093ae', '06489b04f43e11e78c3f9a214cf093ae', 'SYS', '人员组织架构', 'RYZZJG', null, null, 999);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('fd0c7f46f43e11e78c3f9a214cf093ae', 'fd0c794cf43e11e78c3f9a214cf093ae', 'SYS001', '组织机构维护', 'ZZJGWH', 'bars', '/system/organization', '6fa7e366f5b511e78c3f9a214cf093ae', 10);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('fd0c8162f43e11e78c3f9a214cf093ae', 'fd0c794cf43e11e78c3f9a214cf093ae', 'SYS005', '工作职位维护', 'GZZWWH', 'trophy', '/system/post',  '6fa7ee6af5b511e78c3f9a214cf093ae', 20);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('fd0c82b6f43e11e78c3f9a214cf093ae', 'fd0c794cf43e11e78c3f9a214cf093ae', 'SYS010', '人员角色维护', 'RYJSWH', 'team', '/system/role',  '6fa7f6c6f5b511e78c3f9a214cf093ae', 30);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('fd0c83d8f43e11e78c3f9a214cf093ae', 'fd0c794cf43e11e78c3f9a214cf093ae', 'SYS015', '人员信息维护', 'RYXXWH', 'user', '/system/staff',  '0f25b2b0f5b611e78c3f9a214cf093ae', 40);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('fd0c852cf43e11e78c3f9a214cf093ae', 'fd0c794cf43e11e78c3f9a214cf093ae', 'SYS020', '人员工作岗位维护', 'RYGWWH', 'idcard', '/system/position',  '0f25c034f5b611e78c3f9a214cf093ae', 50);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('0ee002507efd4609b6214bf6591685ea', 'fd0c794cf43e11e78c3f9a214cf093ae', 'SYS021', '系统字典维护', 'XTZDWH', 'idcard', '/system/dict',  '3d651641d676425aa875d1eca1848e19', 60);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('5719899431e641d0934184bcc4d639a7', 'fd0c794cf43e11e78c3f9a214cf093ae', 'SYS022', '微信公众号设置', 'WXGZHSZ', 'wechat', '/system/wechat',  'bc742f9e47654a5daedccb51c6eed2ce', 70);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, sort_no)
values('0f669f334da74cb791223da48a7c502e', '06489b04f43e11e78c3f9a214cf093ae', 'USER', '用户个人中心', 'YHGRZX', null, null, 998);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, sort_no)
values('f05033544fd54139b5e78a346cfce622', '0f669f334da74cb791223da48a7c502e', 'PROFILE', '个人信息', 'GRXX', 'user', '/user/profile', 10);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, sort_no)
values('547d0c3aebf14b13bbf0bad5e1342503', '0f669f334da74cb791223da48a7c502e', 'EDIT_PASSWORD', '修改密码', 'XGMM', 'lock', '/user/edit-password', 20);



-- 插入默认系统权限
insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7d3b2f5b511e78c3f9a214cf093ae', null, '*', '根权限', 'XTZQX', 1);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7db96f5b511e78c3f9a214cf093ae', '6fa7d3b2f5b511e78c3f9a214cf093ae', 'sys:*', '人员组织架构维护权限', 'RYZZJGWHQX', 999);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7de0cf5b511e78c3f9a214cf093ae', '6fa7db96f5b511e78c3f9a214cf093ae', 'sys:org:*', '组织机构维护权限', 'XTGLQX', 1);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7df7ef5b511e78c3f9a214cf093ae', '6fa7de0cf5b511e78c3f9a214cf093ae', 'sys:org:add', '新增组织机构权限', 'XZZZJGQX', 1);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7e0b4f5b511e78c3f9a214cf093ae', '6fa7de0cf5b511e78c3f9a214cf093ae', 'sys:org:edit', '编辑组织机构权限', 'BJZZJGQX', 2);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7e1d6f5b511e78c3f9a214cf093ae', '6fa7de0cf5b511e78c3f9a214cf093ae', 'sys:org:del', '删除组织机构权限', 'SCZZJGQX', 3);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7e366f5b511e78c3f9a214cf093ae', '6fa7de0cf5b511e78c3f9a214cf093ae', 'sys:org:view', '查看组织机构权限', 'CKZZJGQX', 4);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7e88ef5b511e78c3f9a214cf093ae', '6fa7db96f5b511e78c3f9a214cf093ae', 'sys:post:*', '工作职位维护权限', 'GZZWWHQX', 2);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7e9d8f5b511e78c3f9a214cf093ae', '6fa7e88ef5b511e78c3f9a214cf093ae', 'sys:post:add', '新增工作职位权限', 'XZGZZWQX', 1);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7eafaf5b511e78c3f9a214cf093ae', '6fa7e88ef5b511e78c3f9a214cf093ae', 'sys:post:edit', '编辑工作职位权限', 'BJGZZWQX', 2);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7ed2af5b511e78c3f9a214cf093ae', '6fa7e88ef5b511e78c3f9a214cf093ae', 'sys:post:del', '删除工作职位权限', 'SCGZZWQX', 3);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7ee6af5b511e78c3f9a214cf093ae', '6fa7e88ef5b511e78c3f9a214cf093ae', 'sys:post:view', '查看工作职位权限', 'CKGZZWQX', 4);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7ef8cf5b511e78c3f9a214cf093ae', '6fa7db96f5b511e78c3f9a214cf093ae', 'sys:role:*', '人员角色维护权限', 'RYJSWHQX', 3);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7f34cf5b511e78c3f9a214cf093ae', '6fa7ef8cf5b511e78c3f9a214cf093ae', 'sys:role:add', '新增人员角色权限', 'XZRYJSQX', 1);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7f496f5b511e78c3f9a214cf093ae', '6fa7ef8cf5b511e78c3f9a214cf093ae', 'sys:role:edit', '编辑人员角色权限', 'BJRYJSQX', 2);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7f5aef5b511e78c3f9a214cf093ae', '6fa7ef8cf5b511e78c3f9a214cf093ae', 'sys:role:del', '删除人员角色权限', 'SCRYJSQX', 3);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7f6c6f5b511e78c3f9a214cf093ae', '6fa7ef8cf5b511e78c3f9a214cf093ae', 'sys:role:view', '查看人员角色权限', 'SCRYJSQX', 4);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7f7def5b511e78c3f9a214cf093ae', '6fa7db96f5b511e78c3f9a214cf093ae', 'sys:staff:*', '人员信息维护权限', 'CKXXWHQX', 4);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7f8f6f5b511e78c3f9a214cf093ae', '6fa7f7def5b511e78c3f9a214cf093ae', 'sys:staff:add', '新增人员信息权限', 'CKXXWHQX', 1);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6fa7fc5cf5b511e78c3f9a214cf093ae', '6fa7f7def5b511e78c3f9a214cf093ae', 'sys:staff:edit', '编辑人员信息权限', 'BJXXWHQX', 2);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('0f25b008f5b611e78c3f9a214cf093ae', '6fa7f7def5b511e78c3f9a214cf093ae', 'sys:staff:del', '删除人员信息权限', 'SCXXWHQX', 3);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('0f25b2b0f5b611e78c3f9a214cf093ae', '6fa7f7def5b511e78c3f9a214cf093ae', 'sys:staff:view', '查看人员信息权限', 'CKXXWHQX', 4);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('0f25b404f5b611e78c3f9a214cf093ae', '6fa7db96f5b511e78c3f9a214cf093ae', 'sys:posi:*', '人员工作岗位维护权限', 'RYGZGWWHQX', 5);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('0f25b530f5b611e78c3f9a214cf093ae', '0f25b404f5b611e78c3f9a214cf093ae', 'sys:posi:add', '新增人员工作岗位权限', 'XZRYGZGWQX', 1);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('0f25ba9ef5b611e78c3f9a214cf093ae', '0f25b404f5b611e78c3f9a214cf093ae', 'sys:posi:edit', '编辑人员工作岗位权限', 'BJRYGZGWQX', 2);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('0f25bd82f5b611e78c3f9a214cf093ae', '0f25b404f5b611e78c3f9a214cf093ae', 'sys:posi:del', '删除人员工作岗位权限', 'SCRYGZGWQX', 3);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('0f25c034f5b611e78c3f9a214cf093ae', '0f25b404f5b611e78c3f9a214cf093ae', 'sys:posi:view', '查看人员工作岗位权限', 'CKRYGZGWQX', 4);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('3d651641d676425aa875d1eca1848e19', '6fa7db96f5b511e78c3f9a214cf093ae', 'sys:dict:op', '系统字典信息维护权限', 'XTZDXXWHQX', 4);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('bc742f9e47654a5daedccb51c6eed2ce', '6fa7db96f5b511e78c3f9a214cf093ae', 'sys:wechat:set', '微信设置权限', 'WXSZQX', 4);

--插入智慧工地模块菜单
insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('8e823449ed604746a470378ce2f8e1e1', '06489b04f43e11e78c3f9a214cf093ae', 'BUILDING', '智慧工地', 'ZHGD', null, null, null, 1);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('043d43d81ece4f9092cccfe2624c5047', '8e823449ed604746a470378ce2f8e1e1', 'BUILDING:SET', '基础设置', 'JCSZ', null, null, null, 999);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('b114e072f03343cc9cd0c8c431eb7520', '043d43d81ece4f9092cccfe2624c5047', 'BUILDING:SET:DEV', '开发商信息维护', 'KFSXXWH', null, '/building/building-developer', '5f97e48f6d7f4bd0af045e3440af4927', 1);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('a6350f45c45b4b3abd6b42f402179e41', '043d43d81ece4f9092cccfe2624c5047', 'BUILDING:SET:SITE', '工地信息维护', 'GDXXWH', null, '/building/building-site', 'b82587f29112441e96e80eaa628dc729', 2);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('ff1733fd2722453abd931109702481a3', '043d43d81ece4f9092cccfe2624c5047', 'BUILDING:SET:EMP', '员工信息维护', 'RYXXWH', null, '/building/employee', 'b82587f29112441e96e80eaa628dc729', 3);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('959c6bc0997648948ff04a78761862c8', '043d43d81ece4f9092cccfe2624c5047', 'BUILDING:SET:WTYTPE', '工种信息维护', 'GZXXWH', null, '/building/work-type', 'fa02700b53c64c5ea703f1fbdb3d1d8e', 4);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('ad979365dbe646769dbed09f22d9f0d4', '043d43d81ece4f9092cccfe2624c5047', 'BUILDING:SET:DEVICE', '设备信息维护', 'GZXXWH', null, '/building/attence-device', 'b0ee7e9101e94da5994d4ef53d5ec20a', 5);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('cb3055a282e0423aa77710881697db41', '043d43d81ece4f9092cccfe2624c5047', 'BUILDING:SET:TIME', '考勤时间维护', 'KQSJWH', null, '/building/time-setting', 'e6456e22e0c942b0af50ef5eecf1ed11', 6);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('5045cff0b2a74e898720112106d971f7', '8e823449ed604746a470378ce2f8e1e1', 'BUILDING:MNY', '代发工资管理', 'YJXZGL', null, null, null, 200);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('e8d0beac4d9548b2b74bcb356068e0f8', '5045cff0b2a74e898720112106d971f7', 'BUILDING:SRY:DEV', '工资发放管理(开发商)', 'GZFFGL', null, '/building/salary-for-developer', '62e2df3e6ac74934a92bcbabc3618670', 1);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('5d3e21f5a56d45f380867f1a0c4fdf5d', '5045cff0b2a74e898720112106d971f7', 'BUILDING:SRY:DO', '工资发放管理(银行)', 'GZFFGL', null, '/building/salary-for-supervisor', '0d943cbb38084b6d99945de10c12733b', 2);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('9d37f5a713a64fd39e23bc399ed0733c', '5045cff0b2a74e898720112106d971f7', 'BUILDING:SRY:SPV', '工资发放详情(监管机构)', 'GZFFGL', null, '/building/salary-for-supervisor', 'b068b8e20591449bac4c7a69f2f6c14a', 3);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('eb1524b74fd0474b913c242c70b2426b', '5045cff0b2a74e898720112106d971f7', 'BUILDING:MNY:DEV', '开发商备付金管理', 'KFSXXWH', null, '/building/developer-deposit', '9a8ff9caed394720bb8aeebbdd190a29', 4);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('b6234f9054fd4520990921372c8e97df', '8e823449ed604746a470378ce2f8e1e1', 'BUILDING:ATT', '员工考勤管理', 'YGKQGL', null, null, null, 100);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('f2f856d7b127486dba2f48a08b45cdac', 'b6234f9054fd4520990921372c8e97df', 'BUILDING:ATT:MANU', '人工考勤', 'RGKQ', null, '/building/manual-clocking', 'c8e37d1043564202b4757e94776cef80', 1);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('edf1d11a7af5426aa74592950fa0b9c5', 'b6234f9054fd4520990921372c8e97df', 'BUILDING:ATT:MANUD', '考勤统计(开发商)', 'RGKQ', null, '/building/attence-record', '139c729d23a44f67a0d4a4ea4475fe41', 2);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('f845ffafa7614eae8449fc418783acd3', 'b6234f9054fd4520990921372c8e97df', 'BUILDING:ATT:MANUR', '考勤统计', 'RGKQ', null, '/building/attence-record-manager', 'a5982499b1dd4084adc02fc4ca33d53d', 3);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('31110492f99c4ef8a29e69d10f5cd4aa', '8e823449ed604746a470378ce2f8e1e1', 'BUILDING:ACT', '员工行为管理', 'YGXWGL', null, null, null, 300);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('89fd227e90604c6980c07e7d983ca39f', '31110492f99c4ef8a29e69d10f5cd4aa', 'BUILDING:ACT:DEV', '员工行为查询上报(开发商)', 'YGXWCXSBKFS', null, '/building/building-DevelopRewardOrPunish', 'e11b68c6fde94c83ac175784fcb84af6', 1);

insert into gbl_menu(id, parent_id, code, name, name_pinyin, icon, link_path, permission_id, sort_no)
values('9ea25fcdcb3c4e99a56f4c0337673484', '31110492f99c4ef8a29e69d10f5cd4aa', 'BUILDING:ACT:SPV', '员工行为查询审核(监管机构)', 'YGXWCXSHJGJG', null, '/building/building-RewardOrPunish', '8b4509a41ba24031a3f3ee4bad892b53', 2);


--插入智慧工地权限
insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('300a92a410c0441ebdb72e910e70197b', '6fa7d3b2f5b511e78c3f9a214cf093ae', 'bdi:*', '智慧工地管理权限', 'ZHGDGLQX', 1);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('97f6164f8ff5427499501169b3c64fce', '300a92a410c0441ebdb72e910e70197b', 'bdi:set:*', '基础设置权限', 'JCSZQX', 999);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('5f97e48f6d7f4bd0af045e3440af4927', '97f6164f8ff5427499501169b3c64fce', 'bdi:set:dev:*', '开发商信息维护权限', 'KFSXXWHQX', 1);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('ee4b881182a540b8b46f4791e1c358c7', '5f97e48f6d7f4bd0af045e3440af4927', 'bdi:set:dev:add', '新增开发商信息权限', 'XZKFSQX', 1);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('978ffb7869624826ab373a990b284851', '5f97e48f6d7f4bd0af045e3440af4927', 'bdi:set:dev:edit', '编辑开发商信息权限', 'BJKFSQX', 2);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('b9bca032b42941ccb4fe789714c66f32', '5f97e48f6d7f4bd0af045e3440af4927', 'bdi:set:dev:del', '删除开发商信息权限', 'BJKFSQX', 3);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('1a5e1faacc524157851026fe1c62452f', '5f97e48f6d7f4bd0af045e3440af4927', 'bdi:set:dev:view', '查看开发商信息权限', 'BJKFSQX', 4);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('b82587f29112441e96e80eaa628dc729', '97f6164f8ff5427499501169b3c64fce', 'bdi:set:site:*', '工地信息维护权限', 'GDXXWHQX', 2);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('096d521bfa5c4a0ba8373d4f2f4341c6', 'b82587f29112441e96e80eaa628dc729', 'bdi:set:site:add', '新增工地信息权限', 'XZGDXXQX', 1);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('d6744d4c446c4249a33da134ab31cfdf', 'b82587f29112441e96e80eaa628dc729', 'bdi:set:site:edit', '编辑工地信息权限', 'BJGDXXQX', 2);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('6e666a333ae2462d8597b8a9354c89bc', 'b82587f29112441e96e80eaa628dc729', 'bdi:set:site:del', '删除工地信息权限', 'SCGDXXQX', 3);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('d887c7996f8741cc97103ba9375999fc', 'b82587f29112441e96e80eaa628dc729', 'bdi:set:site:view', '查看工地信息权限', 'CKGDXXQX', 4);


insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('2515ded4d4954566968ff9bcc73325e6', '97f6164f8ff5427499501169b3c64fce', 'bdi:set:wtype:*', '工种信息维护权限', 'GZXXWHQX', 3);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('b1aae37c4d024ef493a14a86edec20d3', '2515ded4d4954566968ff9bcc73325e6', 'bdi:set:wtype:add', '新增工种信息权限', 'XZGZXXQX', 1);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('1cb801ee04af4897aa4756c2c5a0e26d', '2515ded4d4954566968ff9bcc73325e6', 'bdi:set:wtype:edit', '编辑工种信息权限', 'BJGZXXQX', 2);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('67a8ec39340c4c838f59281b7bd8e1c2', '2515ded4d4954566968ff9bcc73325e6', 'bdi:set:wtype:del', '删除工种信息权限', 'SCGZXXQX', 3);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('fa02700b53c64c5ea703f1fbdb3d1d8e', '2515ded4d4954566968ff9bcc73325e6', 'bdi:set:wtype:view', '查看工种信息权限', 'CKGZXXQX', 4);



insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('52ec8d84cd6e468dbf46cacb5f41dc56', '97f6164f8ff5427499501169b3c64fce', 'bdi:set:emp:*', '员工信息维护权限', 'NMGXXWHQX', 4);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('31685cd464c549c880ec3c5886ddc7c2', '52ec8d84cd6e468dbf46cacb5f41dc56', 'bdi:set:emp:add', '新增员工信息权限', 'XZGZXXQX', 1);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('b553fbd559c44100b6183f2f3a6e5f26', '52ec8d84cd6e468dbf46cacb5f41dc56', 'bdi:set:emp:edit', '编辑员工信息权限', 'BJGZXXQX', 2);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('bb4fd7642c0944359f681329f7723987', '52ec8d84cd6e468dbf46cacb5f41dc56', 'bdi:set:emp:del', '删除员工信息权限', 'SCGZXXQX', 3);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('696e1f9b4c894c69a127e49334ce80f2', '52ec8d84cd6e468dbf46cacb5f41dc56', 'bdi:set:emp:view', '查看员工信息权限', 'CKGZXXQX', 4);


insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('b0ee7e9101e94da5994d4ef53d5ec20a', '97f6164f8ff5427499501169b3c64fce', 'bdi:set:dvc:*', '设备信息维护权限', 'NMGXXWHQX', 4);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('2035d471b4214c97ba7ecae28638a886', 'b0ee7e9101e94da5994d4ef53d5ec20a', 'bdi:set:dvc:add', '新增设备信息权限', 'XZGZXXQX', 1);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('71269484697e40219a6fb4841df42714', 'b0ee7e9101e94da5994d4ef53d5ec20a', 'bdi:set:dvc:edit', '编辑设备信息权限', 'BJGZXXQX', 2);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('f660d3228afa45d88dbbd2ac3aece1cd', 'b0ee7e9101e94da5994d4ef53d5ec20a', 'bdi:set:dvc:del', '删除设备信息权限', 'SCGZXXQX', 3);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('b138ec444f3243989e2b89a0b373175d', 'b0ee7e9101e94da5994d4ef53d5ec20a', 'bdi:set:dvc:view', '查看设备信息权限', 'CKGZXXQX', 4);


insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('e6456e22e0c942b0af50ef5eecf1ed11', '97f6164f8ff5427499501169b3c64fce', 'bdi:set:time:*', '考勤时间设定', 'KQSJSD', 5);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('09d894af8b91428381751fa2fda144e1', '300a92a410c0441ebdb72e910e70197b', 'bdi:att:*', '员工考勤管理权限', 'YGKQGLQX', 1);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('c8e37d1043564202b4757e94776cef80', '09d894af8b91428381751fa2fda144e1', 'bdi:att:clock', '人工考勤权限', 'RGKQQX', 1);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('139c729d23a44f67a0d4a4ea4475fe41', '09d894af8b91428381751fa2fda144e1', 'bdi:att:repd', '考勤统计(开发商)权限', 'KQTJKFSQX', 2);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('a5982499b1dd4084adc02fc4ca33d53d', '09d894af8b91428381751fa2fda144e1', 'bdi:att:repm:*', '考勤统计', 'KQTJQX', 3);


insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('8798149b353340b099757a554050ff2d', '300a92a410c0441ebdb72e910e70197b', 'bdi:salary:*', '代发工资管理权限', 'DFGZGLQX', 2);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('62e2df3e6ac74934a92bcbabc3618670', '8798149b353340b099757a554050ff2d', 'bdi:salary:payd', '工资发放管理(开发商)', 'GZFFGLKFS', 1);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('0d943cbb38084b6d99945de10c12733b', '8798149b353340b099757a554050ff2d', 'bdi:salary:payb:*', '工资发放管理(银行)', 'GZFFGLYH', 2);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('c02eab4f32734d00a158e7b8e71ec8de', '0d943cbb38084b6d99945de10c12733b', 'bdi:salary:payb:export', '导出工资发放需求', 'DCGZFFXQ', 1);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('66a1298f8bf247a291fbff568a915394', '0d943cbb38084b6d99945de10c12733b', 'bdi:salary:payb:import', '导入工资发放结果', 'DRGZFFJG', 2);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('b068b8e20591449bac4c7a69f2f6c14a', '8798149b353340b099757a554050ff2d', 'bdi:salary:paym', '工资发放详情(监管机构)', 'GZFFGLJGJG', 3);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('9a8ff9caed394720bb8aeebbdd190a29', '8798149b353340b099757a554050ff2d', 'bdi:salary:money', '开发商备付金管理', 'KFSBFJGL', 4);


insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('fb8aeb5fbb364681bd950ab0d1ef4cf2', '300a92a410c0441ebdb72e910e70197b', 'bdi:act:*', '员工行为管理', 'YGXWGL', 50);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('e11b68c6fde94c83ac175784fcb84af6', 'fb8aeb5fbb364681bd950ab0d1ef4cf2', 'bdi:act:dev:*', '员工行为查询上报(开发商)', 'YGXWGLKFS', 50);

insert into gbl_permission(id, parent_id, wildcard, name, name_pinyin, sort_no)
values('8b4509a41ba24031a3f3ee4bad892b53', 'fb8aeb5fbb364681bd950ab0d1ef4cf2', 'bdi:act:spv:*', '员工行为查询审核(监管机构)', 'YGXWGLJGJG', 50);



update gbl_menu set icon='setting' where id='043d43d81ece4f9092cccfe2624c5047';
update gbl_menu set icon='pay-circle' where id='5045cff0b2a74e898720112106d971f7';
update gbl_menu set icon='idcard' where id='b6234f9054fd4520990921372c8e97df';
update gbl_menu set icon='team' where id='31110492f99c4ef8a29e69d10f5cd4aa';

insert into gbl_dict_entry (id, code, name, name_pinyin, op_type, description, sort_no, deleted_flag)
values('7654d46a56254b37b6145c653e9c8b5c',  'BANK', '银行', 'YH', 3,'银行列表', 1, 0);

insert into gbl_dict_entry (id, code, name, name_pinyin, op_type, description, sort_no, deleted_flag)
values('2685b02a9d9d449dbc63e1552b0104af', 'ORG_TYPE', '组织机构类型', 'ZZJGLX', 1,'组织机构类型', 2, 0);
