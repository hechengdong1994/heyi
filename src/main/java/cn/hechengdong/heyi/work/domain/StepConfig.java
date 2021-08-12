package cn.hechengdong.heyi.work.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class StepConfig {

    private final String name;

    private final String type;

    private final Map<String, Object> paramConfig = new HashMap<>();

    public StepConfig(String name, String type) {
        this(name, type, null);
    }

    public StepConfig(String name, String type, Map<String, Object> paramConfig) {
        if (StringUtils.isBlank(name)) {
            throw new NullPointerException("name of work can not be blank.");
        }
        if (StringUtils.isBlank(type)) {
            throw new NullPointerException("name of work can not be blank.");
        }
        this.name = name;
        this.type = type;
        if (null != paramConfig) {
            this.paramConfig.putAll(paramConfig);
        }
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Map<String, Object> getParamConfig() {
        return paramConfig;
    }
}
