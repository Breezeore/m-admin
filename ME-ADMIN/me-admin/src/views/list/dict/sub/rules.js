export default function creatRules() {
  return {
    dict_name: {
      validator(rule, value) {
        if (!value) {
          return Promise.reject('请输入字典名称');
        }
        return Promise.resolve();
      },
      required: true,
      trigger: ['blur', 'change'],
    },
    //字典类型必须以字母开头，且只能为（小写字母，数字，下滑线）
    dict_type: {
      validator(rule, value) {
        if (!value) {
          return Promise.reject('请输入字典标识');
        }
        if (!/^[a-z][a-z0-9_]*$/.test(value)) {
          return Promise.reject('字典标识必须以字母开头 | 且只能为(小写字母，数字，下滑线)');
        }
        return Promise.resolve();
      },
      required: true,
      trigger: ['blur', 'change'],
    },
  };
}
