package com.excilys.roadmap.web.mapper;

import static java.util.stream.Collectors.toSet;

import com.excilys.roadmap.model.Skill;
import com.excilys.roadmap.model.Task;
import com.excilys.roadmap.web.dto.SkillDto;
import com.excilys.roadmap.web.dto.TaskDto;
import java.util.Set;

public final class SkillMapper {
  public static Skill toModel(SkillDto dto) {
    Set<Task> tasks = dto.getTasks().stream().map(TaskMapper::toModel).collect(toSet());
    return new Skill(dto.getId(), dto.getName(), dto.getIcon(), dto.getCategory(), dto.isDone(),
        tasks);
  }

  public static SkillDto toDto(Skill model) {
    Set<TaskDto> tasks = model.getTasks().stream().map(TaskMapper::toDto).collect(toSet());
    return new SkillDto(model.getId(), model.getName(), model.getIcon(), model.getCategory(),
        model.isDone(), tasks);
  }
}
