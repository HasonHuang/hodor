package com.ihason.hador.api.script;

/**
 * 脚本选择器
 *
 * @author Hason
 */
public interface ScriptChooser {

    /**
     * 选择脚本
     *
     * @param data 数据对象
     * @param context 上下文
     * @return 脚本
     */
    String choose(Object data, Object context);

}
