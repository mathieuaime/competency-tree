package com.mathieuaime.roadmap.persistence.mapper;

import com.mathieuaime.roadmap.model.Skill;
import com.mathieuaime.roadmap.persistence.entity.SkillEntity;

public final class SkillMapper {

  private SkillMapper() {
    throw new AssertionError();
  }

  public static Skill toModel(SkillEntity entity) {
    return new Skill(entity.getId(), entity.getName(), entity.getIcon());
  }

  public static SkillEntity toEntity(Skill skill) {
    return new SkillEntity(skill.getId(), skill.getName(), skill.getIcon());
  }
}
