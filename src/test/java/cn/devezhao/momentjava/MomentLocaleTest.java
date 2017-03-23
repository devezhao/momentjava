package cn.devezhao.momentjava;

import org.junit.Test;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/22/2017
 */
public class MomentLocaleTest {

	@Test
	public void testLocale() throws Exception {
		Moment moment = Moment.moment();
		System.out.println(moment.locale());
		moment.locale("en_US");
		System.out.println(moment.locale());
	}
}
