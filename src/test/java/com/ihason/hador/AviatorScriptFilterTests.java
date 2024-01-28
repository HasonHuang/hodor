package com.ihason.hador;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.AviatorEvaluatorInstance;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.script.AviatorBindings;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import javax.script.*;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link AviatorScriptFilter} test cases
 *
 * @author Hason
 */
public class AviatorScriptFilterTests {

    private AviatorScriptFilter filter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        filter = new AviatorScriptFilter();
    }

    @Data
    @AllArgsConstructor
    static class Dto {
        private Long id;
        private String name;
        private List<String> list;
        private Map<String, Object> map;

        public Dto(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    @Test
    public void testFilterReturnsTrueWhenResultNotNullAndBooleanTrue() throws Exception {

        ClassPathResource resource = new ClassPathResource("rule/filter.av");
        String script = FileCopyUtils.copyToString(new InputStreamReader(resource.getInputStream()));

        Dto tom = new Dto(1L, "tom");
        tom.setList(Arrays.asList("a", "b", "c"));
        Map<String, Object> map = new HashMap<>();
        tom.setMap(map);
        map.put("k1", "v1");
        map.put("名字", "汤姆");
        map.put("list", Arrays.asList("e", "f", "g"));
        Map<String, Object> embbedMap = new HashMap<>();
        embbedMap.put("kk1", "vv1");
        embbedMap.put("list", Arrays.asList("q", "w", "e"));
        map.put("map", embbedMap);

        // when
        boolean actual = filter.filter(tom, script);

        BDDAssertions.then(actual).isTrue();
    }

    @Test
    void a() throws ScriptException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("aviator");
        CompiledScript script = ((Compilable) scriptEngine).compile("b");

        AviatorBindings bindings = new AviatorBindings();
        bindings.put("a", 1);
        bindings.put("b", Arrays.asList(1,2,3));
        Object result = script.eval(bindings);

        System.out.println(result.getClass());
        System.out.println(result);
    }

}
