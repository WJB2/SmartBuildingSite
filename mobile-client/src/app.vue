<template>
    <div id="app">
        <main-layout />
    </div>
</template>

<script>

    import MainLayout from './views/main/MainLayout.vue';

    import {mapState} from 'vuex';
    
    export default {
        components:{
            MainLayout,
        },
        name:'app',
        data(){
            return{}
        },
        computed:{
            ...mapState({
            })
        },
        methods:{
            
        }
    
    }
</script>

<style lang="scss">
     
</style>