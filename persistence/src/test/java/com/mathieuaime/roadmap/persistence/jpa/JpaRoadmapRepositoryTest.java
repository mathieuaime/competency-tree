package com.mathieuaime.roadmap.persistence.jpa;

import static com.mathieuaime.roadmap.persistence.PersistenceTestFixture.randomRoadmapEntity;
import static org.assertj.core.api.Assertions.assertThat;

import com.mathieuaime.roadmap.TestFixture;
import com.mathieuaime.roadmap.persistence.entity.RoadmapEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(JpaRoadmapRepository.class)
class JpaRoadmapRepositoryTest {

  @Autowired
  private JpaRoadmapRepository roadmapRepository;

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
    var roadmap = TestFixture.randomRoadmap();

    roadmap = roadmapRepository.save(roadmap);

    var saved = em.find(RoadmapEntity.class, roadmap.getId());

    Assertions.assertThat(roadmap.getId()).isEqualTo(saved.getId());
    Assertions.assertThat(roadmap.getName()).isEqualTo(saved.getName());
    Assertions.assertThat(roadmap.getDescription()).isEqualTo(saved.getDescription());
  }
}