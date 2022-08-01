import request from '@/utils/request'

// 查询药品库存列表
export function listDrugstock(query) {
  return request({
    url: '/system/drugstock/list',
    method: 'get',
    params: query
  })
}

// 查询药品库存详细
export function getDrugstock(id) {
  return request({
    url: '/system/drugstock/' + id,
    method: 'get'
  })
}

// 新增药品库存
export function addDrugstock(data) {
  return request({
    url: '/system/drugstock',
    method: 'post',
    data: data
  })
}

// 修改药品库存
export function updateDrugstock(data) {
  return request({
    url: '/system/drugstock',
    method: 'put',
    data: data
  })
}

// 删除药品库存
export function delDrugstock(id) {
  return request({
    url: '/system/drugstock/' + id,
    method: 'delete'
  })
}
export function listDrugstocklimit(query) {
  return request({
    url: '/system/drugstock/listLimit',
    method: 'get',
    params: query
  })
}
