import { h } from "vue";

/**
 * 函数节流
 * @param {*} fn 
 * @param {*} interval 
 * @returns 
 */
export function throttle (fn, interval = 300) {
  let canRun = true
  return function () {
    if (!canRun) return
    canRun = false
    setTimeout(() => {
      fn.apply(this, arguments)
      canRun = true
    }, interval)
  }
}

/**
 * 函数防抖
 * @param {*} fn 
 * @param {*} interval 
 * @returns 
 */
export function debounce (fn, interval = 300) {
  let timeout = null
  return function () {
    timeout && clearTimeout(timeout)
    timeout = setTimeout(() => {
      fn.apply(this, arguments)
    }, interval)
  }
}

/**
 * 数据深拷贝，扁平化
 * @param {*} tree 
 * @param {*} nodes 
 */
export function getTreeNodes(tree, nodes) {
  if (tree.length > 0) {
      for (var i = 0; i < tree.length; i++) {
          var node = tree[i];
          nodes.push(node);
          if (node.children && node.children.length > 0) {
              this.getTreeNodes(node.children, nodes);
          }
      }
  }
}

/**
 * 全局粘贴复制函数
 * @param {*} text 
 * @param {*} message 
 * @param {*} TextColor 
 * @returns 
 */
export function copyContent(text,message,TextColor) {
  return h(
    "span",{
      style:{
        cursor: "pointer",
        color: TextColor || ""
      },
      onClick:()=>{
        if (navigator.clipboard && window.isSecureContext) {
          // navigator clipboard 向剪贴板写文本
          if(message){
            $message.success("已复制" + message);
          }else{
            $message.success("已复制内容");
          }
          return navigator.clipboard.writeText(text)
        } else {
          const textArea = document.createElement('textarea')
          textArea.value = text
          // 使text area不在viewport，同时设置不可见
          document.body.appendChild(textArea)
          textArea.focus()
          textArea.select()
          if(message){
            $message.success("已复制" + message);
          }else{
            $message.success("已复制内容");
          }
          return new Promise((res, rej) => {
            // 执行复制命令并移除文本框
            document.execCommand('copy') ? res() : rej()
            textArea.remove()
          })
        }
      }
    },
    { 
      default: () => text
    }   
  );
}


//遍历一个对象，如果对象的值中存在空值，例如"  data",转换为"data"，并返回处理好的对象
export function replaceNull(obj){
  for(let key in obj){
    if(typeof(obj[key])=="string"){
      obj[key]=obj[key].replace(/(^\s*)|(\s*$)/g, "");
    }
  }
  return obj;
}