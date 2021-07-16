package cn.hechengdong.heyi.work.service;

import cn.hechengdong.heyi.work.domain.WorkConfig;
import cn.hechengdong.heyi.work.domain.WorkConfigFactory;
import cn.hechengdong.heyi.work.repository.WorkConfigRepository;

public class WorkConfigApplicationService {

    private final WorkConfigFactory workConfigFactory;
    private final WorkConfigRepository workConfigRepository;

    public WorkConfigApplicationService(WorkConfigFactory workConfigFactory, WorkConfigRepository workConfigRepository) {
        this.workConfigFactory = workConfigFactory;
        this.workConfigRepository = workConfigRepository;
    }

    public void createWorkConfig(WorkConfig request) {
        WorkConfig workConfig = workConfigFactory.createWorkConfig(request);
//        workConfigRepository.save(workConfig);
    }
}
