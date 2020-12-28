package com.excilys.roadmap.persistence.mapper;

import com.excilys.roadmap.model.Task;
import com.excilys.roadmap.persistence.entity.TaskEntity;

public final class TaskMapper {
  private TaskMapper() {
    throw new AssertionError();
  }

  public static Task toModel(TaskEntity entity) {
    return new Task(entity.getId(), entity.getName(), entity.getDescription());
  }

  public static TaskEntity toEntity(Task task) {
    return new TaskEntity(task.getId(), task.getName(), task.getDescription());
  }
}
