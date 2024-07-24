import { h } from "vue";
import { NButton, NEllipsis, NPopconfirm, NImage,NGradientText,NTag} from "naive-ui";
import setting from '@/settings/designSetting';

export default function creatColumns(T) {
  return [
    {
      title: "是什么",
      key: "shishenmo",
      width: 130,
      align: "center",
    },
    {
      title: "是什么",
      key: "shishenmo",
      width: 130,
      align: "center",
    },
    {
      title: "是什么",
      key: "shishenmo",
      width: 120,
      align: "center",
      ellipsis: {
        tooltip: true,
      },
    },
    {
      title: "是什么",
      key: "shishenmo",
      width: 120,
      align: "center",
      ellipsis: {
        tooltip: true,
      },  
    },
    {
      title: "是什么",
      key: "shishenmo",
      width: 120,
      align: "center",
      ellipsis: {
        tooltip: true,
      },
    },
    {
      title: "是什么",
      key: "shishenmo",
      width: 120,
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
            title: "修改",
            func: () => T.edit(row),
            type: "primary",
          },
          {
            title: "查看",
            func: () => T.watchMore(row),
            type: "error",
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
        res.unshift(
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
