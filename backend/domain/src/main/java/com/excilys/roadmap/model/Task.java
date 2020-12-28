package com.excilys.roadmap.model;

import java.util.Objects;

public class Task implements Comparable<Task> {
  private Long id;
  private String name;
  private String description;
  private boolean done;
  private boolean required;
  private Category category;

  public Task() {
  }

  public Task(Long id, String name, String description, boolean done, boolean required,
      Category category) {
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

  public Task setId(Long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Task setName(String name) {
    this.name = name;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Task setDescription(String description) {
    this.description = description;
    return this;
  }

  public boolean isDone() {
    return done;
  }

  public Task setDone(boolean done) {
    this.done = done;
    return this;
  }

  public boolean isRequired() {
    return required;
  }

  public Task setRequired(boolean required) {
    this.required = required;
    return this;
  }

  public Category getCategory() {
    return category;
  }

  public Task setCategory(Category category) {
    this.category = category;
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

  @Override
  public int compareTo(Task o) {
    return name.compareTo(o.name);
  }
}
