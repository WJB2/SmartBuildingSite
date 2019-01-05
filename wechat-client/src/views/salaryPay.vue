<template>
    <div class="salaryPay">

        <div class="selfMessage">
            <div class="wrapperIcon"><div class="iconfont">&#xe63e;</div></div>
            <div class="name">{{name}}</div>
            <div class="bankCardNo">{{bankNo}}</div>
        </div>
         
        <div class="center">
            <div class="left iconfont" @click="handleBefore">&#xe622;上一月</div>
            <div class="centera">{{tempTime}}</div>
            <div class="right iconfont" @click="handleAfter">下一月&#xe60d;</div>
        </div>

        <div class="paytabs">
        <van-tabs class="tabs">
            <van-tab title="工资发放情况" class="tabsfirst">
                <van-button plain type="primary" size="large" class="button">已发金额</van-button>
                <div class="salary">{{salary?salary:0}}</div>
            </van-tab>
            <van-tab title="考勤记录情况" class="tabssecond">
                <div class="wrapper">
                <div class="kaoqin">
                    <div class="tableheader hover" >
                        <div class="dateaa">日期</div>
                        <div class="uprecord">上班考勤记录</div>
                        <div class="downrecord">下班考勤记录</div>
                    </div>
                    <div class="tableheader"  v-for="items in dataArray">
                        <div class="dateaa">{{items.date}}</div>
                        <div class="uprecord">{{items.uprecord}}</div>
                        <div class="downrecord">{{items.downrecord}}</div>
                    </div>
                </div>
                </div>
            </van-tab>
        </van-tabs>
        </div>

        <div class="tabbar" >
            <van-tabbar v-model="active" @change="handleChangeTabBar">
                <van-tabbar-item @click="handleActionSheetShow">
                    <span  style="color:#1989fa">切换工地</span>
                    <div slot="icon" style="color:#1989fa" class="iconfont">&#xe698;</div>
                </van-tabbar-item>
                <van-tabbar-item >
                    <span>当前工地:{{buildingSiteName}}</span>
                    <div slot="icon" class="iconfont">&#xee04;</div>
                </van-tabbar-item>
            </van-tabbar>
        </div>

        <van-dialog
            v-model="actionShow"
            confirm-button-text="取消"
        >
            <template v-for="buildingSite in buildSiteArray">
                <div class="dialog" @click="handleSelectBuildingSite(buildingSite.name)">{{buildingSite.name}}</div>
            </template>
        </van-dialog>

    </div>
</template>

