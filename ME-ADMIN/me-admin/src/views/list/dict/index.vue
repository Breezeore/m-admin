<template>
  <div>
    <NSpin :show="dataLoading">
      <div class="header" v-show="isInquire">
          <div class="header_boxs">
          <!-- ---------------------------------------------条件搜索--------------------------------------------- -->
            <n-form label-placement="left" inline :model="queryParameters" size="medium" label-width="70px" class="header_boxs_form">
              <n-form-item label="字典状态" >
                <n-select v-model:value="queryParameters.status" clearable :options="sys_normal_disable" :style="page_item" />
              </n-form-item>
              <n-form-item label="字典名称" >
                <n-input  placeholder="请输入字典名称" :style="page_item" clearable v-model:value="queryParameters.dict_name" />
              </n-form-item>
              <n-form-item label="字典标识" >
                <n-input  placeholder="请输入字典标识" :style="page_item" clearable v-model:value="queryParameters.dict_type" />
              </n-form-item>
              
              <div class="search_but">
                <n-button type="primary" size="medium"  @click="getData(true)">
                  <template #icon>
                    <n-icon>
                      <search />
                    </n-icon>
                  </template>搜索
                </n-button>
                <n-button type="tertiary" strong secondary size="medium"  @click="reset"  style="margin-left: 10px;">
                  <template #icon>
                    <n-icon>
                      <SyncSharp />
                    </n-icon>
                  </template>重置
                </n-button>
              </div>
            </n-form>
          </div>
      </div>
      <n-grid x-gap="12" :cols="1" style="margin: 7px 0 0 0;" class="header_content" >
        <n-gi style="padding: 15px;">
          <div class="tableHeadle">
          <!-- ---------------------------------------------功能区域--------------------------------------------- -->
            <div class="tableHeadleleft">
              <n-button type="primary" size="medium"  @click="userAdd">
                <template #icon>
                  <n-icon>
                    <Add12Regular />
                  </n-icon>
                </template>添加
              </n-button>
            </div>
          <!-- ---------------------------------------------表格工具插件--------------------------------------------- -->
            <div class="tableHeadleRight">
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
                <span>刷新数据</span>
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
                <span>表单密度</span>
              </n-tooltip>

              <!--显示隐藏搜索开关-->
              <n-tooltip trigger="hover">
                <template #trigger>
                  <div class="table-toolbar-right-icon" @click="isInquire = !isInquire">
                    <n-icon size="18">
                      <Search />
                    </n-icon>
                  </div>
                </template>
                <span>搜索条件(显示/隐藏)</span>
              </n-tooltip>
            
            </div>
          </div>
          <!-- ---------------------------------------------表格区域--------------------------------------------- -->
          <n-data-table
            :loading="loadingData"
            :bordered="false"
            :columns="columns"
            :data="data"
            remote
            flex-height
            :size="tableSize"
            :striped="isStriped"
            style="height: 65vh"
            :row-key="(row) => row.id"
            :pagination="pagination"
          />
        </n-gi>
      </n-grid>
    
      <!-- ---------------------------------------------添加和修改--------------------------------------------- -->
      <n-drawer v-model:show="add_up" :width="450"  >
        <n-drawer-content :title="formDataTitle" closable>
          <NSpin :show="subLoading">
            <n-form label-position="top" :model="fromData" label-width="10px" :rules="rules" ref="formRef">
              <n-form-item label="字典名称" path="dict_name">
                <n-input placeholder="请输入字典名称" clearable v-model:value="fromData.dict_name" />
              </n-form-item>
              <n-form-item label="字典标识" path="dict_type" >
                <n-input  placeholder="请输入字典标识" clearable v-model:value="fromData.dict_type" />
              </n-form-item>
              <n-form-item label="字典状态" path="status" >
                <n-select v-model:value="fromData.status" placeholder="请选择字典使用状态" default-value="0" clearable :options="sys_normal_disable" />
              </n-form-item>
              <n-form-item label="备注" path="remark" >
                <n-input type="textarea" placeholder="请输入备注" clearable maxlength="150" show-count v-model:value="fromData.remark" />
              </n-form-item>
            </n-form>
            <div class="submit">
              <n-button type="primary"  class="submitButton" @click="subData" :loading="subLoading">提 交</n-button>
              <n-button strong secondary type="tertiary" class="submitButton" @click="add_up = false">取 消</n-button>
            </div>
          </NSpin>  
        </n-drawer-content>
      </n-drawer>

    </NSpin>
  </div>
</template>

<script>
// -------------------------------- sub分离控件 --------------------------------
import config from "./sub/config.js";
import rules from "./sub/rules.js";

// -------------------------------- icon --------------------------------
import { ReloadOutlined,ColumnHeightOutlined } from '@vicons/antd';
import { SyncSharp ,Add,Search} from "@vicons/ionicons5"
import { Add12Regular } from "@vicons/fluent"

// -------------------------------- tools --------------------------------
import { replaceNull } from '@/utils/me_utils/tools';
import { h , getCurrentInstance } from "vue";
import { NButton, NEllipsis, NPopconfirm, NImage, NGradientText, NTag, NSpin, spaceDark, useDialog, NIcon } from "naive-ui";
import QRCode from 'vue-qr/src/packages/vue-qr.vue'
import setting from '@/settings/designSetting';
import { escapeXSS } from "@/utils/escapeXSS"
import { codeConfig } from '@/config/code.config';

// -------------------------------- api --------------------------------
import { getDictList, addDict, updateDict, delDict } from '@/api/system/dict';

//------------------变量------------------
const addTitle = "新增数据字典";
const editTitle = "修改数据字典";

