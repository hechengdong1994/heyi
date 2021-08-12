package cn.hechengdong.heyi.work.domain;

import org.springframework.util.CollectionUtils;

public class WorkConfigFactory {

    private final StepManager stepManager;

    public WorkConfigFactory(StepManager stepManager) {
        this.stepManager = stepManager;
    }

    public WorkConfig create(WorkConfig workConfig) {
        if (workConfig == null) {
            throw new NullPointerException("work config can not be null.");
        }
        if (CollectionUtils.isEmpty(workConfig.getStepConfigs())) {
            throw new IllegalArgumentException("step configs of work config can not be empty.");
        }

        // existence of step executor
        for (StepConfig stepConfig : workConfig.getStepConfigs()) {
            String type = stepConfig.getType();
            if (!stepManager.existsStepExecutor(type)) {
                throw new NullPointerException("non-existent step type : " + type);
            }
        }

        return workConfig;
    }
}
