package cn.devezhao.momentjava.spec;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/22/2017
 */
public interface MomentFormat {
	
	static final String PATTERN_YEAR = "YYYY";
	static final String PATTERN_YEAR2 = "YY";
	static final String PATTERN_MONTH = "MM";
	static final String PATTERN_MONTH2 = "M";
	static final String PATTERN_DAY = "DD";
	static final String PATTERN_DAY2 = "D";
	static final String PATTERN_HOUR = "hh";
	static final String PATTERN_HOUR2 = "h";
	static final String PATTERN_MINUTE = "mm";
	static final String PATTERN_MINUTE2 = "m";
	static final String PATTERN_SECOND = "s";
	static final String PATTERN_SECOND2 = "ss";
	static final String PATTERN_AM_PM = "a";
	static final String PATTERN_AM_PM2 = "A";

	public String format();
	
	public String format(String pattern);
}
