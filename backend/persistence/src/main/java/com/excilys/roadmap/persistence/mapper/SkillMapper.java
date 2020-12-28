package com.excilys.roadmap.persistence.mapper;

import com.excilys.roadmap.model.Skill;
import com.excilys.roadmap.persistence.entity.SkillEntity;

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
