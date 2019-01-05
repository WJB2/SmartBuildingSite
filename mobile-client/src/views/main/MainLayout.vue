<template>
    <div class="main">
        <router-view />
        <initialzing-layout v-if="authenticated && !initialized"/>
    </div>
</template>

<script>

import MainLoginLayout from './MainLoginLayout.vue';
import InitialzingLayout from './InitialzingLayout.vue';
import { mapState } from 'vuex';

export default {
    components:{
        MainLoginLayout,
        InitialzingLayout,
    },
    data(){
        return{

        }
    },
    computed:{
        ...mapState({
            authenticated:state=>state.authentication.authenticated,
            initialized:state=>state.initialized
        })
    }
}
</script>

<style lang="scss" scoped>
.main{
    width:100%;
    height:100%;
}
</style>