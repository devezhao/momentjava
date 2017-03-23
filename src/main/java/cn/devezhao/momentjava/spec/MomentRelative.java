package cn.devezhao.momentjava.spec;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/22/2017
 */
public interface MomentRelative<T> {

	public T startOf(String unit);
	
	public T endOf(String unit);
	
	public String fromNow();
}
