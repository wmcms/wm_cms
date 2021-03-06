import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login',
    method: 'post',
    data: { accountId: data.username, password: data.password,method:"password" }
  })
}

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get'
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
    url: '/user/search',
    method: 'post',
    params: query
  })
}
/**
 * 保存用户
 */
export function saveUser(fromData) {
  return request({
    url: '/user/save',
    method: 'post',
    data: fromData
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
