DROP TABLE `tb_park`;
DROP TABLE `tb_sys_menu`;
DROP TABLE `tb_sys_role`;
DROP TABLE `tb_menu_role_rel`;
DROP TABLE `tb_user`;
DROP TABLE `tb_sys_permission`;
DROP TABLE `tb_role_permission_rel`;
DROP TABLE `tb_user_role_rel`;
DROP TABLE `tb_banner`;
DROP TABLE `tb_guide`;
DROP TABLE `tb_user_custom_menu`;
DROP TABLE `tb_index_info`;
DROP TABLE `tb_order`;
DROP TABLE `tb_order_detail`;
DROP TABLE `tb_pay_record`;
DROP TABLE `tb_integral`;
DROP TABLE `tb_integral_record`;
DROP TABLE `tb_user_message`;
DROP TABLE `tb_user_collection`;
DROP TABLE `tb_enterprise`;
DROP TABLE `tb_enterprise_book`;
DROP TABLE `tb_enterprise_invite`;
DROP TABLE `tb_enterprise_apply`;
DROP TABLE `tb_article`;
DROP TABLE `tb_activity_type`;
DROP TABLE `tb_activity_tag`;
DROP TABLE `tb_activity_tag_rel`;
DROP TABLE `tb_activity_apply`;
DROP TABLE `tb_user_login_record`;
DROP TABLE `tb_notice_record`;
DROP TABLE `tb_agreement`;
DROP TABLE `tb_office_build`;
DROP TABLE `tb_public_resources_rent`;
DROP TABLE `tb_file_resources`;
DROP TABLE `tb_apartment_rent`;
DROP TABLE `tb_public_resources_apply`;
DROP TABLE `tb_park_news`;
DROP TABLE `tb_activity_copy_1`;
DROP TABLE `tb_build_unit`;
DROP TABLE `tb_property_maintenance_apply`;
DROP TABLE `tb_maintenance_type`;
DROP TABLE `tb_property_maintenance_record`;
DROP TABLE `tb_property_pass_apply`;
DROP TABLE `tb_property_pass_record`;

