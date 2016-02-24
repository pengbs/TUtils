package com.boy.tutils;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class DeviceUtils {
	
	private static final String ASSET_MANAGER_PATH = "android.content.res.AssetManager";
	private static final String PACKAGE_PARSER_PATH = "android.content.pm.PackageParser";
	
	/**
	 * dp转px
	 */
	public static int dip2px(Context mContext, float dpValue) {
		final float scale = mContext.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * px转dp
	 */
	public static int px2dip(Context mContext, float pxValue) {
		final float scale = mContext.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * sp转px
	 */
	public static int sp2px(Context mContext, int sp) {
		final float scale = mContext.getResources().getDisplayMetrics().scaledDensity;
		return (int) (sp * scale + 0.5f);
	}

	/**
	 * 取屏幕宽度
	 * 
	 * @return
	 */
	public static int getScreenWidth(Context mContext) {
		DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}

	/**
	 * 取屏幕高度
	 * 
	 * @return
	 */
	public static int getScreenHeight(Context mContext) {
		DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
		return dm.heightPixels - getStatusBarHeight(mContext);
	}

	/**
	 * 取屏幕高度包含状态栏高度
	 * 
	 * @return
	 */
	public static int getScreenHeightWithStatusBar(Context mContext) {
		DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
		return dm.heightPixels;
	}

	/**
	 * 取导航栏高度
	 * 
	 * @return
	 */
	public static int getNavigationBarHeight(Context mContext) {
		int result = 0;
		int resourceId = mContext.getResources().getIdentifier(
				"navigation_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = mContext.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	/**
	 * 取状态栏高度
	 * 
	 * @return
	 */
	public static int getStatusBarHeight(Context mContext) {
		int result = 0;
		int resourceId = mContext.getResources().getIdentifier(
				"status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = mContext.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	public static int getActionBarHeight(Context mContext) {
		int actionBarHeight = 0;

		final TypedValue tv = new TypedValue();
		if (mContext.getTheme().resolveAttribute(android.R.attr.actionBarSize,
				tv, true)) {
			actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,
					mContext.getResources().getDisplayMetrics());
		}
		return actionBarHeight;
	}

	/** 关闭键盘 **/
	public static void hideSoftInput(View paramEditText, Context context) {
		((InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE))
				.hideSoftInputFromWindow(paramEditText.getWindowToken(), 0);
	}

	public static boolean hasCamera(Context mContext) {
		Boolean _hasCamera = null;
		if (_hasCamera == null) {
			PackageManager pckMgr = mContext.getPackageManager();
			boolean flag = pckMgr
					.hasSystemFeature("android.hardware.camera.front");
			boolean flag1 = pckMgr.hasSystemFeature("android.hardware.camera");
			boolean flag2;
			if (flag || flag1)
				flag2 = true;
			else
				flag2 = false;
			_hasCamera = Boolean.valueOf(flag2);
		}
		return _hasCamera.booleanValue();
	}

	public static void gotoMarket(Context context, String pck) {
		if (!isHaveMarket(context)) {
			return;
		}
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("market://details?id=" + pck));
		if (intent.resolveActivity(context.getPackageManager()) != null) {
			context.startActivity(intent);
		}
	}

	public static boolean isHaveMarket(Context context) {
		Intent intent = new Intent();
		intent.setAction("android.intent.action.MAIN");
		intent.addCategory("android.intent.category.APP_MARKET");
		PackageManager pm = context.getPackageManager();
		List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
		return infos.size() > 0;
	}

	public static void openAppInMarket(Context context) {
		if (context != null) {
			String pckName = context.getPackageName();
			try {
				gotoMarket(context, pckName);
			} catch (Exception ex) {
				try {
					String otherMarketUri = "http://market.android.com/details?id="
							+ pckName;
					Intent intent = new Intent(Intent.ACTION_VIEW,
							Uri.parse(otherMarketUri));
					context.startActivity(intent);
				} catch (Exception e) {

				}
			}
		}
	}

	public static boolean gotoGoogleMarket(Activity activity, String pck) {
		try {
			Intent intent = new Intent();
			intent.setPackage("com.android.vending");
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("market://details?id=" + pck));
			activity.startActivity(intent);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 是否有sd卡
	 * 
	 * @return
	 */
	public static boolean isSdcardReady() {
		return Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState());
	}

	public static PackageInfo getPackageInfo(Context context, String pckName) {
		try {
			return context.getPackageManager().getPackageInfo(pckName, 0);
		} catch (NameNotFoundException e) {
		}
		return null;
	}

	/**
	 * 取APP版本号
	 * 
	 * @return
	 */
	public static int getVersionCode(Context context) {
		int versionCode = 0;
		try {
			versionCode = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionCode;
		} catch (PackageManager.NameNotFoundException ex) {
			versionCode = 0;
		}
		return versionCode;
	}

	/**
	 * 取APP版本名
	 * 
	 * @return
	 */
	public static String getVersionName(Context context) {
		String name = "";
		try {
			name = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0).versionName;
		} catch (PackageManager.NameNotFoundException ex) {
			name = "";
		}
		return name;
	}

	public static String getIMEI(Context context) {
		TelephonyManager tel = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return tel.getDeviceId();
	}
	
	/**
     * 获取设备唯一标识
     * @param context
     * @return
     */
    public static String getUUID(Context context) {
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String uniqueId = deviceUuid.toString();

        return uniqueId;
    }
	
	/**
	 * 获取系统版本
	 * @return
	 */
	public static int getAndroidSystemVersion() {
		return android.os.Build.VERSION.SDK_INT;
	}

	/**
	 * 获取手机名称
	 * 
	 * @return
	 */
	public static String getPhoneType() {
		return android.os.Build.MODEL;
	}

	/**
	 * 安装apk
	 * 
	 * @param context
	 * @param file
	 */
	public static void installAPK(Context context, File file) {
		if (file == null || !file.exists())
			return;
		Intent intent = new Intent();
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}

	/**
	 * 卸载应用
	 * 
	 * @param context
	 * @param packageName
	 */
	public static void uninstallApk(Context context, String packageName) {
		PackageInfo info = getPackageInfo(context, packageName);
		if (info != null) {
			Uri packageURI = Uri.parse("package:" + packageName);
			Intent uninstallIntent = new Intent(Intent.ACTION_DELETE,
					packageURI);
			context.startActivity(uninstallIntent);
		}
	}

	/**
	 * 打开拨号盘
	 * 
	 * @param context
	 * @param number
	 */
	public static void openDial(Context context, String number) {
		Uri uri = Uri.parse("tel:" + number);
		Intent it = new Intent(Intent.ACTION_DIAL, uri);
		context.startActivity(it);
	}

	/**
	 * 发送短信
	 * 
	 * @param context
	 * @param smsBody
	 * @param tel
	 */
	public static void openSMS(Context context, String smsBody, String tel) {
		Uri uri = Uri.parse("smsto:" + tel);
		Intent it = new Intent(Intent.ACTION_SENDTO, uri);
		it.putExtra("sms_body", smsBody);
		context.startActivity(it);
	}

	/**
	 * 发送邮件
	 * 
	 * @param context
	 * @param subject
	 *            主题
	 * @param content
	 *            内容
	 * @param emails
	 *            邮件地址
	 */
	public static void sendEmail(Context context, String subject,
			String content, String... emails) {
		try {
			Intent intent = new Intent(Intent.ACTION_SEND);
			// 模拟器
			// intent.setType("text/plain");
			intent.setType("message/rfc822"); // 真机
			intent.putExtra(android.content.Intent.EXTRA_EMAIL, emails);
			intent.putExtra(Intent.EXTRA_SUBJECT, subject);
			intent.putExtra(Intent.EXTRA_TEXT, content);
			context.startActivity(intent);
		} catch (ActivityNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 是否有网络
	 * 
	 * @return
	 */
	public static boolean isNetWorkAvilable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 获取当前网络类型
	 * 
	 * @return 0：没有网络 1：WIFI网络 2：WAP网络 3：NET网络
	 */
	public static int getNetworkType(Context context) {
		int netType = 0;
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo == null) {
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			String extraInfo = networkInfo.getExtraInfo();
			if (!TextUtils.isEmpty(extraInfo)) {
				if (extraInfo.toLowerCase().equals("cmnet")) {
					netType = 3;
				} else {
					netType = 2;
				}
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = 1;
		}
		return netType;
	}

	/**
	 * 判断应用是否在前台
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isApplicationInBackground(Context context) {
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> taskList = am.getRunningTasks(1);
		if (taskList != null && !taskList.isEmpty()) {
			ComponentName topActivity = taskList.get(0).topActivity;
			if (topActivity != null
					&& !topActivity.getPackageName().equals(
							context.getPackageName())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 根据包名获取appIcon
	 * @param context
	 * @param packageName
	 * @return
	 */
	public static Drawable loadIconFromPackageName(Context context, String packageName) {
		if (context == null) {
			return null;
		}
		Drawable icon = null;
		final PackageManager pm = context.getPackageManager();
		if (pm != null) {
			try {
				icon = pm.getApplicationIcon(packageName);
			} catch (NameNotFoundException e) {
				e.printStackTrace();
				icon = null;
			}
		}
		return icon;
	}
	
	/**
	 * 根据apk路径获取appIcon
	 * @param context
	 * @param packageName
	 * @return
	 */
	public static Drawable loadIconFromAPK(Context context, String apkFullPath) {
		if (context == null || apkFullPath == null || apkFullPath.equals("")) {
			return null;
		}
		File apkFile = new File(apkFullPath);
		if (!apkFile.exists()) {
			return null;
		}
		Drawable icon = null;

		try {
			Class pkgParserCls = Class.forName(PACKAGE_PARSER_PATH);
			Class[] typeArgs = new Class[1];
			typeArgs[0] = String.class;
			Constructor pkgParserCt = pkgParserCls.getConstructor(typeArgs);
			Object[] valueArgs = new Object[1];
			valueArgs[0] = apkFullPath;
			Object pkgParser = pkgParserCt.newInstance(valueArgs);

			typeArgs = new Class[4];
			typeArgs[0] = File.class;
			typeArgs[1] = String.class;
			typeArgs[2] = DisplayMetrics.class;
			typeArgs[3] = Integer.TYPE;
			Method mPkgParserParsePackageMtd = pkgParserCls.getDeclaredMethod("parsePackage",
					typeArgs);

			DisplayMetrics metrics = new DisplayMetrics();
			metrics.setToDefaults();
			valueArgs = new Object[4];
			valueArgs[0] = apkFile;
			valueArgs[1] = apkFullPath;
			valueArgs[2] = metrics;
			valueArgs[3] = 0;
			Object pkgParserPkg = mPkgParserParsePackageMtd.invoke(pkgParser, valueArgs);

			Field appInfoFld = pkgParserPkg.getClass().getDeclaredField("applicationInfo");
			ApplicationInfo info = (ApplicationInfo) appInfoFld.get(pkgParserPkg);
			Class assetMagCls = Class.forName(ASSET_MANAGER_PATH);
			Constructor assetMagCt = assetMagCls.getConstructor((Class[]) null);
			Object assetMag = assetMagCt.newInstance((Object[]) null);
			typeArgs = new Class[1];
			typeArgs[0] = String.class;
			Method mMssetMagaddAssetPathMtd = assetMagCls.getDeclaredMethod("addAssetPath",
					typeArgs);
			valueArgs = new Object[1];
			valueArgs[0] = apkFullPath;
			mMssetMagaddAssetPathMtd.invoke(assetMag, valueArgs);

			Resources res = context.getResources();
			typeArgs = new Class[3];
			typeArgs[0] = assetMag.getClass();
			typeArgs[1] = res.getDisplayMetrics().getClass();
			typeArgs[2] = res.getConfiguration().getClass();
			Constructor resCt = Resources.class.getConstructor(typeArgs);
			valueArgs = new Object[3];
			valueArgs[0] = assetMag;
			valueArgs[1] = res.getDisplayMetrics();
			valueArgs[2] = res.getConfiguration();
			res = (Resources) resCt.newInstance(valueArgs);

			icon = res.getDrawable(info.icon);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return icon;
	}

	/**
	 * 从apk文件获取包名
	 * @param context
	 * @param apkFile
	 * @return
	 */
	public static String getPackageNameFromApk(Context context, File apkFile) {
		if (context == null || apkFile == null || !apkFile.exists()) {
			return null;
		}
		PackageManager pm = context.getPackageManager();
		PackageInfo pi = pm.getPackageArchiveInfo(apkFile.getAbsolutePath(),
				PackageManager.GET_ACTIVITIES);
		if (pi != null) {
			return pi.packageName;
		}
		return null;
	}
	
	/**
	 * 设置硬件加速
	 *
	 * @param view
	 * @param accelerate
	 */
	public static void setHardwareAccelerated(View view, int mode) {
		if (Build.VERSION.SDK_INT < 11) {
			return;
		}
		Method sAcceleratedMethod = null;
		try {
			if (null == sAcceleratedMethod) {
				sAcceleratedMethod = View.class.getMethod("setLayerType", new Class[] {
						Integer.TYPE, Paint.class });
			}
			sAcceleratedMethod.invoke(view, new Object[] { Integer.valueOf(mode), null });
		} catch (Throwable e) {
		}
	}

}
