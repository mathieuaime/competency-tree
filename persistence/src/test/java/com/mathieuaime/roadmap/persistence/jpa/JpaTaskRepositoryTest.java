package com.mathieuaime.roadmap.persistence.jpa;

import static com.mathieuaime.roadmap.persistence.PersistenceTestFixture.randomRoadmapEntity;
import static com.mathieuaime.roadmap.persistence.PersistenceTestFixture.randomRoadmapItemEntity;
import static com.mathieuaime.roadmap.persistence.PersistenceTestFixture.randomSkillEntity;
import static com.mathieuaime.roadmap.persistence.PersistenceTestFixture.randomTaskEntity;
import static org.assertj.core.api.Assertions.assertThat;

import com.mathieuaime.roadmap.fixture.TestFixture;
import com.mathieuaime.roadmap.persistence.entity.CheckEntity;
import com.mathieuaime.roadmap.persistence.entity.TaskEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(JpaTaskRepository.class)
class JpaTaskRepositoryTest {

  @Autowired
  private JpaTaskRepository taskRepository;

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

    Assertions.assertThat(tasks).hasSize(1);

    var task1 = tasks.iterator().next();
    Assertions.assertThat(task1.getRoadmap().getId()).isEqualTo(roadmap.getId());
    Assertions.assertThat(task1.getSkill().getId()).isEqualTo(skill.getId());
    assertThat(task1.getTask().getId()).isEqualTo(taskEntity.getId());
  }

  @Test
  void findByUserAndRoadmap_withOtherUser_notFound() {
    var roadmap = em.persistAndFlush(randomRoadmapEntity());
    var skill = em.persistAndFlush(randomSkillEntity());
    var roadmapItem = em.persistAndFlush(randomRoadmapItemEntity(roadmap, skill, taskEntity));

    em.persistAndFlush(CheckEntity.newDoneCheck(1, roadmapItem.getId()));

    var tasks = taskRepository.findByUserAndRoadmap(2, roadmap.getName());

    Assertions.assertThat(tasks).isEmpty();
  }

  @Test
  void findByUserAndRoadmap_withOtherRoadmap_notFound() {
    var roadmap = em.persistAndFlush(randomRoadmapEntity());
    var skill = em.persistAndFlush(randomSkillEntity());
    var roadmapItem = em.persistAndFlush(randomRoadmapItemEntity(roadmap, skill, taskEntity));

    em.persistAndFlush(CheckEntity.newDoneCheck(1, roadmapItem.getId()));

    var tasks = taskRepository.findByUserAndRoadmap(1, roadmap.getName() + "other");

    Assertions.assertThat(tasks).isEmpty();
  }

  @Test
  void retrieveOrCreate_whenNotFound_shouldCreateTask() {
    var task = taskRepository.merge(TestFixture.randomTaskFromId(null));

    var saved = em.find(TaskEntity.class, task.getId());

    assertThat(task.getId()).isEqualTo(saved.getId());
    assertThat(task.getName()).isEqualTo(saved.getName());
    assertThat(task.getDescription()).isEqualTo(saved.getDescription());
  }

  @Test
  void retrieveOrCreate_whenSameName_shouldRetrieveTask() {
    var task = taskRepository.merge(TestFixture.randomTaskFromName(taskEntity.getName()));

    var saved = em.find(TaskEntity.class, task.getId());

    assertThat(task.getId()).isEqualTo(saved.getId());
    assertThat(task.getName()).isEqualTo(saved.getName());
    assertThat(task.getDescription()).isEqualTo(saved.getDescription());
  }

  @Test
  void retrieveOrCreate_whenIdIsNotNull_shouldUpdateTask() {
    var task = taskRepository
        .merge(TestFixture.randomTaskFromIdAndName(taskEntity.getId(), taskEntity.getName()));

    var saved = em.find(TaskEntity.class, task.getId());

    assertThat(task.getId()).isEqualTo(saved.getId());
    assertThat(task.getName()).isEqualTo(saved.getName());
    assertThat(task.getDescription()).isEqualTo(saved.getDescription());
  }
}