import request from '@/utils/request'

// 查询入库详情列表
export function listDrugindetail(query) {
  return request({
    url: '/system/drugindetail/list',
    method: 'get',
    params: query
  })
}

// 查询入库详情详细
export function getDrugindetail(id) {
  return request({
    url: '/system/drugindetail/' + id,
    method: 'get'
  })
}

// 新增入库详情
export function addDrugindetail(data) {
  return request({
    url: '/system/drugindetail',
    method: 'post',
    data: data
  })
}

// 修改入库详情
export function updateDrugindetail(data) {
  return request({
    url: '/system/drugindetail',
    method: 'put',
    data: data
  })
}

// 删除入库详情
export function delDrugindetail(id) {
  return request({
    url: '/system/drugindetail/' + id,
    method: 'delete'
  })
}
