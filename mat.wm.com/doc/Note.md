


安装方法
环境准备
1. node
2. git 
3. python
# 克隆项目
git clone https://github.com/PanJiaChen/vue-element-admin.git
# 进入项目目录
cd vue-element-admin
# 安装依赖
npm install
# 建议不要用 cnpm 安装 会有各种诡异的bug 可以通过如下操作解决 npm 下载速度慢的问题
npm install --registry=https://registry.npm.taobao.org
# 本地开发 启动项目
npm run dev


#安装过程中的错误
#错误
npm ERR! Unexpected end of JSON input while parsing near '..."devDependencies":{"d'
#解决
npm cache clean --force

#错误
npm ERR!  [OperationalError: EPERM: operation not permitted, unlink 
#解决
1.以管理员身份运行cmd
2.还不行,需要删除npmrc文件(强调：不是nodejs安装目录npm模块下的那个npmrc文件,而是在C:\Users\{账户}\下的.npmrc文件)
当然也有说用命令清除缓存的，我自己试了下没成功，上面删除文件来的更加直接彻底，
关键是见效，记录于此。

#错误 
Cannot download "https://github.com/sass/node-sass/releases/download/v4.12.0/win32-x64-72_binding.node"
以上问题解决不了，直接安装cnpm,使用cnpm 运行项目
npm install -g cnpm --registry=https://registry.npm.taobao.org
cnpm run dev #运行开发环境 
cnpm run product #运行生产环境 

#查看端口
netstat -ano|findstr "8080"
taskkill /f /pid 