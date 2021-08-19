package com.mathieuaime.roadmap.it.fixture.persistence;

import com.mathieuaime.roadmap.persistence.entity.RoadmapItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRoadmapItemRepository extends JpaRepository<RoadmapItemEntity, Long> {

}