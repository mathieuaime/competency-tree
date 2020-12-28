package com.excilys.roadmap.web.dto;

import com.excilys.roadmap.model.Category;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class SkillDto {
  private Long id;
  private Category category;
  private boolean done;
  private String icon;
  private String name;
  private Collection<TaskDto> tasks = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
