package com.mathieuaime.roadmap.service;

import com.mathieuaime.roadmap.model.Roadmap;
import com.mathieuaime.roadmap.model.Skill;
import com.mathieuaime.roadmap.repository.projection.TaskProjection;
import java.util.Collection;
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
              var skill = projection.getSkill();

              skills.stream()
                  .filter(s -> skill.getId().equals(s.getId()))
                  .findFirst()
                  .ifPresentOrElse(
                      s -> s.getTasks().add(projection.getTask()),
                      () -> skills.add(Skill.merge(skill, Set.of(projection.getTask()))));

              return skills;
            },
            (skills1, skills2) -> {
              skills1.addAll(skills2);
              return skills1;
            });
  }
}
