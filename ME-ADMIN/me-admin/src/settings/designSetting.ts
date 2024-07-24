// app theme preset color
export const appThemeList: string[] = [
  '#2d8cf0',
  '#0960bd',
  '#0084f4',
  '#009688',
  '#536dfe',
  '#ff5c93',
  '#ee4f12',
  '#0096c7',
  '#9c27b0',
  '#ff9800',
  '#FF3D68',
  '#00C1D4',
  '#71EFA3',
  '#171010',
  '#78DEC7',
  '#1768AC',
  '#FB9300',
  '#FC5404',
];

//获取本地的主题色
const localTheme = localStorage.getItem('APP-THEME');


const setting = {
  //深色主题
  darkTheme: false,
  //系统主题色
  appTheme: '#1768AC',
  //系统内置主题色列表
  appThemeList
};

//如果本地有主题色，就使用本地的主题色，否则使用默认的主题色
if (localTheme) {
  setting.appTheme = localTheme;
}


export default setting;
