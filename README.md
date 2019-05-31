# 深圳市闻忙科技 CMS 产品说明文档
## 目录结构说明
### admin.wm.com 后台站点
 * 技术选型
 
    小程序：mpvue、vuex、vue、vue-router 、vue-cli、weui、fly、babel、webpack、 ES6
    
    app： react-native、redux、NativeBase（技术选型待定）
    
    app：Flutter、Widgets(技术选型待定)
    
 * 环境要求    
 
     node4.X  git python2.X
     
### api.wm.com  CMS系统接口
  * 技术选型
  
      SpringBoot2.2M、SpringMvc5.x、redis、mysql、mybatis、Spring-aop
      
   * 环境要求
      
      jdk1.8+   maven3.x  InterlliJ IDEA2019
      
### mat.wm.com是静态资源
### doc为设计文档

## 部署要求

  * 单节点方案
  
     1.  Linux CentOS7.X 主机一台 4核心8G
     
         安装软件环境：nginx、jdk1.8+
         
     2. 阿里云RDS Mysql数据库一台
   
   * 高可用及高并发方案
      
      1. nginx 2台
      2. Linux CentOS7.X 3台
      3. 阿里云Redis服务
      4. 阿里云消息队列服务
      5. 文件服务器CDN分发（可选用阿里云存储桶）
      6. 阿里云RDS Mysql服务

> 所有源码及其设计均属于深圳市闻忙科技有限公司所有，暂未开源，盗用必究
