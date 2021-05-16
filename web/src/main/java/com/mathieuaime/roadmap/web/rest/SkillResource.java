package com.mathieuaime.roadmap.web.rest;

import static java.util.stream.Collectors.toList;

import com.mathieuaime.roadmap.service.SkillService;
import com.mathieuaime.roadmap.web.dto.SkillDto;
import com.mathieuaime.roadmap.web.mapper.SkillMapper;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
public class SkillResource {

  private final SkillService service;

  public SkillResource(SkillService service) {
    this.service = service;
  }

  @GetMapping("/skills")
  public List<SkillDto> findAll() {
    return service.findAll().stream().map(SkillMapper::toDto).collect(toList());
  }

  @PutMapping("/skills")
  public SkillDto update(@RequestBody SkillDto skillDto) {
    var skill = SkillMapper.toModel(skillDto);

    skill = service.save(skill);

    return SkillMapper.toDto(skill);
  }
}

