package com.mathieuaime.roadmap.repository.projection;

import com.mathieuaime.roadmap.model.Category;
import com.mathieuaime.roadmap.model.Roadmap;
import com.mathieuaime.roadmap.model.Skill;
import com.mathieuaime.roadmap.model.Task;
import java.util.Objects;

public class TaskProjection {
  private final Roadmap roadmap;
  private final Skill skill;
  private final Task task;
  private final boolean done;
  private final boolean required;
  private final Category category;

  public TaskProjection(long roadmapId, String roadmapName, String roadmapDescription,
      long skillId, String skillName, String skillIcon,
      long taskId, String taskName, String taskDescription,
      Category category, Boolean done, Boolean required) {
    this.task = new Task(taskId, taskName, taskDescription, done, required, category);
    this.skill = new Skill(skillId, skillName, skillIcon, category);
    this.roadmap = new Roadmap(roadmapId, roadmapName, roadmapDescription);
    this.done = Boolean.TRUE.equals(done);
    this.required = Boolean.TRUE.equals(required);
    this.category = category;
  }

  public Roadmap getRoadmap() {
    return roadmap;
  }

  public Skill getSkill() {
    return skill;
  }

  public Task getTask() {
    return task;
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TaskProjection that = (TaskProjection) o;
    return Objects.equals(roadmap.getId(), that.roadmap.getId()) &&
        Objects.equals(skill.getId(), that.skill.getId()) &&
        Objects.equals(task.getId(), that.task.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(roadmap.getId(), skill.getId(), task.getId());
  }
}
