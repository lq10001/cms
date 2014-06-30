package com.ly.comm;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CommDate {

	public static void main(String[] args) {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		CommDate test = new CommDate();

		// Date

		Date currentDate = new Date();

		System.out.println("当前日期是：" + df.format(currentDate));

		System.out.println("一周后的日期是：" + df.format(test.nextWeek(currentDate)));

		System.out.println("一月后的日期是：" + df.format(test.nextMonth(currentDate)));

		System.out.println("一年后的日期是：" + df.format(test.nextYear(currentDate)));


		System.out.println("当前日期是：" + df.format(currentDate));

		System.out.println("上一周的日期是：" + df.format(test.preWeek(currentDate)));

		System.out.println("上一月的日期是：" + df.format(test.preMonth(currentDate)));

		System.out.println("上一年的日期是：" + df.format(test.preYear(currentDate)));

	}

	// 获取下一周的日期

	public Date nextWeek(Date currentDate) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(currentDate);
		cal.add(GregorianCalendar.DATE, 7);// 在日期上加7天
		return cal.getTime();
	}
	

	// 获取本周日的日期

	public Date getSunday(Date monday) {

		GregorianCalendar cal = new GregorianCalendar();

		cal.setTime(monday);

		cal.add(GregorianCalendar.DATE, 6);// 在日期上加6天

		return cal.getTime();

	}

	// 获取下一月的日期

	public Date nextMonth(Date currentDate) {

		GregorianCalendar cal = new GregorianCalendar();

		cal.setTime(currentDate);

		cal.add(GregorianCalendar.MONTH, 1);// 在月份上加1

		return cal.getTime();

	}

	// 获取下一年的日期

	public Date nextYear(Date currentDate) {

		GregorianCalendar cal = new GregorianCalendar();

		cal.setTime(currentDate);

		cal.add(GregorianCalendar.YEAR, 1);// 在年上加1

		return cal.getTime();

	}
	
	// 获取上一周的日期

	public Date preWeek(Date currentDate) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(currentDate);
		cal.add(GregorianCalendar.DATE, -7);// 在日期上加7天
		return cal.getTime();
	}

	// 获取上一月的日期

	public Date preMonth(Date currentDate) {

		GregorianCalendar cal = new GregorianCalendar();

		cal.setTime(currentDate);

		cal.add(GregorianCalendar.MONTH, -1);// 在月份上加1

		return cal.getTime();

	}

	// 获取上一年的日期

	public Date preYear(Date currentDate) {

		GregorianCalendar cal = new GregorianCalendar();

		cal.setTime(currentDate);

		cal.add(GregorianCalendar.YEAR, -1);// 在年上加1

		return cal.getTime();

	}

	// 获取当前季度
	public int getQuarter() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		return month / 3;
	}
}