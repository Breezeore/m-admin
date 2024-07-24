<template>
  <div class="view-account">
    <div class="code-is-yes">
      <div class="view-account-header"></div>
    <div class="view-account-container">
      <div class="view-account-top">
        <div class="view-account-top-logo" style="display: flex;justify-content: center;align-items: center;">
          <img style="padding: 0 20px 0 0;width:70px"  :src="websiteConfig.logo" alt="" />
          <div class="login_title">
          {{ websiteConfig.title }}</div>
        </div>
        <div class="view-account-top-desc" style="padding: 10px 0 0 0;">{{ websiteConfig.loginDesc }}</div>
      </div>
      <div class="view-account-form">
        <n-form
          ref="formRef"
          label-placement="left"
          size="large"
          :model="formInline"
          :rules="rules"
        >
          <n-form-item path="username">
            <n-input v-model:value="formInline.username" placeholder="请输入您的账号">
              <template #prefix>
                <n-icon size="18" color="#808695">
                  <PersonOutline />
                </n-icon>
              </template>
            </n-input>
          </n-form-item>
          <n-form-item path="password">
            <n-input
              v-model:value="formInline.password"
              type="password"
              showPasswordOn="click"
              placeholder="请输入您的密码"
            >
              <template #prefix>
                <n-icon size="18" color="#808695">
                  <LockClosedOutline />
                </n-icon>
              </template>
            </n-input>
          </n-form-item>
          <n-form-item>
            <n-button type="primary" @click="handleSubmit" size="large" @keydown.enter="keyDown(e)" :loading="loading" block>
             {{ loginButtonText }} 
            </n-button>
          </n-form-item>
          <!-- <n-form-item class="default-color">
            <div class="flex view-account-other">
              <div class="flex-initial">
                <span style="cursor: pointer;" @click="emaliLogin">邮箱登录</span>
              </div>
              <div class="flex-initial mx-2">
                <a href="javascript:">
                  <n-icon size="24" color="#2d8cf0">
                    <LogoGithub />
                  </n-icon>
                </a>
              </div>
              <div class="flex-initial mx-2">
                <a href="javascript:">
                  <n-icon size="24" color="#2d8cf0">
                    <LogoFacebook />
                  </n-icon>
                </a>
              </div>
              <div class="flex-initial" style="margin-left: auto">
                <span :style="forgetStyle">其他方式</span>
              </div>
            </div>
          </n-form-item> -->
        </n-form>
      </div>
      
    </div>
    </div>
    <div class="code-is-no" v-if="codeIs" @click="closure_code"></div>
    <div class="code-is-verify">
      <div id="captcha-box" style="position: fixed;top:15%;"></div>
    </div>
  </div>
</template>

<script lang="ts" setup> 
  import { reactive, ref ,watch,computed,onMounted,onUnmounted} from 'vue';
  import { useRoute, useRouter } from 'vue-router';
  import { useUserStore } from '@/store/modules/user';
  import { useMessage } from 'naive-ui';
  import { ResultEnum } from '@/enums/httpEnum';
  import { PersonOutline, LockClosedOutline } from '@vicons/ionicons5';
  import { PageEnum } from '@/enums/pageEnum';
  import { websiteConfig } from '@/config/website.config';
  import behavior_recognition from '@/assets/images/behavior_recognition.png';
  import setting from '@/settings/designSetting';
  import { lightenColor } from '@/utils/me_utils/color';
  interface FormState {
    username: string;
    password: string;
  }

