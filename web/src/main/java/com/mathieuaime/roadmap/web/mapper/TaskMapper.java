package com.mathieuaime.roadmap.web.mapper;

import com.mathieuaime.roadmap.model.Task;
import com.mathieuaime.roadmap.web.dto.TaskDto;

public final class TaskMapper {

  private TaskMapper() {
    throw new AssertionError();
  }

  public static Task toModel(TaskDto dto) {
    return new Task(dto.getId(), dto.getName(), dto.getDescription(), dto.isDone(),
        dto.isRequired(), dto.getCategory());
  }

  public static TaskDto toDto(Task model) {
    return new TaskDto(model.getId(), model.getName(), model.getDescription(), model.isDone(),
        model.isRequired(), model.getCategory());
  }
}
