package com.mathieuaime.roadmap.service;

import com.mathieuaime.roadmap.model.Skill;
import java.util.List;

public interface SkillService {
  List<Skill> findAll();

  Skill save(Skill skill);
}
