package com.excilys.roadmap.persistence.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Skill")
@Table(name = "skill")
public class SkillEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true, nullable = false)
  private String name;
  private String icon;

  public SkillEntity() {
  }

  public SkillEntity(Long id) {
    this(id, null, null);
  }

  public SkillEntity(Long id, String name, String icon) {
    this.id = id;
    this.name = name;
    this.icon = icon;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getIcon() {
    return icon;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SkillEntity entity = (SkillEntity) o;
    return Objects.equals(id, entity.id) &&
        Objects.equals(name, entity.name) &&
        Objects.equals(icon, entity.icon);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, icon);
  }

  @Override
  public String toString() {
    return "SkillEntity{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", icon='" + icon + '\'' +
        '}';
  }
}
