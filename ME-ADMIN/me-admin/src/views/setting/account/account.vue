<template>
  <div>
    <n-grid :x-gap="24" style="border-radius: 8px;">
      <n-grid-item span="6">
        <n-card :bordered="false" size="small" class="proCard">
          <n-thing
            class="thing-cell"
            v-for="item in typeTabList"
            :key="item.key"
            :style="type === item.key ? thingCellStyle : ''"
            :class="{ 'thing-cell-on': type === item.key }"
            @click="switchType(item)"
          >
            <template #header>{{ item.name }}</template>
            <template #description>{{ item.desc }}</template>
          </n-thing>
        </n-card>
      </n-grid-item>
      <n-grid-item span="18">
        <n-card :bordered="false" size="small" :title="typeTitle" class="proCard">
          <SafetySetting v-if="type === 1" />
          <BasicSetting v-if="type === 2" />
        </n-card>
      </n-grid-item>
    </n-grid>
  </div>
</template>
<script lang="ts" setup>
  import { ref } from 'vue';
  import BasicSetting from './BasicSetting.vue';
  import SafetySetting from './SafetySetting.vue';
  import setting from '@/settings/designSetting';
  import { lightenColor } from '@/utils/me_utils/color';

  const typeTabList = [
    {
      name: '账户设置',
      desc: '账户的基本信息设置',
      key: 1,
    },
    {
      name: '其他设置',
      desc: '其他设置的非常不错!',
      key: 2,
    },
  ];

// 调整颜色亮度的因子，0表示不变，小于1减淡，大于1增亮
const lighteningFactor = 0.9;

// 计算减淡后的背景颜色
const lightenedColor = lightenColor(setting.appTheme, lighteningFactor);


  const thingCellStyle ={
    background: lightenedColor,
  }

  const type = ref(1);
  const typeTitle = ref('基本设置');

  function switchType(e) {
    type.value = e.key;
    typeTitle.value = e.name;
  }
</script>
<style lang="less" scoped>
.proCard{
  border-radius: 8px;
}
  .thing-cell {
    padding: 10px 20px;
    margin:  10px 1px 0 3px;
    border-radius: 5px;

    &:hover {
      background: #f3f3f3;
      cursor: pointer;
    }
  }

  .thing-cell-on {
    background: #f0faff;
    color: #1768AC;

    ::v-deep(.n-thing-main .n-thing-header .n-thing-header__title) {
      color: #1768AC;
    }

    &:hover {
      background: #f0faff;
    }
  }
</style>
