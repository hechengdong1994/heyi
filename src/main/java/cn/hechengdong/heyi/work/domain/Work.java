package cn.hechengdong.heyi.work.domain;

import cn.hechengdong.heyi.work.enums.WorkStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Work {

    private final String name;

    private final List<Step> stepChain = new ArrayList<>();
    private final List<Result> resultChain = new ArrayList<>();

    private final Map<String, Object> params;

    private WorkStatus status;

    public void validate() {
        for (Step step : stepChain) {
            step.execute(params);
        }
    }

    public void start() {
        // status check
        synchronized (this) {
            // start
            for (Step step : stepChain) {
                // check status
                // execute
                resultChain.add(step.execute(params));
            }
        }
    }

    public void stop() {
        // status check
        synchronized (this) {
            // status change
        }
    }

    public Work(String name, Map<String, Object> params) {
        this.name = name;
        this.params = params;
    }

    public String getName() {
        return name;
    }

    public List<Step> getStepChain() {
        return stepChain;
    }

    public void addStep(Step step) {
        stepChain.add(step);
    }

    public List<Result> getResultChain() {
        return resultChain;
    }

    public void addResult(Result result) {
        resultChain.add(result);
    }

    public Map<String, Object> getParams() {
        return params;
    }
}
