package com.ihason.hador.api.filter;

/**
 * 数据过滤器
 *
 * @author Hason
 */
public interface DataFilter {

    /**
     * 执行过滤
     *
     * @param data 待处理数据
     * @param context 过滤上下文
     * @return 过滤结果
     */
    FilterResult filter(Object data, FilterContext context);

}
