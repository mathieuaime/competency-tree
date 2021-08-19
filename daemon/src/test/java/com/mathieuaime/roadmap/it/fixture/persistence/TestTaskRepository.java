package com.mathieuaime.roadmap.it.fixture.persistence;

import com.mathieuaime.roadmap.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestTaskRepository extends JpaRepository<TaskEntity, Long> {

}