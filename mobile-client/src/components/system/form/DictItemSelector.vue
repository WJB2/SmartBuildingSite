<template>
    <van-popup v-model="visible" position="bottom">
        <van-picker 
            show-toolbar
            title="选择开户行"
            :columns="listNameArray"
            @cancel="handleCancel"
            @confirm="handleSubmit">
        </van-picker>
    </van-popup>
</template>

<script>
import dictService from './../../../api/system/DictService';
export default {
    props:{
        dictSelectorVisible:Boolean,
        enterCode:String,
    },
    data(){
        let me=this;
        return{
            list:[],
            visible:me.dictSelectorVisible,
        }
    },
    watch:{
            dictSelectorVisible(newValue,oldValue){
                if(!newValue===oldValue){
                    this.visible=this.dictSelectorVisible;
                }
            }
    },
    computed:{
            listNameArray: function () {

                const newArray=[];
                this.list.forEach((item,index)=>{newArray.push(item.text)});
                return newArray;
            }
    },
    beforeMount:async function(){
        const result =await dictService.findDictItemList({entryCode:'BANK'});

        this.list=result;
        console.log(result);
    },
    methods:{
        handleCancel(){//点击取消
            this.$emit('onSelectorCancel')
        },
        handleSubmit(e){//点击确定
            this.$emit('onSelectorSubmit',e)
        }
    }

}
</script>

<style>

</style>
