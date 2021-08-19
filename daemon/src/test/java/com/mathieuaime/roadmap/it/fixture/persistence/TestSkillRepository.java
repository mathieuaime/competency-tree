package com.mathieuaime.roadmap.it.fixture.persistence;

import com.mathieuaime.roadmap.persistence.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestSkillRepository extends JpaRepository<SkillEntity, Long> {

}