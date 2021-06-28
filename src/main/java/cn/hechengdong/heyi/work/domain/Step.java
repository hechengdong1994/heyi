package cn.hechengdong.heyi.work.domain;

import java.util.Map;

public interface Step {
    String getName();

    void valid(Map<String, Object> params);

    Result execute(Map<String, Object> params);
}
