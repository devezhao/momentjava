package cn.devezhao.momentjava.util;

import java.text.SimpleDateFormat;
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
}
