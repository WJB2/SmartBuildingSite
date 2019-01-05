<template>
    <div class="login">
        <van-row>
            <van-col span="24" class="headerImg">
                <img :src='bannerUrl' alt="" width="100%">
            </van-col>
        </van-row>
        <div style="margin-top:20px">
        <van-row type="flex" justify="center">
            <van-col span="20">
                <van-cell-group>
                    <van-field 
                        v-model="username"
                        required 
                        clearable
                        label="用户名"
                        icon="question"
                        placeholder="请输入用户名"
                        />

                    <van-field 
                        v-model="password"
                        type="password"
                        label="密码"
                        placeholder="请输入密码"
                        required 
                        />    
                </van-cell-group>
            </van-col>
        </van-row>
        </div>
        <div style="margin-top:80px">
        <van-row type="flex" justify="center">
            <van-col span="18">
                <van-button type="primary" size="large" @click="handleLogin">确定</van-button>
            </van-col>
        </van-row>
        </div>
         
    </div>
</template>

<script>
    // import banner from '../../assets/banner.jpg';
    // import loginStore from '../../store/modules/login';
    
    import { mapState } from 'vuex';
    export default{
        data(){
            return{
                bannerUrl:require("../../assets/banner.jpg"),
                password:'',
                username:'',
            }
        },
        computed:{
            ...mapState({
                errorText:state=>state.errorText,
            })
        },
        methods:{
            handleLogin(){
                if(!this.username || !this.password){
                    this.$toast({
                        message:'请将信息填写完整'
                    });
                }else{
                    this.$store.dispatch('authentication/login',{
                        password:this.password,
                        username:this.username,
                    });
                }   
            }
        }
    }
</script>

<style>
    .login{
        width:100%;
        height:100%;
        z-index:2;
        position:absolute;
        top:0;
        bottom:0;

    }
    .headerImg{
        width:100%;
        background-repeat:no-repeat;
        background-size:100%;
         
    }
</style>