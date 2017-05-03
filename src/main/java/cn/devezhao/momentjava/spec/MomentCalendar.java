package cn.devezhao.momentjava.spec;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/22/2017
 */
public interface MomentCalendar<T> {
	
	/**
	 * @param amount
	 * @param unit
	 * @return
	 */
	public T subtract(int amount, String unit);
	
	/**
	 * @param amount
	 * @param unit
	 * @return
	 */
	public T add(int amount, String unit);
	
	/**
	 * @return
	 */
	public String calendar();
}
