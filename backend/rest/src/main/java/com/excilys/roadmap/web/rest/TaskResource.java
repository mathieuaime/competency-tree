package com.excilys.roadmap.web.rest;

import static java.util.stream.Collectors.toList;

import com.excilys.roadmap.service.TaskService;
import com.excilys.roadmap.web.dto.TaskDto;
import com.excilys.roadmap.web.mapper.TaskMapper;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/tasks")
//@PermitAll
public class TaskResource {
  private final TaskService service;
  private final TaskMapper mapper;

  public TaskResource(TaskService service, TaskMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public List<TaskDto> findAll() {
    return service.findAll().stream().map(mapper::toDto).collect(toList());
  }
}
