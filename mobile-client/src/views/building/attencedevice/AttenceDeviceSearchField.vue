<template>
    <div class="cover" @click="handleCancelSearch" v-if="searchFieldVisible">
        <div class="resetField" @click.stop="handleClickField">
        <van-cell-group>
            <div @click="handleBuildingSiteSelect">
            <van-field 
                v-model="filter.buildingSiteName" 
                label="所属工地"
                disabled 
                is-link
            />
            </div>
            <van-field v-model="filter.attenceDeviceText" label="人员关键字"></van-field>
            
        </van-cell-group>
        <div class="button">
            <div class="submitButton" @click="handleSearch">查询</div>
            <div class="resetButton" @click="handleReset">重置</div>
        </div>
        </div>

        <building-site-selector 
            :buildingSiteSelectorVisible="buildingSiteSelectorVisible"
            v-on:onSelectorCancel="handleSelectorCancel"
            v-on:onSelectorSubmit="handleSelectorSubmit"
        />

    </div>
</template>

<script>
import BuildingSiteSelector from './../../../components/building/form/BuildingSiteSelector.vue';
export default {
    props:['searchFieldVisible'],
    data(){
        return{
            filter:{
                buildingSiteId:'',
                attenceDeviceText:'',
                buildingSiteName:'',
            },
            buildingSiteSelectorVisible:false,
        }
    },
    computed:{
        
    },
    components:{
        BuildingSiteSelector,
    },
    methods:{
        handleBuildingSiteSelect(){//点击选择所属工地
            this.buildingSiteSelectorVisible=true;

        },
        handleSelectorCancel(){//点击取消取消选择
            setTimeout(() => {
                this.buildingSiteSelectorVisible = false; // picker蒙层
            });
        },
        handleSelectorSubmit(id,e){
            setTimeout(() => {

                this.filter.buildingSiteName=e;
                this.filter.buildingSiteId=id;
                this.buildingSiteSelectorVisible = false; // picker蒙层
            });
        },
        handleCancelSearch(){
            if (!this.buildingSiteSelectorVisible) {
                this.$emit('onClickCover'); // 这里提交到哪里
                this.filter.buildingSiteName = "";
                this.filter.attenceDeviceText = "";
            }
        },
        handleClickField(){
  
        },
        handleSearch(){
            
            this.$emit(
                "onSearch",
                this.filter.buildingSiteId || this.filter.attenceDeviceText
            ? {
                buildingSiteId: this.filter.buildingSiteId,
                employeeText: this.filter.attenceDeviceText
                }
            : {}
            );
            this.filter.buildingSiteName = "";
            this.filter.attenceDeviceText = "";
        },
        handleReset(){
            this.filter.buildingSiteName='';
            this.filter.attenceDeviceText='';
        }
    }
}
</script>

<style lang="scss" scoped>
@import "./../../../style/common.scss";
.cover{
    position:fixed;
    width:800px;
    background-color:rgba(0,0,0,.5);
    z-index:12;
    height:1400px;
    top: 94px;

    .resetField{
        z-index:13;
        width:500px;
        height:300px;
        border-radius:10px;
        background-color:white;
        margin-top:300px;
        margin-left:130px;
        padding:10px;
        .button{

            height:100px;
            width:100%;
            display:flex;
            justify-content:space-around;
            align-items:center;
            color:$white;

            .submitButton{
                width:120px;
                line-height:70px;
                height:70px;
                text-align:center;
                background-color:$navBarColor;
                border-radius:10px;
            }

            .resetButton{
                width:120px;
                line-height:70px;
                text-align:center;
                height:70px;
                background-color:$resetButtonColor;
                border-radius:10px;
            }
        }

        

    }
}
</style>
