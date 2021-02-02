package com.mathieuaime.roadmap.model;

import java.util.Collection;
import java.util.Objects;
import java.util.TreeSet;

public class Roadmap {
  private final Long id;
  private final String name;
  private final String description;
  private final Collection<Skill> skills;

  public Roadmap(Long id, String name, String description) {
    this(id, name, description, new TreeSet<>(Skill.categoryAndIdComparator()));
  }

  public Roadmap(Long id, String name, String description, Collection<Skill> skills) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.skills = skills;
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

  public Collection<Skill> getSkills() {
    return skills;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Roadmap roadmap = (Roadmap) o;
    return Objects.equals(id, roadmap.id) &&
        Objects.equals(name, roadmap.name) &&
        Objects.equals(description, roadmap.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description);
  }

  @Override
  public String toString() {
    return "Roadmap{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", skills=" + skills +
        '}';
  }
}
