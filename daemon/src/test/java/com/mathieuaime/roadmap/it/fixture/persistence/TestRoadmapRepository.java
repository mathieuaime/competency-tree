package com.mathieuaime.roadmap.it.fixture.persistence;

import com.mathieuaime.roadmap.persistence.entity.RoadmapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRoadmapRepository extends JpaRepository<RoadmapEntity, Long> {

}