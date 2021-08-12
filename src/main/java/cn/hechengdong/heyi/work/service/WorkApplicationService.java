package cn.hechengdong.heyi.work.service;

import cn.hechengdong.heyi.work.domain.Work;
import cn.hechengdong.heyi.work.domain.WorkConfig;
import cn.hechengdong.heyi.work.domain.WorkFactory;
import cn.hechengdong.heyi.work.repository.WorkRepository;

import java.util.Map;

public class WorkApplicationService {

    private final WorkFactory workFactory;
    private final WorkRepository workRepository;

    public WorkApplicationService(WorkFactory workFactory, WorkRepository workRepository) {
        this.workFactory = workFactory;
        this.workRepository = workRepository;
    }

    public Work createWork(WorkConfig workConfig, Map<String, Object> params) {
        return workFactory.create(workConfig, params);
    }

    public Work findById(String workId) {
        return workRepository.findById(workId);
    }

}