<script>
import axios from 'axios';
import moment from 'moment';
import getRecordEnum from './../enum/getRecordEnum';
export default {
    data(){
        return{
            salary:'',//薪水
            tempTime:moment(new Date()).format('YYYY年MM月'),
            name:'',
            idCardNo:'',
            bankNo:'',
            attenceRecords:[],
            checkTimeArray:[],
            active:0,
            buildSiteArray:[],
            actionShow:false,
            buildSiteNameArray:[],
            confirmVisible:false,
            buildingSiteName:'',//当前工地
        }
    },
    computed:{
        dataArray:function(){
            let data=[];
            for(var i=1;i<31;i++){
                    data.push({
                        date:this.tempTime+`${(i<10)?('0'+i):i}日`,
                        uprecord:this.attenceRecords.some((item)=>{return this.tempTime+`${(i<10)?('0'+i):i}日`===item.checkDate})?
                            this.attenceRecords.map((item)=>{
                                let status;
                                if(this.tempTime+`${(i<10)?('0'+i):i}日`===item.checkDate){
                                    status=getRecordEnum.getRecordText(item.checkInStatus)
                                }
                                return status;
                            }).find(item=>{return item})
                            :moment(this.tempTime+`${(i<10)?('0'+i):i}日`,'YYYY年MM月DD日').format('YYYYMMDD')>moment(new Date()).format('YYYYMMDD')?'无':'未打卡',
                        downrecord:this.attenceRecords.some((item)=>{return this.tempTime+`${(i<10)?('0'+i):i}日`===item.checkDate})?
                            this.attenceRecords.map((item)=>{
                                let status;
                                if(this.tempTime+`${(i<10)?('0'+i):i}日`===item.checkDate){
                                    status=getRecordEnum.getRecordText(item.checkOutStatus)
                                }
                                return status;
                            }).find(item=>{return item})
                            :moment(this.tempTime+`${(i<10)?('0'+i):i}日`,'YYYY年MM月DD日').format('YYYYMMDD')>moment(new Date()).format('YYYYMMDD')?'无':'未打卡',
                    })
            };
            return data;
        }
    },
    created:function(){
        axios.get(`/api/wechat/employee/report/employee-attence-record-and-salary?beginDate=${moment(new Date()).startOf('month').format('YYYY-MM-DD HH:mm:ss.SSS')}&endDate=${moment(new Date()).endOf('month').format('YYYY-MM-DD HH:mm:ss.SSS')}`)
            .then((res)=>{
                if(res.status===200){
                    this.name=res.data["employee"].name;
                    this.bankNo=res.data["employee"].bankNo;

                    console.log("1");
                    res.data["reports"].map((item,index)=>{//工地数组
                        this.buildSiteArray.push({name:item.buildingSite.name,salary:item.salary,attenceRecords:item.attenceRecords});
                    });

                    this.buildingSiteName=this.buildSiteArray[0].name;
                    this.salary=this.buildSiteArray[0].salary;//第一个工地的薪水
                    this.attenceRecords=this.buildSiteArray[0].attenceRecords?this.buildSiteArray[0].attenceRecords:[];//第一个工地的记录
                    this.attenceRecords.map((item,index)=>{
                        item.checkDate=item.checkDate.substr(0,item.checkDate.length-9).substr(0,4)+"年"+item.checkDate.substr(0,item.checkDate.length-9).substr(4,2)+"月"+item.checkDate.substr(0,item.checkDate.length-9).substr(6,7)+"日";
                        console.log(item.checkDate)
                    });
                }
            })
            .catch((err)=>{console.log(err)})
    },
    mounted:function(){

    },
    methods:{
        handleBefore(e){
            this.tempTime=moment(moment(this.tempTime,'YYYY年MM月').add(-1, "months")).format('YYYY年MM月')
            axios.get(`/api/wechat/employee/report/employee-attence-record-and-salary?beginDate=${moment(this.tempTime,'YYYY年MM月').startOf('month').format('YYYY-MM-DD HH:mm:ss.SSS')}&endDate=${moment(this.tempTime,'YYYY年MM月').endOf('month').format('YYYY-MM-DD HH:mm:ss.SSS')}`)
                .then((res)=>{
                    if(res.status===200){
                        console.log(res);
                        this.buildSiteArray=[];
                        res.data["reports"].map((item,index)=>{//工地数组
                            this.buildSiteArray.push({name:item.buildingSite.name,salary:item.salary,attenceRecords:item.attenceRecords});
                        });
                        console.log(this.buildSiteArray);
                        this.salary=this.buildSiteArray.find(item => item.name === this.buildingSiteName).salary;//第一个工地的薪水
                        this.attenceRecords=this.buildSiteArray.find(item => item.name === this.buildingSiteName).attenceRecords?this.buildSiteArray.find(item => item.name === this.buildingSiteName).attenceRecords:[];//第一个工地的记录
                        console.log(this.attenceRecords);
                        this.attenceRecords.map((item,index)=>{
                            item.checkDate=item.checkDate.substr(0,item.checkDate.length-9).substr(0,4)+"年"+item.checkDate.substr(0,item.checkDate.length-9).substr(4,2)+"月"+item.checkDate.substr(0,item.checkDate.length-9).substr(6,7)+"日";
                        });
                    }
                })
                .catch((err)=>{console.log('我出错了');console.log(err)})
        },
        handleAfter(e){
            this.tempTime=moment(moment(this.tempTime,'YYYY年MM月').add(1, "months")).format('YYYY年MM月')
            axios.get(`/api/wechat/employee/report/employee-attence-record-and-salary?beginDate=${moment(this.tempTime,'YYYY年MM月').startOf('month').format('YYYY-MM-DD HH:mm:ss.SSS')}&endDate=${moment(this.tempTime,'YYYY年MM月').endOf('month').format('YYYY-MM-DD HH:mm:ss.SSS')}`)
                .then((res)=>{
                    if(res.status===200){
                        this.buildSiteArray=[];
                        res.data["reports"].map((item,index)=>{//工地数组
                            this.buildSiteArray.push({name:item.buildingSite.name,salary:item.salary,attenceRecords:item.attenceRecords});
                        });
                        this.salary=this.buildSiteArray.find(item => item.name === this.buildingSiteName).salary;//第一个工地的薪水
                        this.attenceRecords=this.buildSiteArray.find(item => item.name === this.buildingSiteName).attenceRecords?this.buildSiteArray.find(item => item.name === this.buildingSiteName).attenceRecords:[];//第一个工地的记录
                        this.attenceRecords.map((item,index)=>{
                            item.checkDate=item.checkDate.substr(0,item.checkDate.length-9).substr(0,4)+"年"+item.checkDate.substr(0,item.checkDate.length-9).substr(4,2)+"月"+item.checkDate.substr(0,item.checkDate.length-9).substr(6,7)+"日";
                        });
                    }
                })
                .catch((err)=>{console.log(err)})
        },
        handleActionSheetShow(){
            this.actionShow=true;
        },
        handleSelectBuildingSite(value){
            console.log(value);
            this.actionShow=false;
            this.buildingSiteName=value;
            this.salary=this.buildSiteArray.find(items=>items.name === value).salary;
            this.attenceRecords=this.buildSiteArray.find(items=>items.name === value).attenceRecords?this.buildSiteArray.find(items=>items.name === value).attenceRecords:[];//第一个工地的记录
            console.log(this.attenceRecords);
            // this.attenceRecords.map((item,index)=>{
            //     item.checkDate=item.checkDate.substr(0,item.checkDate.length-9).substr(0,4)+"年"+item.checkDate.substr(0,item.checkDate.length-9).substr(4,2)+"月"+item.checkDate.substr(0,item.checkDate.length-9).substr(6,7)+"日";
            // });
        },
        handleChangeTabBar(active){
            console.log(active);
            if (active === 1){
                return;
            }
        }
    }
}
</script>

