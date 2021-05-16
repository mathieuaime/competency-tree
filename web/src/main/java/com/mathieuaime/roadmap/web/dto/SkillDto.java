package com.mathieuaime.roadmap.web.dto;

import com.mathieuaime.roadmap.model.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SkillDto {

  private Long id;
  private String name;
  private String icon;
  private Category category;
  private boolean done;
  private List<TaskDto> tasks = new ArrayList<>();

  public SkillDto() {
  }

  public SkillDto(
      Long id,
      String name,
      String icon,
      Category category,
      boolean done,
      List<TaskDto> tasks
  ) {
    this.id = id;
    this.name = name;
    this.icon = icon;
    this.category = category;
    this.done = done;
    this.tasks = List.copyOf(tasks);
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

  public List<TaskDto> getTasks() {
    return tasks;
  }

  public void setTasks(List<TaskDto> tasks) {
    this.tasks = List.copyOf(tasks);
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
    return done == skillDto.done
        && Objects.equals(id, skillDto.id)
        && Objects.equals(name, skillDto.name)
        && Objects.equals(icon, skillDto.icon)
        && category == skillDto.category
        && Objects.equals(tasks, skillDto.tasks);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, icon, category, done, tasks);
  }

  @Override
  public String toString() {
    return "SkillDto{" +
        "id=" + id +
        ", category=" + category +
        ", done='" + done + '\'' +
        ", icon='" + icon + '\'' +
        ", name='" + name + '\'' +
        ", tasks='" + tasks + '\'' +
        '}';
  }
}
