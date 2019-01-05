<template>
    <van-tabbar v-model="activeIndexData" class="tabbar" @change="handleChangeTabBar">
            <van-tabbar-item>
                <span>人员信息维护</span>
                <i slot="icon" class="iconfont icon-yewurenyuanxinxiguanli" style="{fontSize:'20px'}"></i>
            </van-tabbar-item>
            <van-tabbar-item icon="records">设备信息维护</van-tabbar-item>
            <van-tabbar-item>
                <span>我的</span>
                <i slot="icon" class="iconfont icon-tuanduicankaoxian-" style="{fontSize:'20px'}"></i>
            </van-tabbar-item>
    </van-tabbar>
</template>

<script>
export default {
    data(){
        let me=this;
        return{
            activeIndexData:me.activeIndex,
        }
    },
    methods:{
        handleChangeTabBar(e){
            this.$emit('onChange',e)
        }
    },
    props:{
        activeIndex:{
            type:Number,
            default:0,
        },
    },

}
</script>

<style lang="scss">
@import "./../../../style/common.scss";

.tabbar{
    background-color: $navBarColor;

}

</style>
