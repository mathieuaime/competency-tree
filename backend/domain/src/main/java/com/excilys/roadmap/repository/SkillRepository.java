package com.excilys.roadmap.repository;

import com.excilys.roadmap.model.Skill;
import java.util.List;

public interface SkillRepository {
  List<Skill> findAll();

  Skill save(Skill roadmap);
}
