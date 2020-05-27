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

    /**
     * @return
     */
	public static Moment moment() {
		return new Moment();
	}

    /**
     * @param date
     * @return
     */
	public static Moment moment(Date date) {
		return new Moment(date);
	}

    /**
     * @param source
     * @return
     */
	public static Moment moment(String source) {
		return moment(source, null);
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
     * Sets default
     *
	 * @param locale
	 * @param format
	 */
	public static void config(String locale, String format) {
		MomentDelegate.config(locale, format);
	}

	// --

	private MomentDelegate delegate;

    protected Moment() {
		this.delegate = new MomentDelegate();
	}

	protected Moment(Date date) {
		this.delegate = new MomentDelegate(date);
	}

    protected Moment(String source, String pattern) {
		this.delegate = new MomentDelegate(source, pattern);
	}

	@Override
    public Moment startOf(String unit) {
		delegate.startOf(unit);
		return this;
	}

	@Override
    public Moment endOf(String unit) {
		delegate.endOf(unit);
		return this;
	}

	@Override
    public String fromNow() {
		return delegate.fromNow();
	}
	
	@Override
    public String locale() {
		return delegate.locale();
	}
	
	@Override
    public Moment locale(String locale) {
		delegate.locale(locale);
		return this;
	}

	@Override
    public Moment add(int amount, String unit) {
		delegate.add(amount, unit);
		return this;
	}
	
	@Override
    public Moment subtract(int amount, String unit) {
		delegate.subtract(amount, unit);
		return this;
	}
	
	@Override
    public String calendar() {
		return delegate.calendar();
	}
	
	@Override
    public String format() {
		return delegate.format();
	}
	
	@Override
    public String format(String pattern) {
		return delegate.format(pattern);
	}
	
	@Override
    public Date date() {
		return delegate.date();
	}
	
	@Override
	public String toString() {
		return delegate.toString();
	}
}
