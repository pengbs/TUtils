package com.boy.tutils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

public class ToastUtils {
	
	private static Toast mToast;
	public static void showShortToast(Context context, int resId) {
        show(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }
	
	public static void showShortToast(Context context, CharSequence text) {
	        show(context, text, Toast.LENGTH_SHORT);
	    }
	 
    public static void showLongToast(Context context, int resId) {
        show(context, context.getResources().getText(resId), Toast.LENGTH_LONG);
    }
    
    public static void showLongToast(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_LONG);
    }

    public static void show(Context context, CharSequence text, int duration) {
    	if (mToast == null) {
    		mToast = Toast.makeText(context, text, duration);
		}else {
			mToast.setText(text);
			mToast.setDuration(duration);
		}
    	mToast.show();
    }
    
    public static void cancel(){
    	if (mToast != null) {
			mToast.cancel();
			mToast = null;
		}
    }

    public static void showShortToast(Context context, int resId, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), Toast.LENGTH_SHORT);
    }

    public static void showShortToast(Context context, String format, Object... args) {
        show(context, String.format(format, args), Toast.LENGTH_SHORT);
    }
    
    public static void showLongToast(Context context, int resId, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), Toast.LENGTH_LONG);
    }
    
    public static void showLongToast(Context context, String format, Object... args) {
    	show(context, String.format(format, args), Toast.LENGTH_LONG);
    }
}
