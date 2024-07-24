<template>
  <div class="config_box">
    <iframe src="https://www.so.com/" style="width: 100%;height:100%;border-radius: 8px;" frameborder="0"></iframe>
  </div>
</template>

<script>
import { getData } from '@/api/system/console';
export default {
  components: {
  },
  setup() {
    getData().then(res => {
      console.log(res);
    })
    return {
    };
  },
  data() {
    return {
      
    };
  },
}

</script>

<style lang="less" scoped>


.config_box{
  width: 100%;
  height: 85vh;
  border-radius: 8px;
  background-color: rgb(255, 255, 255);
}

</style>