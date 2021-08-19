package com.mathieuaime.roadmap.model;

import static java.util.Collections.emptySet;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Skill implements Comparable<Skill> {

  private static final Comparator<Skill> CATEGORY_AND_ID_COMPARATOR =
      Comparator.comparing(Skill::getCategory).thenComparing(Skill::getId);
  private final Long id;
  private final Category category;
  private final String icon;
  private final String name;
  private final Set<Task> tasks;
  private boolean done;

  public Skill(Long id, String name, String icon) {
    this(id, name, icon, null);
  }

  public Skill(Long id, String name, String icon, Category category) {
    this(id, name, icon, category, true, emptySet());
  }

  public Skill(
      Long id,
      String name,
      String icon,
      Category category,
      boolean done,
      Set<Task> tasks
  ) {
    this.id = id;
    this.category = category;
    this.icon = icon;
    this.name = name;
    this.done = done;
    this.tasks = new TreeSet<>();
    this.tasks.addAll(tasks);
  }

  public static Comparator<Skill> categoryAndIdComparator() {
    return CATEGORY_AND_ID_COMPARATOR;
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
    return Set.copyOf(tasks);
  }

  public void addTask(Task task) {
    tasks.add(task);
    done = done && (!task.isRequired() || task.isDone());
  }

  public boolean isDone() {
    return done;
  }

  public Skill withId(long id) {
    return new Skill(id, name, icon, category, done, tasks);
  }

  @Override
  public int compareTo(Skill o) {
    int categoryCompare = category.compareTo(o.category);
    return categoryCompare == 0 ? name.compareTo(o.name) : categoryCompare;
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
        ", tasks=" + tasks +
        ", done=" + done +
        '}';
  }
}
