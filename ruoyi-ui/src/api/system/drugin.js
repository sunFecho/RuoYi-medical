import request from '@/utils/request'

// 查询药品入库列表
export function listDrugin(query) {
  return request({
    url: '/system/drugin/list',
    method: 'get',
    params: query
  })
}

// 查询药品入库详细
export function getDrugin(id) {
  return request({
    url: '/system/drugin/' + id,
    method: 'get'
  })
}

// 新增药品入库
export function addDrugin(data) {
  return request({
    url: '/system/drugin',
    method: 'post',
    data: data
  })
}

// 修改药品入库
export function updateDrugin(data) {
  return request({
    url: '/system/drugin',
    method: 'put',
    data: data
  })
}

// 删除药品入库
export function delDrugin(id) {
  return request({
    url: '/system/drugin/' + id,
    method: 'delete'
  })
}
export function listDruginTotal(query) {
  return request({
    url: '/system/drugindetail/listTotal',
    method: 'get',
    params: query
  })
}
export function listDruginSum(query) {
  return request({
    url: '/system/sumdrug/list',
    method: 'get',
    params: query
  })
}
export function changeStatusin(id) {
  return request({
    url: '/system/drugin/editStatusFinish/'+id,
    method: 'get',
  })
}
