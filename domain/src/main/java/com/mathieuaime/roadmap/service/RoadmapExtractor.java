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

class RoadmapExtractor {

  private final Collection<TaskProjection> projections;

  public RoadmapExtractor(Collection<TaskProjection> projections) {
    this.projections = projections;
  }

  public Optional<Roadmap> getRoadmap() {
    return projections.stream()
        .findFirst()
        .map(TaskProjection::getRoadmap)
        .map(r -> new Roadmap(r.getId(), r.getName(), r.getDescription(), r.getColor(),
            getSkills()));
  }

  private Set<Skill> getSkills() {
    return projections.stream().collect(
        () -> new TreeSet<>(Skill.categoryAndIdComparator()),
        RoadmapExtractor::accumulate,
        TreeSet::addAll
    );
  }

  private static void accumulate(TreeSet<Skill> skills, TaskProjection projection) {
    skills.stream()
        .filter(s -> Objects.equals(projection.getSkill(), s))
        .findFirst()
        .ifPresentOrElse(
            s -> merge(s, projection.getTask()),
            () -> skills.add(merge(projection.getSkill(), projection.getTask()))
        );
  }

  private static Skill merge(Skill skill, Task task) {
    skill.addTask(task);
    return skill;
  }
}
