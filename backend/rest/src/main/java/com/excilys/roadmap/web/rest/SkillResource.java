package com.excilys.roadmap.web.rest;

import static java.util.stream.Collectors.toList;

import com.excilys.roadmap.service.SkillService;
import com.excilys.roadmap.web.dto.SkillDto;
import com.excilys.roadmap.web.mapper.SkillMapper;
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
@RequestMapping("/api/v1/skills")
//@PermitAll
public class SkillResource {
  private final SkillService service;
  private final SkillMapper mapper;

  public SkillResource(SkillService service, SkillMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public List<SkillDto> findAll() {
    return service.findAll().stream().map(mapper::toDto).collect(toList());
  }

  @PutMapping
  public ResponseEntity<SkillDto> save(@RequestBody SkillDto skillDto) {
    var skill = mapper.toModel(skillDto);
    skill = service.save(skill);
    return ResponseEntity.ok(mapper.toDto(skill));
  }
}
