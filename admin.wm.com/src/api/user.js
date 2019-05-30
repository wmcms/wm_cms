import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    params: { userName: data.username, password: data.password }
  })
}

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}
/**
 * 用户列表
 */
export function searchUser(query) {
  return request({
    url: '/user/list',
    method: 'post',
    params: query
  })
}
/**
 * 保存用户
 */
export function saveUser(query) {
  return request({
    url: '/user/save',
    method: 'post',
    params: query
  })
}
/**
 * 批量删除
 */
export function batchDelete(query) {
  return request({
    url: '/user/batchdelete',
    method: 'post',
    params: query
  })
}
