package com.boy.tutils;

import java.io.Serializable;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;

public class BundleUtils {
	
	public static String getString(Bundle bundle, String key) {
		return getString(bundle, key, "");
	}

	private static String getString(Bundle bundle, String key, String df) {
		if (df == null) {
			df = "";
		}
		return getValue(bundle, key, df);
	}

	public static boolean getBoolean(Bundle bundle, String key, boolean df) {
		return getValue(bundle, key, df);
	}

	public static int getInt(Bundle bundle, String key) {
		return getInt(bundle, key, 0);
	}

	public static int getInt(Bundle bundle, String key, int df) {
		return getValue(bundle, key, df);
	}

	public static double getDouble(Bundle bundle, String key) {
		return getDouble(bundle, key, 0.0);
	}

	public static double getDouble(Bundle bundle, String key, double df) {
		return getValue(bundle, key, df);
	}
	public static long getLong(Bundle bundle, String key) {
		return getLong(bundle, key, 0);
	}
	
	public static long getLong(Bundle bundle, String key, long df) {
		return getValue(bundle, key, df);
	}
	
	public static Parcelable getParcelable(Bundle bundle, String key) {
		return getValue(bundle, key, null);
	}
	public static Serializable getSerializable(Bundle bundle, String key) {
		return getValue(bundle, key, null);
	}
	
	public static <T> ArrayList<T> getArrayList(Bundle bundle, String key) {
		return getValue(bundle, key, null);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getValue(Bundle bundle, String key, T df) {
		if (bundle == null || TextUtils.isEmpty(key)) {
			return df;
		}

		if (df == null) {
			return df;
		}

		if (!bundle.containsKey(key)) {
			return df;
		}
		T value = df;
		Object obj = bundle.get(key);
		if (obj != null && value.getClass().isAssignableFrom(obj.getClass())) {
			value = (T) obj;
		} 
		return value;
	}
}
