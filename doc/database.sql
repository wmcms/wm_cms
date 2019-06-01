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
DROP TABLE IF EXISTS ad;
CREATE TABLE  ad
(
   id              	BIGINT PRIMARY KEY COMMENT '主键',
   target_id       	BIGINT NOT NULL COMMENT '目标id=meta.id',
   name            	VARCHAR(100) NOT NULL COMMENT '名称',
   begin_date      	TIMESTAMP COMMENT '开始时间',
   end_date        	TIMESTAMP COMMENT '结束时间',
   url             	VARCHAR(150) COMMENT '广告url',
   type            	SMALLINT NOT NULL DEFAULT 1 COMMENT '类型：图片',
   res_id          	BIGINT COMMENT '资源ID=material.id',
   sort             	SMALLINT NOT NULL DEFAULT 1 COMMENT '排序',
   status             	SMALLINT NOT NULL DEFAULT 0 COMMENT '状态(0:正常,1:已删除)',
   create_time		TIMESTAMP NOT NULL  COMMENT '创建时间',
   create_user_id	BIGINT COMMENT '创建人',
   update_time		TIMESTAMP COMMENT '修改时间',
   update_user_id	BIGINT COMMENT '修改人'
)ENGINE = INNODB;
ALTER TABLE ad COMMENT '广告表';

/*==============================================================*/
/* Table: login_log                                                */
/*==============================================================*/
DROP TABLE IF EXISTS login_log;
CREATE TABLE  login_log
(
   id            BIGINT PRIMARY KEY COMMENT '主键',
   user_id       BIGINT NOT NULL COMMENT '关联id=user.id',
   ip            VARCHAR(20) COMMENT 'ip',
   os      			 VARCHAR(20) COMMENT '操作系统',
   device      	 VARCHAR(20) COMMENT '设备',
   browser       VARCHAR(20) COMMENT '浏览器',
   country       VARCHAR(50) COMMENT '国家',
   area      		 VARCHAR(50) COMMENT '地区',
   login_time    TIMESTAMP NOT NULL COMMENT '登录时间'
)ENGINE = INNODB;
ALTER TABLE login_log COMMENT '用户登录日志';
	
/*==============================================================*/
/* Table: user_collection                                                */
/*==============================================================*/
DROP TABLE IF EXISTS user_collection;
CREATE TABLE  user_collection
(
   id            BIGINT PRIMARY KEY COMMENT '主键',
   target_id     BIGINT NOT NULL COMMENT '关联id=news.id',
   user_id     	 BIGINT NOT NULL COMMENT '关联id=user.id',
   status      	 SMALLINT NOT NULL DEFAULT 0  COMMENT '状态 -1：删除, 0：正常',
   create_time    TIMESTAMP COMMENT '创建时间'
)ENGINE = INNODB;
ALTER TABLE user_collection COMMENT '用户收藏';

/*==============================================================*/
/* Table: user_parise                                                */
/*==============================================================*/
DROP TABLE IF EXISTS user_parise;
CREATE TABLE  user_parise
(
   id            BIGINT PRIMARY KEY COMMENT '主键',
   target_id     BIGINT NOT NULL COMMENT '关联id=news.id',
   user_id     	 BIGINT NOT NULL COMMENT '关联id=user.id',
   status      	 SMALLINT NOT NULL DEFAULT 0  COMMENT '状态 -1：删除, 0：正常',
   create_time    TIMESTAMP COMMENT '创建时间'
)ENGINE = INNODB;
ALTER TABLE user_parise COMMENT '用户点赞';


/*==============================================================*/
/* Table: user_forward                                                */
/*==============================================================*/
DROP TABLE IF EXISTS user_forward;
CREATE TABLE  user_forward
(
   id            BIGINT PRIMARY KEY COMMENT '主键',
   target_id     BIGINT NOT NULL COMMENT '关联id=news.id',
   user_id     	 BIGINT NOT NULL COMMENT '关联id=user.id',
   status      	 SMALLINT NOT NULL DEFAULT 0  COMMENT '状态 -1：删除, 0：正常',
   create_time    TIMESTAMP COMMENT '创建时间'
)ENGINE = INNODB;
ALTER TABLE user_forward COMMENT '用户转发';

