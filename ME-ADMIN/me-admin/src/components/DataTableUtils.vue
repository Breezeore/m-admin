<template>
  <div>
    <div class="tableHeadleRight">
     <!--斑马纹-->
     <n-tooltip trigger="hover">
        <template #trigger>
          <div class="mr-2 table-toolbar-right-icon">
            <n-switch  @update:value="setStriped" />
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
        <div class="table-toolbar-right-icon" @click="isInquire">
          <n-icon size="18">
            <Search />
          </n-icon>
        </div>
      </template>
      <span>搜索条件(显示/隐藏)</span>
    </n-tooltip>
  
  </div>
  </div>
</template>

<script>
import { AddCircleOutline, Search } from "@vicons/ionicons5";
import { ReloadOutlined, ColumnHeightOutlined } from "@vicons/antd";

export default {
  components: {
    AddCircleOutline,
    Search,
    ReloadOutlined,
    ColumnHeightOutlined
  },
  props: {
    setStriped: Function,
    reload: Function,
    isInquire: Function,
    densityOptions: Array,
  },
};
</script>

<style scoped>
.tableHeadle{
  background: #fff;
  padding: 0 0 15px 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.tableHeadleRight{
    display: flex;
    margin-left: 10px;
  }
</style>
