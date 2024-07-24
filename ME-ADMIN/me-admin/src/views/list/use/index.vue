<template>
  <div class="use_box">
    已跳转官网(<span class="openLink" @click="openLink">若没有跳转点我</span>)<span>{{ moving_particles }}</span>
  </div>
</template>

<script>
 import { websiteConfig } from '@/config/website.config';
export default {
  components: {
  },
  setup() {
      window.open(websiteConfig.website,'_blank')
    return {
      
    };
  },
  data() {
    return {
      moving_particles:'.'
    };
  },
  mounted() {
    let timer = setInterval(() => {
      if(this.moving_particles.length < 3){
        this.moving_particles += '.'
      }else{
        this.moving_particles = '.'
      }
    }, 500);
  },
  methods: {
    openLink(){
      window.open(websiteConfig.website,'_blank')
    }
  },
}

</script>

<style lang="less" scoped>

.use_box{
  width: 100%;
  height: 85vh;
  border-radius: 8px;
  background:#ffffff;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
  margin: 0 auto;

}

.openLink{
  color: #56a7f8;
  cursor: pointer;
}

</style>