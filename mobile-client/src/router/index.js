import Vue from 'vue';
import VueRouter from 'vue-router';
import UserLogin from './../views/main/MainLoginLayout.vue';

import Employee from './../views/building/employee/EmployeeIndex.vue';
import AttenceDevice from './../views/building/attencedevice/AttenceDeviceIndex.vue';
import Profile from './../views/profile/ProfileIndex.vue';

import globalStore from '../store/global';

Vue.use(VueRouter);

const router=new VueRouter({
    // mode:'history',
    routes:[
        {
            path:'/',
            redirect:to=>{
                if(localStorage.getItem('AUTHENTICATION'&&globalStore.state.initialized)){
                    return {path:'/employee'}
                }else{
                    return {path:'/user/login'}
                }
            }
        },
        {
            path:'/user/login',
            name:'UserLogin',
            component:UserLogin,
        },
        {
            path:'/employee',
            name:'Employee',
            component:resolve=>require(['./../views/building/employee/EmployeeIndex.vue'],resolve),
            meta:{
                requiresAuth:true,
            }
        },
        {
            path:'/attenceDevice',
            name:'AttenceDevice',
            component:resolve=>require(['./../views/building/attencedevice/AttenceDeviceIndex.vue'],resolve),
        },
        {
            path:'/profile',
            name:'Profile',
            component:Profile
        }   
         
    ]
});

//注册全局钩子用来拦截导航
router.beforeEach((to,from,next)=>{
    next();
})

export default router;