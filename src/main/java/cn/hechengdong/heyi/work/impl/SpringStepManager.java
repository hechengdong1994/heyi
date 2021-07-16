package cn.hechengdong.heyi.work.impl;

import cn.hechengdong.heyi.work.domain.StepExecutor;
import cn.hechengdong.heyi.work.domain.StepManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringStepManager implements StepManager, ApplicationContextAware {

    private volatile static ApplicationContext applicationContext;

    @Override
    public synchronized void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    @Override
    public StepExecutor getStepExecutor(String type) {
        return applicationContext.getBean(type, StepExecutor.class);
    }

    @Override
    public boolean existsStepExecutor(String type) {
        return null != this.getStepExecutor(type);
    }
}