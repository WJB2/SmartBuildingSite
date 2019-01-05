<template>
    <div class="initial">
        <img :src="imgUrl" alt="loading"/>
        <div class="loading">系统加载中...</div>
    </div>
</template>

<script>

export default {
    data(){
        return{
            imgUrl:require('./../../assets/loading.gif'),
        }
    },
    created:async function(){
        await this.$store.dispatch('initialize');

        await this.$router.push({path:'/employee'})
    },
     
}
</script>

<style>
    .initial{
        display:flex;
        align-items:center;
        justify-content: center;
        width:100%;
        height:100%;
        position:absolute;
        top:0;
        z-index: 10;
        background-color:white;
        flex-direction: column;
    }
    .loading{
        text-align:center;
        font-size:0.4rem;
    }
</style>
