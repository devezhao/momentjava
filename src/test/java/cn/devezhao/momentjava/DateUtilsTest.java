package cn.devezhao.momentjava;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import cn.devezhao.momentjava.util.DateUtils;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 12/08/2017
 */
public class DateUtilsTest {

	@Test
	public void testSubtract() throws Exception {
		Date subtrahend = DateUtils.parse("2017-09-01");
		Date minuend = DateUtils.parse("2015-11-01");
		
		long dayLeft = DateUtils.subtract(subtrahend, minuend, Calendar.DAY_OF_MONTH);
		System.out.println(dayLeft + " " + DateUtils.getDayLeft(minuend, subtrahend));
		
		long monthLeft = DateUtils.subtract(subtrahend, minuend, Calendar.MONTH);
		System.out.println(monthLeft);
		
		long yearLeft = DateUtils.subtract(subtrahend, minuend, Calendar.YEAR);
		System.out.println(yearLeft);
	}
}
