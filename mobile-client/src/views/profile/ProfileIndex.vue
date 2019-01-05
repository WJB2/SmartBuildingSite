<template>
    <div class="profile">

         <van-nav-bar
            title="智慧工地"
            left-text=""
            right-text="注销"
            left-arrow
            class="navbar"
            @click-right="onClickRight"
        />

        <div class="header">
            <div class="protrait iconfont icon-touxiang"></div>
            <div class="name">{{name}}</div>
        </div>

        <div class="rest"></div>

        <common-footer 
            :activeIndex="activeIndex"
            v-on:onChange="handleChangeTabBar"
        />

    </div>
</template>

<script>
import CommonFooter from './../../components/common/foot/Footer.vue';
import {mapState} from 'vuex';

export default{
    data(){
        return{
            activeIndex:2,
            
        }
    },
    components:{
        CommonFooter
    },
    computed:{
        ...mapState({
            currentUser:state=>state.currentUser,
        }),
        name:function(){
            return localStorage.getItem('USERNAME');
        }
    },
    methods:{
        handleChangeTabBar(e){
            console.log(e)
    
            if(e===0){
                this.$router.push({push:'/employee'});
            }else if(e===1){
                this.$router.push({path:'/attenceDevice'});
            }
        },
        onClickRight(){
            this.$dialog.confirm({
                message:'是否注销当前用户'
            }).then((e)=>{//确认
                this.$store.dispatch('authentication/logout',{})
                this.$router.push({path:'/'})
            }).catch((e)=>{//取消
                console.log(e)
            })
        }
    }
}
</script>

<style lang="scss" scoped>
@import "./../../style/common.scss";
.profile{

    .navbar{
        background-color:$navBarColor;
    }

    .header{

        width:100%;
        height:500px;
        background-color:skyblue;
        display:flex;
        justify-content:center;
        align-items:center;
        flex-direction:column;

        .protrait{

            font-size:200px;
            color:$navBarColor;

        }

    }

    .rest{
        height:700px;
        width:100%;
        background-color:skyblue;
    }
}

</style>
