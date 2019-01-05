<template>
    <div class="formIndex">
        <div class="formHeader">{{title}}</div>
        <div class="van-hairline--bottom"></div>
        <div class="formContent">
        <van-cell-group>
            <div @click="handleSelectBuildingSite">
                <van-field 
                    is-link
                    v-model="employee.buildingSiteName" 
                    required 
                    label="所属工地"
                    disabled
                    ></van-field>
            </div>
            <van-field v-model="employee.code" required label="员工工号"></van-field>
            <van-field v-model="employee.name" required label="工人姓名"></van-field>
            <van-field v-model="employee.idCardNo" required label="身份证号"></van-field>
            <div @click="handleSelectDict">
                <van-field 
                    v-model="employee.bank"
                    required 
                    label="开户行"
                    disabled 
                    is-link></van-field>
            </div>
            <van-field v-model="employee.bankNo" required label="银行账户号"></van-field>
            <div @click="handleSelectWork">
                <van-field 
                v-model="employee.workType" 
                label="工种"
                disabled 
                is-link></van-field>
            </div>
            <van-field v-model="employee.mobile" label="手机号"></van-field>
            <van-field v-model="employee.address" label="联系地址"></van-field>
        </van-cell-group>
        </div>
        <div class="formFooter">
            <van-button type="default" size="small" @click="handleCancelForm" style="{color:'#fff'}">取消</van-button>
            <van-button type="default" size="small" @click="handleSubmitForm" class="submitButton">确定</van-button>
            <van-button type="danger" size="small" @click="handleDeleteForm" v-if="formType==='ADD'?false:true" class="deleteButton">删除</van-button>
        </div>

        <building-site-selector 
            :buildingSiteSelectorVisible="buildingSiteSelectorVisible"
            v-on:onSelectorCancel="handleBuildingSiteSelectorCancel"
            v-on:onSelectorSubmit="handleBuildingSiteSelectorSubmit"
        />

        <dict-item-selector 
            :dictSelectorVisible="dictSelectorVisible"
            :entryCode="BANK"
            v-on:onSelectorCancel="handleDictSelectorCancel"
            v-on:onSelectorSubmit="handleDictSelectorSubmit"
        />

        <work-type-selector 
            :workTypeSelectorVisible="workTypeSelectorVisible"
            v-on:onSelectorCancel="handleWorkSelectorCancel"
            v-on:onSelectorSubmit="handleWorkSelectorSubmit"
        />

    </div>
</template>

<script>
import BuildingSiteSelector from './../../../components/building/form/BuildingSiteSelector.vue';
import DictItemSelector from './../../../components/system/form/DictItemSelector.vue';
import WorkTypeSelector from './../../../components/building/form/WorkTypeSelector.vue';
import employeeService from './../../../api/building/BuildingSiteService';
async function transform(id){
    const buildingSite=await employeeService.findBuildingSiteById({id:id});
    return  buildingSite.name;
}
export default{
    components:{
        BuildingSiteSelector,
        DictItemSelector,
        WorkTypeSelector,
    },
    props:{
        currentEmployee:Object,
        formType:String,
    },
    computed:{
        filterAddEmployee:function(){
            delete this.employee.buildingSiteName;
            console.log(delete this.employee.buildingSiteName)
            console.log(this.employee);
            return this.employee;
        },
        filterEditEmployee:function(){
            delete this.employee.buildingSiteName;
            this.employee.buildingSiteId=this.currentEmployee.buildingSiteId;
            return this.employee;
        }
    },
    data(){
        return{
            employee:{},
            title:{},
            BANK:'BANK',
            buildingSiteSelectorVisible:false,
            dictSelectorVisible:false,
            workTypeSelectorVisible:false,

            buildingSitelinkVisible:true,
            dictLinkVisible:true,
            workTypeLinkVisible:true,
        }
    },
    mounted:async function(){
    
        const employee=this.formType==="EDIT"?{buildingSiteName:await transform(this.currentEmployee.buildingSiteId),code:this.currentEmployee.code,
            name:this.currentEmployee.name,idCardNo:this.currentEmployee.idCardNo,bank:this.currentEmployee.bank,
            bankNo:this.currentEmployee.bankNo,workType:this.currentEmployee.workType,mobile:this.currentEmployee.mobile,address:this.currentEmployee.address}:{
                buildingSiteName:'',code:'',name:'',idCardNo:'',bank:'',bankNo:'',workType:'',mobile:'',address:'',
        };
        const title=this.formType==="ADD"?"新建人员信息":"编辑人员信息";
         
        this.title=title;
        this.employee=employee;
    },
    methods:{
        handleCancelForm(){
            this.$emit('onFormCancel');
        },
        handleSelectBuildingSite(){//选择工地
            this.buildingSiteSelectorVisible=true;
        },
        handleSelectDict(){//选择开户行
            this.dictSelectorVisible=true;
        },
        handleSelectWork(){//选择工种
            this.workTypeSelectorVisible=true;
        },
        handleBuildingSiteSelectorCancel(){//点击选择工地的取消
            this.buildingSiteSelectorVisible=false;
        },
        handleDictSelectorCancel(){//点击选择开户行取消
            this.dictSelectorVisible=false;
        },
        handleWorkSelectorCancel(){//点击选择工种取消
            this.workTypeSelectorVisible=false;
        },
        handleBuildingSiteSelectorSubmit(id,e){//点击选择工地的确定
        
            this.employee.buildingSiteId=id;
            this.employee.buildingSiteName=e;
            this.buildingSitelinkVisible=false;
            this.buildingSiteSelectorVisible=false;
            
        },
        handleDictSelectorSubmit(e){
            this.employee.bank=e;
            this.dictSelectorVisible=false;
        },
        handleWorkSelectorSubmit(e){
            this.employee.workType=e;
            this.workTypeSelectorVisible=false;
        },
        handleSubmitForm(){
            this.$emit('onFormSubmit',this.formType==="ADD"?this.filterAddEmployee:this.filterEditEmployee);
        },
        handleDeleteForm(){
            this.$emit('onFormDelete',this.currentEmployee);
        }

    }
}
</script>

<style lang="scss" scoped>
@import "./../../../style/common.scss";
.formIndex{

    position:absolute;
    width:600px;
    height:1100px;
    background-color:#fff;
    border-radius:10px;
    top:150px;
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