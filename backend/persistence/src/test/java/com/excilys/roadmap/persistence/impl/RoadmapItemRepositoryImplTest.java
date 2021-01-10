package com.excilys.roadmap.persistence.impl;

import static com.excilys.roadmap.persistence.TestFixture.randomRoadmapEntity;
import static com.excilys.roadmap.persistence.TestFixture.randomRoadmapItemEntity;
import static com.excilys.roadmap.persistence.TestFixture.randomSkillEntity;
import static com.excilys.roadmap.persistence.TestFixture.randomTask;
import static com.excilys.roadmap.persistence.TestFixture.randomTaskEntity;
import static org.assertj.core.api.Assertions.assertThat;

import com.excilys.roadmap.model.Task;
import com.excilys.roadmap.persistence.entity.RoadmapEntity;
import com.excilys.roadmap.persistence.entity.RoadmapItemEntity;
import com.excilys.roadmap.persistence.entity.SkillEntity;
import com.excilys.roadmap.persistence.entity.TaskEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(RoadmapItemRepositoryImpl.class)
class RoadmapItemRepositoryImplTest {
  @Autowired
  private RoadmapItemRepositoryImpl roadmapItemRepository;

  @Autowired
  private TestEntityManager em;

  private RoadmapEntity roadmap;
  private SkillEntity skill;
  private TaskEntity task;

  @BeforeEach
  void setUp() {
    roadmap = em.persistAndFlush(randomRoadmapEntity());
    skill = em.persistAndFlush(randomSkillEntity());
    task = em.persistAndFlush(randomTaskEntity());
  }

  @Test
  void findRoadmapByTask() {
    em.persistAndFlush(randomRoadmapItemEntity(roadmap, skill, task));

    var roadmaps = roadmapItemRepository.findByTask(task.getId());

    assertThat(roadmaps).hasSize(1);

    var roadmap = roadmaps.get(0);
    assertThat(roadmap.getId()).isEqualTo(this.roadmap.getId());
  }

  @Test
  void findRoadmapByTask_notFound() {
    var roadmaps = roadmapItemRepository.findByTask(task.getId());

    assertThat(roadmaps).isEmpty();
  }

  @Test
  void create() {
    Task task = randomTask();

    roadmapItemRepository.create(this.roadmap.getId(), this.skill.getId(), this.task.getId(), task);

    var roadmapItem = em.find(RoadmapItemEntity.class, 1L);

    assertThat(roadmapItem.getRoadmap().getId()).isEqualTo(this.roadmap.getId());
    assertThat(roadmapItem.getSkill().getId()).isEqualTo(this.skill.getId());
    assertThat(roadmapItem.getTask().getId()).isEqualTo(this.task.getId());
    assertThat(roadmapItem.getCategory()).isEqualTo(task.getCategory());
    assertThat(roadmapItem.isRequired()).isEqualTo(task.isRequired());
  }
}