import Vue from "vue";
import VueRouter from "vue-router";

import bindSite from "./views/bindSite.vue";
import salaryPay from "./views/salaryPay.vue";

Vue.use(VueRouter);

const routes=[
    {
        path:'/',
        component:bindSite,
    },
    {
        path:'/salaryPay',
        name:'salaryPay',
        component:salaryPay,
    }
];

const router=new VueRouter({
    routes
});

export default router;