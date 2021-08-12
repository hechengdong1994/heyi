package cn.hechengdong.heyi.work.domain;

import cn.hechengdong.heyi.work.enums.WorkStatus;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * synchronized protected by this
 */
public class Work {

    /**
     * work name
     */
    private final String name;

    /**
     * steps to execute of this work
     */
    private final List<Step> stepChain = new ArrayList<>();
    /**
     * results created by steps in stepChain
     */
    private final List<Result> resultChain = new ArrayList<>();

    /**
     * params to execute this work
     */
    private final Map<String, Object> params;

    /**
     * current status of this work
     */
    private WorkStatus status;

    public Work(String name, Map<String, Object> params) {
        if (StringUtils.isBlank(name)) {
            throw new NullPointerException("name of work can not be blank.");
        }
        this.name = name;
        this.params = MapUtils.emptyIfNull(params);
        // change status
        status = WorkStatus.CREATED;
    }

    /**
     * validate this work before execute
     */
    public synchronized void validate() {
        // check status
        if (WorkStatus.CREATED != this.status) {
            throw new IllegalStateException("status of work " + name + " is not created, can not execute.");
        }
        // validate
        for (Step step : stepChain) {
            step.valid(params);
        }
        // change status
        status = WorkStatus.RUNNABLE;
    }

    /**
     * execute this work
     */
    public synchronized void execute() {
        // check status
        if (WorkStatus.RUNNABLE != this.status) {
            throw new IllegalStateException("status of work " + name + " is not runnable, can not execute.");
        }
        // change status
        status = WorkStatus.RUNNING;
        // start to execute
        try {
            for (Step step : stepChain) {
                if (status == WorkStatus.RUNNING) {
                    // execute
                    resultChain.add(step.execute(params));
                }
            }
        } catch (Exception e) {
            // change status
            status = WorkStatus.FAILED;
        }
        // change status
        status = WorkStatus.SUCCESS;
    }

    /**
     * stop this work
     */
    public void stop() {
        // change status
        status = WorkStatus.STOP;
    }

    public String getName() {
        return name;
    }

    public List<Step> getStepChain() {
        return stepChain;
    }

    public synchronized void addStep(Step step) {
        // check status
        if (WorkStatus.CREATED != this.status) {
            throw new IllegalStateException("status of work " + name + " is not created, can not add step.");
        }
        stepChain.add(step);
    }

    public List<Result> getResultChain() {
        return resultChain;
    }

    public synchronized void addResult(Result result) {
        // check status
        if (WorkStatus.RUNNING != this.status) {
            throw new IllegalStateException("status of work " + name + " is not running, can not add result.");
        }
        resultChain.add(result);
    }

    public Map<String, Object> getParams() {
        return params;
    }
}
