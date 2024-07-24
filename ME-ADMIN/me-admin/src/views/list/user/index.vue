<template>
  <div>
      <div style="display: flex;" class="header">

        <div style="display: flex;">
          <div style="padding: 18px 10px 15px 10px;">
            <n-input v-model:value="queryParameters.words" type="text" style="width: 250px;" clearable placeholder="请输入搜索条件" />
          </div>
        
          <div style="padding: 18px 10px 15px 10px;">
            <n-button type="primary" size="medium"  @click="getData" :disabled="!this.queryParameters.words">
              <template #icon>
                <n-icon>
                  <search />
                </n-icon>
              </template>搜索
            </n-button>
          </div>
        
          <div style="padding: 18px 10px 15px 10px;">
            <n-button type="primary" size="medium"  @click="reset" >
              <template #icon>
                <n-icon>
                  <SyncSharp />
                </n-icon>
              </template>刷新
            </n-button>
          </div>
        
        </div>
    </div>
    <n-grid x-gap="12" :cols="1" style="margin: 7px 0 0 0;" class="header_content">
    
      <n-gi style="padding: 15px;">
        <div class="tableHeadle">
          <div class="tableHeadleItme">
            <n-button type="primary" size="medium"  @click="userAdd">
              <template #icon>
                <n-icon>
                  <AddCircleOutline />
                </n-icon>
              </template>添加
            </n-button>
          </div>

          <div class="tableHeadleItme">
             <!--斑马纹-->
             <n-tooltip trigger="hover">
                  <template #trigger>
                    <div class="mr-2 table-toolbar-right-icon">
                      <n-switch v-model:value="isStriped" @update:value="setStriped" />
                    </div>
                  </template>
                  <span>表格斑马纹</span>
            </n-tooltip>

                      <!--刷新-->
              <n-tooltip trigger="hover">
                <template #trigger>
                  <div class="table-toolbar-right-icon" @click="reload">
                    <n-icon size="18">
                      <ReloadOutlined />
                    </n-icon>
                  </div>
                </template>
                <span>刷新</span>
              </n-tooltip>


                    <!--密度-->
            <n-tooltip trigger="hover">
              <template #trigger>
                <div class="table-toolbar-right-icon">
                  <n-dropdown
                    @select="densitySelect"
                    trigger="click"
                    :options="densityOptions"
                    v-model:value="tableSize"
                  >
                    <n-icon size="18">
                      <ColumnHeightOutlined />
                    </n-icon>
                  </n-dropdown>
                </div>
              </template>
              <span>密度</span>
            </n-tooltip>
          
            <!-- <ColumnSetting/> -->
          </div>
        </div>
        <n-data-table
          :loading="loadingData"
          :bordered="false"
          :columns="columns"
          :data="data"
          flex-height
          style="height: 93%"
          :row-key="(row) => row.id"
        />
        <n-pagination
            :item-count="count"
            style="float: right;padding: 15px 0 0 0;"
            :on-update:page="pageData"
            simple 
            :default-page-size="queryParameters.limit"
          />
      </n-gi>
    </n-grid>
    

    <n-drawer v-model:show="add_up" :width="450"  >
      <n-drawer-content :title="formDataTitle" closable>
        <NSpin :show="subLoading">
          <n-form label-position="top" :model="FromData" label-width="10px" >
            <n-form-item label="标题" >
              <n-input placeholder="请输入标题" clearable v-model:value="FromData.title" />
            </n-form-item>
            <n-form-item label="内容" >
              <n-input  placeholder="请输入内容" v-model:value="FromData.content" />
            </n-form-item>
          </n-form>
          <div class="submit">
            <n-button type="primary" style="width:100%;height:40px;margin: 15px 0 0 0;" @click="subData" :loading="subLoading">提 交</n-button>
            <n-button strong secondary type="tertiary" style="width:100%;height:40px;margin: 15px 0 0 0;" @click="active = false">取 消</n-button>
          </div>
        </NSpin>
      </n-drawer-content>
    </n-drawer>
  </div>
</template>

<script>
import config from "./config.js";
import ColumnSetting from '@/components/Table/src/components/settings/ColumnSetting.vue';
import { Search,SyncSharp,PaperPlane ,AddCircleOutline} from "@vicons/ionicons5"
import { QrcodeOutlined ,LockFilled,UnlockFilled} from "@vicons/antd"
import { CountTo } from '@/components/CountTo/index';
import setting from '@/settings/designSetting';
import { escapeXSS } from "@/utils/escapeXSS"
import { BasicTable, TableAction } from '@/components/Table';
import { ReloadOutlined,ColumnHeightOutlined } from '@vicons/antd';


import { h } from "vue";
import {NButton, NEllipsis, NPopconfirm, NImage,NGradientText,NTag,NSpin,spaceDark, useDialog,NIcon} from "naive-ui";
// import QRCode from 'qrcode.vue'
import QRCode from 'vue-qr/src/packages/vue-qr.vue'

export default {
  components:{
    Search,
    SyncSharp,
    CountTo,
    QrcodeOutlined,
    QRCode,
    NSpin,
    AddCircleOutline,
    ReloadOutlined,
    ColumnHeightOutlined,
    ColumnSetting
  },
  setup(){
    const dialog = useDialog();
    return{
      dialog,
      PaperPlane,
    }
  },
  data() {
    return {
      data: [],
      count: 0,
      //提交表单的loading
      subLoading:false,
      //添加和修改显示控件
      add_up:false,
      //提交表单对象
      FromData:{},
      //表标题
      formDataTitle:"添加信息",
      //表单loading
      loadingData:true,
      //查询参数
      queryParameters:{
        page: 1,
        limit: 10,
        type:null,
        words:null
      },
    }
  },
  watch: {
    'queryParameters.words': function (val){
      //去掉前后空格
      val = val.replace(/(^\s*)|(\s*$)/g, "");
    }
  },
  created() {
    this.columns = config(this);
    this.getData();
  },
  methods: {
    subData(){
    },
    //添加
    userAdd(){
      this.add_up = true;
    },
    //重置
    reset(){
      this.queryParameters.words = null;
      this.getData();
    },
    //删除
    del(i) {
    },
    //修改
    edit(row) {
    },
    //查看详情
    watchMore(row) {
    },
    //分页
    pageData(page) {
      this.queryParameters.page = page;
    },
    //获取数据列表
    getData() {
    },
    
  },
}

</script>

<style lang="less" scoped>

.submit{
  width: 100%;
  height: 50px;
  margin: 20px 0 0 0;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  
}
.header{
  width: 100%;
  height: 70px;
  border-radius: 8px;
  background-color: rgb(255, 255, 255);
  display: flex;
  justify-content: space-between;
}
.header_content{
  // font-weight: bold;
  
  width: 100%;
  height: 100%;
  border-radius: 5px;
  background-color: rgb(255, 255, 255);
}
.tableHeadle{
  padding: 0 0 15px 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  .tableHeadleItme{
    display: flex;
    margin-left: 10px;
  }
}
.table-toolbar-right-icon{
  // 居中
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>