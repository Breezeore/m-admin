import { http } from '@/utils/http/axios';

/**
 * @description: 控制台获取数据
 */
export function getData() {
  return http.request({
    url: '/api/data/get',
    method: 'post',
  });
}
