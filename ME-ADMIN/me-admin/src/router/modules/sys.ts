import { RouteRecordRaw } from 'vue-router';
import { Layout } from '@/router/constant';
import { StarSettings24Regular } from '@vicons/fluent';
import { renderIcon } from '@/utils/index';

/**
 * @param name 路由名称, 必须设置,且不能重名
 * @param meta 路由元信息（路由附带扩展信息）
 * @param redirect 重定向地址, 访问这个路由时,自定进行重定向
 * @param meta.disabled 禁用整个菜单
 * @param meta.title 菜单名称
 * @param meta.icon 菜单图标
 * @param meta.keepAlive 缓存该路由
 * @param meta.sort 排序越小越排前
 *
 * */
const routes: Array<RouteRecordRaw> = [
  {
    path: '/sys',
    name: 'sys',
    redirect: '/sys/user',
    component: Layout,
    meta: {
      title: '系统管理',
      icon: renderIcon(StarSettings24Regular),
      sort: 3,
    },
    children: [
      {
        path: 'user',
        name: 'user-index',
        meta: {
          title: '用户管理',
        },
        component: () => import('@/views/list/user/index.vue'),
      },
      {
        path: 'role',
        name: 'role-index',
        meta: {
          title: '角色管理',
        },
        component: () => import('@/views/list/role/index.vue'),
      },
      {
        path: 'purview',
        name: 'purview-index',
        meta: {
          title: '权限管理',
        },
        component: () => import('@/views/list/menu/index.vue'),
      },
      {
        path: 'dict',
        name: 'dict-index',
        meta: {
          title: '字典管理',
        },
        component: () => import('@/views/list/dict/index.vue'),
      },
    ],
  },
];

export default routes;
