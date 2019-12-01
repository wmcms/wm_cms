import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getToken, removeToken } from '@/utils/auth'

const api_base_url = process.env.VUE_APP_BASE_API
const service = axios.create({
  baseURL: api_base_url,
  withCredentials: true,
  timeout: 30 * 1000
})

// request interceptor
service.interceptors.request.use(
  config => {
    if (store.getters.token) {
      config.headers['x-api-token'] = getToken()
      config.headers['Content-Type'] = 'application/json'
      config.headers['cache-control'] = 'no-cache'
    }
    config.withCredentials = false
    //  debugger;
    // config.data=JSON.stringify(config.data)
    return config
  },
  error => {
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

service.interceptors.response.use(
  response => {
    const res = response.data
    if (res.code == 405) {
      removeToken()
      location = '/#/login'
    }
    if (res.code !== 200) {
      Message({
        message: res.msg || 'error',
        type: 'error',
        duration: 5 * 1000
      })
      if (res.code === 403 || res.code === 50012 || res.code === 50014) {
        MessageBox.confirm('You have been logged out, you can cancel to stay on this page, or log in again', 'Confirm logout', {
          confirmButtonText: 'Re-Login',
          cancelButtonText: 'Cancel',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      }
      return Promise.reject(res.msg || 'error')
    } else {
      return res
    }
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export function HttpGet(url, data) {
  return service({
    url: url,
    method: 'get',
    data
  })
}

/**
 * Post
 */
export function HttpPost(url, data) {
  return service({
    url: url,
    method: 'post',
    data
  })
}
/**
 * 上传
 */
export function HttpUpload(url, data, callback) {
  var xhr = new XMLHttpRequest()
  xhr.withCredentials = false
  xhr.open('POST', api_base_url + url)
  xhr.setRequestHeader('x-api-token', getToken())
  xhr.onload = function() {
    callback && callback(xhr)
  }
  xhr.send(data)
}
