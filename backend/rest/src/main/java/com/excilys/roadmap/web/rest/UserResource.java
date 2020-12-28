package com.excilys.roadmap.web.rest;

import com.excilys.roadmap.service.RoadmapService;
import com.excilys.roadmap.service.TaskService;
import com.excilys.roadmap.web.dto.RoadmapDto;
import com.excilys.roadmap.web.mapper.RoadmapMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/me")
public class UserResource {
  private final RoadmapService roadmapService;
  private final RoadmapMapper roadmapMapper;
  private final TaskService taskService;

  public UserResource(
      RoadmapService roadmapService,
      RoadmapMapper roadmapMapper,
      TaskService taskService) {
    this.roadmapService = roadmapService;
    this.roadmapMapper = roadmapMapper;
    this.taskService = taskService;
  }

  @GetMapping("/roadmaps/{name}")
  public ResponseEntity<RoadmapDto> findAll(@PathVariable String name) {
    // get in security context
    long userId = 1;
    var optRoadmap = roadmapService.findByUserAndName(userId, name).map(roadmapMapper::toDto);
    return ResponseEntity.of(optRoadmap);
  }

  @PutMapping("/tasks/{taskId}/check")
  public ResponseEntity<Void> check(@PathVariable long taskId) {
    // get in security context
    long userId = 1;
    taskService.check(userId, taskId);
    return ResponseEntity.accepted().build();
  }
}
