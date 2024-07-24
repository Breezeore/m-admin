import { http } from '@/utils/http/axios';

/**
 * @description: 角色列表
 */
export function getDictList(params) {
  return http.request({
    url: '/api/sys/dictionary/getDictList',
    method: 'get',
    params,
  },
  {
    isTransformResponse: false,
  });
}


// 根据字典类型查询字典数据信息
export function getDicts(dictType) {
  return http.request({
    url: '/api/sys/dictData/getDictData/' + dictType,
    method: 'get'
  },
  {
    isTransformResponse: false,
  })
}

// 新增字典类型
export function addDict(data) {
  return http.request({
    url: '/api/sys/dictionary/addDict',
    method: 'post',
    data
  },
  {
    isTransformResponse: false,
  })
}

// 修改字典类型
export function updateDict(data) {
  return http.request({
    url: '/api/sys/dictionary/upDict',
    method: 'put',
    data
  },
  {
    isTransformResponse: false,
  })
}

//删除数据字典
export function delDict(dictId) {
  return http.request({
    url: '/api/sys/dictionary/delDict/' + dictId,
    method: 'delete'
  },
  {
    isTransformResponse: false,
  })
}

