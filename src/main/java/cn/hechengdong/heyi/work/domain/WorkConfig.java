package cn.hechengdong.heyi.work.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

public class WorkConfig {

    private final String name;

    private List<StepConfig> stepConfigs;

    public WorkConfig(String name, List<StepConfig> stepConfigs) {
        if (StringUtils.isBlank(name)) {
            throw new NullPointerException("name of work can not be blank.");
        }
        this.name = name;
        this.stepConfigs = stepConfigs;
    }

    public String getName() {
        return name;
    }

    public List<StepConfig> getStepConfigs() {
        return stepConfigs;
    }

    public void setStepConfigs(List<StepConfig> stepConfigs) {
        this.stepConfigs = stepConfigs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkConfig that = (WorkConfig) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
