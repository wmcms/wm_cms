#闻忙科技CMS系统设计
> 文档摘要

    本文档主要用于描述，CMS产品的数据结构，及API接口说明


## 数据结构设计
> 摘要

### 1. 用户相关

  
>1.1 用户主表 user 
 
    主用于记录用户帐号信息，用于快速登录。

 |列名|类型|长度|是否可空|Default|描述|
 |:----|:----:|:----:|:---:|:---:|:---:|
 |id|bigint| |N| |用户ID 主键|
 |name|varchar|20|N| |用户名|
 |password|varchar|32|N| |密码MD5密文|
 |slat|varchar|6|N| |加密混淆码|
 |status|smallint| |N| 0|状态 0：正常,1：禁用,2：删除|

>1.2 用户三方帐号表 user_open

 |列名|类型|长度|是否可空|Default|描述|
  |:----|:----:|:----:|:---:|:---:|:---:|
  |id|bigint| |N| |主键|
  |user_id|bigint| |N| |用户id==user.id|
  |open_type|smallint| |N| |帐号类型 1-手机，2-微信，3-新浪，4-百度，5-qq|
  |open_id|varchar|32|Y| |open_id MD5密文不加混淆码|

>1.3 用户信息表 user_info 

    记录用户数据，用于信息展示。

 |列名|类型|长度|是否可空|Default|描述|
 |:----|:----:|:----:|:---:|:---:|:---:|
 |id|bigint| |N| |主键 id==user.id| 
 |nickname|varchar|20|N| |昵称|
 |real_name|varchar|20|N| |用户实名|
 |head_url|varchar|100|Y| |头像地址|
 |email|varchar|50|Y| |邮箱|
 |mobile|varchar|13|Y| |手机|
 |id_no|varchar|18|Y| |身份证|
 |gender|char|1|Y| |性别|
 |birth_date|timestamp| 8|Y| |生日|
 |level|smallint| |N| 1|等级|
 |type|smallint| |N| 1|类型 0--普通会员，2--系统用户|
 |status|smallint| |N| 0|状态==user.status|
 |activate_status|smallint| |N| 0|激活状态:1,2,4,8|
 |create_time|timestamp| |N|  |注册时间|
 |create_user_id|bigint| |N|  |创建人|
 |update_time|timestamp| |Y|  |修改时间|
 |update_user_id|bigint| |Y|  |修改人|
 
 >1.4 用户登录日志表
 
 >1.5 用户收藏表
 
 >1.6 用户点赞表
 
 >1.7 用户评论表
 
 >1.8 用户回复评论
 

### 2. 文章相关

>2.1 文章表 news

>2.2 文章内容表(存储大字段)

>2.3 文章项目表

>2.4 文章统计表

>2.5 广告表 ad

>2.6 素材表

### 4. 系统相关

>4.1 数据字典表 meta

    主用于设计系统数据字典，比如文章类型等。

 |列名|类型|长度|是否可空|Default|描述|
 |:----|:----:|:----:|:---:|:---:|:---:|
 |id|bigint| |N| |用户ID 主键|
 |type|smallint| |N| 0|字典类型 1-文章,2-广告位,3-素材|
 |parent_id|bigint| |N| |父级ID==mate.id|
 |name|varchar|20|N| |名称|
 |parent_path|varchar|200|N| |父级路径以，分隔| 
 |level|smallint| |N| 1|层级 |
 |sort|smallint| |N| 1|层级 |
 |remark|varchar|50|N| |备注| 
 |slat|varchar|6|N| |排序值|
 |status|smallint| |N| 0|状态 0：正常,1：禁用,2：删除|
|create_time|timestamp| |N|  |注册时间|
 |create_user_id|bigint| |N|  |创建人|
  |update_time|timestamp| |Y|  |修改时间|
  |update_user_id|bigint| |Y|  |修改人|
  
>4.2 角色表

    鉴于一期的工作量，先不作，暂时通过用户类型来区分，能不能使用后台，普通会员只能登录后台

>4.3 角色权限表

    鉴于一期的工作量，先不作，暂时通过用户类型来区分，能不能使用后台，普通会员只能登录后台

>4.4 定时任务表

>4.5 系统日志表

>4.4 页面访问日志表


### 报表相关

 ## 接口设计
 >摘要
 
   1. 接口调用方式 http + Json 格式请求
   2. 请求头统一参数
       
           Header:{
               x-api-token:null
           }
           其中x-api-token 为登录时返回身份认证票据，除登录接口外，其余所有接口调用时
           必须加上此参数，才能正确请求。
   3. 接口返回值统一格式
         
          {
              success:true
              ,code:200 
              ,msg:null
              ,data:null #接口数据域
           }  
           success 是指示，接口有没有被正确处理
           code 聚会为200，404，403，402，500
                  * 200 成功返回
                  * 403 没有权限
                  * 404 没有定义，或是资源没有被找到
                  * 402 没有登录或Token过期
                  * 500 代码异常
           msg 消息文本
           data 接口返回的数据域，以下接口描述的返回值就是对本域的解释

### 1. 用户相关

>1.1 用户登录 
     
  * url /user/login
  * 请求参数
  * 返回结果
  
 > 帐号唯一性较验 
      
   * url /check/user
   * 请求参数
   * 返回结果
   
 > 获取图片验证码 
        
   * url /check/getverificationcode
   * 请求参数
   * 返回结果

> 刷新token 
  
  * 适用场景：延长Token时效       
  * url /refresh/token
  * 请求参数
  * 返回结果
  
 > 用户登出 
      
   * url /user/logout
   * 请求参数
   * 返回结果
   
 > 获取短信验证码 
        
   * url /check/getsmscode
   * 请求参数
   * 返回结果
 
> 刷新用户查询 
  
  * 适用场景：用户管理列表，会员列表展示   
  * url /userinfo/search
  * 请求参数
  * 返回结果
  
 > 保存用户信息 
      
   * url /userinfo/save
   * 请求参数
   * 返回结果
   
 > 保存帐号信息
 
   * 适用场景：修改密码     
   * url /user/save
   * 请求参数
   * 返回结果
 
> 刷新token 
     
  * url /refresh/token
  * 请求参数
  * 返回结果
  
 > 用户登出 
      
   * url /user/logout
   * 请求参数
   * 返回结果
   
 > 获取短信验证码 
        
   * url /check/getsmscode
   * 请求参数
   * 返回结果

> 刷新token 
     
  * url /refresh/token
  * 请求参数
  * 返回结果
  
 > 用户登出 
      
   * url /user/logout
   * 请求参数
   * 返回结果
   
 > 获取短信验证码 
        
   * url /check/getsmscode
   * 请求参数
   * 返回结果

> 刷新token 
     
  * url /refresh/token
  * 请求参数
  * 返回结果
  
 > 用户登出 
      
   * url /user/logout
   * 请求参数
   * 返回结果
   
 > 获取短信验证码 
        
   * url /check/getsmscode
   * 请求参数
   * 返回结果

> 刷新token 
     
  * url /refresh/token
  * 请求参数
  * 返回结果
  
 > 用户登出 
      
   * url /user/logout
   * 请求参数
   * 返回结果
   
 > 获取短信验证码 
        
   * url /check/getsmscode
   * 请求参数
   * 返回结果

      
      