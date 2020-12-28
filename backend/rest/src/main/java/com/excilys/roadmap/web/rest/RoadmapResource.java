package com.excilys.roadmap.web.rest;

import com.excilys.roadmap.model.Roadmap;
import com.excilys.roadmap.service.RoadmapService;
import com.excilys.roadmap.web.dto.RoadmapDto;
import com.excilys.roadmap.web.mapper.RoadmapMapper;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/roadmaps")
//@PermitAll
public class RoadmapResource {
  private final RoadmapService service;
  private final RoadmapMapper mapper;

  public RoadmapResource(RoadmapService service, RoadmapMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public List<Roadmap> findAll() {
    return service.findAll();
  }

  @PutMapping
  public ResponseEntity<RoadmapDto> save(@RequestBody RoadmapDto roadmapDto) {
    var roadmap = mapper.toModel(roadmapDto);
    roadmap = service.save(roadmap);
    return ResponseEntity.ok(mapper.toDto(roadmap));
  }
}
