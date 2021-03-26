package cn.devezhao.momentjava.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/23/2017
 */
public class DateUtils {
	
	/**
	 * 解析日期
	 * 
	 * @param source
	 * @return
	 * @throws DateFormatException
	 */
	public static Date parse(String source) {
		return parse(source, null);
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
		    if (pattern != null) {
                return new SimpleDateFormat(pattern).parse(source);
            } else if (source.length() == 6) {
				return new SimpleDateFormat("yyMMdd").parse(source);
			} else if (source.length() == 8) {
				return new SimpleDateFormat("yyyyMMdd").parse(source);
			} else if (source.length() == 10) {
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
		return (int) subtract(end, begin, Calendar.DAY_OF_YEAR);
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
	
	/**
	 * 两个日期的相差值 <code>subtrahend - minuend
	 * 
	 * @param subtrahend
	 * @param minuend
	 * @param field
	 * @return
	 */
	public static long subtract(Date subtrahend, Date minuend, int field) {
		BigDecimal smLeft = BigDecimal.valueOf(subtrahend.getTime())
				.subtract(BigDecimal.valueOf(minuend.getTime()));
		
		// 毫秒
		if (field == Calendar.MILLISECOND) {
			return smLeft.longValue();
		}
		// 秒
		if (field == Calendar.SECOND) {
			return subtractFormat(smLeft, 1000);
		}
		// 分
		if (field == Calendar.MINUTE) {
			return subtractFormat(smLeft, 1000 * 60);
		}
		// 时
		if (field == Calendar.HOUR || field == Calendar.HOUR_OF_DAY) {
			return subtractFormat(smLeft, 1000 * 60 * 60);
		}
		// 天
		if (field == Calendar.DAY_OF_YEAR || field == Calendar.DAY_OF_MONTH) {
			return subtractFormat(smLeft,  1000 * 60 * 60 * 24);
		}
		
		Calendar c1 = Calendar.getInstance(),
				 c2 = Calendar.getInstance();
		c1.setTime(subtrahend);
		c2.setTime(minuend);
		long yearLeft = c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
		// 月
		if (field == Calendar.MONTH) {
			long monthLeft = c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
			monthLeft += (yearLeft * 12);
			return monthLeft;
		}
		// 年
		if (field == Calendar.YEAR) {
			return yearLeft;
		}
		
		throw new UnsupportedOperationException("field: " + field);
	}
	
	private static long subtractFormat(BigDecimal x, long divisor) {
		x = x.divide(BigDecimal.valueOf(divisor), 2, RoundingMode.HALF_UP);
		return x.setScale(0, RoundingMode.UP).longValue();
	}
}
