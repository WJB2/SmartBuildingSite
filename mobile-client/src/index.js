import Vue from 'vue';
import router from './router/index.js';
import App from './app.vue';
import './index.scss';
import 'lib-flexible';
import Vuex from 'vuex';
import store from './store/global';
import moment from 'moment';
import './utils/Rem';//和上面效果一致
import {Dialog,Row,Col,Picker,Field,Cell,CellGroup,Button,Toast,NavBar,Tabbar,TabbarItem,List,Panel,NoticeBar,Tag,Popup} from 'vant';
import CircleButton from './components/circlebutton';

Vue.config.productionTip=false;

Vue.config.devtools = true;
Vue.prototype.$moment = moment;

const FastClick=require('fastclick');
FastClick.attach(document.body);

Vue.use(Row).use(Col);
Vue.use(Field);
Vue.use(Cell).use(CellGroup);
Vue.use(Button);
Vue.use(Toast);
Vue.use(NavBar);
Vue.use(Tabbar).use(TabbarItem);
Vue.use(Panel);
Vue.use(List);
Vue.use(NoticeBar);
Vue.use(Tag);
Vue.use(CircleButton);
Vue.use(Popup);
Vue.use(Picker);
Vue.use(Dialog);

new Vue({
    el:'#app',
    router,
    store,
    render:h=>h(App),
});