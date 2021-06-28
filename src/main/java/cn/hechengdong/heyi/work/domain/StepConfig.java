package cn.hechengdong.heyi.work.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

public class StepConfig {

    @NotBlank(message = "步骤名称不能为空")
    private final String name;

    @NotBlank(message = "步骤类型不能为空")
    private final String type;

    @NotNull(message = "步骤参数配置不能为空")
    private final Map<String, Object> paramConfig = new HashMap<>();

    public StepConfig(String name, String type) {
        this(name, type, null);
    }

    public StepConfig(String name, String type, Map<String, Object> paramConfig) {
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
