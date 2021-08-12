package cn.hechengdong.heyi.work.repository;

import cn.hechengdong.heyi.work.domain.Work;

public interface WorkRepository {

    Work findById(String workId);
}
