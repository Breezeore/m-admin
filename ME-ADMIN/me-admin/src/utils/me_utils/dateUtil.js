/**
 * 格式化时间 将时间戳转换为日期格式
 * @param {*} date 
 * @param {*} fmt 
 * @returns 
 */
export function formatDatetime(date, fmt) {
  if (date === null || date === undefined) {
    return ''
  }
  if (typeof date === 'string') {
    date = new Date(date.replace(/-/g, '/'))
  }
  if (typeof date === 'number') {
    date = new Date(date)
  }
  if (!(date instanceof Date)) {
    return ''
  }
  if (!fmt) {
    fmt = 'yyyy-MM-dd hh:mm:ss'
  }
  const o = {
    'M+': date.getMonth() + 1, // 月份
    'd+': date.getDate(), // 日
    'D+': date.getDate(), // 日
    'h+': date.getHours(), // 小时
    'H+': date.getHours(), // 小时
    'm+': date.getMinutes(), // 分
    's+': date.getSeconds(), // 秒
    'S+': date.getMilliseconds() // 毫秒
  }
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(RegExp.$1, (`${date.getFullYear()}`).substr(4 - RegExp.$1.length))
  }
  for (const k in o) {
    if (new RegExp(`(${k})`).test(fmt)) {
      const str = `${o[k]}`
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? str : (`00${str}`).substr(str.length))
    }
  }
  return fmt
}
