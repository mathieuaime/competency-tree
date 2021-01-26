package com.mathieuaime.roadmap.model;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Skill implements Comparable<Skill> {
  private static final Comparator<Skill> CATEGORY_AND_ID_COMPARATOR =
      Comparator.comparing(Skill::getCategory).thenComparing(Skill::getId);

  public static Comparator<Skill> categoryAndIdComparator() {
    return CATEGORY_AND_ID_COMPARATOR;
  }

  private final Long id;
  private final Category category;
  private final String icon;
  private final String name;
  private final Set<Task> tasks;
  private final boolean done;

  public Skill(Long id, String name, String icon) {
    this(id, name, icon, null);
  }

  public Skill(Long id, String name, String icon, Category category) {
    this(id, name, icon, category, true, new TreeSet<>());
  }

  public Skill(Long id, String name, String icon, Category category, boolean done,
      Set<Task> tasks) {
    this.id = id;
    this.category = category;
    this.icon = icon;
    this.name = name;
    this.done = done;
    this.tasks = tasks;
  }

  public Long getId() {
    return id;
  }

  public Category getCategory() {
    return category;
  }

  public String getIcon() {
    return icon;
  }

  public String getName() {
    return name;
  }

  public Set<Task> getTasks() {
    return tasks;
  }

  public boolean isDone() {
    return done;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Skill skill = (Skill) o;
    return Objects.equals(id, skill.id)
        && category == skill.category
        && Objects.equals(name, skill.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, category, name);
  }

  @Override
  public String toString() {
    return "Skill{" +
        "id=" + id +
        ", category=" + category +
        ", icon='" + icon + '\'' +
        ", name='" + name + '\'' +
        '}';
  }

  @Override
  public int compareTo(Skill o) {
    int nameCompare = name.compareTo(o.name);
    return nameCompare == 0 ? category.compareTo(o.category) : nameCompare;
  }
}
