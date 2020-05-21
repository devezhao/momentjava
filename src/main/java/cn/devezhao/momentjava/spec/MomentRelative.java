package cn.devezhao.momentjava.spec;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/22/2017
 */
public interface MomentRelative<T> {
	
	/**
	 * @param unit
	 * @return
	 */
    T startOf(String unit);
	
	/**
	 * @param unit
	 * @return
	 */
    T endOf(String unit);
	
	/**
	 * @return
	 */
    String fromNow();
}
