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
		System.out.println(Moment.moment().startOf(Moment.UNIT_YEAR).format() + " - year");
		System.out.println(Moment.moment().startOf(Moment.UNIT_MONTH).format() + " - month");
		System.out.println(Moment.moment().startOf(Moment.UNIT_DAY).format() + " - day");
		System.out.println(Moment.moment().startOf(Moment.UNIT_HOUR).format() + " - hour");
		System.out.println(Moment.moment().startOf(Moment.UNIT_MINUTE).format() + " - minute");
		System.out.println(Moment.moment().startOf(Moment.UNIT_SECOND).format() + " - second");
		System.out.println(Moment.moment().startOf(Moment.UNIT_MILLISECOND).format() + " - millisecond");
	}
	
	@Test
	public void testEndOf() throws Exception {
		System.out.println(Moment.moment().endOf(Moment.UNIT_YEAR_SHORT).format() + " - year");
		System.out.println(Moment.moment().endOf(Moment.UNIT_MONTH_SHORT).format() + " - month");
		System.out.println(Moment.moment().endOf(Moment.UNIT_DAY_SHORT).format() + " - day");
		System.out.println(Moment.moment().endOf(Moment.UNIT_HOUR_SHORT).format() + " - hour");
		System.out.println(Moment.moment().endOf(Moment.UNIT_MINUTE_SHORT).format() + " - minute");
		System.out.println(Moment.moment().endOf(Moment.UNIT_SECOND_SHORT).format() + " - second");
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
