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

>用户登录 
     
  * url POST: {domain}/login
  * 请求参数 
  
   |位置|参数|类型|长度|是否必填|Default|描述|
 |:----|:----|:----:|:----:|:---:|:---:|:---:|
 |body|accountId|String| /|Y|/ |帐号，method=password时，代表用户名，method=mobile时，代表手机号，method=weixin，代表UnionId|
 |body|method|string|/|N|password |登录方式 取值范围：[password,mobile,weixin]|
 |body|password|string|/|N| /|密码 method=password时必须填写|
 |body|smsCode|string|/|N|/ |短信验证码 method=mobile时必须填写|
 |body|imgCode|string|/|N|/ |图片验证码 imgCodeId非空时必须填写|
 |body|imgCodeId|string|/ |N| /|图片验证Id|
 
  * 返回结果
  
  		{
		    "success": true,
		    "data": {
		        "token": "18A18E988DF5CC1AE722FB33948DCF17"
		    },
		    "code": 200,
		    "msg": null
		}
  
   > 获取登录用户 
      
   * url GET: {domain}/user/info   
   * 请求参数
   
      |位置|参数|类型|长度|是否必填|Default|描述|
	 |:----|:----|:----:|:----:|:---:|:---:|:---:|
	 |header|x-api-token|stirng| /|Y|/|令牌|
	 
   * 返回结果
   
   		{
		    "success": true,
		    "data": {
		        "name": "zhaojianwei",//姓名
		        "nickname": "admin",//昵称
		        "headUrl": null,//头像地址
		        "rose": [ //角色列表
		            "admin"
		        ]
		    },
		    "code": 200,
		    "msg": null
		}
		
   > 获取图片验证码 
      
   * url GET: {domain}/imagecode
   * 请求参数
   
     	无
	 
   * 返回结果
   
   		{
		    "success": true,
		    "data": {
		        "id": "7C88272480FD00113A7FF743F87A592C",
                       "data": "data:image/jpeg;base64,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"
		    },
		    "code": 200,
		    "msg": null
		}
  
  > 发送短信验证码 
      
   * url POST: {domain}/sendsmscode 
   * 请求参数
   
      |位置|参数|类型|长度|是否必填|Default|描述|
	 |:----|:----|:----:|:----:|:---:|:---:|:---:|
	 |body|mobile|stirng| /|Y|/|手机号码|
	 |body|imgCodeId|stirng| /|Y|/|图片验证码Id|
	 |body|imgCodeId|stirng| /|Y|/|图片验证码|
	 
   * 返回结果
   
   	   {
		    "success": true,
		    "data": "短信验证码是:960969", //为方便调试输出的，接通短信平台后，此处是没有值的
		    "code": 200,
		    "msg": null
		}

  > 检查帐号的合法性 
      
   * url POST: {domain}/checkaccount 
   * 请求参数
   
      |位置|参数|类型|长度|是否必填|Default|描述|
	 |:----|:----|:----:|:----:|:---:|:---:|:---:|
	 |form|accountId|stirng| /|Y|/|帐号|
	 
   * 返回结果
   
   		{
		    "success": true,
		    "data": false,//是否合法，true是合法，可以注册，false 不合法
		    "code": 405,
		    "msg": "已被注册" //
		}

  > 注册帐号 
      
   * url POST: {domain}/register 
   * 请求参数
   
      |位置|参数|类型|长度|是否必填|Default|描述|
	 |:----|:----|:----:|:----:|:---:|:---:|:---:|
	 |body|accountId|stirng| /|Y|/|帐号|
 	 |body|password|string|/|Y| /|密码 |
 	 |body|nickname|string|/|N|/ |昵称,不填系统自动成功，WM_6位数字|
 	 |body|imgCode|string|/|Y|/ |图片验证码|
 	 |body|imgCodeId|string|/ |Y| /|图片验证Id|
	 
   * 返回结果
   
   		{
		    "success": true,
		    "data": null,
		    "code": 200,
		    "msg": null
		}
  > 退出登录 
      
   * url POST: {domain}/user/logout   
   * 请求参数
   
      |位置|参数|类型|长度|是否必填|Default|描述|
	 |:----|:----|:----:|:----:|:---:|:---:|:---:|
	 |header|x-api-token|stirng| /|Y|/|令牌|
	 
   * 返回结果
   
   		{
		    "success": true,
		    "data": null,
		    "code": 200,
		    "msg": null
		}
		
 > 查询文章列表 
      
   * url PSOT: {domain}/news
   * 请求参数
   
      |位置|参数|类型|长度|是否必填|Default|描述|
	 |:----|:----|:----:|:----:|:---:|:---:|:---:|
	 |body|pageIndex|number| /|N|1|页码|
	 |body|pageSize|number|/|N|10 |页面大小|
	 |body|metaId|number|/|N| /|分类Id|
	 |body|keyword|string|/|N|/ |关键字，支持标题，关键字，作者进行查询|
   * 返回结果
   
   		{
		    "success": true,
		    "data": {
		        "total": 509,
		        "items": [
		            {
		                "id": 1907270104320001, //文章Id
		                "createTime": 1574576527535,//创建时间
		                "updateTime": 1574576527535,//更新时间
		                "metaId": 1906032244030001,//分类Id
		                "title": "全世界最大的玻璃厂，从开工就没停止过(每天可产200万个瓶子)",
		                "coverUrl": "https://image.dugoogle.com/uploads/allimg/190723/9-1ZH31H1490-L.jpg",
		                "keyword": null,//关键字
		                "description": null,//描述
		                "sourceUrl": null,//来源链接
		                "source": null,//来源
		                "tags": null,//标签
		                "author": "JIU",//作者
		                "sort": null,//排序值
		                "isTop": false,//是否置顶
		                "isHot": false,//是否热门
		                "isRecommend": false//是否推荐
		            }
		        ]
		    },
		    "code": 200,
		    "msg": null
		}
		
 > 查询文章详情 
        
   * url /news/{id}
   * 请求参数   
   
   	 |位置|参数|类型|长度|是否必填|Default|描述|   	 
	|:----|:----|:----:|:----:|:---:|:---:|:---:|
	|path|id|number| /|Y|1|文章Id|
	
   * 返回结果
   
   		{
		    "success": true,
		    "data": {
		        "votes": [
		        	 {
		                "id": 1907270104320002,//投票项目ID
		                "createTime": 1574582240338, //创建时间
		                "targetId": 1907270104320001,//关联项目ID
		                "url": null,// 链接
		                "title": "toupiao",//标题
		                "description": "1559456504",//描述
		                "beginTime": 1559456504,//开始时间
		                "endTime": 1559457504,//结束时间
		                "interval": 100,//间隔天数
		                "maxSelected": 1,//最多可选几项
		                "mode": 1,//方式 :1:独立用户,2:独立IP
		                "sort": 1,//排序值
		                "total": 0//总票数
		            }
		        ],
		        "news": {
		            "id": 1907270104320001, //文章Id
		            "createTime": 1574576527535,//创建时间
		            "updateTime": 1574576527535,//更新时间
		            "metaId": 1906032244030001,//分类Id
		            "title": "全世界最大的玻璃厂，从开工就没停止过(每天可产200万个瓶子)",
		            "coverUrl": "https://image.dugoogle.com/uploads/allimg/190723/9-1ZH31H1490-L.jpg",
		            "keyword": null,//关键字
		            "description": null,//描述
		            "sourceUrl": null,//来源链接
		            "source": null,//来源
		            "tags": null,//标签
		            "author": "JIU",//作者
		            "sort": null,//排序值
		            "isTop": false,//是否置顶
		            "isHot": false,//是否热门
		            "isRecommend": false//是否推荐
		            "content": "<p>以色列是一个由犹太人建立的国家，这个国家虽然不是很大，人口也不是很多，但他们却已经是一个发达的资本主义国家了。在这个国家有一个全世界最大的玻璃厂，从这个工厂的机械开工之后，就一直没有停止过，每天可以生产200多万个玻璃制品。</p> \n<h2>全球最大的玻璃制造厂</h2> \n<div> \n <img alt=\"全世界最大的玻璃厂，从开工就没停止过(每天可产200万个瓶子)\" src=\"https://image.dugoogle.com/uploads/allimg/190723/1H3234053-0.jpg\"> \n</div> \n<p>在地中海东南沿岸，有一个面积25740平方公里，总人口884万人左右的国家，它就是以色列。这个国家是全球唯一以犹太人作为主体民族的国家，虽然这个国家人口不多面积不大，但他们一直致力于科学和工程学研究，现在已经发展为发达的资本主义国家了。</p> \n<div> \n <img alt=\"全世界最大的玻璃厂，从开工就没停止过(每天可产200万个瓶子)\" src=\"https://image.dugoogle.com/uploads/allimg/190723/1H32332D-1.jpg\"> \n</div> \n<p>在以色列的内盖夫沙漠，有一座全世界最大的玻璃厂，这个玻璃厂拥有极为先进的工艺，每天可以生产200多万个玻璃制品。这个十分庞大的玻璃工厂，需要的员工其实也并不多，仅需300多人就可以轻松运营整个工厂，而且制作的玻璃制品还十分的精良。</p> \n<div> \n <img alt=\"全世界最大的玻璃厂，从开工就没停止过(每天可产200万个瓶子)\" src=\"https://image.dugoogle.com/uploads/allimg/190723/1H3233401-2.jpg\"> \n</div> \n<p>这个工厂在质量方面的把控十分严格，对于一些不符合规定的玻璃制品，一律销毁进行回炉重造。这家公司每天所生产的玻璃制品，大多数都被运往欧洲。欧洲和中东五分之二的玻璃制品，都是由这家工厂提供的，可以说它是当之无愧的最大玻璃厂。</p> \n<div> \n <img alt=\"全世界最大的玻璃厂，从开工就没停止过(每天可产200万个瓶子)\" src=\"https://image.dugoogle.com/uploads/allimg/190723/1H3235160-3.jpg\"> \n</div> \n<p>每天机械24小时不停歇的生产，能够产出200多万个玻璃制品，其中每天有30万个不符合质量的玻璃，他们都会直接进行销毁重造。就是因为严格的质量把控，才让这个工厂能够发展的那么好。这个工厂从机械开动之后，就一直没有停止过，每天24小时不间断的工作。</p> \n<div> \n <img alt=\"全世界最大的玻璃厂，从开工就没停止过(每天可产200万个瓶子)\" src=\"https://image.dugoogle.com/uploads/allimg/190723/1H323F22-4.jpg\"> \n</div> \n<p>因为机械一旦停止，那么造成的损失将是巨大的。因为一旦机器停止运转，整个烤箱当中的玻璃就会在瞬间硬化，然后堵塞住通道，然后让整个生产线瘫痪。许多网友都很好奇，如果停电了会怎么办。相信他们会有各种措施防止意外发生，毕竟器械停止的损失太大。</p>",
		        },
		        "newsReportPo": {
		            "id": 1907270104320001,
		            "visitCount": 81, //访问次数
		            "commentCount": 0,//评论次数
		            "praiseCount": 0,//点赞次数
		            "collectionCount": 0,//收藏次数
		            "forwardCount": 0//转发次数
		        }
		    },
		    "code": 200,
		    "msg": null
		}

