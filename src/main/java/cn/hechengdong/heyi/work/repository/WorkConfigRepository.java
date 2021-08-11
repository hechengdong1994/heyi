package cn.hechengdong.heyi.work.repository;

import cn.hechengdong.heyi.work.domain.WorkConfig;

import java.util.List;

public interface WorkConfigRepository {

    /**
     * persistent work config
     *
     * @param workConfig work config need to save
     * @return saved work config
     */
    WorkConfig save(WorkConfig workConfig);

    /**
     * find all work configs
     *
     * @return all work configs
     */
    List<WorkConfig> findAll();

    /**
     * get a work config by id from persistence
     *
     * @param workConfigId work config id to be find
     * @return work config found by id
     */
    WorkConfig findById(String workConfigId);
}
