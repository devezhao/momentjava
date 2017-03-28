package cn.devezhao.momentjava.spec;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/22/2017
 */
public interface MomentCalendar<T> {

	public T subtract(int amount, String unit);
	
	public T add(int amount, String unit);
	
	public String calendar();
}
