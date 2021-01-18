package com.excilys.roadmap.persistence.impl;

import static com.excilys.roadmap.TestFixture.randomRoadmap;
import static com.excilys.roadmap.persistence.PersistenceTestFixture.randomRoadmapEntity;
import static org.assertj.core.api.Assertions.assertThat;

import com.excilys.roadmap.persistence.entity.RoadmapEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(RoadmapRepositoryImpl.class)
class RoadmapRepositoryImplTest {
  @Autowired
  private RoadmapRepositoryImpl roadmapRepository;

  @Autowired
  private TestEntityManager em;

  @Test
  void findAll() {
    var roadmapEntity = em.persistAndFlush(randomRoadmapEntity());

    var roadmaps = roadmapRepository.findAll();

    assertThat(roadmaps).hasSize(1);

    var roadmap = roadmaps.get(0);
    assertThat(roadmap.getId()).isEqualTo(roadmapEntity.getId());
    assertThat(roadmap.getName()).isEqualTo(roadmapEntity.getName());
    assertThat(roadmap.getDescription()).isEqualTo(roadmapEntity.getDescription());
  }

  @Test
  void save() {
    var roadmap = randomRoadmap();

    roadmap = roadmapRepository.save(roadmap);

    var saved = em.find(RoadmapEntity.class, roadmap.getId());

    assertThat(roadmap.getId()).isEqualTo(saved.getId());
    assertThat(roadmap.getName()).isEqualTo(saved.getName());
    assertThat(roadmap.getDescription()).isEqualTo(saved.getDescription());
  }
}