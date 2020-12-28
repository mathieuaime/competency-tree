package com.excilys.roadmap.model;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Skill implements Comparable<Skill> {
  private static final Comparator<Skill> CATEGORY_AND_ID_COMPARATOR =
      Comparator.comparing(Skill::getCategory).thenComparing(Skill::getId);

  private Long id;
  private Category category;
  private String icon;
  private String name;
  private Set<Task> tasks = new TreeSet<>();
  private boolean done;

  public static Comparator<Skill> categoryAndIdComparator() {
    return CATEGORY_AND_ID_COMPARATOR;
  }

  public Skill() {
  }

  public Skill(Long id, String name, String icon) {
    this(id, name, icon, null);
  }

  public Skill(Long id, String name, String icon, Category category) {
    this.id = id;
    this.name = name;
    this.icon = icon;
    this.category = category;
    this.done = true;
  }

  public Long getId() {
    return id;
  }

  public Skill setId(Long id) {
    this.id = id;
    return this;
  }

  public Category getCategory() {
    return category;
  }

  public Skill setCategory(Category category) {
    this.category = category;
    return this;
  }

  public String getIcon() {
    return icon;
  }

  public Skill setIcon(String icon) {
    this.icon = icon;
    return this;
  }

  public String getName() {
    return name;
  }

  public Skill setName(String name) {
    this.name = name;
    return this;
  }

  public void addTask(Task task) {
    boolean add = tasks.add(task);

    if (add && task.isRequired()) {
      this.done &= task.isDone();
    }
  }

  public Set<Task> getTasks() {
    return tasks;
  }

  public Skill setTasks(Set<Task> tasks) {
    this.tasks = tasks;
    return this;
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
