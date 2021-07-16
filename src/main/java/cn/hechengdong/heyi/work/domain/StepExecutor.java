package cn.hechengdong.heyi.work.domain;

import java.util.List;
import java.util.Map;

public interface StepExecutor {
    String getType();

    List<String> getParamConfigs();

    Result execute(Map<String, Object> params);
}
