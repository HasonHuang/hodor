package com.ihason.hador;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于 Aviator 的脚本过滤器
 *
 * @author Hason
 */
public class AviatorScriptFilter implements Filter {

    private AviatorEvaluatorInstance evaluator = AviatorEvaluator.getInstance();

    @Override
    public void preFilter(Object obj, Object context) {
        if (context instanceof String) {
            evaluator.compile((String) context, true);
        }
    }

    @Override
    public boolean filter(Object obj, Object context) {
        Expression expression;
        if (context instanceof String) {
            expression = evaluator.compile((String) context, true);
        } else {
            ClassPathResource resource = new ClassPathResource("rule/filter.av");
            try {
                expression = evaluator.compile(FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream())), true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Map<String, Object> env = new HashMap<>(4);
        env.put("$0", BeanMap.create(obj));
        env.put("_ctx", context);

        Object result = expression.execute(env);
        return result != null && Boolean.parseBoolean(result.toString());
    }

}
