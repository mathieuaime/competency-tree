package com.mathieuaime.roadmap.web.dto;

import com.mathieuaime.roadmap.model.Category;
import java.util.Objects;

public class TaskDto {
  private Long id;
  private String name;
  private String description;
  private boolean done;
  private boolean required;
  private Category category;

  public TaskDto() {
  }

  public TaskDto(
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isDone() {
    return done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  public boolean isRequired() {
    return required;
  }

  public void setRequired(boolean required) {
    this.required = required;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaskDto task = (TaskDto) o;
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
    return "TaskDto{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", done=" + done +
        ", required=" + required +
        ", category=" + category +
        '}';
  }
}
