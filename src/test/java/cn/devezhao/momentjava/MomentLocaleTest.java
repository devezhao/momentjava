package cn.devezhao.momentjava;

import org.junit.Assert;
import org.junit.Test;

import cn.devezhao.momentjava.util.I18nUtils;
import cn.devezhao.momentjava.util.MomentException;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/22/2017
 */
public class MomentLocaleTest {

	@Test
	public void testLocale() {
		Moment moment = Moment.moment();
		System.out.println(moment.locale());
		moment.locale("en_US");
		System.out.println(moment.locale());
	}
	
	@Test
	public void testI18n() {
		System.out.println(I18nUtils.string("zh_CN", "Calendar.today"));
		try {
			System.out.println(I18nUtils.string("zh_HK", "Calendar.today"));
		} catch (MomentException e) {
			Assert.assertTrue(true);
		}
	}
}
