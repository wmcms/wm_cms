import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)
import Layout from '@/layout'

export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path*',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/auth-redirect'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error-page/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error-page/401'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: '首页',
        meta: { title: '首页', icon: 'dashboard', affix: true }
      }
    ]
  },
  {
    path: '/cms',
    component: Layout,
    redirect: '/cms',
    name: 'cms',
    meta: {
      title: '内容管理',
      icon: 'education'
    },
    children: [

      {
        path: 'create',
        component: () => import('@/views/news/edit'),
        name: 'newseditor',
        meta: { title: '发布文章', icon: 'edit' }
      },
      {
        path: 'newsedit/:id(\\d+)',
        component: () => import('@/views/news/edit'),
        name: 'newseditor',
        hidden: true,
        meta: { title: '发布文章', icon: 'edit' }
      },
      {
        path: 'draft',
        component: () => import('@/views/news/draft'),
        name: 'news',
        meta: { title: '草稿列表', icon: 'list' }
      },
      {
        path: 'publish',
        component: () => import('@/views/news/publish'),
        name: 'publish',
        meta: { title: '发布列表', icon: 'list' }
      },
      {
        path: 'ad',
        component: () => import('@/views/ad/index'),
        name: 'ad',
        meta: { title: '广告管理', icon: 'link' }
      },
      {
        path: 'material',
        component: () => import('@/views/material/index'),
        name: 'material',
        meta: { title: '素材管理', icon: 'tree' }
      }
    ]
  },

  {
    path: '/system',
    component: Layout,
    redirect: '/permission/page',
    alwaysShow: true, // will always show the root menu
    name: 'System',
    meta: {
      title: '系统管理',
      icon: 'component',
      roles: ['admin', 'editor'] // you can set roles in root nav
    },
    children: [
      {
        path: 'mate',
        component: () => import('@/views/meta/index'),
        name: 'meta',
        meta: {
          title: '数据字典',
          icon: 'zip'
          // roles: ['admin']
        }
      },
      {
        path: 'user',
        component: () => import('@/views/user/index'),
        name: 'PagePermission',
        meta: {
          title: '用户管理',
          roles: ['admin'],
          icon: 'user'
        }
      }
    ]
  }

]

const createRouter = () => new Router({
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
