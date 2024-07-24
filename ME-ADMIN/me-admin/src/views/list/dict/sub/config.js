import { h  } from "vue";
import { NButton, NEllipsis, NPopconfirm, NImage,NGradientText,NTag} from "naive-ui";
import setting from '@/settings/designSetting';
import { parseTime, resetForm, addDateRange, handleTree, selectDictLabel, selectDictLabels,selectDictLabelTag } from '@/utils/me_utils/me-admin'
import { copyContent } from '@/utils/me_utils/tools';


export default function creatColumns(T,L) {

  return [
    {
      title: "字典ID",
      key: "id",
      width: 130,
      align: "center",
    },
    {
      title: "字典名称",
      key: "dict_name",
      width: 130,
      align: "center",
      ellipsis: {
        tooltip: true,
      },
      render(row){
        return copyContent(row.dict_name,"字典名称");
      }
    },
    {
      title: "字典标识",
      key: "dict_type",
      width: 120,
      align: "center",
      ellipsis: {
        tooltip: true,
      },
      render(row){
        return copyContent(row.dict_type,"字典标识");
      }
    },
    {
      title: "字典状态",
      key: "status",
      width: 120,
      align: "center",
      ellipsis: {
        tooltip: true,
      },
      render(row){
        return selectDictLabelTag(L.sys_normal_disable.value,row.status);
      }
    },
    {
      title: "创建时间",
      key: "create_time",
      width: 120,
      align: "center",
      ellipsis: {
        tooltip: true,
      },
    },
    {
      title: "字典备注",
      key: "remark",
      width: 130,
      align: "center",
      ellipsis: {
        tooltip: true,
      },
    },
    {
      title: "表单操作",
      key: "oepration",
      width: 180,
      render(row) {
        let operationList = [];
        operationList = [
          {
            title: "查看",
            func: () => T.watchMore(row),
            type: "error",
          },
          {
            title: "修改",
            func: () => T.edit(row),
            type: "primary",
          }, 
        ];

        let res = operationList.map((item) => {
          return h(
            NButton,
            {
              size: "small",
              quaternary: true,
              type: item.type,
              onClick: item.func,
            },
            { default: () => item.title }
          );
        });
        res.push(
          h(
            NPopconfirm,
            {
              onPositiveClick: () => T.del(row),
              negativeText: "取消",
              positiveText: "删除",
            },
            {
              default: () => "您确定要删除此信息？",
              trigger: () =>
                h(
                  NButton,
                  {
                    size: "small",
                    quaternary: true,
                    type: "error",
                  },
                  { default: () => "删除" }
                ),
            }
          )
        );
        return res;
      },
      align: "center",
    },
  ];
}


