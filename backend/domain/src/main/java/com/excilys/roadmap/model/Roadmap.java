package com.excilys.roadmap.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.TreeSet;

public class Roadmap {
  private Long id;
  private String name;
  private String description;
  private Collection<Skill> skills = new TreeSet<>(Skill.categoryAndIdComparator());

  public Roadmap() {
  }

  public Roadmap(Long id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public Roadmap setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Roadmap setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Roadmap setDescription(String description) {
    this.description = description;
    return this;
  }

  public Collection<Skill> getSkills() {
    return skills;
  }

  public Roadmap setSkills(Collection<Skill> skills) {
    this.skills = skills;
    return this;
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
        '}';
  }

  public Roadmap addSkill(Skill skill) {
    this.skills.add(skill);
    return this;
  }

  public Roadmap addSkills(Collection<Skill> skills) {
    this.skills.addAll(skills);
    return this;
  }
}
