<template>
   
       <van-cell-group>
           <van-cell title="人员信息列表">
               <template slot="right-icon" name="filter">
                   <div class="iconfont icon-shaixuan" @click="handleClickIconfont"></div>
               </template>
           </van-cell>
       </van-cell-group>
  
</template>

<script>
    import BuildingSiteSelector from './../../../components/building/form/BuildingSiteSelector.vue';
    export default{
        data(){
            return{
                 
            }
        },
        components:{
            BuildingSiteSelector,
        },
        methods:{
            handleClickIconfont(){//点击图标
                this.$emit('onClickIcon');
            }
            
        }
    }
</script>

<style lang="scss"> 
@import "./../../../style/common.scss";


</style>