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

  private static TreeSet<Skill> accumulate(TreeSet<Skill> skills, TaskProjection projection) {
    skills.stream()
        .filter(s -> Objects.equals(projection.getSkill(), s))
        .findFirst()
        .ifPresentOrElse(
            s -> merge(s, projection.getTask()),
            () -> skills.add(merge(projection.getSkill(), projection.getTask()))
        );

    return skills;
  }

  private static Skill merge(Skill skill, Task task) {
    skill.addTask(task);
    return skill;
  }

  private static TreeSet<Skill> combine(TreeSet<Skill> skills1, TreeSet<Skill> skills2) {
    skills1.addAll(skills2);
    return skills1;
  }

  public Optional<Roadmap> getRoadmap() {
    return projections.stream()
        .findFirst()
        .map(TaskProjection::getRoadmap)
        .map(r -> new Roadmap(r.getId(), r.getName(), r.getDescription(), getSkills()));
  }

  private Set<Skill> getSkills() {
    return projections.stream().reduce(
        new TreeSet<>(Skill.categoryAndIdComparator()),
        RoadmapExtractor::accumulate,
        RoadmapExtractor::combine
    );
  }
}
