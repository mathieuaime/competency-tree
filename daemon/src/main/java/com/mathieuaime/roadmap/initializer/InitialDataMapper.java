package com.mathieuaime.roadmap.initializer;

import com.mathieuaime.roadmap.model.Roadmap;
import com.mathieuaime.roadmap.model.Skill;
import com.mathieuaime.roadmap.model.Task;

final class InitialDataMapper {

  private InitialDataMapper() {
    throw new AssertionError();
  }

  static Roadmap toModel(RoadmapData roadmap) {
    return new Roadmap(null, roadmap.name(), roadmap.description(), roadmap.color());
  }

  static Skill toModel(SkillData skill) {
    return new Skill(null, skill.name(), skill.icon());
  }

  static Task toModel(TaskData task) {
    return new Task(null, task.name(), task.description(), false, task.required(), task.category());
  }
}
