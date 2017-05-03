package cn.devezhao.momentjava.spec;

import java.util.Date;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/28/2017
 */
public interface MomentBase<T> {
	
	public static final String UNIT_YEAR = "year";
	public static final String UNIT_MONTH = "month";
	public static final String UNIT_DAY = "day";
	public static final String UNIT_HOUR = "hour";
	public static final String UNIT_MINUTE = "minute";
	public static final String UNIT_SECOND = "second";
	public static final String UNIT_MILLISECOND = "millisecond";
	
	public static final String UNIT_YEAR_SHORT = "y";
	public static final String UNIT_MONTH_SHORT = "M";
	public static final String UNIT_DAY_SHORT = "d";
	public static final String UNIT_HOUR_SHORT = "h";
	public static final String UNIT_MINUTE_SHORT = "m";
	public static final String UNIT_SECOND_SHORT = "s";
	public static final String UNIT_MILLISECOND_SHORT = "ms";
	
	
	/**
	 * @return
	 */
	Date date();
	
}
