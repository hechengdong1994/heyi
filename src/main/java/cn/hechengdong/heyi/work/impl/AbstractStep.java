package cn.hechengdong.heyi.work.impl;

import cn.hechengdong.heyi.work.domain.Result;
import cn.hechengdong.heyi.work.domain.Step;
import cn.hechengdong.heyi.work.domain.StepExecutor;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public abstract class AbstractStep implements Step {
    private final String name;
    private final StepExecutor executor;
    private final Map<String, Object> paramConfig;

    public AbstractStep(String name, StepExecutor executor, Map<String, Object> paramConfig) {
        this.name = name;
        this.executor = executor;
        this.paramConfig = null == paramConfig ?
                ImmutableMap.<String, Object>builder().build() :
                ImmutableMap.<String, Object>builder().putAll(paramConfig).build();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void valid(Map<String, Object> params) {
        if (!executor.getParamConfigs().containsAll(params.keySet())){
            throw new IllegalArgumentException("step : " + name + " params required.");
        }
    }

    @Override
    public final Result execute(Map<String, Object> params) {
        this.doPrepare(params);
        Result result = this.doExecute(params);
        this.doResolve(params, result);
        return result;
    }

    protected abstract void doPrepare(Map<String, Object> params);

    protected Result doExecute(Map<String, Object> params) {
        return executor.execute(params);
    }

    protected abstract void doResolve(Map<String, Object> params, Result result);
}
