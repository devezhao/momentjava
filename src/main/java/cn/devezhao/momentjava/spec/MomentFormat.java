package cn.devezhao.momentjava.spec;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/22/2017
 */
public interface MomentFormat {
	
	/**
	 * @return
	 */
    String format();
	
	/**
	 * @param pattern
	 * @return
	 */
    String format(String pattern);
}