<style lang="scss">
@import "./../styles/common.scss";
.salaryPay{

    width:100%;
    height:100%;
    display:flex;
    flex-direction:column;

    .van-nav-bar .van-icon{
        color:#fff;
    }

    .van-nav-bar__text{
        color:#fff;
    }

    .s_navbar{

        background-color: $commonBlue;
        color:#fff;
        flex:0 0 pxToRem(50px);
        
    }

    .selfMessage{
        display:flex;
        flex-direction:column;
        justify-content:center;
        align-items:center;
        flex:0 0 pxToRem(200px);

        .wrapperIcon{

            width:pxToRem(100px);
            height:pxToRem(100px);
            background-color:$commonBlue;
            box-shadow:1px 1px 1px 1px #ddd;
            border-radius:10px;
            text-align:center;

            .iconfont{
                font-size:pxToRem(80px);
                color:#fff;
            }

        }

        .name{
            margin-top:pxToRem(20px);
            height:pxToRem(15px);
            font-size:pxToRem(15px);
            font-weight:600;
        }

        .bankCardNo{
            margin-top:pxToRem(20px);
            font-size:pxToRem(15px);
            height:pxToRem(15px);
            font-weight:600;
        }

    }

    .center{

        margin-top:pxToRem(30px);
        width:100%;
        height:pxToRem(60px);
        background-color:$commonBlue;
        align-items:center;
        display:flex;
        flex:0 0 pxToRem(60px);

        .left{
            font-size:pxToRem(20px);
            flex-basis:pxToRem(80px);
            flex-grow:0;
            flex-shrink:0;
            color:#fff;
        }

        .centera{
            flex:1;
            font-size:pxToRem(25px);
            text-align:center;
            color:#fff;
        }

        .right{
            font-size:pxToRem(20px);
            flex-basis:pxToRem(80px);
            flex-grow:0;
            flex-shrink:0;
            color:#fff;
        }

    }

    .paytabs{

        flex:1;
        padding-top:pxToRem(10px);
        margin-bottom:pxToRem(60px);;
        box-sizing:border-box;

        .tabs{}

        .tabsfirst{

            width:100%;

            .button{

                background-color:#86D54F;
                color:#fff;
                border:1px solid #86D54F;
                width:70%;
                margin-left:15%;
                border-radius:10px;
                box-shadow:1px 1px 1px 1px #86D54F;

            }

            .salary{
                width:100%;
                text-align:center;
                font-size:pxToRem(80px);
            }

        }

        .tabssecond{

            height:pxToRem(415px)!important;
            width:100%;

            .wrapper{
                display:flex;
                height:pxToRem(415px)!important;
                width:100%;
            }

            .kaoqin{

            width:100%;
            overflow:auto;

            .hover{
                background-color:grey!important;
                box-shadow:1px 1px 1px 1px grey!important;
            }

            .tableheader{
                margin-top:pxToRem(10px);
                display:flex;
                width:100%;
                height:pxToRem(50px);
                font-size:pxToRem(20px);
                background-color:#fff;
                align-items:center;
                justify-content: center;
                text-align:center;
                box-shadow:1px 1px 1px 1px #fff;
                .dateaa{
                    flex:0 0 pxToRem(160px);
                }
                .uprecord{
                    flex:0 0 pxToRem(200px);
                }
                .downrecord{
                    flex:1;
                }
            }
        }
        }



    }
    .dialog{
        height:pxToRem(80px);
        line-height:pxToRem(80px);
        text-align: center;
        background-color:#fff;
    }
    .tabbar{
        .van-tabbar-item--active{
            color:#000;
        }
    }

}
</style>