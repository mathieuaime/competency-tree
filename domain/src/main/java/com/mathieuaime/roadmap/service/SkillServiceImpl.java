package com.mathieuaime.roadmap.service;

import com.mathieuaime.roadmap.model.Skill;
import com.mathieuaime.roadmap.repository.SkillRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Transactional
public class SkillServiceImpl implements SkillService {

  private final SkillRepository repository;

  public SkillServiceImpl(SkillRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Skill> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<Skill> findByName(String name) {
    return repository.findByName(name);
  }

  @Override
  public Skill save(Skill skill) {
    return repository.save(skill);
  }
}
