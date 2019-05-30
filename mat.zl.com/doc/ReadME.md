#========================接口说明文档===========================#

#1.摘要
#1.1 统一规则
    #请求：头部统一加x-api-token 进行身份识别
    Header:{
        x-api-token:null
    }
    #响应
    {
        IsSuccess:true #请求处理是否成功
        ,Code:200 #返回值代码：成功-200，未授权-403，未定义-404，402-未登录，405-验证错误，代码异常-500
        ,Msg:null #消息文本
        ,Data:null #接口数据域
    }
    
#1.2 接口定义说明
    1.接口交互规则 http + json
    2.参数均以Json格式传传入
    3.接口定义中的Request为参数部分，Reponse为响应部分的Data结构说明
    
    
    
#2 用户相关接口
#2.1  登录 /user/login
    #Requset
    {
        UserName:null,
        Password:null
    }
    #Reponse
    {
        Token:null #票据
       ,NickName:null #昵称
       ,HeadUrl:null #头像地址
    }
    
    
#2.2 登出 /user/logout
    #参数
     null
    #Reponse
     null #Data为空，参考响应编码进行相应的业务操作即可
#2.3 列表 /user/list
    #Request
    {
        PageIndex:1 #当前页码
        ,PageSize:10 #数据条目大小
        ,Keyword:null #关键字查询
    }
    #Response
    {
        Total:null #总记录数
       Items: [
            {ID,UserName,NickName,RealName,BirthDate,Gender,Level,Status} #用户对象，字段说明参考表结构
       ]
     }
#2.4 保存 /user/save
    #Request
     User对象
    #Response
    { ID：null}#返回数据主键    
#2.5 批处理 /user/batch
    #Request
    #Response
#2.6 主键查询 /user/get
    #Request
     {
        ID:null
     }
     #Response
     {
        Item:user //用户对象
     }

#3 数据字典相关

#4.广告相关

#5 新闻相关

#6 报表相关

#7 日志相关

#8 数据采集相关

#9 定时器相关