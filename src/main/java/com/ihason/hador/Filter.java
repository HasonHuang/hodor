package com.ihason.hador;

/**
 * 数据过滤器
 *
 * @author Hason
 */
public interface Filter {

    void preFilter(Object obj, Object context);

    /**
     * 过滤数据
     *
     * @param obj 数据
     * @param context 上下文
     * @return true 表示过滤，false 表示不过滤
     */
    boolean filter(Object obj, Object context);
}
