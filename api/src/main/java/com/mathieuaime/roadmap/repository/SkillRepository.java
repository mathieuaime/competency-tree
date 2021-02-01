package com.mathieuaime.roadmap.repository;

import com.mathieuaime.roadmap.model.Skill;
import java.util.List;

public interface SkillRepository {
  List<Skill> findAll();

  Skill save(Skill roadmap);
}
