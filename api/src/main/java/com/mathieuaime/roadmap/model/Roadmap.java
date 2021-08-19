package com.mathieuaime.roadmap.model;

import static java.util.Collections.emptySet;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public class Roadmap {

  private final Long id;
  private final String name;
  private final String description;
  private final String color;
  private final Set<Skill> skills;

  public Roadmap(Long id, String name, String description, String color) {
    this(id, name, description, color, emptySet());
  }

  public Roadmap(Long id, String name, String description, String color, Collection<Skill> skills) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.color = color;
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

  public String getColor() {
    return color;
  }

  public Set<Skill> getSkills() {
    return skills;
  }

  public Roadmap withId(long id) {
    return new Roadmap(id, name, description, color, skills);
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
        Objects.equals(description, roadmap.description) &&
        Objects.equals(color, roadmap.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, color);
  }

  @Override
  public String toString() {
    return "Roadmap{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", color='" + color + '\'' +
        ", skills=" + skills +
        '}';
  }
}
