import Vue from 'vue'
import { HttpGet, HttpPost, HttpUpload } from './HttpHelper'
import Cookies from 'js-cookie'
const Lan = 'Language'

const Language = function() {
  return Cookies.get(Lan)
}
// 注册VUE组件
const register = function() {
  // 1.统一处理Http请求
  Vue.prototype.httpGet = HttpGet
  Vue.prototype.httpPost = HttpPost
  Vue.prototype.httpUpload = HttpUpload

  // 2.统一消息提示
  Vue.prototype.success = function(msg) {
    message(this, 'success', msg)
  }
  Vue.prototype.warning = function(msg) {
    message(this, 'warning', msg)
  }
  Vue.prototype.error = function(msg) {
    message(this, 'error', msg)
  }
  Vue.prototype.confirm = function(msg, callback) {
    this.$confirm(msg).then(async() => {
      callback && callback()
    }).catch(() => {})
  }

  // 3.多语言
  Vue.prototype.L = (function() {
    var _lan = Language() || 'zh'

    return _lan == 'zh' ? {
      A: '123'
    } : { A: 'English' }
  }())

  // 4.配置
  Vue.prototype.C = {
    ApiUrl: process.env.VUE_APP_BASE_API
  }
}

const message = function(vue, type, msg) {
  vue.$message({
    type: type,
    message: msg
  })
}

register()
