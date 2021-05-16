package com.mathieuaime.roadmap.repository.projection;

import java.util.Objects;

public class RoadmapItemProjection {

  private final long id;

  public RoadmapItemProjection(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoadmapItemProjection that = (RoadmapItemProjection) o;
    return id == that.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
