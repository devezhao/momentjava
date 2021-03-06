package cn.devezhao.momentjava.spec;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/22/2017
 */
public interface MomentLocale<T> {

	/**
	 * @return
	 */
    String locale();
	
	/**
	 * @param locale
	 * @return
	 */
    T locale(String locale);
}
