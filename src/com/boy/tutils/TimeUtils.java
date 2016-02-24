package com.boy.tutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {
	
	 /**
     * 返回指定转换格式当前系统时间
     */
    public static String getDataTime(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }
	
	/**
	 * 将时间戳转为代表"距现在多久之前"的字符串
	 * 
	 * @param timeStr
	 *            时间戳
	 * @return
	 */
	public static String getStandardDate(long t) {
		
		StringBuffer sb = new StringBuffer();

		long time = System.currentTimeMillis() - (t * 1000);
		long mill = (long) Math.ceil(time / 1000);// 秒前

		long minute = (long) Math.ceil(time / 60 / 1000);// 分钟前

		long hour = (long) Math.ceil(time / 60 / 60 / 1000);// 小时

		long day = (long) Math.ceil(time / 24 / 60 / 60 / 1000);// 天前

		if (day - 1 > 0) {
			if (day > 7) {
				sb.append(getDateToString(t*1000, "yyyy/MM/dd"));
			}else {
				sb.append(day + "天前");
			}
		} else if (hour - 1 > 0) {
			if (hour >= 24) {
				sb.append("1天前");
			} else {
				sb.append(hour + "小时前");
			}
		} else if (minute - 1 > 0) {
			if (minute >= 60) {
				sb.append("1小时前");
			} else {
				sb.append(minute + "分钟前");
			}
		} else if (mill - 1 > 0) {
			if (mill >= 60) {
				sb.append("1分钟前");
			} else {
				sb.append(mill + "秒前");
			}
		} else {
			sb.append("刚刚");
		}
		return sb.toString();
	}
	
	/**
	 * 日期格式字符串转换成时间戳
	 * 
	 * @param date
	 *            字符串日期
	 * @param format
	 *            如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static long date2TimeStamp(String date_str, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(date_str).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * @param time
	 *            时间戳
	 * @param strFormat
	 *            转换时间格式
	 * @return 返回自定义格式时间
	 */
	public static String getDateToString(long time, String strFormat) {
		Date d = new Date(time);
		SimpleDateFormat sf = new SimpleDateFormat(strFormat);
		return sf.format(d);
	}
	
	/**
	 * @param pTime 字符串格式日期
	 * @return
	 * 判断指定日期为星期几
	 */
	public static String getWeek(String pTime, String format) {

		String strWeek = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(pTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int day = c.get(Calendar.DAY_OF_WEEK) - 1;
		switch (day) {
		case 0:
			strWeek = "周日";
			break;
		case 1:
			strWeek = "周一";
			break;
		case 2:
			strWeek = "周二";
			break;
		case 3:
			strWeek = "周三";
			break;
		case 4:
			strWeek = "周四";
			break;
		case 5:
			strWeek = "周五";
			break;
		case 6:
			strWeek = "周六";
			break;
		}
		return strWeek;
	}
	
	/**
	 * @param pTime
	 * @param format
	 * @return
	 * 判断当前字符串日期是否为今天
	 */
	public static boolean isToday(String pTime, String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String nowTime = sdf.format(new Date());
		try {
			if(sdf.parse(pTime).compareTo(sdf.parse(nowTime)) == 0){
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	  /** 
     * 根据年 月 获取对应的月份 天数 
     * */  
    public static int getDaysByYearMonth(int year, int month) {  
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, year);  
        a.set(Calendar.MONTH, month - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }
    
    /**
     * 获取当前时间为每年第几周
     * 
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        int week = c.get(Calendar.WEEK_OF_YEAR) - 1;
        week = week == 0 ? 52 : week;
        return week > 0 ? week : 1;
    }

}
