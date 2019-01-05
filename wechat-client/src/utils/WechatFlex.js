/*
* 禁止浏览器下拉回弹
* */
import $ from 'jquery';

function stopDrop() {
    let lastY;//最后一次y坐标点
    $(document.body).on('touchstart',function (event) {
        lastY = event.originalEvent.changedTouches[0].clientY;//点击屏幕记录最后一次y坐标
    });
    $(document.body).on('touchmove',function(event){
        let y=event.originalEvent.changedTouches[0].clientY;
        var st=$(this).scrollTop();//滚动条高度
        if (y>=lastY && st<0){//如果滚动条高度小于0可以理解为到顶了 确实下拉情况下 阻止touchmove事件
            lastY = y;
            event.preventDefault();
        }
        lastY = y;
    });
}
window.onresize=function(){
    stopDrop();
}