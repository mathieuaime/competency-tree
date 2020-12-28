package com.excilys.roadmap.web.mapper;

import com.excilys.roadmap.model.Task;
import com.excilys.roadmap.web.dto.TaskDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper extends DtoMapper<TaskDto, Task> {
}
