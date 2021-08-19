package com.mathieuaime.roadmap.service;

import static com.mathieuaime.roadmap.fixture.TestFixture.randomCategory;
import static com.mathieuaime.roadmap.fixture.TestFixture.randomRoadmapWithId;
import static com.mathieuaime.roadmap.fixture.TestFixture.randomSkillFromCategory;
import static com.mathieuaime.roadmap.fixture.TestFixture.randomTaskFromCategory;
import static com.mathieuaime.roadmap.fixture.TestFixture.randomTaskProjection;
import static org.assertj.core.api.Assertions.assertThat;

import com.mathieuaime.roadmap.model.Category;
import com.mathieuaime.roadmap.model.Roadmap;
import com.mathieuaime.roadmap.model.Skill;
import com.mathieuaime.roadmap.model.Task;
import com.mathieuaime.roadmap.repository.projection.TaskProjection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

class RoadmapExtractorTest {

  @Test
  void roadmapExtractor_withoutTasks() {
    TestBuilder.builder().hasNoRoadmap();
  }

  @Test
  void roadmapExtractor_withOneTask() {
    Category category = randomCategory();
    Roadmap roadmap = randomRoadmapWithId();
    Skill skill = randomSkillFromCategory(category);
    Task task = randomTaskFromCategory(category);

    long skillId = skill.getId();

    TestBuilder.builder(roadmap)
        .withProjection(skill, task)
        .hasRoadmap()
        .assertThatRoadmapEquals(roadmap)
        .assertThatRoadmapIs(r -> r.getSkills().size() == 1)
        .assertThatSkillEquals(skillId, skill)
        .assertThatSkillIs(skillId, s -> s.getTasks().size() == 1)
        .assertThatTaskEquals(skillId, task.getId(), task);
  }

  @Test
  void roadmapExtractor_withTasksOfSameSkill() {
    Category category = randomCategory();
    Skill skill = randomSkillFromCategory(category);
    Task task1 = randomTaskFromCategory(category);
    Task task2 = randomTaskFromCategory(category);

    long skillId = skill.getId();

    TestBuilder.builder()
        .withProjection(skill, task1)
        .withProjection(skill, task2)
        .hasRoadmap()
        .assertThatRoadmapIs(roadmap -> roadmap.getSkills().size() == 1)
        .assertThatSkillIs(skillId, s -> s.getTasks().size() == 2)
        .assertThatTaskEquals(skillId, task1.getId(), task1)
        .assertThatTaskEquals(skillId, task2.getId(), task2);
  }

  @Test
  void roadmapExtractor_withTasksOfDifferentSkillsAndCategories() {
    Category category = randomCategory();
    Category category2 = randomCategory();
    Skill skill1 = randomSkillFromCategory(category);
    Skill skill2 = randomSkillFromCategory(category);
    Skill skill3 = randomSkillFromCategory(category2);
    Task task1 = randomTaskFromCategory(category);
    Task task2 = randomTaskFromCategory(category);
    Task task3 = randomTaskFromCategory(category2);
    Task task4 = randomTaskFromCategory(category2);

    long skill1Id = skill1.getId();
    long skill2Id = skill2.getId();
    long skill3Id = skill3.getId();

    TestBuilder.builder()
        .withProjection(skill1, task1)
        .withProjection(skill2, task2)
        .withProjection(skill3, task3)
        .withProjection(skill3, task4)
        .hasRoadmap()
        .assertThatRoadmapIs(roadmap -> roadmap.getSkills().size() == 3)
        .assertThatSkillIs(skill1Id, s -> s.getTasks().size() == 1)
        .assertThatTaskEquals(skill1Id, task1.getId(), task1)
        .assertThatSkillIs(skill2Id, s -> s.getTasks().size() == 1)
        .assertThatTaskEquals(skill2Id, task2.getId(), task2)
        .assertThatSkillIs(skill3Id, s -> s.getTasks().size() == 2)
        .assertThatTaskEquals(skill3Id, task3.getId(), task3)
        .assertThatTaskEquals(skill3Id, task4.getId(), task4);
  }

  static class TestBuilder {

    private final List<TaskProjection> projections = new ArrayList<>();
    private final Roadmap roadmap;
    private RoadmapExtractor extractor;

    private TestBuilder(Roadmap roadmap) {
      this.roadmap = roadmap;
    }

    static TestBuilder builder() {
      return new TestBuilder(randomRoadmapWithId());
    }

    static TestBuilder builder(Roadmap roadmap) {
      return new TestBuilder(roadmap);
    }

    TestBuilder withProjection(Skill skill, Task task) {
      TaskProjection projection = randomTaskProjection(roadmap, skill, task);
      projections.add(projection);
      return this;
    }

    TestBuilder hasRoadmap() {
      extractor = new RoadmapExtractor(projections);
      assertThat(extractor.getRoadmap()).isPresent();
      return this;
    }

    TestBuilder hasNoRoadmap() {
      extractor = new RoadmapExtractor(projections);
      assertThat(extractor.getRoadmap()).isNotPresent();
      return this;
    }

    TestBuilder assertThatRoadmapIs(Predicate<Roadmap> predicate) {
      Roadmap roadmap = getRoadmap();
      assertThat(roadmap).matches(predicate);
      return this;
    }

    TestBuilder assertThatRoadmapEquals(Roadmap roadmap) {
      return assertThatRoadmapIs(r -> Objects.equals(r, roadmap));
    }

    TestBuilder assertThatSkillIs(long skillId, Predicate<Skill> predicate) {
      Roadmap roadmap = getRoadmap();
      Skill skill = getSkillById(roadmap.getSkills(), skillId);
      assertThat(skill).matches(predicate);
      return this;
    }

    TestBuilder assertThatSkillEquals(long skillId, Skill skill) {
      return assertThatSkillIs(skillId, s -> Objects.equals(s, skill));
    }

    TestBuilder assertThatTask(long skillId, long taskId, Predicate<Task> predicate) {
      Roadmap roadmap = getRoadmap();
      Skill skill = getSkillById(roadmap.getSkills(), skillId);
      Task task = getTaskById(skill.getTasks(), taskId);
      assertThat(task).matches(predicate);
      return this;
    }

    TestBuilder assertThatTaskEquals(long skillId, long taskId, Task task) {
      return assertThatTask(skillId, taskId, t -> Objects.equals(t, task));
    }

    private Roadmap getRoadmap() {
      return extractor.getRoadmap().orElseThrow();
    }

    private Skill getSkillById(Set<Skill> skills, long skillId) {
      return skills.stream().filter(s -> s.getId() == skillId).findFirst().orElseThrow();
    }

    private Task getTaskById(Set<Task> tasks, long taskId) {
      return tasks.stream().filter(t -> t.getId() == taskId).findFirst().orElseThrow();
    }
  }
}