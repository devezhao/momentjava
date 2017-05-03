package cn.devezhao.momentjava;

import java.util.Date;

import cn.devezhao.momentjava.spec.MomentBase;
import cn.devezhao.momentjava.spec.MomentCalendar;
import cn.devezhao.momentjava.spec.MomentFormat;
import cn.devezhao.momentjava.spec.MomentLocale;
import cn.devezhao.momentjava.spec.MomentRelative;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/22/2017
 */
public class Moment implements MomentBase<Moment>, MomentRelative<Moment>, MomentLocale<Moment>, MomentCalendar<Moment>, MomentFormat {
	
	public static Moment moment() {
		return new Moment();
	}

	public static Moment moment(Date date) {
		return new Moment(date);
	}

	public static Moment moment(String source) {
		return new Moment(source);
	}
	
	/**
	 * @param source
	 * @param pattern JAVA pattern
	 * @return
	 */
	public static Moment moment(String source, String pattern) {
		return new Moment(source, pattern);
	}
	
	/**
	 * @param locale
	 * @param format
	 */
	public static void config(String locale, String format) {
		MomentDelegate.config(locale, format);
	}

	// --

	private MomentDelegate delegate;

	public Moment() {
		this.delegate = new MomentDelegate();
	}

	public Moment(Date date) {
		this.delegate = new MomentDelegate(date);
	}

	public Moment(String source) {
		this.delegate = new MomentDelegate(source);
	}

	public Moment(String source, String pattern) {
		this.delegate = new MomentDelegate(source, pattern);
	}

	public Moment startOf(String unit) {
		delegate.startOf(unit);
		return this;
	}

	public Moment endOf(String unit) {
		delegate.endOf(unit);
		return this;
	}

	public String fromNow() {
		return delegate.fromNow();
	}
	
	public String locale() {
		return delegate.locale();
	}
	
	public Moment locale(String locale) {
		delegate.locale(locale);
		return this;
	}

	public Moment add(int amount, String unit) {
		delegate.add(amount, unit);
		return this;
	}
	
	public Moment subtract(int amount, String unit) {
		delegate.subtract(amount, unit);
		return this;
	}
	
	public String calendar() {
		return delegate.calendar();
	}
	
	public String format() {
		return delegate.format();
	}
	
	public String format(String pattern) {
		return delegate.format(pattern);
	}
	
	public Date date() {
		return delegate.date();
	}
	
	@Override
	public String toString() {
		return delegate.toString();
	}
}
