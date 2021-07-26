import Vue from 'vue'
import Router from 'vue-router'
import Dashboard from '@/components/Dashboard'
import Login from '@/components/pages/Login'
import Products from '@/components/pages/Products'
import Orders from '@/components/pages/Order'
import CustomerOrders from '@/components/pages/CustomerOrders'
import Counpons from '@/components/pages/Counpons'

Vue.use(Router)

export default new Router({
    routes: [{
            path: '*',
            redirect: 'Login'
        },
        {
            path: '/admin',
            name: 'Dashboard',
            component: Dashboard,
            meta: {
                requireAuth: true,
                title: 'IVYDAYSHOP'
            },
            children: [{
                path: 'products',
                name: 'Products',
                component: Products,
                meta: {
                    requireAuth: true,
                    title: 'IVYDAYSHOP產品'
                },

            }, {
                path: 'orders',
                name: 'Orders',
                component: Orders,
                meta: {
                    requireAuth: true,
                    title: 'IVYDAYSHOP訂單'
                },
            }, {
                path: 'coupons',
                name: 'coupons',
                component: Counpons,
                meta: {
                    requireAuth: true,
                    title: 'IVYDAYSHOP優惠券'
                },
            }]
        },
        {
            path: '/',
            name: 'Dashboard',
            component: Dashboard,
            meta: {
                requireAuth: true,
                title: 'IVYDAYSHOP'
            },
            children: [{

                path: 'customer_orders',
                name: 'CustomerOrders',
                component: CustomerOrders,
                meta: {
                    requireAuth: true,
                    title: 'IVYDAYSHO模擬訂單'
                },
            }]
        },
        {
            path: '/login',
            name: 'Login',
            component: Login
        }
    ]
})