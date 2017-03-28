package cn.devezhao.momentjava.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/23/2017
 */
public class I18nUtils {
	
	/**
	 * @param locale
	 * @param key
	 * @return
	 */
	public static String string(String locale, String key) {
		JSONObject res = loadResource(locale);
		return res.getString(key);
	}
	
	private static JSONObject cache = null;
	private static JSONObject loadResource(String locale) {
		if (cache != null) {
			return cache;
		}
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		StringBuffer sb = new StringBuffer();
		try {
			is = I18nUtils.class.getClassLoader().getResourceAsStream("i18n/" + locale + ".json");
			isr = new InputStreamReader(is, "utf-8");
			br = new BufferedReader(isr);
			
			String l = null;
			while ((l = br.readLine()) != null) {
				sb.append(l);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException e) { }
			try {
				if (isr != null) isr.close();
			} catch (IOException e) { }
			try {
				if (is != null) is.close();
			} catch (IOException e) { }
			
		}
		
		cache = JSON.parseObject(sb.toString());
		return cache;
	}
	
}
