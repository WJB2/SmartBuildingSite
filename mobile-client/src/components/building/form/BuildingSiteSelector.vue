<template>
    <van-popup v-model="visible" position="bottom">
        <van-picker 
            class="picker" 
            show-toolbar
            title="选择工地"
            :columns="listNameArray"
            @cancel="handleCancel"
            @confirm="handleSubmit">
        </van-picker>
    </van-popup>
</template>

<script>
    import buildingSiteService from './../../../api/building/BuildingSiteService';
    export default {
        props:['buildingSiteSelectorVisible'],
        data(){
            let me=this;
            return{
                list:[],
                formVisible:true,
                temp:true,
                visible:me.buildingSiteSelectorVisible
            }
        },
        computed: {
            listNameArray: function () {
                const newArray=[];
                this.list.forEach((item,index)=>{newArray.push(item.name)});
                return newArray;
            }
        },  
        beforeMount:async function(e){
            const result=await buildingSiteService.findBuildingSiteList({buildingDeveloperId:localStorage.getItem('currentPositionId'),deletedFlag:false});
            this.list=result;
            console.log(result);
        },
        mounted:function(){
            console.log(this.visible)
        },
        watch:{
            buildingSiteSelectorVisible(newValue,oldValue){
                 
                if(!newValue===oldValue){
                    this.visible=this.buildingSiteSelectorVisible;
                }
            }
        },
        methods:{
            handleCancel(){
                this.$emit('onSelectorCancel');
            },
            handleSubmit(e){
                let currentId=null;
                this.list.forEach((item)=>{if(item.name===e){currentId=item.id}})
                this.$emit('onSelectorSubmit',currentId,e);
            },
        }
    }
</script>

<style lang="scss">

</style>