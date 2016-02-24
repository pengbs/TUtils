package com.boy.tutils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

public class JsonUtils {

	public static String getString(JSONObject data, String key) {
		String ret = "";
		if (data.has(key) && !data.isNull(key)) {
			try {
				ret = data.getString(key);
				if (!TextUtils.isEmpty(ret)) {
					ret = "";
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static String getString(String jsonData, String key) {
		if (TextUtils.isEmpty(jsonData)) {
			return "";
		}

		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			return getString(jsonObject, key);
		} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static long getLong(JSONObject data, String key) {
		long ret = 0;
		if (data.has(key) && !data.isNull(key)) {
			try {
				ret = data.getLong(key);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static long getLong(String jsonData, String key) {
		if (TextUtils.isEmpty(jsonData)) {
			return 0;
		}

		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			return getLong(jsonObject, key);
		} catch (JSONException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static int getInt(JSONObject data, String key) {
		int ret = 0;
		if (data.has(key) && !data.isNull(key)) {
			try {
				ret = data.getInt(key);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static int getInt(String jsonData, String key) {
		if (TextUtils.isEmpty(jsonData)) {
			return 0;
		}

		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			return getInt(jsonObject, key);
		} catch (JSONException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static boolean getBoolean(JSONObject data, String key) {
		boolean ret = false;
		if (data.has(key) && !data.isNull(key)) {
			try {
				ret = data.getBoolean(key);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static boolean getBoolean(String jsonData, String key) {
		if (TextUtils.isEmpty(jsonData)) {
			return false;
		}

		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			return getBoolean(jsonObject, key);
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static double getDouble(JSONObject data, String key) {
		double ret = 0;
		if (data.has(key) && !data.isNull(key)) {
			try {
				ret = data.getDouble(key);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static double getDouble(String jsonData, String key) {
		if (TextUtils.isEmpty(jsonData)) {
			return 0;
		}

		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			return getDouble(jsonObject, key);
		} catch (JSONException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static JSONArray getJSONArray(String jsonData, String key) {
		if (TextUtils.isEmpty(jsonData)) {
			return null;
		}

		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			return getJSONArray(jsonObject, key);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static JSONArray getJSONArray(JSONObject jsonObject, String key) {
		if (jsonObject == null || TextUtils.isEmpty(key)) {
			return null;
		}

		try {
			return jsonObject.getJSONArray(key);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static JSONObject getJSONObject(JSONObject jsonObject, String key) {
		if (jsonObject == null || TextUtils.isEmpty(key)) {
			return null;
		}

		try {
			return jsonObject.getJSONObject(key);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static JSONObject getJSONObject(String jsonData, String key) {
		if (TextUtils.isEmpty(jsonData)) {
			return null;
		}

		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			return getJSONObject(jsonObject, key);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

}
