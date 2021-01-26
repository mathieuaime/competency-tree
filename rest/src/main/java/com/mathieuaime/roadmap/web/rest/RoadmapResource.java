package com.mathieuaime.roadmap.web.rest;

import static java.util.stream.Collectors.toList;

import com.mathieuaime.roadmap.service.RoadmapService;
import com.mathieuaime.roadmap.web.dto.RoadmapDto;
import com.mathieuaime.roadmap.web.mapper.RoadmapMapper;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoadmapResource {
  private final RoadmapService service;

  public RoadmapResource(RoadmapService service) {
    this.service = service;
  }

  @GetMapping("/roadmaps")
  public List<RoadmapDto> findAll() {
    return service.findAll().stream().map(RoadmapMapper::toDto).collect(toList());
  }

  @GetMapping("/roadmaps/{name}")
  public ResponseEntity<RoadmapDto> findByName(@PathVariable String name) {
    var roadmap = service.findByName(name).map(RoadmapMapper::toDto);

    return ResponseEntity.of(roadmap);
  }

  @GetMapping("/me/roadmaps/{name}")
  public ResponseEntity<RoadmapDto> findUserRoadmaps(@PathVariable String name) {
    // get in security context
    long userId = 1;

    var roadmapDto = service.findByUserAndName(userId, name).map(RoadmapMapper::toDto);

    return ResponseEntity.of(roadmapDto);
  }

  @PutMapping("/roadmaps")
  public RoadmapDto update(@RequestBody RoadmapDto roadmapDto) {
    var roadmap = RoadmapMapper.toModel(roadmapDto);

    roadmap = service.save(roadmap);
    roadmapDto = RoadmapMapper.toDto(roadmap);

    return roadmapDto;
  }
}
