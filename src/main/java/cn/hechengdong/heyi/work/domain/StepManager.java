package cn.hechengdong.heyi.work.domain;

public interface StepManager {

    StepExecutor getStepExecutor(String type);

    boolean existsStepExecutor(String type);
}