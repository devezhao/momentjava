package cn.devezhao.momentjava.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

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
	
	private static final Map<String, JSONObject> RESCACHED = new HashMap<>();
	/**
	 * @param locale
	 * @return
	 */
	private static JSONObject loadResource(String locale) {
		if (RESCACHED.containsKey(locale)) {
			return RESCACHED.get(locale);
		}
		
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			is = I18nUtils.class.getClassLoader().getResourceAsStream("i18n/" + locale + ".json");
			if (is == null) {
				throw new FileNotFoundException();
			}
			isr = new InputStreamReader(is, StandardCharsets.UTF_8);
			br = new BufferedReader(isr);
			
			String l;
			while ((l = br.readLine()) != null) {
				sb.append(l);
			}
		} catch (FileNotFoundException e) {
			throw new MomentException("无效 locale 资源: " + locale);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException e) {
			    // NOOP
            }
			try {
				if (isr != null) isr.close();
			} catch (IOException e) {
			    // NOOP
            }
			try {
				if (is != null) is.close();
			} catch (IOException e) {
			    // NOOP
            }
		}
		
		JSONObject res = JSON.parseObject(sb.toString());
		RESCACHED.put(locale, res);
		return res;
	}
}
