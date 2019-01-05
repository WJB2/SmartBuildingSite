<template>
    <div id="app">
        <router-view></router-view>
    </div>
</template>

<script>

export default{
    data() {
        return{
            backgroundUrl:require('./../assets/background.jpg'),
        }
    },
}
</script>

<style lang="scss">
@import "./styles/main.css";
@import "./styles/main.scss";
</style>