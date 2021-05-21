package cn.hechengdong.heyi.work.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class WorkConfig {

    @NotBlank(message = "工作名字不能为空")
    private String name;

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

    public void setName(String name) {
        this.name = name;
    }

    public List<StepConfig> getStepConfigs() {
        return stepConfigs;
    }

    public void setStepConfigs(List<StepConfig> stepConfigs) {
        this.stepConfigs = stepConfigs;
    }
}
