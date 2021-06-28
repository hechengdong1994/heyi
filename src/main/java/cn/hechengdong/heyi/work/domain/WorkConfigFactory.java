package cn.hechengdong.heyi.work.domain;

import cn.hechengdong.heyi.util.ValidateUtil;
import org.apache.commons.lang3.StringUtils;

public class WorkConfigFactory {

    private final StepManager stepManager;

    public WorkConfigFactory(StepManager stepManager) {
        this.stepManager = stepManager;
    }

    public WorkConfig createWorkConfig(WorkConfig workConfig) {
        // 参数校验
        String errorMessage = ValidateUtil.validate(workConfig);
        if (StringUtils.isNotBlank(errorMessage)) {
            throw new IllegalArgumentException(errorMessage);
        }

        // 步骤存在性校验
        for (StepConfig stepConfig : workConfig.getStepConfigs()) {
            String type = stepConfig.getType();
            if (!stepManager.existsStepExecutor(type)) {
                throw new IllegalArgumentException("non-existent step type : " + type);
            }
        }

        return workConfig;
    }
}
