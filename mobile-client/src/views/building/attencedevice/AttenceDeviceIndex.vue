<template>
    <div class="index">

        <van-nav-bar
            title="智慧工地"
            left-text=""
            right-text=""
            left-arrow
            class="navbar"
            fixed="fixed"
        >
        </van-nav-bar>

        <div class="container">

        <attence-device-menu class="menu"
             v-on:onClickIcon="handleClickIcon"
        />

        <attence-device-search-field 
            :searchFieldVisible="searchFieldVisible"
            v-on:onClickCover="handleClickCover"
            v-on:onSearch="handleAttenceDeviceSearch"
        />

        <van-list class="list">
            <attence-device-list 
                v-for="(item,index) in list" 
                :key="index" 
                :attenceDevice="item"
                v-on:onAttenceDeviceEdit="handleAttenceDeviceEdit" 
            />
        </van-list>

        <CircleButton :onHandleAttenceDevice="handleAttenceDeviceAdd" :unique="attenceDevice"/>

        </div>

        <common-footer 
            :activeIndex="activeIndex"
            v-on:onChange="handleChangeTabBar" 
        />
    

        <div v-if="formVisible.formVisible">
        <van-popup v-model="formVisible.formVisible">
            <attence-device-form 
                :currentAttenceDevice="currentAttenceDevice"
                :formType="formType"
                v-on:onFormCancel="handleFormCancel"
                v-on:onFormSubmit="handleFormSubmit"
                v-on:onFormDelete="handleFormDelete"
            />
        </van-popup>
        </div>
        

    </div>
</template>

<script>

import AttenceDeviceMenu from './AttenceDeviceMenu.vue';
import AttenceDeviceList from './AttenceDeviceList.vue';
import AttenceDeviceForm from './AttenceDeviceForm.vue';
import AttenceDeviceSearchField from './AttenceDeviceSearchField.vue';
import CommonFooter from './../../../components/common/foot/Footer.vue';

import {mapState} from 'vuex';

export default {
    components:{
        AttenceDeviceMenu,
        AttenceDeviceList,
        AttenceDeviceForm,
        AttenceDeviceSearchField,
        CommonFooter
    },
    data(){
        return{
            attenceDevice:"attenceDevice",
            searchFieldVisible:false,
            activeIndex:1,
            fixed:true,
        }
    },
    computed:mapState({
        list:state=>state.attenceDevice.list,
        formVisible:state=>state.attenceDevice,
        formType:state=>state.attenceDevice.formType,
        currentAttenceDevice:state=>state.attenceDevice.currentAttenceDevice,
    }),
    created:function(){
        this.$store.dispatch('attenceDevice/queryAttenceDevicePageAsync',{})
    },
    methods:{
        handleAttenceDeviceAdd(){//增加设备信息
            this.$store.commit('attenceDevice/updateState',{
                formVisible:true,
                formType:'ADD',
                currentAttenceDevice:null,
            });
        },
        handleFormCancel(){//点击表单的取消
            this.$store.commit('attenceDevice/updateState',{
                formVisible:false,
                formType:undefined,
                currentAttenceDevice:null,
            })
        },
        handleFormSubmit(e){//点击表单的确定
            if(this.formType==='ADD'){
                this.$store.dispatch('attenceDevice/addAttenceDeviceAsync',e);
            }else if(this.formType==='EDIT'){
                this.$store.dispatch('attenceDevice/editAttenceDeviceAsync',e)
            }
        },
        handleAttenceDeviceEdit(id){//点击其中之一的表单
            this.$store.dispatch('attenceDevice/editAttenceDeviceAction',{id:id});
        },
        handleFormDelete(e){
            this.$store.dispatch('attenceDevice/deleteAttenceDeviceByIdAsync',e)
        },
        handleClickCover(){//点击蒙层
            this.searchFieldVisible=false;
        },
        handleAttenceDeviceSearch(e){
            console.log(e)
            this.$store.dispatch('attenceDevice/queryAttenceDevicePageAsync',e);
            this.searchFieldVisible=false;
        },
        handleClickIcon(){//点击图标
            if(this.searchFieldVisible===false){
                this.searchFieldVisible=true;
            }else if(this.searchFieldVisible===true){
                this.searchFieldVisible=false;
            }
        },
        handleChangeTabBar(e){
            console.log(e);
            if(e===0){
                this.$router.push({path:'/employee'})
            }else if(e===2){
                this.$router.push({path:'/profile'})
            }
         
          
        }
        
    }
}
</script>

<style lang="scss" scoped>
@import "./../../../style/common.scss";
.index{
    
    display:flex;
    flex-direction:column;
    width:100%;
    height:100%;

    .van-popup{
    
        width:100%;
        height:100%;
        background-color:transparent !important;
    }

    .navbar{
        background-color: $navBarColor;
    }

    .container{
        position:relative;
        display:flex;
        flex-direction:column;
        width:100%;
        margin-top:100px;
        @include wcalc(calc(100vh - 2.7rem));

        .menu{
            
            
        }

        .list{

            flex:1;
            -webkit-overflow-scrolling:touch;
            overflow:auto;
            
        }

    }

}


</style>
