package com.mathieuaime.roadmap.repository.projection;

public class RoadmapItemProjection {
  private final long id;

  public RoadmapItemProjection(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }
}
