package com.boy.tutils;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

public class ToastUtils {

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
    	if (!Thread.currentThread().getName().equals("main")) {
			Looper.prepare();
			Toast.makeText(context, text, duration).show();
			Looper.loop();
		} else {
			Toast.makeText(context, text, duration).show();
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