CREATE TABLE `tb_park` (
`id` bigint(20) NOT NULL COMMENT 'id',
`name` varchar(100) NULL COMMENT '名称',
`code` varchar(50) NULL COMMENT '园区代码',
PRIMARY KEY (`id`) 
);
CREATE TABLE `tb_sys_menu` (
`id` bigint(20) NOT NULL COMMENT 'id',
`name` varchar(50) NOT NULL COMMENT '名称',
`code` varchar(50) NOT NULL COMMENT '菜单代码',
`status` varchar(20) NOT NULL COMMENT '状态',
`create_time` datetime NOT NULL COMMENT '创建时间',
`update_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) 
)
COMMENT = '菜单';
CREATE TABLE `tb_sys_role` (
`id` bigint(20) NOT NULL COMMENT 'id',
`name` varchar(50) NULL COMMENT '名称',
`code` varchar(50) NULL COMMENT '角色代码',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
PRIMARY KEY (`id`) 
)
COMMENT = '角色表';
CREATE TABLE `tb_menu_role_rel` (
`id` bigint(20) NOT NULL COMMENT 'id',
`menu_code` varchar(50) NOT NULL COMMENT '菜单代码',
`role_code` varchar(50) NOT NULL COMMENT '角色代码',
`status` varchar(20) NOT NULL COMMENT '状态',
`create_time` datetime NOT NULL COMMENT '创建时间',
`update_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) 
)
COMMENT = '角色菜单关联表';
CREATE TABLE `tb_user` (
`id` bigint(20) NOT NULL COMMENT 'id',
`account` varchar(50) NULL COMMENT '账号',
`phone` varchar(20) NULL COMMENT '电话',
`email` varchar(100) NULL COMMENT 'email',
`password` varchar(255) NULL COMMENT '密码',
`user_name` varchar(50) NULL COMMENT '昵称',
`img_src` varchar(1000) NULL COMMENT '头像',
`default_park_code` varchar(50) NULL COMMENT '默认园区代码',
`open_id` varchar(100) NULL COMMENT '微信用户标识',
`session_key` varchar(255) NULL COMMENT '会话秘钥',
`union_id` varchar(100) NULL COMMENT '微信开放平台唯一标识符',
`user_type` varchar(50) NULL COMMENT '用户类型',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '创建时间',
PRIMARY KEY (`id`) 
)
COMMENT = '用户表';
CREATE TABLE `tb_sys_permission` (
`id` bigint(20) NOT NULL COMMENT 'id',
`name` varchar(50) NULL COMMENT '名称',
`code` varchar(50) NULL COMMENT '权限代码',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
PRIMARY KEY (`id`) 
)
COMMENT = '权限表';
CREATE TABLE `tb_role_permission_rel` (
`id` bigint(20) NOT NULL COMMENT 'id',
`permission_code` varchar(50) NOT NULL COMMENT '权限代码',
`role_code` varchar(50) NOT NULL COMMENT '角色代码',
`status` varchar(20) NOT NULL COMMENT '状态',
`create_time` datetime NOT NULL COMMENT '创建时间',
`update_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) 
)
COMMENT = '角色权限关联表';
CREATE TABLE `tb_user_role_rel` (
`id` bigint(20) NOT NULL COMMENT 'id',
`user_id` bigint(20) NOT NULL COMMENT '用户 id',
`role_code` varchar(50) NOT NULL COMMENT '角色代码',
`status` varchar(20) NOT NULL COMMENT '状态',
`create_time` datetime NOT NULL COMMENT '创建时间',
`update_time` datetime NOT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) 
)
COMMENT = '用户角色关联表';
CREATE TABLE `tb_banner` (
`id` bigint(20) NOT NULL COMMENT 'id',
`name` varchar(20) NULL COMMENT '名称',
`img_src` varchar(1000) NULL COMMENT '图片地址',
`serial_numuber` int(2) NULL COMMENT '序号',
`link_url` varchar(1000) NULL COMMENT '链接',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = 'Banner';
CREATE TABLE `tb_guide` (
`id` bigint(20) NOT NULL COMMENT 'id',
`name` varchar(20) NULL COMMENT '名称',
`img_src` varchar(1000) NULL COMMENT '图片地址',
`serial_numuber` int(2) NULL COMMENT '序号',
`link_url` varchar(1000) NULL COMMENT '链接',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '引导页信息表';
CREATE TABLE `tb_user_custom_menu` (
`id` bigint(20) NOT NULL COMMENT 'id',
`user_id` bigint(20) NOT NULL COMMENT '用户 id',
`menu_code` varchar(500) NULL COMMENT '菜单代码',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '个人定制功能表';
CREATE TABLE `tb_index_info` (
`id` bigint(20) NOT NULL COMMENT 'id',
`name` varchar(50) NULL COMMENT '名称',
`code` varchar(50) NULL COMMENT '代码',
`value` text NULL COMMENT '值',
`status` varchar(20) NULL COMMENT 'status',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(20) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '主页数据信息表';
CREATE TABLE `tb_order` (
`id` bigint(20) NOT NULL COMMENT 'id',
`user_id` bigint(20) NULL COMMENT '用户 id',
`code` varchar(100) NULL COMMENT '订单号',
`amount` decimal(10,2) NULL COMMENT '金额',
`integral` int(10) NULL COMMENT '使用积分',
`count` int(10) NULL COMMENT '商品数量',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(20) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '订单表';
CREATE TABLE `tb_order_detail` (
`id` bigint(20) NOT NULL COMMENT 'id',
`user_id` bigint(20) NULL COMMENT '用户 id',
`order_code` varchar(100) NULL COMMENT '订单号',
`module` varchar(50) NULL COMMENT '模块名称',
`product_id` bigint(20) NULL COMMENT '商品 id',
`amount` decimal(10,2) NULL COMMENT '金额',
`integral` int(10) NULL COMMENT '使用积分',
`count` int(10) NULL COMMENT '商品数量',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(20) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '订单明细表';
CREATE TABLE `tb_pay_record` (
`id` bigint(20) NOT NULL COMMENT 'id',
`user_id` bigint(20) NULL COMMENT '用户 id',
`order_code` varchar(100) NULL COMMENT '订单号',
`pay_code` varchar(100) NULL COMMENT '支付号',
`pay_type` varchar(50) NULL COMMENT '支付类型',
`amount` decimal(10,2) NULL COMMENT '金额',
`integral` int(10) NULL COMMENT '使用积分',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(20) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '支付记录表';
CREATE TABLE `tb_integral` (
`id` bigint(20) NOT NULL COMMENT 'id',
`user_id` bigint(20) NOT NULL COMMENT '用户 id',
`integral` int(10) NULL COMMENT '积分',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '积分表';
CREATE TABLE `tb_integral_record` (
`id` bigint(20) NOT NULL COMMENT 'id',
`user_id` bigint(20) NOT NULL COMMENT '用户 id',
`integral` int(10) NULL COMMENT '积分',
`operation` varchar(50) NULL COMMENT '操作',
`module` varchar(50) NULL COMMENT '模块',
`sub_id` bigint(20) NULL COMMENT '模块记录 id',
`remark` varchar(255) NULL COMMENT '备注',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '积分记录表';
CREATE TABLE `tb_user_message` (
`id` bigint(20) NOT NULL COMMENT 'id',
`user_id` bigint(20) NOT NULL COMMENT '用户 id',
`type` varchar(50) NULL COMMENT '类型',
`message` varchar(1000) NULL COMMENT '消息',
`module` varchar(20) NULL COMMENT '模块',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '用户消息表';
CREATE TABLE `tb_user_collection` (
`id` bigint(20) NOT NULL COMMENT 'id',
`user_id` bigint(20) NOT NULL COMMENT '用户 id',
`module` varchar(20) NULL COMMENT '模块',
`sub_id` bigint(20) NULL COMMENT '模块记录 id',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '用户收藏表';
CREATE TABLE `tb_enterprise` (
`id` bigint(20) NOT NULL COMMENT 'id',
`name` varchar(255) NULL COMMENT '名称',
`code` varchar(50) NULL COMMENT '企业代码',
`erterprise_type` varchar(50) NULL COMMENT '企业类型',
`address` varchar(255) NULL COMMENT '地址',
`tel` varchar(50) NULL COMMENT '联系方式',
`header` varchar(50) NULL COMMENT '负责人',
`header_contect` varchar(50) NULL COMMENT '负责人联系方式',
`introduction` varchar(1000) NULL COMMENT '简介',
`remark` varchar(1000) NULL COMMENT '备注',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '企业信息表';
CREATE TABLE `tb_enterprise_book` (
`id` bigint(20) NOT NULL COMMENT 'id',
`enterprise_id` bigint(20) NULL COMMENT '企业 id',
`user_id` bigint(20) NULL COMMENT '用户 id',
`job` varchar(50) NULL COMMENT '职位',
`address` varchar(255) NULL COMMENT '地址',
`tel` varchar(50) NULL COMMENT '联系方式',
`introduction` varchar(1000) NULL COMMENT '简介',
`remark` varchar(1000) NULL COMMENT '备注',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '企业通信录';
CREATE TABLE `tb_enterprise_invite` (
`id` bigint(20) NOT NULL COMMENT 'id',
`enterprise_id` bigint(20) NULL COMMENT '企业 id',
`user_id` bigint(20) NULL COMMENT '用户 id',
`invite_code` varchar(100) NULL COMMENT '邀请码',
`creator` bigint(20) NULL COMMENT '生成者',
`approval` bigint(20) NULL COMMENT '审核者',
`remark` varchar(1000) NULL COMMENT '备注',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '企业邀请码';
CREATE TABLE `tb_enterprise_apply` (
`id` bigint(20) NOT NULL COMMENT 'id',
`enterprise_id` bigint(20) NULL COMMENT '企业 id',
`user_id` bigint(20) NULL COMMENT '用户 id',
`invite_code` varchar(100) NULL COMMENT '邀请码',
`apply_type` varchar(50) NULL COMMENT '申请类型',
`apply_role_code` varchar(50) NULL COMMENT '申请角色代码',
`approval` bigint(20) NULL COMMENT '审核者',
`remark` varchar(1000) NULL COMMENT '备注',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '用户认证申请';
CREATE TABLE `tb_article` (
`id` bigint(20) NOT NULL COMMENT 'id',
`title` varchar(250) NULL COMMENT '标题',
`type_id` bigint(20) NULL COMMENT '类型',
`creator` bigint(20) NULL COMMENT '作者',
`context` text NULL COMMENT '详情',
`address` varchar(200) NULL COMMENT '活动地址',
`introduction` varchar(1000) NULL COMMENT '简介',
`original_url` text NULL COMMENT '原文链接',
`start_time` datetime NULL COMMENT '开始时间',
`end_time` datetime NULL COMMENT '结束时间',
`remark` varchar(1000) NULL COMMENT '备注',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
`module` varchar(255) NULL COMMENT '模块',
PRIMARY KEY (`id`) 
)
COMMENT = '园区文章表';
CREATE TABLE `tb_activity_type` (
`id` bigint(20) NOT NULL COMMENT 'id',
`name` varchar(50) NOT NULL COMMENT '名称',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '园区活动类型表';
CREATE TABLE `tb_activity_tag` (
`id` bigint(20) NOT NULL COMMENT 'id',
`name` varchar(50) NOT NULL COMMENT '名称',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '园区活动标签表';
CREATE TABLE `tb_activity_tag_rel` (
`id` bigint(20) NOT NULL COMMENT 'id',
`activity_id` bigint(20) NOT NULL COMMENT '活动 id',
`tag_id` bigint(20) NULL COMMENT '标签 id',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '园区活动标签关联表';
CREATE TABLE `tb_activity_apply` (
`id` bigint(20) NOT NULL COMMENT 'id',
`activity_id` bigint(20) NOT NULL COMMENT '活动 id',
`user_id` bigint(20) NULL COMMENT '用户 id',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '园区活动标签关联表';
CREATE TABLE `tb_user_login_record` (
`id` bigint(20) NOT NULL COMMENT 'id',
`user_id` varchar(50) NULL COMMENT '用户名称',
`redis_key` varchar(200) NULL COMMENT 'redis key',
`user_token` varchar(1000) NULL COMMENT 'user token',
`device_id` varchar(100) NULL COMMENT '手机唯一标识符',
`device_token` varchar(1000) NULL COMMENT '应用唯一标识符',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
PRIMARY KEY (`id`) 
)
COMMENT = '用户登录信息表';
CREATE TABLE `tb_notice_record` (
`id` bigint(20) NOT NULL COMMENT 'id',
`notice_type` varchar(50) NULL,
`context` text NULL COMMENT '通知内容',
`scope` varchar(50) NULL COMMENT '范围',
`user_list` text NULL COMMENT '用户列表',
`send_time` datetime NULL COMMENT '发送时间',
`send_type` varchar(50) NULL COMMENT '推送类型',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '通知类型',
PRIMARY KEY (`id`) 
)
COMMENT = '系统通知表';
CREATE TABLE `tb_agreement` (
`id` bigint(20) NOT NULL COMMENT 'id',
`name` varchar(50) NULL COMMENT '名称',
`context` text NULL COMMENT '通知内容',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '通知类型',
PRIMARY KEY (`id`) 
)
COMMENT = '协议信息表';
CREATE TABLE `tb_office_build` (
`id` bigint(20) NOT NULL COMMENT 'id',
`name` varchar(50) NULL COMMENT '名称',
`address` varchar(255) NULL COMMENT '地址',
`img_src` varchar(1000) NULL COMMENT '封面地址',
`context` text NULL COMMENT '详情',
`area` decimal(10,2) NULL COMMENT '面积',
`model` varchar(50) NULL COMMENT '户型',
`direction` varchar(50) NULL COMMENT '朝向',
`floor` int(3) NULL COMMENT '楼层',
`build_unit` varchar(255) NULL COMMENT '楼栋',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '通知类型',
PRIMARY KEY (`id`) 
)
COMMENT = '写字楼信息表';
CREATE TABLE `tb_public_resources_rent` (
`id` bigint(20) NOT NULL COMMENT 'id',
`resources_type` varchar(50) NULL COMMENT '资源类型',
`name` varchar(50) NULL COMMENT '名称',
`min_price` decimal(10,2) NULL COMMENT '最低价格',
`max_price` decimal(10,2) NULL COMMENT '最大价格',
`price_unit` varchar(50) NULL COMMENT '价格单位',
`time_unit` varchar(50) NULL COMMENT '时间单位',
`address` varchar(255) NULL COMMENT '地址',
`img_src` varchar(1000) NULL COMMENT '封面地址',
`context` text NULL COMMENT '详情',
`area` decimal(10,2) NULL COMMENT '面积',
`model` varchar(50) NULL COMMENT '户型',
`direction` varchar(50) NULL COMMENT '朝向',
`floor` int(3) NULL COMMENT '楼层',
`build_unit` varchar(255) NULL COMMENT '楼栋',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '通知类型',
PRIMARY KEY (`id`) 
)
COMMENT = '公共资源租赁表';
CREATE TABLE `tb_file_resources` (
`id` bigint(20) NOT NULL COMMENT 'id',
`parent_id` bigint(20) NULL COMMENT '父id',
`serial_number` int(3) NULL COMMENT '序号',
`file_type` varchar(50) NULL COMMENT '文件类型',
`file_name` varchar(50) NULL COMMENT '名称',
`file_size` varchar(50) NULL COMMENT '大小',
`file_src` text NULL COMMENT '地址',
`status` varchar(20) NULL COMMENT 'status',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(20) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '主页数据信息表';
CREATE TABLE `tb_apartment_rent` (
`id` bigint(20) NOT NULL COMMENT 'id',
`name` varchar(50) NULL COMMENT '名称',
`min_price` decimal(10,2) NULL COMMENT '最低价格',
`max_price` decimal(10,2) NULL COMMENT '最大价格',
`price_unit` varchar(50) NULL COMMENT '价格单位',
`time_unit` varchar(50) NULL COMMENT '时间单位',
`address` varchar(255) NULL COMMENT '地址',
`img_src` varchar(1000) NULL COMMENT '封面地址',
`context` text NULL COMMENT '详情',
`area` decimal(10,2) NULL COMMENT '面积',
`model` varchar(50) NULL COMMENT '户型',
`direction` varchar(50) NULL COMMENT '朝向',
`floor` int(3) NULL COMMENT '楼层',
`build_unit` varchar(255) NULL COMMENT '楼栋',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '通知类型',
PRIMARY KEY (`id`) 
)
COMMENT = '公寓租赁表';
CREATE TABLE `tb_public_resources_apply` (
`id` bigint(20) NOT NULL COMMENT 'id',
`apply_type` varchar(50) NULL COMMENT '申请类型',
`resources_type` varchar(50) NOT NULL COMMENT '资源类型',
`resources_id` bigint(20) NULL COMMENT '资源 id',
`user_id` bigint(20) NULL COMMENT '用户 id',
`customer_name` varchar(50) NULL COMMENT '申请人姓名',
`customer_contect` varchar(50) NULL COMMENT '申请人',
`leave_message` varchar(255) NULL,
`supervisor` bigint(20) NULL,
`remark` varchar(255) NULL,
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '公共资源申请记录表';
CREATE TABLE `tb_park_news` (
`id` bigint(20) NOT NULL COMMENT 'id',
`activity_id` bigint(20) NOT NULL COMMENT '活动 id',
`user_id` bigint(20) NULL COMMENT '用户 id',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '园区活动标签关联表';
CREATE TABLE `tb_activity_copy_1` (
`id` bigint(20) NOT NULL COMMENT 'id',
`title` varchar(250) NULL COMMENT '标题',
`type_id` bigint(20) NULL COMMENT '类型',
`creator` bigint(20) NULL COMMENT '作者',
`context` text NULL COMMENT '详情',
`address` varchar(200) NULL COMMENT '活动地址',
`introduction` varchar(1000) NULL COMMENT '简介',
`original_url` text NULL COMMENT '原文链接',
`start_time` datetime NULL COMMENT '开始时间',
`end_time` datetime NULL COMMENT '结束时间',
`remark` varchar(1000) NULL COMMENT '备注',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '园区活动表';
CREATE TABLE `tb_build_unit` (
`id` bigint(20) NOT NULL COMMENT 'id',
`name` varchar(50) NOT NULL COMMENT '名称',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '园区单位表';
CREATE TABLE `tb_property_maintenance_apply` (
`id` bigint(20) NOT NULL COMMENT 'id',
`build_unit` varchar(250) NOT NULL COMMENT '单元',
`user_id` bigint(20) NULL COMMENT '用户 id',
`customer_name` varchar(50) NULL COMMENT '申请人姓名',
`customer_contect` varchar(50) NULL COMMENT '申请人联系方式',
`maintenance_type` varchar(50) NULL COMMENT '维修类型',
`message` varchar(255) NULL COMMENT '留言',
`supervisor` bigint(20) NULL COMMENT '跟进人员',
`remark` varchar(255) NULL COMMENT '备注',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '物业报修申请表';
CREATE TABLE `tb_maintenance_type` (
`id` bigint(20) NOT NULL COMMENT 'id',
`name` varchar(50) NOT NULL COMMENT '名称',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '物业报修类型表';
CREATE TABLE `tb_property_maintenance_record` (
`id` bigint(20) NOT NULL COMMENT 'id',
`apply_id` bigint(20) NOT NULL COMMENT '申请 id',
`operate` varchar(50) NULL COMMENT '操作',
`user_id` bigint(20) NULL COMMENT '用户 id',
`remark` varchar(255) NULL COMMENT '备注',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '物业报修记录表';
CREATE TABLE `tb_property_pass_apply` (
`id` bigint(20) NOT NULL COMMENT 'id',
`customer_name` varchar(50) NOT NULL COMMENT '申请人名称',
`customer_contect` varchar(50) NULL COMMENT '申请人联系方式',
`user_id` bigint(20) NULL COMMENT '用户 id',
`apply_type` varchar(50) NULL COMMENT '申请类型',
`apply_time` datetime NULL COMMENT '申请放行时间',
`property_list` text NULL COMMENT '物品清单',
`approval_time` datetime NULL COMMENT '审核时间',
`complete_time` datetime NULL COMMENT '完成时间',
`remark` varchar(255) NULL COMMENT '备注',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '物品放行申请表';
CREATE TABLE `tb_property_pass_record` (
`id` bigint(20) NOT NULL COMMENT 'id',
`apply_id` bigint(20) NOT NULL COMMENT '申请 id',
`operate` varchar(50) NULL COMMENT '操作',
`user_id` bigint(20) NULL COMMENT '用户 id',
`remark` varchar(255) NULL COMMENT '备注',
`status` varchar(20) NULL COMMENT '状态',
`create_time` datetime NULL COMMENT '创建时间',
`update_time` datetime NULL COMMENT '更新时间',
`park_code` varchar(50) NULL COMMENT '园区代号',
PRIMARY KEY (`id`) 
)
COMMENT = '物品放行记录表';
