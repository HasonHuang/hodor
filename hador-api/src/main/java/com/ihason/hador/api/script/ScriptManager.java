package com.ihason.hador.api.script;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 脚本管理器
 *
 * @author Hason
 */
public class ScriptManager {

    private final Map<String, String> scripts = new ConcurrentHashMap<>();

    public void add(String scriptId, String script) {
        scripts.put(scriptId, script);
    }

    public String get(String scriptId, String script) {
        return scripts.get(scriptId);
    }

    public String remove(String scriptId) {
        return scripts.remove(scriptId);
    }

}
