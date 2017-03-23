package cn.devezhao.momentjava;

import java.util.Date;

import cn.devezhao.momentjava.spec.MomentLocale;
import cn.devezhao.momentjava.spec.MomentRelative;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/22/2017
 */
public class Moment implements MomentRelative<MomentBase>, MomentLocale<MomentBase> {

	public static Moment moment() {
		return new Moment();
	}

	public static Moment moment(String source) {
		return new Moment();
	}

	public static Moment moment(String source, String pattern) {
		return new Moment();
	}

	// --

	private MomentBase delegate;

	public Moment() {
		this.delegate = new MomentBase();
	}

	public Moment(String source) {
		this.delegate = new MomentBase(source);
	}

	public Moment(String source, String pattern) {
		this.delegate = new MomentBase(source, pattern);
	}

	public MomentBase startOf(String unit) {
		return this.delegate.startOf(unit);
	}

	public MomentBase endOf(String unit) {
		return this.delegate.endOf(unit);
	}

	public String fromNow() {
		return this.delegate.fromNow();
	}
	
	public String locale() {
		return this.delegate.locale();
	}
	
	public MomentBase locale(String locale) {
		return this.delegate.locale(locale);
	}

	public Date toDate() {
		return delegate.date();
	}
	
	@Override
	public String toString() {
		return delegate.toString();
	}
}
