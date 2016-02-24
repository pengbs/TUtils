package com.boy.tutils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ActivityUtils {
	
    /**
     * 通过Class跳转界面
     * @param cls
     */
	public static void startActivity(Activity activity, Class<?> cls) {
		if (activity == null || cls == null) {
			return;
		}
        startActivity(activity, cls, null);
    }
    
    /**
     * 含有Bundle通过Class跳转界面
     * @param cls
     */
	public static void startActivity(Activity activity, Class<?> cls, Bundle bundle) {
		if (activity == null || cls == null) {
			return;
		}
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
    }
	
	 /** 含有Bundle通过Class带result跳转界面 **/
    protected void startActivityForResult(Activity activity, Class<?> cls, Bundle bundle, int requestCode) {
    	if (activity == null || cls == null) {
			return;
		}
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, requestCode);
    }
}
