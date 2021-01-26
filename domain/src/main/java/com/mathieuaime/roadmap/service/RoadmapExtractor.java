package com.mathieuaime.roadmap.service;

import com.mathieuaime.roadmap.model.Roadmap;
import com.mathieuaime.roadmap.model.Skill;
import com.mathieuaime.roadmap.model.Task;
import com.mathieuaime.roadmap.repository.projection.TaskProjection;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class RoadmapExtractor {
  private final Collection<TaskProjection> projections;

  public RoadmapExtractor(Collection<TaskProjection> projections) {
    this.projections = projections;
  }

  public Optional<Roadmap> getRoadmap() {
    return projections.stream()
        .findFirst()
        .map(TaskProjection::getRoadmap)
        .map(r -> new Roadmap(r.getId(), r.getName(), r.getDescription(), getSkills()));
  }

  private Collection<Skill> getSkills() {
    return projections.stream()
        .reduce(new TreeSet<>(Skill.categoryAndIdComparator()),
            (skills, projection) -> {
              skills.stream()
                  .filter(s -> Objects.equals(projection.getSkill(), s))
                  .findFirst()
                  .ifPresentOrElse(
                      s -> merge(s, projection.getTask()),
                      () -> skills.add(merge(projection.getSkill(), projection.getTask())));

              return skills;
            },
            (skills1, skills2) -> {
              skills1.addAll(skills2);
              return skills1;
            });
  }

  public static Skill merge(Skill skill, Task task) {
    Set<Task> newTasks = new TreeSet<>(skill.getTasks());
    newTasks.add(task);
    boolean isSkillDone = skill.isDone() && (!task.isRequired() || task.isDone());

    return new Skill(skill.getId(), skill.getName(), skill.getIcon(), skill.getCategory(),
        isSkillDone, newTasks);
  }
}
