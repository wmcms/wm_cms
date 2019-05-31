/*============================================================*/
/* Table: user                                                */
/*============================================================*/
DROP TABLE IF EXISTS user;
CREATE TABLE user(
	id BIGINT PRIMARY KEY COMMENT '主键',
	login_key CHAR(32) NOT NULL COMMENT '帐号 MD5密文不混淆',
	password CHAR(32) NOT NULL COMMENT '密码 MD5密文混淆',
	slat CHAR(6) NOT NULL COMMENT '加密混淆码',
	type SMALLINT NOT NULL COMMENT '用户类型:1系统用户,2网站会员',
	status SMALLINT NOT NULL DEFAULT 0 COMMENT'状态：-1删除，0有效，1锁定',
	UNIQUE KEY(login_key)
) ENGINE=INNODB;
ALTER TABLE user COMMENT '用户主表';
INSERT INTO user (id,login_key,password,slat,type) SELECT 18000001,MD5('13424293896'),MD5('123456#888888'),'888888',1;

/*==============================================================*/
/* Table:user_info                                              */
/*==============================================================*/
DROP TABLE IF EXISTS user_info;
CREATE TABLE user_info
(
		id            			BIGINT PRIMARY KEY COMMENT '用户ID',
		name          			VARCHAR(20) NOT NULL COMMENT '姓名',
		nickname          	    VARCHAR(20) COMMENT '昵称',
		head_url			    VARCHAR(100) COMMENT '用户头像',
		gender             	    CHAR(1) COMMENT '性别',
		email              	    VARCHAR(50) COMMENT '电子邮箱',
		id_card_no              VARCHAR(18) COMMENT '手机号',
		mobile             	    VARCHAR(13) COMMENT '手机',
		birth_date         	    TIMESTAMP COMMENT '出生年月',
		activate_status         SMALLINT NOT NULL DEFAULT 0 COMMENT '激活状态值(0:未激活,1:邮箱激活,2:手机激活，4实名)',
		level             	    SMALLINT NOT NULL DEFAULT 1 COMMENT '等级',
		type           			SMALLINT NOT NULL DEFAULT 0 COMMENT '类型(2:会员,1:系统用户)',
		status             	    SMALLINT NOT NULL DEFAULT 0 COMMENT '状态(0:正常,1:锁定,2:已删除)',
		create_time				TIMESTAMP NOT NULL  COMMENT '创建时间',
		create_user_id			BIGINT COMMENT '创建人，为空是自注册',
		update_time				TIMESTAMP DEFAULT 0 COMMENT '修改时间',
		update_user_id			BIGINT COMMENT '修改人'
)ENGINE = INNODB;
ALTER TABLE user_info COMMENT '用户信息表';
INSERT INTO user_info(id,name,nickname,mobile,type,create_time,update_time)
SELECT 18000001,'赵建威','admin','13424293896',1,CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP();

/*============================================================*/
/* Table: user_open                                           */
/*============================================================*/
DROP TABLE IF EXISTS user_open;
CREATE TABLE user_open(
	id BIGINT PRIMARY KEY COMMENT '主键=user.id',
	user_id BIGINT NOT NULL DEFAULT 0 COMMENT 'userId=user.id',
	type SMALLINT NOT NULL COMMENT 'openid类型:1微信,2新浪,3百度',
	open_id CHAR(32) NOT NULL COMMENT 'openid MD5密文不混淆',
	UNIQUE KEY(open_id)
) ENGINE=INNODB;
ALTER TABLE user_open COMMENT '用户第三方帐号表';

/*==============================================================*/
/* Table:meta                                              */
/*==============================================================*/
DROP TABLE IF EXISTS meta;
CREATE TABLE meta
(
		id          			BIGINT PRIMARY KEY COMMENT '主键',
		parent_id          	    BIGINT  COMMENT '父级ID',
		name          			VARCHAR(20) NOT NULL COMMENT '名称',
		type					SMALLINT NOT NULL DEFAULT 0 COMMENT '类型1-新闻，2-附件',
		parent_path				VARCHAR(200) COMMENT '父级路径',
		remark				    VARCHAR(50) COMMENT '备注',
		level					SMALLINT NOT NULL DEFAULT 1 COMMENT '层级',
		sort    				SMALLINT NOT NULL DEFAULT 1 COMMENT '值越小越靠前，值相同按修改时间倒序排列',
		status             	    SMALLINT NOT NULL DEFAULT 0 COMMENT '状态(0:正常,1:已删除)',
		create_time				TIMESTAMP NOT NULL  COMMENT '创建时间',
		create_user_id			BIGINT COMMENT '创建人',
		update_time				TIMESTAMP COMMENT '修改时间',
		update_user_id			BIGINT COMMENT '修改人'
) ENGINE = INNODB;
ALTER TABLE meta COMMENT '数据字典';


/*==============================================================*/
/* Table: ad                                                */
/*==============================================================*/
CREATE TABLE  ad
(
   id              BIGINT PRIMARY KEY COMMENT '主键',
   target_id       BIGINT NOT NULL COMMENT '目标id=meta.id',
   name            VARCHAR(100) NOT NULL COMMENT '名称',
   begin_date      TIMESTAMP COMMENT '开始时间',
   end_date        TIMESTAMP COMMENT '结束时间',
   url             VARCHAR(150) COMMENT '广告url',
   type            SMALLINT NOT NULL DEFAULT 1 '类型：图片',
   res_id          BIGINT COMMENT '资源ID=material.id',
   sort             SMALLINT NOT NULL DEFAULT 1 COMMENT '排序',
   status             	    SMALLINT NOT NULL DEFAULT 0 COMMENT '状态(0:正常,1:已删除)',
   create_time				TIMESTAMP NOT NULL  COMMENT '创建时间',
   create_user_id			BIGINT COMMENT '创建人',
   update_time				TIMESTAMP COMMENT '修改时间',
   update_user_id			BIGINT COMMENT '修改人'
)ENGINE = INNODB;
ALTER TABLE ad COMMENT '广告表';
