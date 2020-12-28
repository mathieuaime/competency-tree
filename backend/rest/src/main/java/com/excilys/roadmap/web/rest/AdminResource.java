package com.excilys.roadmap.web.rest;

import com.excilys.roadmap.model.Roadmap;
import com.excilys.roadmap.model.Task;
import com.excilys.roadmap.service.RoadmapService;
import com.excilys.roadmap.service.TaskService;
import com.excilys.roadmap.web.dto.TaskDto;
import com.excilys.roadmap.web.mapper.TaskMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/admin")
//@RolesAllowed("ADMIN")
public class AdminResource {
  private final RoadmapService roadmapService;
  private final TaskService taskService;
  private final TaskMapper taskMapper;

  public AdminResource(
      RoadmapService roadmapService,
      TaskService taskService,
      TaskMapper taskMapper) {
    this.roadmapService = roadmapService;
    this.taskService = taskService;
    this.taskMapper = taskMapper;
  }

  @GetMapping("/roadmaps/{name}")
  public ResponseEntity<Roadmap> findByName(@PathVariable String name) {
    return ResponseEntity.of(roadmapService.findByName(name));
  }

  @PutMapping("/roadmaps/{roadmapId}/skills/{skillId}/tasks")
  public ResponseEntity<TaskDto> saveTask(
      @PathVariable long roadmapId,
      @PathVariable long skillId,
      @RequestBody TaskDto taskDto) {
    var task = taskMapper.toModel(taskDto);
    task = taskService.save(roadmapId, skillId, task);
    return ResponseEntity.ok(taskMapper.toDto(task));
  }
}