/*==============================================================*/
/* Table: user_comment                                                */
/*==============================================================*/
DROP TABLE IF EXISTS user_comment;
CREATE TABLE  user_comment
(
   id            BIGINT PRIMARY KEY COMMENT '主键',
   target_id     BIGINT NOT NULL COMMENT '关联id=news.id',
   user_id     	 BIGINT NOT NULL COMMENT '关联id=user.id',
   content     	 VARCHAR(300) NOT NULL COMMENT '内容',
   status      	 SMALLINT NOT NULL DEFAULT 0  COMMENT '状态 -1：删除, 0：正常',
   create_time    TIMESTAMP COMMENT '创建时间'
)ENGINE = INNODB;
ALTER TABLE user_comment COMMENT '用户评论';

/*==============================================================*/
/* Table: user_reply                                                */
/*==============================================================*/
DROP TABLE IF EXISTS user_reply;
CREATE TABLE  user_reply
(
   id            BIGINT PRIMARY KEY COMMENT '主键',
   comment_id    BIGINT NOT NULL COMMENT '关联id=user_omment.id',
   user_id     	 BIGINT NOT NULL COMMENT '关联id=user.id',
   content     	 VARCHAR(300) NOT NULL COMMENT '内容',
   status      	 SMALLINT NOT NULL DEFAULT 0  COMMENT '状态 -1：删除, 0：正常',
   create_time    TIMESTAMP COMMENT '创建时间'
)ENGINE = INNODB;
ALTER TABLE user_reply COMMENT '用户回复评论';

/*==============================================================*/
/* Table: user_vote                                                */
/*==============================================================*/
DROP TABLE IF EXISTS user_vote;
CREATE TABLE  user_vote
(
   id            BIGINT PRIMARY KEY COMMENT '主键',
   vote_id    	 BIGINT NOT NULL COMMENT '关联id=vote.id',
   user_id     	 BIGINT NOT NULL COMMENT '关联id=user.id',
   ip     	 		 VARCHAR(20) COMMENT 'ip',
   status      	 SMALLINT NOT NULL DEFAULT 0  COMMENT '状态 -1：删除, 0：正常',
   create_time    TIMESTAMP COMMENT '创建时间'
)ENGINE = INNODB;
ALTER TABLE user_vote COMMENT '用户投票';

/*==============================================================*/
/* Table: news                                                */
/*==============================================================*/
DROP TABLE IF EXISTS news;
CREATE TABLE  news
(
   id            			BIGINT PRIMARY KEY COMMENT '主键',
   category_id    		BIGINT NOT NULL COMMENT '类别Id=meta.id',
   title     	 				VARCHAR(150) NOT NULL COMMENT '标题',
   cover_url     	 		VARCHAR(150) NOT NULL COMMENT '封面',
   keyword     	 			VARCHAR(100)  COMMENT '关键字',
   description     	 	VARCHAR(200)  COMMENT '描述',
   source     	 				VARCHAR(100)  COMMENT '来源',
   source_url     	 				VARCHAR(150)  COMMENT '来源链接',
   author     	 				VARCHAR(50)  COMMENT '作者',
   publish_time    TIMESTAMP COMMENT '发布时间',
	offlinne_time    TIMESTAMP COMMENT '上线时间',
	is_auto_publish   BIT NOT NULL DEFAULT 0 COMMENT'是否自动发布',
	is_auto_offline   BIT NOT NULL DEFAULT 0 COMMENT'是否自动下线',
	is_top   BIT NOT NULL DEFAULT 0 COMMENT'是否置顶',
	is_hot   BIT NOT NULL DEFAULT 0 COMMENT'是否热门',
	is_recommend   BIT NOT NULL DEFAULT 0 COMMENT'是否推荐',
 	sort             	    SMALLINT NOT NULL DEFAULT 1 COMMENT '排序值',
 	status             	    SMALLINT NOT NULL DEFAULT 0 COMMENT '状态(-1删除，0草稿,1:发布)',
		create_time				TIMESTAMP NOT NULL  COMMENT '创建时间',
		create_user_id			BIGINT COMMENT '创建人',
		update_time				TIMESTAMP DEFAULT 0 COMMENT '修改时间',
		update_user_id			BIGINT COMMENT '修改人'
)ENGINE = INNODB;
ALTER TABLE news COMMENT '文章';

/*==============================================================*/
/* Table: material                                                */
/*==============================================================*/
DROP TABLE IF EXISTS material;
CREATE TABLE  material
(
   id            			BIGINT PRIMARY KEY COMMENT '主键',
   category_id    		BIGINT NOT NULL COMMENT '类别Id=meta.id',
   name     	 				VARCHAR(50) NOT NULL COMMENT '名称',
   file_name     	 			VARCHAR(100)  COMMENT '文件名',
   file_type     	 			VARCHAR(20)  COMMENT '文件类型',
   file_szie    		BIGINT NOT NULL COMMENT '文件大小',
   url     	 			VARCHAR(100)  COMMENT 'url',
 status             	    SMALLINT NOT NULL DEFAULT 0 COMMENT '状态(-1删除，0正常)',
		create_time				TIMESTAMP NOT NULL  COMMENT '创建时间',
		create_user_id			BIGINT COMMENT '创建人'
)ENGINE = INNODB;
ALTER TABLE material COMMENT '素材表';
	

