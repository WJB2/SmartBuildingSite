import Vue from 'vue';//在import Vue的过程中，Vue主要是在原型上完成方法的挂载，并且初始化了全局的API。
import App from './app.vue';//引入主VUE页面
import "./utils/Rem";//引入将根页面的fontsize修改后 直接就可以在项目中使用rem实现自适应
import router from "./router";
import { Dialog,Actionsheet,Tabbar,TabbarItem,NavBar,Field,Cell, CellGroup,Button,Tab, Tabs,List,Notify,Toast  } from 'vant';

// import VConsole from 'vconsole/dist/vconsole.min.js' //import vconsole
// let vConsole = new VConsole() // 初始化

import $ from 'jquery';


/*
* 禁止浏览器下拉回弹
* */
function stopDrop(){
    var lastY;//最后一次y坐标点
    $(document.body).on('touchstart', function(event) {
        lastY = event.originalEvent.changedTouches[0].clientY;//点击屏幕时记录最后一次Y度坐标。
    });
    $(document.body).on('touchmove', function(event) {
        var y = event.originalEvent.changedTouches[0].clientY;
        var st = $(this).scrollTop(); //滚动条高度  
        if (y >= lastY && st <= 10) {//如果滚动条高度小于0，可以理解为到顶了，且是下拉情况下，阻止touchmove事件。
            lastY = y;
            event.preventDefault();
        }
        lastY = y;

    });
}

window.onresize=function(){
    stopDrop();
}

Vue.use(NavBar);
Vue.use(Field);
Vue.use(Cell).use(CellGroup);
Vue.use(Button);
Vue.use(Tab).use(Tabs);
Vue.use(List);
Vue.use(Notify);
Vue.use(Toast);
Vue.use(Tabbar).use(TabbarItem);
Vue.use(Actionsheet);
Vue.use(Dialog);

new Vue({
    el:"#root",//将渲染结果挂在这id为root的html上
    router,//注入到根实例中
    render:h=>h(App),//render函数是渲染一个视图，然后提供给el挂载，如果没有render那页面什么都不会出来
});