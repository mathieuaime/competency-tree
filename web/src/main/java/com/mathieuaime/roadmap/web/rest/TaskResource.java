package com.mathieuaime.roadmap.web.rest;

import static java.util.stream.Collectors.toList;

import com.mathieuaime.roadmap.service.TaskService;
import com.mathieuaime.roadmap.web.dto.TaskDto;
import com.mathieuaime.roadmap.web.mapper.TaskMapper;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
public class TaskResource {

  private final TaskService service;

  public TaskResource(TaskService service) {
    this.service = service;
  }

  @GetMapping("/tasks")
  public List<TaskDto> findAll() {
    return service.findAll().stream().map(TaskMapper::toDto).collect(toList());
  }

  @PutMapping("/me/tasks/{taskId}/check")
  public ResponseEntity<Void> check(@PathVariable long taskId) {
    // get in security context
    long userId = 1;
    service.check(userId, taskId);

    return ResponseEntity.noContent().build();
  }

  @PutMapping("/roadmaps/{roadmapId}/skills/{skillId}/tasks")
  public TaskDto save(
      @RequestBody TaskDto taskDto,
      @PathVariable int roadmapId,
      @PathVariable int skillId
  ) {
    var task = TaskMapper.toModel(taskDto);

    task = service.save(roadmapId, skillId, task);

    return TaskMapper.toDto(task);
  }
}
