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
		System.out.println(Moment.moment().subtract(1, "M").format());
		System.out.println(Moment.moment().subtract(1, "y").format());
	}
	
	@Test
	public void testAdd() {
		System.out.println(Moment.moment().add(1, "M").format());
		System.out.println(Moment.moment().add(1, "h").format());
	}
	
	@Test
	public void testCalendar() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Moment.moment().subtract(i, "d").calendar());
		}
		
		System.out.println("--");
		
		for (int i = 0; i < 10; i++) {
			System.out.println(Moment.moment().add(i, "d").calendar());
		}
	}
}