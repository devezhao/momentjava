package cn.devezhao.momentjava;

import org.junit.Test;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 04/14/2017
 */
public class MomentCalendarTest {

	@Test
	public void testSubtract() {
		System.out.println(Moment.moment().subtract(1, Moment.UNIT_MONTH).format());
		System.out.println(Moment.moment().subtract(1, Moment.UNIT_YEAR).format());
	}
	
	@Test
	public void testAdd() {
		System.out.println(Moment.moment().add(1, Moment.UNIT_MONTH).format());
		System.out.println(Moment.moment().add(1, Moment.UNIT_HOUR).format());
	}
	
	@Test
	public void testCalendar() {
		System.out.println("--");
		for (int i = -1; i < 10; i++) {
			System.out.println(Moment.moment().add(i, Moment.UNIT_DAY).calendar());
		}
	}
}