# TUtils
自己整理的android中一些常用的工具类，android develop utils<br>
 jar包在assets目录下，有需要的可以直接下载使用；<br>
   
   
**ActivityUtils**：activity快捷跳转，bundle可选；<br>
**AppManager**：应用程序所有activity管理类，可以获取到当前activity，指定activity，在基类BaseActivity的onCreate()中加入```AppManager.getAppManager().addActivity(this);```即可将activity加入，在程序结束时调用```AppManager.getAppManager().AppExit();```即可关闭所有activity，完全退出程序；<br>
**BundleUtils**：快捷获取activity之间传递的数据；<br>
**ButtonClickUtils**：FastDoubleClick检测；<br>
**CleanCacheUtils**：应用程序数据清理，可以一键清除所有，也可以只清除数据库、SharedPreference、file；<br>
**DataUtils**：String、List、Map等非空判断，大小获取；<br>
**DeviceUtils**：一些通用的方法，例如获取设备信息、单位转换等；<br>
**FileUtils**：文件处理，例如文件复制、删除、重命名、大小获取和单位转换；<br>
**ImageUtils**：bitmap相关，图片高质量压缩、缩放、获取图片旋转角度、旋转图片等；<br>
**JsonUtils**：根据key快速获取value；<br>
**PreferencesUtils**：SharedPreference快捷存取数据；<br>
**SecurityUtil**：MD5加密解密、3DES加密解密；<br>
**TimeUtils**：时间和date相关；<br>
**ToastUtils**：toast相关，可以防止toast重复显示；<br>
**WifiUtils**：wifi相关，创建wifi热点、连接wifi、关闭wifi等；<br>
**ParcelableUtils**：序列化存取工具类；

以后我还会持续收集和整理，欢迎pull request，一起维护增强！





