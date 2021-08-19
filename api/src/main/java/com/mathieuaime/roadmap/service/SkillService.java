package com.mathieuaime.roadmap.service;

import com.mathieuaime.roadmap.model.Skill;
import java.util.List;
import java.util.Optional;

public interface SkillService {

  List<Skill> findAll();

  Optional<Skill> findByName(String name);

  Skill save(Skill skill);
}
