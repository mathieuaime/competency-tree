package com.mathieuaime.roadmap.persistence.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity(name = "Check")
@Table(name = "user_check", indexes = @Index(columnList = "user_id"))
public class CheckEntity {

  @EmbeddedId
  @AttributeOverrides({
      @AttributeOverride(name = "userId", column = @Column(name = "user_id")),
      @AttributeOverride(name = "roadmapItemId", column = @Column(name = "roadmap_item_id"))
  })
  private CheckId id;
  private boolean done = false;

  public CheckEntity() {
  }

  private CheckEntity(CheckId id, boolean done) {
    this.id = id;
    this.done = done;
  }

  public static CheckId id(long userId, long roadmapId) {
    return new CheckId(userId, roadmapId);
  }

  public static CheckEntity newDoneCheck(long userId, long roadmapItemId) {
    return new CheckEntity(id(userId, roadmapItemId), true);
  }

  public static CheckEntity newUndoneCheck(long userId, long roadmapItemId) {
    return new CheckEntity(id(userId, roadmapItemId), false);
  }

  public long getUserId() {
    return id.userId;
  }

  public long getRoadmapItemId() {
    return id.roadmapItemId;
  }

  public boolean isDone() {
    return done;
  }

  @Embeddable
  private static class CheckId implements Serializable {

    private long userId;
    private long roadmapItemId;

    public CheckId() {
    }

    public CheckId(long userId, long roadmapItemId) {
      this.userId = userId;
      this.roadmapItemId = roadmapItemId;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }

      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      CheckId that = (CheckId) o;
      return Objects.equals(userId, that.userId)
          && Objects.equals(roadmapItemId, that.roadmapItemId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(userId, roadmapItemId);
    }
  }
}
