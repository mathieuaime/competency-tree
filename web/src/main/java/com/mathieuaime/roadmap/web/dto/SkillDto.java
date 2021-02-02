package com.mathieuaime.roadmap.web.dto;

import com.mathieuaime.roadmap.model.Category;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class SkillDto {
  private Long id;
  private String name;
  private String icon;
  private Category category;
  private boolean done;
  private Collection<TaskDto> tasks = new ArrayList<>();

  public SkillDto() {
  }

  public SkillDto(
      Long id,
      String name,
      String icon,
      Category category,
      boolean done,
      Collection<TaskDto> tasks
  ) {
    this.id = id;
    this.name = name;
    this.icon = icon;
    this.category = category;
    this.done = done;
    this.tasks = tasks;
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

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public boolean isDone() {
    return done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  public Collection<TaskDto> getTasks() {
    return tasks;
  }

  public void setTasks(Collection<TaskDto> tasks) {
    this.tasks = tasks;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SkillDto skillDto = (SkillDto) o;
    return done == skillDto.done &&
        Objects.equals(id, skillDto.id) &&
        category == skillDto.category &&
        Objects.equals(icon, skillDto.icon) &&
        Objects.equals(name, skillDto.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, category, done, icon, name);
  }

  @Override
  public String toString() {
    return "SkillDto{" +
        "id=" + id +
        ", category=" + category +
        ", done='" + done + '\'' +
        ", icon='" + icon + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
