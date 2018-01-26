package org.corgiking.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.Era;
import java.time.format.DateTimeFormatter;

public class LocalDateTest {

	public static void main(String[] args) {
		// 日期
		LocalDate toDay = LocalDate.now();
		System.out.println("今天的日期：" + toDay);
		LocalDateTime toDayZero = toDay.atStartOfDay();
		System.out.println("今天的凌晨：" + toDayZero);
		LocalDateTime atTime = toDay.atTime(12, 12, 12);
		System.out.println("今天的12时12分12秒：" + atTime);
		DayOfWeek dayOfWeek = toDay.getDayOfWeek();
		System.out.println("DayOfWeek:" + dayOfWeek);
		int dayOfMonth = toDay.getDayOfMonth();
		System.out.println("DayOfMonth:" + dayOfMonth);
		int dayOfYear = toDay.getDayOfYear();
		System.out.println("DayOfYear:" + dayOfYear);
		Era era = toDay.getEra();
		System.out.println("纪元：" + era);
		
		//date->string
		String date = toDay.format(DateTimeFormatter.BASIC_ISO_DATE);
		System.out.println(date);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年  MM月  dd日");
		String date2 = toDay.format(formatter);
		System.out.println(date2);
		
		//string->date
		LocalDate day = LocalDate.parse("2018年  01月  11日", formatter);
		System.out.println(day);
	}

}
