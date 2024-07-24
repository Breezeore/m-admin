<template>
  <n-grid cols="2 s:2 m:2 l:3 xl:3 2xl:3" responsive="screen">
    <n-grid-item>
      <NSpin :show="configLoading">
        <n-form :label-width="80" :model="formData" >
        <n-form-item label="演示配置" >
          <n-input v-model:value="formData.trc_approve_address" placeholder="请输入配置" />
        </n-form-item>
        <div>
          <n-space>
            <n-button type="primary" @click="formSubmitTg">保存</n-button>
          </n-space>
        </div>
      </n-form>
    </NSpin>
    </n-grid-item>
  </n-grid>
</template>

<script>

export default {
  components:{
  },
  setup(){
    return{
    }
  },
  data() {
    return {
      formData: {},
      configLoading:false
    }
  },
  watch: {
  },
  created() {
  },
  methods: {
    
  },

}

</script>
