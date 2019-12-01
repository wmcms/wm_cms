import request from '@/utils/request'

/**
 * 查询枚举
 */
export function getEnum(query) {
  return request({ url: '/meta/getenum', method: 'get', params: query })
}
/**
 * 获取自定义分类
 */
export function getMeta(query) {
  return request({ url: '/meta/get/' + query, method: 'get' })
}
/**
 * 查询列表
 */
export function searchMeta(query) { return request({ url: '/meta/search', method: 'post', params: query }) }
/**
 * 保存
 */
export function saveMeta(query) { return request({ url: '/meta/save', method: 'post', params: query }) }
/**
 * 删除所选
 */
export function removeAt(query) { return request({ url: '/meta/removeat', method: 'post', params: query }) }
