#闻忙科技CMS系统——接口设计
> 文档摘要

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

      
      