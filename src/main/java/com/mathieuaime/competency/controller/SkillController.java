package com.mathieuaime.competency.controller;

import com.mathieuaime.competency.model.Skill;
import com.mathieuaime.competency.service.SkillService;
import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SkillController {

  private final SkillService skillService;

  @Autowired
  public SkillController(SkillService skillService) {
    this.skillService = skillService;
  }

  @GetMapping("/skill")
  public Skill findByTitle(@RequestParam String name) {
    return skillService.findByName(name);
  }

  @GetMapping("/skills")
  public Collection<Skill> findByTitleLike(@RequestParam String name) {
    return skillService.findByNameLike(name);
  }

  @GetMapping("/graph")
  public Map<String, Object> graph(@RequestParam(value = "limit", required = false) Integer limit) {
    return skillService.graph(limit == null ? 100 : limit);
  }
}
