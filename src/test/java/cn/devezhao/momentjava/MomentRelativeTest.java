package cn.devezhao.momentjava;

import org.junit.Test;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/22/2017
 */
public class MomentRelativeTest {

	@Test
	public void testStartOf() {
		System.out.println(Moment.moment().startOf(Moment.UNIT_YEAR).format() + " - year");
		System.out.println(Moment.moment().startOf(Moment.UNIT_MONTH).format() + " - month");
		System.out.println(Moment.moment().startOf(Moment.UNIT_DAY).format() + " - day");
		System.out.println(Moment.moment().startOf(Moment.UNIT_HOUR).format() + " - hour");
		System.out.println(Moment.moment().startOf(Moment.UNIT_MINUTE).format() + " - minute");
		System.out.println(Moment.moment().startOf(Moment.UNIT_SECOND).format() + " - second");
		System.out.println(Moment.moment().startOf(Moment.UNIT_MILLISECOND).format() + " - millisecond");

		System.out.println(Moment.moment().startOf(Moment.UNIT_QUARTER).format() + " - quarter");
		System.out.println(Moment.moment().startOf(Moment.UNIT_WEEK).format() + " - week");
        System.out.println(Moment.moment("20200530").startOf(Moment.UNIT_WEEK).format() + " - week1");
        System.out.println(Moment.moment("20200531").startOf(Moment.UNIT_WEEK).format() + " - week2");
        System.out.println(Moment.moment("20200601").startOf(Moment.UNIT_WEEK).format() + " - week3");
        System.out.println(Moment.moment("20200602").startOf(Moment.UNIT_WEEK).format() + " - week4");
        System.out.println(Moment.moment("20200603").startOf(Moment.UNIT_WEEK).format() + " - week5");
        System.out.println(Moment.moment("20200604").startOf(Moment.UNIT_WEEK).format() + " - week6");
        System.out.println(Moment.moment("20200605").startOf(Moment.UNIT_WEEK).format() + " - week7");
        System.out.println(Moment.moment("20200606").startOf(Moment.UNIT_WEEK).format() + " - week8");
        System.out.println(Moment.moment("20200607").startOf(Moment.UNIT_WEEK).format() + " - week9");
        System.out.println(Moment.moment("20200608").startOf(Moment.UNIT_WEEK).format() + " - week10");
        System.out.println(Moment.moment("20200609").startOf(Moment.UNIT_WEEK).format() + " - week11");
	}
	
	@Test
	public void testEndOf() {
		System.out.println(Moment.moment().endOf(Moment.UNIT_YEAR_SHORT).format() + " - year");
		System.out.println(Moment.moment().endOf(Moment.UNIT_MONTH_SHORT).format() + " - month");
		System.out.println(Moment.moment().endOf(Moment.UNIT_DAY_SHORT).format() + " - day");
		System.out.println(Moment.moment().endOf(Moment.UNIT_HOUR_SHORT).format() + " - hour");
		System.out.println(Moment.moment().endOf(Moment.UNIT_MINUTE_SHORT).format() + " - minute");
		System.out.println(Moment.moment().endOf(Moment.UNIT_SECOND_SHORT).format() + " - second");

        System.out.println(Moment.moment().endOf(Moment.UNIT_QUARTER).format() + " - quarter");
        System.out.println(Moment.moment().endOf(Moment.UNIT_WEEK).format() + " - week");
        System.out.println(Moment.moment("20200530").endOf(Moment.UNIT_WEEK).format() + " - week1");
        System.out.println(Moment.moment("20200531").endOf(Moment.UNIT_WEEK).format() + " - week2");
        System.out.println(Moment.moment("20200601").endOf(Moment.UNIT_WEEK).format() + " - week3");
        System.out.println(Moment.moment("20200602").endOf(Moment.UNIT_WEEK).format() + " - week4");
        System.out.println(Moment.moment("20200603").endOf(Moment.UNIT_WEEK).format() + " - week5");
        System.out.println(Moment.moment("20200604").endOf(Moment.UNIT_WEEK).format() + " - week6");
        System.out.println(Moment.moment("20200605").endOf(Moment.UNIT_WEEK).format() + " - week7");
        System.out.println(Moment.moment("20200606").endOf(Moment.UNIT_WEEK).format() + " - week8");
        System.out.println(Moment.moment("20200607").endOf(Moment.UNIT_WEEK).format() + " - week9");
        System.out.println(Moment.moment("20200608").endOf(Moment.UNIT_WEEK).format() + " - week10");
        System.out.println(Moment.moment("20200609").endOf(Moment.UNIT_WEEK).format() + " - week11");
	}
	
	@Test
	public void testFromNow() {
		System.out.println(Moment.moment().fromNow());
		System.out.println(Moment.moment().add(1, Moment.UNIT_MINUTE).fromNow());
		System.out.println(Moment.moment().add(2, Moment.UNIT_HOUR).fromNow());
		System.out.println(Moment.moment().add(2, Moment.UNIT_DAY).fromNow());
		System.out.println(Moment.moment().add(2, Moment.UNIT_MONTH).fromNow());
		System.out.println(Moment.moment().add(3, Moment.UNIT_MONTH).fromNow());
		System.out.println(Moment.moment().add(4, Moment.UNIT_MONTH).fromNow());
	}
}
