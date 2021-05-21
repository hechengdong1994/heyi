package cn.hechengdong.heyi.work.domain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class StepManager implements ApplicationContextAware {

    private volatile static ApplicationContext applicationContext;

    @Override
    public synchronized void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public static Step getStep(String type) {
        return applicationContext.getBean(type, Step.class);
    }

    public static boolean existsStep(String type) {
        return null != StepManager.getStep(type);
    }
}