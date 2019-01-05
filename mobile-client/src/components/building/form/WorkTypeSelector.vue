<template>
    <van-popup v-model="visible" position="bottom">
        <van-picker 
            show-toolbar
            title="选择工种"
            :columns="listNameArray"
            @cancel="handleCancel"
            @confirm="handleSubmit">
        </van-picker>
    </van-popup>
</template>

<script>
import workTypeService from './../../../api/building/WorkTypeService';
export default {
    props:{
        workTypeSelectorVisible:Boolean,
    },
    data(){
        let me=this;
        return{
            list:[],
            visible:me.workTypeSelectorVisible
        }
    },
    computed:{
            listNameArray: function () {
                const newArray=[];
                this.list.forEach((item,index)=>{newArray.push(item.name)});
                return newArray;
            }
    },
    beforeMount:async function(){
        const result =await workTypeService.findWorkTypeList({buildingDeveloperId:localStorage.getItem('currentPositionId')});
        this.list=result;
    },
    methods:{
        handleCancel(){//点击取消
            this.$emit('onSelectorCancel')
        },
        handleSubmit(e){//点击确定
            this.$emit('onSelectorSubmit',e)
        }
    },
    watch:{
            workTypeSelectorVisible(newValue,oldValue){
                if(!newValue===oldValue){
                    this.visible=this.workTypeSelectorVisible;
                }
            }
    },

}
</script>

<style>

</style>