/*==============================================================*/
/* Table: visit_log                                                */
/*==============================================================*/
DROP TABLE IF EXISTS visit_log;
CREATE TABLE  visit_log
(
   id            			BIGINT PRIMARY KEY COMMENT '主键',
   url     	 			VARCHAR(150)  COMMENT 'url',
   referrer     	 			VARCHAR(100)  COMMENT '来源',
   source     	 			VARCHAR(50)  COMMENT '来源域名',
   token     	 			VARCHAR(32)  COMMENT '票据',
   parameter     	 			VARCHAR(2000)  COMMENT '参数',
   ip     	 			VARCHAR(20)  COMMENT 'ip',
   os     	 			VARCHAR(20)  COMMENT '操作系统',
   user_agent     	 			VARCHAR(200)  COMMENT '用户代理',
   device     	 			VARCHAR(20)  COMMENT '设备',
   browser     	 			VARCHAR(20)  COMMENT '浏览器',
   country     	 			VARCHAR(50)  COMMENT '国家',
   area     	 			VARCHAR(50)  COMMENT '地区',
	 request_time				TIMESTAMP NOT NULL  COMMENT '请求时间',
	user_id			BIGINT COMMENT '请求人'
)ENGINE = INNODB;
ALTER TABLE visit_log COMMENT '接口访问记录';

/*==============================================================*/
/* Table: news_content              */
/*==============================================================*/
DROP TABLE IF EXISTS news_content;
CREATE TABLE  news_content
(
   id            			BIGINT PRIMARY KEY COMMENT '主键=news.id',
   text     	 			 MEDIUMTEXT  COMMENT '文章内容'
   
)ENGINE = INNODB;
ALTER TABLE news_content COMMENT '文章内容';


/*==============================================================*/
/* Table: vote                                                */
/*==============================================================*/
DROP TABLE IF EXISTS vote;
CREATE TABLE  vote
(
		id            			BIGINT PRIMARY KEY COMMENT '主键',
		target_id           BIGINT NOT NULL COMMENT '关联Id=news.id',
		url     	 					VARCHAR(150)  COMMENT 'url',
		title     	 				VARCHAR(100)  COMMENT '标题',
		descrtiption     	 	VARCHAR(200)  COMMENT '描述',
		begin_date					TIMESTAMP COMMENT '开始时间',
		end_date						TIMESTAMP COMMENT '结束时间',
		`interval`       			SMALLINT NOT NULL DEFAULT 0 COMMENT '间隔天数',
		max_selected      	SMALLINT NOT NULL DEFAULT 1 COMMENT '最多可选几项',
		mode      					SMALLINT NOT NULL DEFAULT 1 COMMENT '模式 1:独立用户,2:独立IP',
		total								INT NOT NULL DEFAULT 0 COMMENT '总票数',
		sort       					SMALLINT  NOT NULL DEFAULT 1 COMMENT '排序值',
		status             	SMALLINT NOT NULL DEFAULT 0 COMMENT '状态(-1删除，0正常)',
		create_time					TIMESTAMP NOT NULL  COMMENT '创建时间',
		create_user_id			BIGINT COMMENT '创建人',
		update_time					TIMESTAMP DEFAULT 0 COMMENT '修改时间',
		update_user_id			BIGINT COMMENT '修改人'
)ENGINE = INNODB;
ALTER TABLE vote COMMENT '文章项目';

/*==============================================================*/
/* Table: news_reptor              */
/*==============================================================*/
DROP TABLE IF EXISTS news_reptor;
CREATE TABLE  news_reptor
(
   id            			BIGINT PRIMARY KEY COMMENT '主键=news.id',
   visit_count     	  INT NOT NULL  COMMENT '访问次数',
   comment_count     	  INT NOT NULL  COMMENT '评论次数',
   praise_count     	  INT NOT NULL  COMMENT '点赞次数',
   collection_count     	  INT NOT NULL  COMMENT '收藏次数',
   forward_count     	  INT NOT NULL  COMMENT '转发次数'
   
)ENGINE = INNODB;
ALTER TABLE news_reptor COMMENT '文章内容';
