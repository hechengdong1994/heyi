package cn.hechengdong.heyi.work.impl;

import cn.hechengdong.heyi.work.domain.Result;
import cn.hechengdong.heyi.work.domain.StepExecutor;

import java.util.Map;

public class DefaultStep extends AbstractStep {


    public DefaultStep(String name, StepExecutor executor, Map<String, Object> paramConfig) {
        super(name, executor, paramConfig);
    }

    @Override
    protected void doPrepare(Map<String, Object> params) {
    }

    @Override
    protected void doResolve(Map<String, Object> params, Result result) {
    }
}
