package com.mathieuaime.roadmap.initializer;

import com.mathieuaime.roadmap.model.Roadmap;
import com.mathieuaime.roadmap.model.Skill;
import com.mathieuaime.roadmap.model.Task;

final class InitialDataMapper {

  private InitialDataMapper() {
    throw new AssertionError();
  }

  static Roadmap toModel(RoadmapData roadmap) {
    return new Roadmap(roadmap.id(), roadmap.name(), roadmap.description());
  }

  static Skill toModel(SkillData skill) {
    return new Skill(skill.id(), skill.name(), skill.icon());
  }

  static Task toModel(TaskData task) {
    return new Task(task.id(), task.name(), task.description(), false, task.required(),
        task.category());
  }
}
