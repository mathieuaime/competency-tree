package com.excilys.roadmap.service;

import static java.util.stream.Collectors.toCollection;

import com.excilys.roadmap.model.Roadmap;
import com.excilys.roadmap.model.Skill;
import com.excilys.roadmap.repository.projection.TaskProjection;
import java.util.Collection;
import java.util.Optional;
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
        .map(TaskProjection::getSkill)
        .collect(toCollection(() -> new TreeSet<>(Skill.categoryAndIdComparator())));
  }
}
