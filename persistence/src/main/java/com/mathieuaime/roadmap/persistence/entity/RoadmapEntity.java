package com.mathieuaime.roadmap.persistence.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;

@Entity(name = "Roadmap")
@Table(name = "roadmap")
public class RoadmapEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NaturalId
  @Column(unique = true, nullable = false)
  private String name;
  @Column(columnDefinition = "TEXT")
  private String description;
  @Column(nullable = false)
  private String color;

  public RoadmapEntity() {
  }

  public RoadmapEntity(Long id) {
    this(id, null, null, null);
  }

  public RoadmapEntity(Long id, String name, String description, String color) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.color = color;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getColor() {
    return color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoadmapEntity that = (RoadmapEntity) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(name, that.name) &&
        Objects.equals(description, that.description) &&
        Objects.equals(color, that.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, color);
  }

  @Override
  public String toString() {
    return "RoadmapEntity{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", color='" + color + '\'' +
        '}';
  }
}
