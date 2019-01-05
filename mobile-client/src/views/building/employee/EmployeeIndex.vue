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

        <employee-menu 
            v-on:onClickIcon="handleClickIcon"
            class="menu"
        />

        <employee-search-field 
            :searchFieldVisible="searchFieldVisible"
            v-on:onClickCover="handleClickCover"
            v-on:onSearch="handleEmployeeSearch"
        />

        <van-list class="list">
            <employee-list v-for="(item,index) in list" 
            :key="index" :bank="item.bank" :buildingSite="item.buildingSite.name"
            :name="item.name" :registerTime="item.registerTime" :unregisterTime="item.unregisterTime"
            :id="item.id" v-on:onEmployeeEdit="handleEmployeeEdit"/>
        </van-list>
         
        <CircleButton :onHandleEmployee="handleEmployeeAdd" :unique="employee" />

       </div>
        
        <common-footer 
            :activeIndex="activeIndex"
            v-on:onChange="handleChangeTabBar"
        />
        
        <div v-if="formVisible.formVisible">
        <van-popup v-model="formVisible.formVisible">
            <employee-form 
                :currentEmployee="currentEmployee"
                :formType="formType"
                v-on:onFormCancel="handleFormCancel" 
                v-on:onFormSubmit="handleFormSubmit"
                v-on:onFormDelete="handleFormDelete">
            </employee-form>
        </van-popup>
        </div>
    </div>
</template>

<script>
import EmployeeList from './EmployeeList.vue';
import EmployeeForm from './EmployeeForm.vue';
import EmployeeMenu from './EmployeeMenu.vue';
import EmployeeSearchField from './EmployeeSearchField.vue';
import CommonFooter from './../../../components/common/foot/Footer.vue';

import { mapState } from 'vuex';

export default {
    components:{
        EmployeeList,
        EmployeeForm,
        EmployeeMenu,
        EmployeeSearchField,
        CommonFooter
    },
    data(){
        return{
            activeIndex:0,
            iconfontStyle:{fontSize:'20px'},
            test:'测试',
            employee:"employee",
            searchFieldVisible:false,
            fixed:true,
        }
    },
    computed:mapState({
        list:state=>state.employee.list,
        formVisible:state=>state.employee,
        currentEmployee:state=>state.employee.currentEmployee,
        formType:state=>state.employee.formType,
    }),
    mounted(){
        console.log('emplyee加载了')
        this.$store.dispatch('employee/queryEmployeePageAsync',{})
    },
    methods:{
        handleEmployeeAdd(){//点击增加按钮
            this.$store.commit('employee/updateState',{
                formVisible:true,
                formType:'ADD',
            })
        },
        handleFormCancel(e){//点击表单取消父
            this.$store.commit('employee/updateState',{
                formVisible:false,
                formType:null,
                currentEmployee:null,
            })
        },
        handleFormSubmit(e){//点击了表单确定父
            console.log(e);
            if(this.formType==='ADD'){
                this.$store.dispatch('employee/addEmployeeAsync',e);
            }else if(this.formType==='EDIT'){
                this.$store.dispatch('employee/editEmployeeAsync',e)
            }
        },
        handleFormDelete(e){//点击了表单的删除
            this.$store.dispatch('employee/deleteEmployeeByIdAsync',e)
        },
        handleEmployeeEdit(id){//点击其中之一的表单
            this.$store.dispatch('employee/editEmployeeAction',{id:id});
        },
        handleClickIcon(){//点击图标
            if(this.searchFieldVisible===false){
                this.searchFieldVisible=true;
            }else if(this.searchFieldVisible===true){
                this.searchFieldVisible=false;
            }
        },
        handleClickCover(){//点击蒙层
            this.searchFieldVisible=false;
        },
        handleEmployeeSearch(e){//点击查询
            this.$store.dispatch('employee/queryEmployeePageAsync',e);
            this.searchFieldVisible=false;
        },
        handleChangeTabBar(e){
            console.log(e);
            if(e===1){
                this.$router.push({path:'/attenceDevice'})
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
