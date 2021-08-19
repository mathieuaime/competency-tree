package com.mathieuaime.roadmap.repository;

import com.mathieuaime.roadmap.model.Skill;
import java.util.List;
import java.util.Optional;

public interface SkillRepository {

  List<Skill> findAll();

  Optional<Skill> findByName(String name);

  Skill save(Skill roadmap);
}
