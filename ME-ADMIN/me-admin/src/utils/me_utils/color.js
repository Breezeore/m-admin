/**
 * 计算新的颜色，主要是减淡颜色
 * @param {*} color 
 * @param {*} factor 
 * @returns 
 */
export function lightenColor(color, factor) {
  // 解析颜色为RGB值
  const r = parseInt(color.slice(1, 3), 16);
  const g = parseInt(color.slice(3, 5), 16);
  const b = parseInt(color.slice(5, 7), 16);

  // 计算新的RGB值
  const newR = Math.min(255, r + (255 - r) * factor);
  const newG = Math.min(255, g + (255 - g) * factor);
  const newB = Math.min(255, b + (255 - b) * factor);

  // 将新的RGB值转换为十六进制颜色表示
  const newColor = `#${Math.round(newR).toString(16)}${Math.round(newG).toString(16)}${Math.round(newB).toString(16)}`;

  return newColor;
}