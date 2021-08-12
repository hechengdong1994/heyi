package cn.hechengdong.heyi.work.domain;

import cn.hechengdong.heyi.work.impl.DefaultStep;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Map;

public class WorkFactory {

    private final StepManager stepManager;

    public WorkFactory(StepManager stepManager) {
        this.stepManager = stepManager;
    }

    public Work create(WorkConfig workConfig, Map<String, Object> params) {
        if (workConfig == null) {
            throw new NullPointerException("work config can not be null.");
        }
        if (CollectionUtils.isEmpty(workConfig.getStepConfigs())) {
            throw new NullPointerException("step configs of work config can not be empty.");
        }

        Work work = new Work(workConfig.getName(), params);
        // handle step chain
        for (StepConfig stepConfig : workConfig.getStepConfigs()) {
            StepExecutor stepExecutor = stepManager.getStepExecutor(stepConfig.getType());
            if (stepExecutor == null) {
                throw new NullPointerException("no step executor named " + stepConfig.getType());
            }
            Step step = new DefaultStep(stepConfig.getName(), stepExecutor, stepConfig.getParamConfig());
            work.addStep(step);
        }
        work.validate();
        return work;
    }
}
