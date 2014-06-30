package com.ly.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期类型与字符串类型相互转换工具类
 * 
 * @author xiaoqz
 * @since 2008-8
 */
public class DateUtils {

	/**
	 * Number of milliseconds in a standard second.
	 */
	public static final long MILLIS_PER_SECOND = 1000;
	/**
	 * Number of milliseconds in a standard minute.
	 */
	public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
	/**
	 * Number of milliseconds in a standard hour.
	 */
	public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
	/**
	 * Number of milliseconds in a standard day.
	 */
	public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

	/**
	 * ISO8601 formatter for date without time zone. The format used is
	 * <tt>yyyy-MM-dd</tt>.
	 */
	public static final FastDateFormat DATE_FORMAT = FastDateFormat
			.getInstance("yyyy-MM-dd");

	/**
	 * ISO8601 formatter for date-time without time zone. The format used is
	 * <tt>yyyy-MM-dd HH:mm:ss</tt>.
	 */
	public static final FastDateFormat DATETIME_FORMAT = FastDateFormat
			.getInstance("yyyy-MM-dd HH:mm:ss");

	/**
	 * Compact ISO8601 date format yyyyMMdd
	 */
	public static final String COMPACT_DATE_FORMAT_PATTERN = "yyyyMMdd";

	/**
	 * ISO8601 date format: yyyy-MM-dd
	 */
	public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";

	/**
	 * ISO8601 date-time format: yyyy-MM-dd HH:mm:ss
	 */
	public static String DATETIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static Date parse(String str) {
		return parse(str, DATE_FORMAT_PATTERN);
	}

	public static Date parse(String str, String pattern) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		DateFormat parser = new SimpleDateFormat(pattern);
		try {
			return parser.parse(str);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Can't parse " + str + " using "
					+ pattern);
		}
	}

	/**
	 * 根据时间变量返回时间字符串
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		FastDateFormat df = FastDateFormat.getInstance(pattern);
		return df.format(date);
	}

	/**
	 * return date format is <code>yyyy-MM-dd</code>
	 */
	public static String format(Date date) {
		return date == null ? null : DATE_FORMAT.format(date);
	}

	/**
	 * return date format is <code>yyyy-MM-dd</code>
	 */
	public static String getCurrentDateAsString() {
		return DATE_FORMAT.format(new Date());
	}

	/**
	 * return date format is <code>pattern</code>
	 */
	public static String getCurrentDateAsString(String pattern) {
		FastDateFormat formatter = FastDateFormat.getInstance(pattern);
		return formatter.format(new Date());
	}

	/**
	 * return date format is <code>yyyy-MM-dd HH:mm:ss</code>
	 */
	public static String getCurrentDateTimeAsString() {
		return DATETIME_FORMAT.format(new Date());
	}

	public static Date getStartDateTimeOfCurrentMonth() {
		return getStartDateTimeOfMonth(new Date());
	}

	/**
	 * The value of
	 * <ul>
	 * <li>Calendar.HOUR_OF_DAY
	 * <li>Calendar.MINUTE
	 * <li>Calendar.MINUTE
	 * </ul>
	 * will be set 0.
	 */
	public static Date getStartDateTimeOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	public static Date getEndDateTimeOfCurrentMonth() {
		return getEndDateTimeOfMonth(new Date());
	}

	public static Date getEndDateTimeOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	public static Date getStartTimeOfCurrentDate() {
		return getStartTimeOfDate(new Date());
	}

	public static Date getStartTimeOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * <tt>2005-12-27 17:58:56</tt> will be returned as
	 * <tt>2005-12-27 23:59:59</tt>
	 */
	public static Date getEndTimeOfCurrentDate() {
		return getEndTimeOfDate(new Date());
	}

	public static Date getEndTimeOfDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	public static Date addHours(Date date, int hours) {
		return add(date, Calendar.HOUR_OF_DAY, hours);
	}

	public static Date addMinutes(Date date, int minutes) {
		return add(date, Calendar.MINUTE, minutes);
	}

	public static Date addDays(Date date, int days) {
		return add(date, Calendar.DATE, days);
	}

	public static Date addMonths(Date date, int months) {
		return add(date, Calendar.MONTH, months);
	}

	public static Date addYears(Date date, int years) {
		return add(date, Calendar.YEAR, years);
	}

	private static Date add(Date date, int field, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field, amount);
		return cal.getTime();
	}

	/**
	 * 计算两个日期之间的天数
	 */
	public static final int daysBetween(Date early,
			Date late) {
		Calendar ecal = Calendar.getInstance();
		Calendar lcal = Calendar.getInstance();
		ecal.setTime(early);
		lcal.setTime(late);

		long etime = ecal.getTimeInMillis();
		long ltime = lcal.getTimeInMillis();

		return (int) ((ltime - etime) / MILLIS_PER_DAY);
	}
	
	/**
	 * 根据日期获取星期
	 */
	public static final int getWeek(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 7;
		return w;
	}
	
	public static final int getAM(Date date){
		GregorianCalendar   ca   =   new   GregorianCalendar(); 
		ca.setTime(date);
		return ca.get(GregorianCalendar.AM_PM);
	}
}
