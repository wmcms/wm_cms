import request from '@/utils/upload'

export function uploadImage(data) {
  return request({
    url: '/upload/image',
    method: 'post',
    params: data
  })
}
