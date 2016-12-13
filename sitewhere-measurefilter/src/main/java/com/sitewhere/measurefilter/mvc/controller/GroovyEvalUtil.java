package com.sitewhere.measurefilter.mvc.controller;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Map;

/**
 * Groovy eval util 用于在java中调用script
 *
 * @author Joeg
 */
public class GroovyEvalUtil {




    public static Object eval(Map<String, Object> values, String script) {

        return null;
    }

    public static ScriptEngine buildGroovyScriptEngine() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("groovy");
        return engine;
    }

}