> 用户行为查询 
       
  * url POST: {domain}/my/{behavior}
  * 请求参数
  
   |位置|参数|类型|长度|是否必填|Default|描述|   	 
	|:----|:----|:----:|:----:|:---:|:---:|:---:|
	|header|x-api-token|stirng| /|Y|/|令牌|
	|path|behavior|string| /|Y|1|行为，取值范围 [collection,parise,forward,comment,reply,vote]|
	|body|pageIndex|number| /|N|1|页码|
	|body|pageSize|number|/|N|10 |页面大小|
	|body|targetId|number|/|N|/ |关联项目Id|
	
  * 返回结果
  	
	  	{
		    "success": true,
		    "data": {
		        "total": 2,
		        "items": [
		            {
		                "id": 1906021919440001, //
		                "targetId": 1906032244030001,//关联项目Id
		                "targetType": 1,//关联项目类型：1-文章
		                "userId": 18000001,//用户Id
		                "createTime": 1559474384,//创建时间
		                "content": null,//
		                "ip": null,//内容
		                "title": "最贵的杜卡迪大魔鬼多少钱，48.9万元(妥妥的一辆宝马5系)"
		            },
		            {
		                "id": 1906021428420001,
		                "targetId": 1906032244030001,
		                "targetType": 1,
		                "userId": 18000001,
		                "createTime": 1559456922,
		                "content": null,
		                "ip": null,
		                "title": "全世界最大的玻璃厂，从开工就没停止过(每天可产200万个瓶子)"
		            }
		        ]
		    },
		    "code": 200,
		    "msg": null
		}
		
> 提交用户行为
       
  * url POST: {domain}/save/{behavior}
  * 请求参数
  
   |位置|参数|类型|长度|是否必填|Default|描述|   	 
	|:----|:----|:----:|:----:|:---:|:---:|:---:|
	|header|x-api-token|stirng| /|Y|/|令牌|
	|path|behavior|string| /|Y|1|行为，取值范围 [collection,parise,forward,comment,reply,vote]|
	|body|targetId|number|/|Y|/ |关联项目Id|
	|body|targetType|number|/|Y|/ |联项目类型：1-文章|
	|body|title|string|/|Y|/ |项目标题|
	|body|content|string|/|N|/ |内容：comment，reply 不能为空|
	
	
  * 返回结果
  	
	  	{
		    "success": true,
		    "data": 1911241553500001,
		    "code": 200,
		    "msg": null
		}
 

      
      
