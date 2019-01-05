<template>
<van-row type="flex" justify="center" class="list">
    <van-col span="20">
        <div @click="handleAttenceDeviceEdit(attenceDevice.id)">
            <van-panel class="panel">
                <div slot="header" class="header">
                    <div class="developbuilding">{{attenceDevice.buildingDeveloper.name}}</div>
                    <div class="buildingsite">{{attenceDevice.buildingSite.name}}</div>
                </div>

                <div class="content">
                    <div class="name">{{attenceDevice.type}}</div>
                    <div class="status" :style="{'color':attenceDevice.onlineFlag?'green':'red'}">
                        <div class="dot" :style="{'backgroundColor':attenceDevice.onlineFlag?'green':'red'}"></div>
                        <div class="statusname">{{attenceDevice.onlineFlag?'离线':'离线'}}</div>
                    </div>
                </div>

                <div slot=footer class="footer">
                    <div class="time">
                        {{attenceDevice.sn}}
                    </div>
                </div>

            </van-panel>
        </div>
    </van-col>
</van-row>
</template>

<script>
export default {
    props:{
        attenceDevice:Object,
    },
    data(){
        return{

        }
    },
    created:function(){
  
    },
    methods:{
        handleAttenceDeviceEdit(id){
            this.$emit('onAttenceDeviceEdit',id);
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

            .van-panel__footer{
                padding-right:15px;
            }

            .time{
                margin-right:40px;
                font-size:20px;
                text-align:right;
                white-space: nowrap;
            }
        }
    }
</style>