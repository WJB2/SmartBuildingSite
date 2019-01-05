<template>
<van-row  class="list" type="flex" justify="center">
    <van-col span="20">
        <div  @click="handleEmployeeEdit(id)">
        <van-panel class="panel">
            <div slot="header" class="header">
                <div class="developbuilding">{{bank}}</div>
                <div class="buildingsite">{{buildingSite}}</div>
            </div>
            <div class="content">
                <div class="name">{{name}}</div>
                <div class="status" :style="{'color':unregisterTime?'red':'green'}">
                    <div class="dot" :style="{'backgroundColor':unregisterTime?'red':'green'}"></div>
                    <div class="statusname">{{unregisterTime?'离职':'在职'}}</div>
                </div>
            </div>
            <div slot=footer class="footer">
                <div class="time" :style="{'color':unregisterTime?'red':'green'}">
                    {{unregisterTime?`${moment(unregisterTime, 'YYYYMMDDHHmmssSSS').format('YYYY.MM.DD')}离职`:`${this.$moment(registerTime, 'YYYYMMDDHHmmssSSS').format('YYYY.MM.DD')}入职`}}
                </div>
            </div>
        </van-panel>
        </div>
    </van-col>
</van-row>
</template>

<script>

export default{
    props:{
        list:Array,
        bank:String,
        buildingSite:String,
        name:String,
        registerTime:String,
        unregisterTime:String,
        id:String,
    },
    data(){
        return{

        }
    },
    mounted:function(){
     
    },
    methods:{
        handleEmployeeEdit(id){
            this.$emit('onEmployeeEdit',id);
        }
    }
}
</script>

<style lang="scss" scoped>
@import "./../../../style/common.scss";
    .list{
        margin:20px 0px;
    }
    .panel{
        border:1px solid $panelBorderColor;
        border-radius:5px;
        box-shadow:1px 2px 2px #ddd;

        .header{
            width:100%;
            display:flex;
            height:80px;
            font-size:35px;
            align-items:center;
            color:black;
            text-shadow:5px 5px 5px #ddd;
            padding:0px 20px 0px 20px;

            .developbuilding{
                width:250px;
                flex-shrink:0;
                flex-grow:0;
            }

            .buildingsite{
                flex:1;
            }

        }

        .content{

            display:flex;
            width:100%;
            align-items:center;
            height:80px;
            margin-left:20px;

            .name{
                color:$navBarColor;
                font-size:50px;
                text-shadow:2px 2px 1px $navBarColor;
                flex-shrink:0;
                flex-grow:0;
                width:200px;
            }

            .status{
                flex:1;
                font-size:40px;
                display:flex;
                align-items:center;
                justify-content:center;

                .dot{
                    width:20px;
                    height:20px;
                    border-radius:50px;
                }

                .statusname{
                    font-size:30px;
                    margin-left:20px;
                }
            }
        }

        .footer{
            width:100%;
            height:30px;

            .time{
                margin-right:40px;
                font-size:20px;
                text-align:right;
            }
        }
    }
    
</style>