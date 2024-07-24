import useDictStore from '@/utils/dictUtils'
import { getDicts } from '@/api/system/dict'
import { ref, toRefs } from 'vue'

/**
 * 获取字典数据
 */
export function useDict(...args) {
  const res = ref({});
  return (() => {
    args.forEach((dictType, index) => {
      res.value[dictType] = [];
      const dicts = useDictStore().getDict(dictType);
      if (dicts) {
        res.value[dictType] = dicts;
      } else {
         getDicts(dictType).then(resp => {
          res.value[dictType] = resp.data.map(p => ({ label: p.dict_label, value: p.dict_value, remark: p.remark, dictSort: p.dict_sort ,info: p.info }))
          useDictStore().setDict(dictType, res.value[dictType]);
        })
      }
    })
    return toRefs(res.value);
  })()
}
