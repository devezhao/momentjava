package cn.devezhao.momentjava;

import java.io.File;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * 
 * @author zhaofang123@gmail.com
 * @since 03/22/2017
 */
public class Momentjs {
	
	private ScriptEngineManager scriptEngineManager;
	private ScriptEngine scriptEngine;

	public Momentjs() {
		scriptEngineManager = new ScriptEngineManager();
		scriptEngine = scriptEngineManager.getEngineByName("javascript");
		File momentjs = new File(Momentjs.class.getClassLoader().getResource("js/momentjs-2.10.2.js").getFile());
		try {
			scriptEngine.eval(new FileReader(momentjs));
		} catch (Exception e) {
			throw new RuntimeException("加载 momentjs 文件失败", e);
		}
	}
	
	public Object invokeFunction(String functionName, Object...args) {
		try {
			return ((Invocable) scriptEngine).invokeFunction(functionName, args);
		} catch (Exception e) {
			throw new RuntimeException("执行 JS 方法失败", e);
		}
	}
	
	public ScriptEngine getScriptEngine() {
		return scriptEngine;
	}
	
	public ScriptEngineManager getScriptEngineManager() {
		return scriptEngineManager;
	}
	
	/**
	 * 调用 JS 方法
	 * 
	 * @param functionName
	 * @param args
	 * @return
	 */
	public static Object invoke(String functionName, Object...args) {
		return new Momentjs().invokeFunction(functionName, args);
	}
}
