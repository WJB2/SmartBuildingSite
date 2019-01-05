<template>
       <van-cell-group class="group">
           <van-cell title="设备信息列表">
               <template slot="right-icon" name="filter">
                   <span class="iconfont icon-shaixuan" @click="handleClickIconfont"></span>
               </template>
           </van-cell>
       </van-cell-group>
</template>

<script>
    export default{
        data(){
            return{

            }
        },
        methods:{
            handleClickIconfont(){//点击图标
                this.$emit('onClickIcon');
            }
        }

    }
</script>

<style lang="scss" scoped>
@import "./../../../style/common.scss";
</style>