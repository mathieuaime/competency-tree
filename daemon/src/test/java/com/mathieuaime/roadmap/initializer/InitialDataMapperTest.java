package com.mathieuaime.roadmap.initializer;


import static org.assertj.core.api.Assertions.assertThat;

import com.mathieuaime.roadmap.model.Category;
import com.mathieuaime.roadmap.model.Roadmap;
import com.mathieuaime.roadmap.model.Skill;
import com.mathieuaime.roadmap.model.Task;
import org.junit.jupiter.api.Test;

class InitialDataMapperTest {

  @Test
  void roadmapDataToModel() {
    RoadmapData roadmapData = new RoadmapData("name", "description", "#000000");
    Roadmap roadmap = InitialDataMapper.toModel(roadmapData);

    assertThat(roadmap.getName()).isEqualTo(roadmapData.name());
    assertThat(roadmap.getDescription()).isEqualTo(roadmapData.description());
    assertThat(roadmap.getColor()).isEqualTo(roadmapData.color());
  }

  @Test
  void skillDataToModel() {
    SkillData skillData = new SkillData("name", "icon");
    Skill skill = InitialDataMapper.toModel(skillData);

    assertThat(skill.getName()).isEqualTo(skillData.name());
    assertThat(skill.getIcon()).isEqualTo(skillData.icon());
  }

  @Test
  void taskDataToModel() {
    TaskData taskData =
        new TaskData("roadmap", "skill", "name", "description", true, Category.BASIC);
    Task task = InitialDataMapper.toModel(taskData);

    assertThat(task.getName()).isEqualTo(taskData.name());
    assertThat(task.getDescription()).isEqualTo(taskData.description());
    assertThat(task.getCategory()).isEqualTo(taskData.category());
    assertThat(task.isRequired()).isEqualTo(taskData.required());
    assertThat(task.isDone()).isFalse();
  }
}