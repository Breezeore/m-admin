import { http } from '@/utils/http/axios';

export interface BasicResponseModel<T = any> {
  code: number;
  message: string;
  result: T;
}

export interface BasicPageParams {
  pageNumber: number;
  pageSize: number;
  total: number;
}

/**
 * @description: 获取用户信息
 */
export function getUserInfo() {
  return new Promise(rel=>{
    rel({
      // userId: '1',
      // username: 'admin',
      // realName: 'Admin',
      // avatar: '123123',
      // desc: 'manager',
      // password: '213123',
      // token:'123123',
      permissions: [
        {
          name: '数据中心',
          value: 'dashboard_console',
        },
      ],
    })
  })
}

/**
 * @description: 用户登录
 */
export function login(params) {
  return http.request<BasicResponseModel>(
    {
      url: '/api/user/login',
      method: 'POST',
      params,
    },
    {
      isTransformResponse: false,
    }
  );
}

/**
 * @description: 登出
 */
export function logout() {
  return http.request<BasicResponseModel>(
    {
      url: '/api/user/logout',
      method: 'get',
    },
    {
      isTransformResponse: false,
    }
  );
}

/**
 * @description: 用户修改密码
 */
export function changePassword(params, uid) {
  return http.request(
    {
      url: `/user/u${uid}/changepw`,
      method: 'POST',
      params,
    },
    {
      isTransformResponse: false,
    }
  );
}
