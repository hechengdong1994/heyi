package cn.hechengdong.heyi.work.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

public class WorkConfig {

    @NotBlank(message = "工作名字不能为空")
    private final String name;

    @NotEmpty(message = "工作步骤配置不能为空")
    @Valid
    private List<StepConfig> stepConfigs;

    public WorkConfig(String name, List<StepConfig> stepConfigs) {
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
