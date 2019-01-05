<template>
    <div class="bindSite">
        <div class="cellgroup"> 
        <van-cell-group>
            <van-field v-model="bildingSite.name" required label="姓名" placeholder="请输入用户名" />
            <van-field v-model="bildingSite.idCard" required label="身份证号" placeholder="请输入身份证号" />
            <van-field v-model="bildingSite.bankCardNo" required label="银行卡号" placeholder="请输入银行卡号" />
        </van-cell-group>
        </div>
        <van-button plain type="primary" size="large" class="button" @click="handleBindingBankNo">绑定账号</van-button>
    </div>
</template>

<script >


import axios from 'axios';

export default {
    data(){
        return{
            bildingSite:{
                name:'',
                idCard:'',
                bankCardNo:'',
            }
        }
    },
    methods:{
        handleBindingBankNo(){

            if(!/^[\u4e00-\u9fa5]{2,4}$/.test(this.bildingSite.name)){
                this.$notify("您输入的姓名有误");
            }else if(!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x))$/.test(this.bildingSite.idCard)){
                this.$notify("您输入的身份证号有误");
            }else if(!this.bildingSite.bankCardNo){
                this.$notify("银行卡号不能为空");
            }else{
                axios({
                    method:'POST',
                    url:'/api/wechat/mp/register',
                    data:{
                        name:this.bildingSite.name,
                        idCardNo:this.bildingSite.idCard,
                        bankNo:this.bildingSite.bankCardNo,
                    }
                })
                    .then((res)=>{
                        if(res.status===200){
                            this.$router.replace({name:'salaryPay',params:{name:this.bildingSite.name,bankNo:this.bildingSite.bankCardNo}})
                        }
                    })
                    .catch(e=>{console.log(e);this.$toast.fail(e.response.data.errorText)})
            }

        }
    }
}
</script>

<style lang="scss">
@import "./../styles/common.scss";
.bindSite{

    width:100%;
    height:100%;

    .navbar{
        background-color: $commonBlue;
        color:#fff;
    }

    .cellgroup{
        width:80%;
        margin:pxToRem(30px) auto;
        border:1px solid $commonBlue;
        border-radius:10px;
        padding:pxToRem(20px);
        box-shadow:1px 1px 1px 1px $commonBlue;
    }

    .button{
        background-color: $commonBlue;
        color:#fff;
        border:1px solid $commonBlue;
        width:80%;
        margin-left:10%;
        border-radius:10px;
        
    }

}
</style>