// 调整颜色亮度的因子，0表示不变，小于1减淡，大于1增亮
const lighteningFactor = 0.2;

  const forgetStyle = {
    cursor: 'pointer',
    color: lightenColor(setting.appTheme, lighteningFactor) ,
  };


  const formRef = ref();
  const message = useMessage();
  const codeIs = ref(false);
  const loading = ref(false);
  const LOGIN_NAME = PageEnum.BASE_LOGIN_NAME;

  const loginButtonText = computed(() => {
    return loading.value ? '登 录 中...' : '登 录';
  });

  const formInline = reactive({
    username: '',
    password: '',
    isCaptcha: true,
  });

  //监听账户和密码不能有空格，否则去除空格
  const watchUser = (val) => {
    formInline.username = val.replace(/\s*/g, '');
  };
  const watchPassword = (val) => {
    formInline.password = val.replace(/\s*/g, '');
  };

  watch(() => formInline.username, watchUser);
  watch(() => formInline.password, watchPassword);


  const rules = {
    username: { required: true, message: '请输入您的账号', trigger: 'blur' },
    password: { required: true, message: '请输入您的密码', trigger: 'blur' },
  };

  function emaliLogin(){
  }
  
  function closure_code() {
    codeIs.value = false;
    document.getElementById('captcha-box').innerHTML = '';
    loading.value = false
    message.destroyAll();
    // message.warning('行为验证未通过');
  }

  onMounted(() => {
    window.addEventListener('keydown', keyDown)
  })

  onUnmounted(() => {
    window.removeEventListener('keydown', keyDown, false)
  })

  const keyDown = (e) => {
  //如果是回车则执行登录方法 喜欢的可以多研究下其他的按键keyCode
  if (e.keyCode == 13) {
    handleSubmit(e)
  }
}

  const userStore = useUserStore();

  const router = useRouter();
  const route = useRoute();

  const handleSubmit = (e) => {

    e.preventDefault();
    formRef.value.validate(async (errors) => {

      if (!errors) {
        codeIs.value = true;
        // message.loading('行为验证中...');
        loading.value = true;

        const config = {
            // 生成接口
            requestCaptchaDataUrl: "http://127.0.0.1:8181/api/verificationCode/codeImg",
            // 验证接口
            validCaptchaUrl: "http://127.0.0.1:8181/api/verificationCode/check",
            // 验证码绑定的div块
            bindEl: "#captcha-box",
            // 验证成功回调函数
            validSuccess: async(res, c, tac) => {
              codeIs.value = false;
              document.getElementById('captcha-box').innerHTML = '';

              const { username, password } = formInline;
                const params: FormState = {
                  username,
                  password,
                };
              
                try {
                  const res = await userStore.login(params);
                  message.destroyAll();
                  if (res.code == ResultEnum.SUCCESS) {
                    const toPath = decodeURIComponent((route.query?.redirect || '/') as string);
                    message.success('登录成功');
                    if (route.name === LOGIN_NAME) {
                      router.replace('/');
                    } else router.replace(toPath);
                  } else {
                    message.info(res.message);
                  }
                } finally {
                  loading.value = false;
                }
                // 销毁验证码服务
                tac.destroyWindow();
            }
        }
        const style ={
          logoUrl: behavior_recognition,
        }
        new window.TAC(config,style).init()
        
      } else {
        // message.warning('账户和密码提交不完整');
      }
    });
  };
</script>

<style lang="less" scoped>

  //淡入动画
  @keyframes fade {
	  from {
	  	opacity: 0;
	  }
	  to {
	  	opacity: 1;
	  }
  }
  .login_title{
    font-size: 25px;
    font-weight: bold;
    color: #515a6e;
  }
  .code-is-verify{
    animation: fade 1.2s;
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 500;
  }
  .code-is-yes{
    z-index: 200;
  }
  .code-is-no{
    animation: fade 0.3s;
    width: 100%;
    height: 100%;
    background-color: rgba(0,0,0,0.5);
    position: absolute;
    z-index: 400;
  }
  .view-account {
    display: flex;
    flex-direction: column;
    height: 100vh;
    overflow: auto;

    &-container {
      flex: 1;
      padding: 32px 12px;
      max-width: 384px;
      min-width: 320px;
      margin: 0 auto;
    }

    &-top {
      padding: 32px 0;
      text-align: center;

      &-desc {
        font-size: 14px;
        color: #808695;
      }
    }

    &-other {
      width: 100%;
    }

    .default-color {
      color: #515a6e;

      .ant-checkbox-wrapper {
        color: #515a6e;
      }
    }
  }

  @media (min-width: 768px) {
    .view-account {
      background-image: url('../../assets/images/login.svg');
      background-repeat: no-repeat;
      background-position: 50%;
      background-size: 100%;
    }

    .page-account-container {
      padding: 32px 0 24px 0;
    }
  }
</style>
