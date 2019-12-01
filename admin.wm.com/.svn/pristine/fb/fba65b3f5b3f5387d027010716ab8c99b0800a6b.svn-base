import request from '@/utils/request'

export function getNews(id) {
  return request({
    url: '/news/get/' + (id || 0),
    method: 'get'
  })
}

export function saveNews(data) {
  return request({
    url: '/news/save',
    method: 'post',
    data: data
  })
}

export function search(data) {
  return request({
    url: '/news/search',
    method: 'post',
    data: data
  })
}
