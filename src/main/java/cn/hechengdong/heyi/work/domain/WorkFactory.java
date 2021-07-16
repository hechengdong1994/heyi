package cn.hechengdong.heyi.work.domain;

import cn.hechengdong.heyi.work.impl.DefaultStep;

import java.util.Map;

public class WorkFactory {

    private final StepManager stepManager;

    public WorkFactory(StepManager stepManager) {
        this.stepManager = stepManager;
    }

    public Work createWork(WorkConfig workConfig, Map<String, Object> params) {
        Work work = new Work(workConfig.getName(), params);
        // StepChain
        for (StepConfig stepConfig : workConfig.getStepConfigs()) {
            Step step = new DefaultStep(stepConfig.getName(), stepManager.getStepExecutor(stepConfig.getType()), stepConfig.getParamConfig());
            work.addStep(step);
        }
        work.validate();
        return work;
    }
}
