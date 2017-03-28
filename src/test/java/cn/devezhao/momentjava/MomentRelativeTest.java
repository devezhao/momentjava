package cn.devezhao.momentjava;

import org.junit.Test;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/22/2017
 */
public class MomentRelativeTest {

	@Test
	public void testStartOf() throws Exception {
		System.out.println(Moment.moment().startOf("year").format() + " - year");
		System.out.println(Moment.moment().startOf("month").format() + " - month");
		System.out.println(Moment.moment().startOf("day").format() + " - day");
		System.out.println(Moment.moment().startOf("hour").format() + " - hour");
		System.out.println(Moment.moment().startOf("minute").format() + " - minute");
		System.out.println(Moment.moment().startOf("second").format() + " - second");
	}
	
	@Test
	public void testEndOf() throws Exception {
		System.out.println(Moment.moment().endOf("year").format() + " - year");
		System.out.println(Moment.moment().endOf("month").format() + " - month");
		System.out.println(Moment.moment().endOf("day").format() + " - day");
		System.out.println(Moment.moment().endOf("hour").format() + " - hour");
		System.out.println(Moment.moment().endOf("minute").format() + " - minute");
		System.out.println(Moment.moment().endOf("second").format() + " - second");
	}
	
	@Test
	public void testFromNow() throws Exception {
		System.out.println(Moment.moment().fromNow());
		System.out.println(Moment.moment().add(1, "m").fromNow());
		System.out.println(Moment.moment().add(2, "h").fromNow());
		System.out.println(Moment.moment().add(2, "d").fromNow());
		System.out.println(Moment.moment().add(2, "M").fromNow());
		System.out.println(Moment.moment().add(3, "M").fromNow());
		System.out.println(Moment.moment().add(4, "M").fromNow());
	}
}
