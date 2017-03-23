package cn.devezhao.momentjava.spec;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/22/2017
 */
public interface MomentLocale<T> {

	public String locale();
	
	public T locale(String locale);
}
