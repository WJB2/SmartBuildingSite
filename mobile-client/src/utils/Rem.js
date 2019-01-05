const baseSize=32;

function setRem(){
    //当前页面宽度相对于750宽的缩放比例,可根据自己需要修改。
    const htmlWidth=document.documentElement.clientWidth||document.body.clientWidth;

    const htmlDom=document.getElementsByTagName('html')[0];

    //设置页面根节点字体大小
    htmlDom.style.fontSize=htmlWidth/10+'px';
    
}

setRem();

window.onresize=function(){
    setRem();
}