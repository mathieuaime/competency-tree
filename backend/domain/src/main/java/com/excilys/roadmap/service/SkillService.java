package com.excilys.roadmap.service;

import com.excilys.roadmap.model.Skill;
import com.excilys.roadmap.repository.SkillRepository;
import java.util.List;
import javax.transaction.Transactional;

@Transactional
public class SkillService {
  private final SkillRepository repository;

  public SkillService(SkillRepository repository) {
    this.repository = repository;
  }

  public List<Skill> findAll() {
    return repository.findAll();
  }

  public Skill save(Skill skill) {
    return repository.save(skill);
  }
}
