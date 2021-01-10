package com.excilys.roadmap.web.mapper;

import com.excilys.roadmap.model.Task;
import com.excilys.roadmap.web.dto.TaskDto;

public final class TaskMapper {
  public static Task toModel(TaskDto dto) {
    return new Task(dto.getId(), dto.getName(), dto.getDescription(), dto.isDone(),
        dto.isRequired(), dto.getCategory());
  }

  public static TaskDto toDto(Task model) {
    return new TaskDto(model.getId(), model.getName(), model.getDescription(), model.isDone(),
        model.isRequired(), model.getCategory());
  }
}
