package com.crif.ticketbooking.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

	private static final SimpleDateFormat SDF_DDMMYYYY = new SimpleDateFormat("ddMMyyyy");

	public static Date getDateDDMMYY(String date) throws ParseException {
		SDF_DDMMYYYY.setTimeZone(TimeZone.getTimeZone("IST"));
		SDF_DDMMYYYY.setLenient(true);
		return SDF_DDMMYYYY.parse(date);
	}

	public static Date removeTime(Date date) throws ParseException {
		String strDate = SDF_DDMMYYYY.format(date);
		return getDateDDMMYY(strDate);
	}
}
