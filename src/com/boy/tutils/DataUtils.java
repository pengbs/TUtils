package com.boy.tutils;

import java.util.List;
import java.util.Map;

public class DataUtils {

	/**
	 * @param sourceList
	 * @return 获取list大小
	 */
	public static <V> int getSize(List<V> sourceList) {
		return sourceList == null ? 0 : sourceList.size();
	}

	/**
	 * @param sourceMap
	 * @return 获取Map大小
	 */
	public static <K, V> int getSize(Map<K, V> sourceMap) {
		return sourceMap == null ? 0 : sourceMap.size();
	}

	/**
	 * @param sourceList
	 * @return if list is null or its size is 0, return true, else return false.
	 */
	public static <V> boolean isEmpty(List<V> sourceList) {
		return (sourceList == null || sourceList.size() == 0);
	}

	/**
	 * @param sourceMap
	 * @return if map is null or its size is 0, return true, else return false.
	 */
	public static <K, V> boolean isEmpty(Map<K, V> sourceMap) {
		return (sourceMap == null || sourceMap.size() == 0);
	}

	/**
	 * is null or its length is 0 or is "null"
	 * 
	 * @param str
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.length() == 0 || "null".equals(str) || str
				.trim().length() == 0);
	}

}
