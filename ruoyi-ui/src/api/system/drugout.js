import request from '@/utils/request'

// 查询药品出库列表
export function listDrugout(query) {
  return request({
    url: '/system/drugout/list',
    method: 'get',
    params: query
  })
}

// 查询药品出库详细
export function getDrugout(id) {
  return request({
    url: '/system/drugout/' + id,
    method: 'get'
  })
}

// 新增药品出库
export function addDrugout(data) {
  return request({
    url: '/system/drugout',
    method: 'post',
    data: data
  })
}

// 修改药品出库
export function updateDrugout(data) {
  return request({
    url: '/system/drugout',
    method: 'put',
    data: data
  })
}

// 删除药品出库
export function delDrugout(id) {
  return request({
    url: '/system/drugout/' + id,
    method: 'delete'
  })
}
// 查询药品出库列表
export function listDrugoutTotal(query) {
  return request({
    url: '/system/drugoutdetail/listTotal',
    method: 'get',
    params: query
  })
}

export function listDrugoutSum(query) {
  return request({
    url: '/system/sumdrug//listSumout',
    method: 'get',
    params: query
  })
}

export function changeStatus(id) {
  return request({
    url: '/system/drugout/editStatusFinish/'+id,
    method: 'get',
  })
}