export default {
  components:{
    SyncSharp,
    QRCode,
    NSpin,
    Search,
    Add12Regular,
    ReloadOutlined,
    ColumnHeightOutlined
  },
  setup(){
    //全局设置代理对象
    const { proxy } = getCurrentInstance();

    //弹窗提示组件
    const dialog = useDialog();

    //全局密度设置
    const densityOptions = proxy.$densityOptions()

    //字典列表 | 字典读取
    const { sys_normal_disable } = proxy.$useDict('sys_normal_disable');
    let dictList = {
      sys_normal_disable: sys_normal_disable
    }
    return{
      //弹窗组件
      dialog,
      // 字典list
      dictList,
      // 密度
      densityOptions
    }
  },
  data() {
    return {
      //字典启用禁用 | 避免系统开关字典被禁用！
      sys_normal_disable:[
        { label: '启用', value: '0' },
        { label: '禁用', value: '1' },
      ],
      //查询参数
      queryParameters:{
        page: 1,
        limit: 10,
      },
      //分页
      pagination: {
        page: 1 ,
        pageSize: 10,
        pageSizes: [10, 20, 30, 40, 50],
        showQuickJumper: true,
        showSizePicker: true,
        pageCount: 0,
        onChange: (page) => {
          this.pagination.page = page;
          this.queryParameters.page = page;
          this.getData();
        },
        onUpdatePageSize: (pageSize) => {
          this.pagination.pageSize = pageSize;
          this.queryParameters.limit = pageSize;
          this.pagination.page = 1;
          this.queryParameters.page = 1;
          this.getData();
        }
      },
      //整个页面的loading
      dataLoading: false,
      //搜索表格列的长度
      page_item:{
        width: "250px"
      },
      //表格斑马纹
      isStriped: false,
      //表格密度
      tableSize: 'medium',
      //表格数据
      data: [],
      //提交表单的loading
      subLoading:false,
      //添加和修改显示控件
      add_up:false,
      //提交表单对象
      fromData:{},
      //提交表单标题
      formDataTitle: addTitle,
      //表单loading
      loadingData:true,
      //显示隐藏搜索条件
      isInquire: true,
      //提交表单验证规则
      rules: {},
    }
  },
  created() {
    this.columns = config(this,this.dictList);
    this.rules = rules();
    this.getData();
  },
  methods: {
    subData(){
      //提交表单验证  
      this.$refs.formRef?.validate((valid) => {
        if (!valid) {
          this.subLoading = true;
          if (this.fromData.id != undefined) {
            //----------------修改----------------
            updateDict(this.fromData).then(res => {
              if(res.code == codeConfig.success){
                this.subLoading = false;
                this.add_up = false;
                this.getData();
                $message.success(res.message);
              }else{
                this.subLoading = false;
                $message.error(res.message);
              }
            }).catch(err => {
              this.subLoading = false;
              this.add_up = false;
              let dataTime = null;
            })

          } else {
            //----------------添加----------------
            addDict(this.fromData).then(res => {
              if(res.code == codeConfig.success){
                this.subLoading = false;
                this.add_up = false;
                this.getData();
                $message.success(res.message);
              } else {
                this.subLoading = false;
                $message.error(res.message);
              }
            }).catch(err => {
              this.subLoading = false;
              this.add_up = false;
            })
          }
        }
      })
    },
    //添加按钮点击操作
    userAdd(){
      this.formDataTitle = addTitle;
      this.fromData = {};
      this.add_up = true;
    },
    //删除
    del(row) {
      delDict(row.dict_type).then((res)=>{
        if(res.code == codeConfig.success){
          this.getData();
          $message.success(res.message);
        }else{
          $message.error(res.message);
        }
      })
    },
    //修改
    edit(row) {
      this.formDataTitle = editTitle;
      this.fromData = row;
      this.add_up = true;
    },
    //查看详情
    watchMore(row) {

    },
    //获取数据列表
    getData(val) {
      //是搜索也会添加数据表格也会出现Loading
      if(val){
        this.queryParameters.page = 1;
        this.dataLoading = true;
      }
      //数据表格Loading
      if(!val){
        this.loadingData = true;
      }
      //获取数据
      getDictList(replaceNull(this.queryParameters)).then(res => {
        this.data = res.data.records;
        this.pagination.pageCount = res.data.totalPage;

        //数据表格的Loading
        this.loadingData = false;
        //查询字典Loading
        this.dataLoading = false;
      }).catch(err => {
        //数据报错错误处理
        this.loadingData = false;
        this.dataLoading = false;
      })

    },
    //设置斑马纹
    setStriped(value) {
      this.isStriped = value;
    },
    //密度选择
    densitySelect(e) {
      this.tableSize = e;
    },
    //刷新数据
    reload(){
      this.getData();
    },
    //重置
    reset(){
      //重置逻辑...
      for(let key in this.queryParameters){
        if(key == "page" || key == "limit") continue;
        this.queryParameters[key] = null;
      }
      this.getData();
    },
  },
}

</script>

<style lang="less" scoped>
.submitButton{
  width:100%;
  height:37px;
  margin: 15px 0 0 0;
}
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
  border-radius: 5px;
  background-color: rgb(255, 255, 255);
  display: flex;
  flex-wrap: wrap;
}
.header_content{
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
  .tableHeadleRight{
    display: flex;
    margin-left: 10px;
  }
}
.header_boxs{
  padding: 20px 20px 0 20px ;
}
.search_but{
  padding: 0 0 20px 0 ;
}
.header_boxs_form{
  display: flex;
  // flex-direction: row-reverse;
  flex-wrap: wrap;
}
.table-toolbar-right-icon{
  margin-left: 15px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
}

.form_item{
  width: 320px;
}

</style>./sub/config.js/index.js