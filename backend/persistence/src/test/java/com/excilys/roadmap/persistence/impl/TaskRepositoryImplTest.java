package com.excilys.roadmap.persistence.impl;

import static com.excilys.roadmap.TestFixture.randomTaskFromId;
import static com.excilys.roadmap.TestFixture.randomTaskFromIdAndName;
import static com.excilys.roadmap.TestFixture.randomTaskFromName;
import static com.excilys.roadmap.persistence.PersistenceTestFixture.randomRoadmapEntity;
import static com.excilys.roadmap.persistence.PersistenceTestFixture.randomRoadmapItemEntity;
import static com.excilys.roadmap.persistence.PersistenceTestFixture.randomSkillEntity;
import static com.excilys.roadmap.persistence.PersistenceTestFixture.randomTaskEntity;
import static org.assertj.core.api.Assertions.assertThat;

import com.excilys.roadmap.persistence.entity.CheckEntity;
import com.excilys.roadmap.persistence.entity.TaskEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TaskRepositoryImpl.class)
class TaskRepositoryImplTest {
  @Autowired
  private TaskRepositoryImpl taskRepository;

  @Autowired
  private TestEntityManager em;

  private TaskEntity taskEntity;

  @BeforeEach
  void setUp() {
    taskEntity = em.persistAndFlush(randomTaskEntity());
  }

  @Test
  void findAll() {
    var tasks = taskRepository.findAll();

    assertThat(tasks).hasSize(1);

    var task1 = tasks.get(0);
    assertThat(task1.getId()).isEqualTo(taskEntity.getId());
    assertThat(task1.getName()).isEqualTo(taskEntity.getName());
    assertThat(task1.getDescription()).isEqualTo(taskEntity.getDescription());
  }

  @Test
  void findByUserAndRoadmap() {
    var roadmap = em.persistAndFlush(randomRoadmapEntity());
    var skill = em.persistAndFlush(randomSkillEntity());
    var roadmapItem = em.persistAndFlush(randomRoadmapItemEntity(roadmap, skill, taskEntity));

    em.persistAndFlush(CheckEntity.newDoneCheck(1, roadmapItem.getId()));

    var tasks = taskRepository.findByUserAndRoadmap(1, roadmap.getName());

    assertThat(tasks).hasSize(1);

    var task1 = tasks.iterator().next();
    assertThat(task1.getRoadmap().getId()).isEqualTo(roadmap.getId());
    assertThat(task1.getSkill().getId()).isEqualTo(skill.getId());
    assertThat(task1.getTask().getId()).isEqualTo(taskEntity.getId());
  }

  @Test
  void findByUserAndRoadmap_withOtherUser_notFound() {
    var roadmap = em.persistAndFlush(randomRoadmapEntity());
    var skill = em.persistAndFlush(randomSkillEntity());
    var roadmapItem = em.persistAndFlush(randomRoadmapItemEntity(roadmap, skill, taskEntity));

    em.persistAndFlush(CheckEntity.newDoneCheck(1, roadmapItem.getId()));

    var tasks = taskRepository.findByUserAndRoadmap(2, roadmap.getName());

    assertThat(tasks).isEmpty();
  }

  @Test
  void findByUserAndRoadmap_withOtherRoadmap_notFound() {
    var roadmap = em.persistAndFlush(randomRoadmapEntity());
    var skill = em.persistAndFlush(randomSkillEntity());
    var roadmapItem = em.persistAndFlush(randomRoadmapItemEntity(roadmap, skill, taskEntity));

    em.persistAndFlush(CheckEntity.newDoneCheck(1, roadmapItem.getId()));

    var tasks = taskRepository.findByUserAndRoadmap(1, roadmap.getName() + "other");

    assertThat(tasks).isEmpty();
  }

  @Test
  void retrieveOrCreate_whenNotFound_shouldCreateTask() {
    var task = taskRepository.merge(randomTaskFromId(null));

    var saved = em.find(TaskEntity.class, task.getId());

    assertThat(task.getId()).isEqualTo(saved.getId());
    assertThat(task.getName()).isEqualTo(saved.getName());
    assertThat(task.getDescription()).isEqualTo(saved.getDescription());
  }

  @Test
  void retrieveOrCreate_whenSameName_shouldRetrieveTask() {
    var task = taskRepository.merge(randomTaskFromName(taskEntity.getName()));

    var saved = em.find(TaskEntity.class, task.getId());

    assertThat(task.getId()).isEqualTo(saved.getId());
    assertThat(task.getName()).isEqualTo(saved.getName());
    assertThat(task.getDescription()).isEqualTo(saved.getDescription());
  }

  @Test
  void retrieveOrCreate_whenIdIsNotNull_shouldUpdateTask() {
    var task = taskRepository
        .merge(randomTaskFromIdAndName(taskEntity.getId(), taskEntity.getName()));

    var saved = em.find(TaskEntity.class, task.getId());

    assertThat(task.getId()).isEqualTo(saved.getId());
    assertThat(task.getName()).isEqualTo(saved.getName());
    assertThat(task.getDescription()).isEqualTo(saved.getDescription());
  }
}