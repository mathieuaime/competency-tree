package com.mathieuaime.roadmap.model;

import java.util.Objects;

public class Task implements Comparable<Task> {

  private final Long id;
  private final String name;
  private final String description;
  private final boolean done;
  private final boolean required;
  private final Category category;

  public Task(
      Long id,
      String name,
      String description,
      boolean done,
      boolean required,
      Category category
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.done = done;
    this.required = required;
    this.category = category;
  }

  public Task(Long id, String name, String description) {
    this(id, name, description, false, false, null);
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

  public boolean isDone() {
    return done;
  }

  public boolean isRequired() {
    return required;
  }

  public Category getCategory() {
    return category;
  }

  @Override
  public int compareTo(Task o) {
    return name.compareTo(o.name);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Task task = (Task) o;
    return done == task.done &&
        required == task.required &&
        Objects.equals(id, task.id) &&
        Objects.equals(name, task.name) &&
        Objects.equals(description, task.description) &&
        category == task.category;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, done, required, category);
  }

  @Override
  public String toString() {
    return "Task{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", done=" + done +
        ", required=" + required +
        ", category=" + category +
        '}';
  }
}
