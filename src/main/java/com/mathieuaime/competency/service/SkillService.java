package com.mathieuaime.competency.service;

import com.mathieuaime.competency.dao.SkillRepository;
import com.mathieuaime.competency.mapper.SkillMapper;
import com.mathieuaime.competency.model.Skill;
import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SkillService {

  private SkillRepository skillRepository;

  private SkillMapper skillMapper;

  @Autowired
  public SkillService(SkillRepository skillRepository, SkillMapper skillMapper) {
    this.skillRepository = skillRepository;
    this.skillMapper = skillMapper;
  }

  public Skill findByName(String name) {
    return skillRepository.findByName(name);
  }

  public Collection<Skill> findByNameLike(String name) {
    return skillRepository.findByNameLike(name);
  }

  public Map<String, Object> graph(int limit) {
    Collection<Skill> result = skillRepository.graph(limit);
    return skillMapper.toD3Format(result);
  }
}
