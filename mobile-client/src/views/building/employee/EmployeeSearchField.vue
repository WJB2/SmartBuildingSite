<template>
    <div class="cover" @click="handleFieldCancelSearch" v-if="searchFieldVisible">
        <div class="resetField" @click.stop="handleClickField">
        <van-cell-group>
            <div @click="handleFieldBuildingSiteSelect">
            <van-field 
                v-model="filter.buildingSiteName" 
                label="所属工地"
                disabled 
                is-link
            />
            </div>
            <van-field v-model="filter.employeeText" label="人员关键字"></van-field>
            
        </van-cell-group>
        <div class="button">
            <div class="submitButton" @click="handleFieldSearch">查询</div>
            <div class="resetButton" @click="handleFieldReset">重置</div>
        </div>
        </div>

        <building-site-selector 
            :buildingSiteSelectorVisible="buildingSiteSelectorVisible"
            v-on:onSelectorCancel="handleFieldSelectorCancel"
            v-on:onSelectorSubmit="handleFieldSelectorSubmit"
        />

    </div>
</template>

<script>
import BuildingSiteSelector from "./../../../components/building/form/BuildingSiteSelector.vue";
export default {
  props: ["searchFieldVisible"],
  data() {
    return {
      filter: {
        buildingSiteId: "",
        employeeText: "",
        buildingSiteName:""
      },
      buildingSiteSelectorVisible: false
    };
  },
  computed: {},
  components: {
    BuildingSiteSelector
  },
  methods: {
    handleFieldBuildingSiteSelect() {  //点击选择所属工地
      this.buildingSiteSelectorVisible = true;
    },
    handleFieldSelectorCancel() {
      //点击取消取消选择
      setTimeout(() => {
        this.buildingSiteSelectorVisible = false; // picker蒙层
      });
      // console.log(this.searchFieldVisible)// console.log(this.searchFieldVisible)
    },
    handleFieldSelectorSubmit(id, e) {
      setTimeout(() => {

        this.filter.buildingSiteName=e;
        this.filter.buildingSiteId=id;
        this.buildingSiteSelectorVisible = false; // picker蒙层
       
      });
    },
    handleFieldCancelSearch() {
      // 穿透了
      if (!this.buildingSiteSelectorVisible) {
        this.$emit('onClickCover'); // 这里提交到哪里
        this.filter.buildingSiteName = "";
        this.filter.employeeText = "";
      }
    },
    handleClickField() {},
    handleFieldSearch() {
      this.$emit(
        "onSearch",
        this.filter.buildingSiteId || this.filter.employeeText
          ? {
              buildingSiteId: this.filter.buildingSiteId,
              employeeText: this.filter.employeeText
            }
          : {}
      );

      this.filter.buildingSiteName = "";
      this.filter.employeeText = "";
    },
    handleFieldReset() {
      this.filter.buildingSiteName = "";
      this.filter.employeeText = "";
    }
  }
};
</script>

<style lang="scss" scoped>
@import "./../../../style/common.scss";
.cover {
  position: fixed;
  width: 800px;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 12;
  height: 1400px;
  top: 94px;
  transition: all 0.8s ease 0.1s;

  .resetField {
    z-index: 13;
    width: 500px;
    height: 300px;
    border-radius: 10px;
    background-color: white;
    margin-top: 300px;
    margin-left: 130px;
    padding: 10px;
    transition: all 0.8s ease 0.1s;

    .button {
      height: 100px;
      width: 100%;
      display: flex;
      justify-content: space-around;
      align-items: center;
      color: $white;
      text-align: center;

      .submitButton {
        width: 120px;
        line-height: 70px;
        height: 70px;
        background-color: $navBarColor;
        border-radius: 10px;
      }

      .resetButton {
        width: 120px;
        line-height: 70px;
        height: 70px;
        background-color: $resetButtonColor;
        border-radius: 10px;
      }
    }
  }
}
</style>
