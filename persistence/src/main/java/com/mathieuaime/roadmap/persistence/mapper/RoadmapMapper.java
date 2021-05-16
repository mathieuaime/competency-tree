package com.mathieuaime.roadmap.persistence.mapper;

import com.mathieuaime.roadmap.model.Roadmap;
import com.mathieuaime.roadmap.persistence.entity.RoadmapEntity;

public final class RoadmapMapper {

  private RoadmapMapper() {
    throw new AssertionError();
  }

  public static Roadmap toModel(RoadmapEntity entity) {
    return new Roadmap(entity.getId(), entity.getName(), entity.getDescription());
  }

  public static RoadmapEntity toEntity(Roadmap roadmap) {
    return new RoadmapEntity(roadmap.getId(), roadmap.getName(), roadmap.getDescription());
  }
}
