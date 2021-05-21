package cn.hechengdong.heyi.work.domain;

import java.util.Map;

public interface Step {
    String getType();

    Result execute(Map<String, Object> params);
}
