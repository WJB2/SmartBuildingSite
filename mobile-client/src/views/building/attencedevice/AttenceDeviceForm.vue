<template>
     <div class="formIndex">
        <div class="formHeader">{{title}}</div>
        <div class="van-hairline--bottom"></div>
        <div class="formContent">
        <van-cell-group>
            <div  @click="handleSelectBuildingSite">
                <van-field 
                    is-link
                    v-model="attenceDevice.buildingSiteName" 
                    required 
                    label="所属工地"
                    disabled
                ></van-field>
            </div>
            <van-field v-model="attenceDevice.sn" required label="设备序列号"></van-field>
            <van-field v-model="attenceDevice.type" required label="设备型号"></van-field>
            <van-field v-model="attenceDevice.remark" label="备注"></van-field>
        </van-cell-group>
        </div>
        <div class="formFooter">
             <van-button type="default" style="{color:'#fff'}" size="small" @click="handleCancelForm">取消</van-button>
            <van-button type="default" size="small" @click="handleSubmitForm" class="submitButton">确定</van-button>
            <van-button type="danger" size="small" @click="handleDeleteForm" v-if="formType==='ADD'?false:true" class="deleteButton">删除</van-button>
        </div>

        <building-site-selector 
            :buildingSiteSelectorVisible="buildingSiteSelectorVisible"
            v-on:onSelectorCancel="handleBuildingSiteSelectorCancel"
            v-on:onSelectorSubmit="handleBuildingSiteSelectorSubmit"
        />

    </div>
</template>

<script>
    import BuildingSiteSelector from './../../../components/building/form/BuildingSiteSelector.vue';
    import employeeService from './../../../api/building/BuildingSiteService';
    async function transform(id){
        const buildingSite=await employeeService.findBuildingSiteById({id:id});
        console.log(buildingSite)
        return  buildingSite.name;
    }
    export default{
        data(){
            return{
                buildingSiteSelectorVisible:false,
                attenceDevice:{buildingSiteId:'',sn:'',type:'',remark:''},
                title:'',
            }
        },
        components:{
            BuildingSiteSelector,
        },
        props:['currentAttenceDevice','formType'],
        created:async function(){
            
            let attenceDeviceCopy=this.formType==="EDIT"?{buildingSiteName:await transform(this.currentAttenceDevice.buildingSiteId),sn:this.currentAttenceDevice.sn,
                type:this.currentAttenceDevice.type,remark:this.currentAttenceDevice.remark}:{
                buildingSiteName:'',sn:'',type:'',remark:''
            };
            const title=this.formType==="ADD"?"新建设备信息":"编辑设备信息";
            this.title=title;
            this.attenceDevice=attenceDeviceCopy;
        },
        computed:{
            filterAddAttenceDevice:function(){
                delete this.attenceDevice.buildingSiteName;
                return this.attenceDevice;
            },
            filterEditAttenceDevice:function(){
                delete this.attenceDevice.buildingSiteName;
                this.attenceDevice.buildingSiteId=this.currentAttenceDevice.buildingSiteId;
                return this.attenceDevice;
            }
        },
        methods:{
            handleCancelForm(){//点击表单取消
                this.$emit('onFormCancel');
            },
            handleSubmitForm(){//点击表单提交
                this.$emit('onFormSubmit',this.formType==="ADD"?this.filterAddAttenceDevice:this.filterEditAttenceDevice);
            },
            handleSelectBuildingSite(){//选择工地
                this.buildingSiteSelectorVisible=true;
            },
            handleBuildingSiteSelectorCancel(){//点击选择工地的取消
                this.buildingSiteSelectorVisible=false;
            },
            handleBuildingSiteSelectorSubmit(id,e){//点击选择工地的确定
        
                this.attenceDevice.buildingSiteName=e;
                this.attenceDevice.buildingSiteId=id;
                this.buildingSitelinkVisible=false;
                this.buildingSiteSelectorVisible=false;
            },
            handleDeleteForm(){
                this.$emit('onFormDelete',this.currentAttenceDevice);
            }   
         }

    }
</script>

<style lang="scss" scoped>
@import "./../../../style/common.scss";
.formIndex{

    position:absolute;
    width:600px;
    height:800px;
    background-color:#fff;
    border-radius:10px;
    top:200px;
    left:90px;
    z-index:12;
    box-shadow:1px 1px 1px 1px #ddd;
    display:flex;
    flex-direction:column;


    .formHeader{
        font-size:25px;
        width:100%;
        height:80px;
        line-height:80px;
        color:black;
        flex-shrink:0;
        flex-grow:0;
        text-align:center;
    }

    .formContent{
        flex:1;
    }

    .formFooter{
        height:100px;
        display:flex;
        justify-content:flex-end;
        align-items:center;

        .submitButton{
            background-color:$navBarColor;
            margin:0 40px;
            color:white;
        }

        .deleteButton{
            margin-right:30px;
        }

    }

}
</style>