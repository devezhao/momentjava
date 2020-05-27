package cn.devezhao.momentjava.spec;

import java.util.Date;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/28/2017
 */
public interface MomentBase<T> {
	
	String UNIT_YEAR = "year";
	String UNIT_QUARTER = "quarter";
	String UNIT_MONTH = "month";
	String UNIT_WEEK = "week";
	String UNIT_DAY = "day";
	String UNIT_HOUR = "hour";
	String UNIT_MINUTE = "minute";
	String UNIT_SECOND = "second";
	String UNIT_MILLISECOND = "millisecond";

	String UNIT_YEAR_SHORT = "Y";
	String UNIT_QUARTER_SHORT = "Q";
	String UNIT_MONTH_SHORT = "M";
	String UNIT_WEEK_SHORT = "W";
	String UNIT_DAY_SHORT = "D";
	String UNIT_HOUR_SHORT = "h";
	String UNIT_MINUTE_SHORT = "m";
	String UNIT_SECOND_SHORT = "s";
	String UNIT_MILLISECOND_SHORT = "ms";

	/**
	 * @return
	 */
	Date date();
	
}
