<template>
    <div class="circle" @click="handleAdd">
        <div class="plus"></div>
    </div>    
</template>

<script>
export default {
    data(){
        return{

        }
    },
    props:['onHandleEmployee','onHandleAttenceDevice','unique'],
    methods:{
        handleAdd(){
            if(this.unique==="employee"){
                this.onHandleEmployee();
            }else if(this.unique==="attenceDevice"){
                this.onHandleAttenceDevice();
            }
        }
    }
}
</script>

<style lang="scss" scoped>
@import './../../style/common.scss';

.circle{

    width:100px;
    height:100px;
    border-radius:50%;
    background-color:$navBarColor;
    position:fixed;
    display:flex;
    justify-content:center;
    align-items:center;
    bottom:150px;
    right:80px;
    z-index:10;
    box-shadow: $navBarColor 0px 0px 15px;

    .plus{

        background-color:$white;
        height:60px;
        position:absolute;
        top:22px;
        width:5px;


    }

    .plus:after{
        background:$white;
        content:"";
        height:60px;
        left:1px;
        position:absolute;
        width:5px;
        transform:rotateZ(90deg)
    }

}
</style>
