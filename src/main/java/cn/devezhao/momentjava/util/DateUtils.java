package cn.devezhao.momentjava.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/23/2017
 */
public class DateUtils {
	
	private static final Map<String, String> JS2JAVA = new HashMap<String, String>();
	static {
		JS2JAVA.put("YYYY", "yyyy");
		JS2JAVA.put("YY", "yy");
		JS2JAVA.put("MM", "MM");
		JS2JAVA.put("M", "M");
		JS2JAVA.put("DD", "dd");
		JS2JAVA.put("D", "d");
		JS2JAVA.put("HH", "hh");
		JS2JAVA.put("H", "h");
		JS2JAVA.put("mm", "mm");
		JS2JAVA.put("m", "m");
		JS2JAVA.put("ss", "ss");
		JS2JAVA.put("s", "s");
	}
	
	/**
	 * 解析日期
	 * 
	 * @param source
	 * @return
	 * @throws DateFormatException
	 */
	public static Date parse(String source) {
		try {
			if (source.length() == 6) {
				return new SimpleDateFormat("yyMMdd").parse(source);
			} else if (source.length() == 8) {
				return new SimpleDateFormat("yyyyMMdd").parse(source);
			} else if (source.length() == 8) {
				return new SimpleDateFormat("yyyy-MM-dd").parse(source);
			} else if (source.length() == 19) {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
			} else {
				throw new DateFormatException("source = " + source);
			}
		} catch(DateFormatException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new DateFormatException("source = " + source, ex);
		}
	}
	
	/**
	 * 解析日期
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 * @throws DateFormatException
	 */
	public static Date parse(String source, String pattern) {
		try {
			if (source.length() == 6) {
				return new SimpleDateFormat("yyMMdd").parse(source);
			} else if (source.length() == 8) {
				return new SimpleDateFormat("yyyyMMdd").parse(source);
			} else if (source.length() == 8) {
				return new SimpleDateFormat("yyyy-MM-dd").parse(source);
			} else if (source.length() == 19) {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
			} else {
				throw new DateFormatException("source = " + source);
			}
		} catch(DateFormatException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new DateFormatException("source = " + source, ex);
		}
	}
	
	/**
	 * 获取当前日期到指定日期的相差天数，如果结束日期小于当前日期将返回负数
	 * 
	 * @param end
	 * @return
	 */
	public static int getDayLeft(Date end) {
		return getDayLeft(now(), end);
	}
	
	/**
	 * 获取开始日期到结束日期的相差天数，如果结束日期小于开始日期将返回负数
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int getDayLeft(Date begin, Date end) {
		begin = startTime(begin);
		end = startTime(end);
		int ct = begin.compareTo(end);
		if (ct == 0) {
			return 0;
		}
		
		if (ct < 0) {
			Calendar cal = calendar(begin);
			int days = -1;
			while (cal.getTime().compareTo(end) <= 0) {
				days++;
				cal.add(Calendar.DAY_OF_YEAR, 1);
			}
			return days;
		} else {
			Calendar cal = calendar(end);
			int days = -1;
			while (cal.getTime().compareTo(begin) <= 0) {
				days++;
				cal.add(Calendar.DAY_OF_YEAR, 1);
			}
			return -days;
		}
	}
	
	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date now() {
		return Calendar.getInstance().getTime();
	}
	
	/**
	 * 获取日历
	 * 
	 * @return
	 */
	public static Calendar calendar() {
		return Calendar.getInstance();
	}
	
	/**
	 * 获取日历
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar calendar(Date date) {
		Calendar cal = calendar();
		cal.setTime(date);
		return cal;
	}
	
	/**
	 * 设置时间为 <code>00:00:00,000
	 * 
	 * @param date
	 * @return
	 */
	public static Date startTime(Date date) {
		Calendar cal = calendar(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	/**
	 * 设置时间为 <code>23:59:59,999
	 * 
	 * @param date
	 * @return
	 */
	public static Date endTime(Date date) {
		Calendar cal = calendar(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}
}
