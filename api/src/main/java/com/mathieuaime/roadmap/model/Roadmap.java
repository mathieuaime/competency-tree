package com.mathieuaime.roadmap.model;

import static java.util.Collections.emptySet;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public class Roadmap {

  private final Long id;
  private final String name;
  private final String description;
  private final Set<Skill> skills;

  public Roadmap(Long id, String name, String description) {
    this(id, name, description, emptySet());
  }

  public Roadmap(Long id, String name, String description, Collection<Skill> skills) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.skills = Set.copyOf(skills);
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

  public Set<Skill> getSkills() {
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
