package com.excilys.roadmap.persistence.impl;

import com.excilys.roadmap.model.Skill;
import com.excilys.roadmap.persistence.entity.SkillEntity;
import com.excilys.roadmap.persistence.mapper.SkillMapper;
import com.excilys.roadmap.repository.SkillRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class SkillRepositoryImpl implements SkillRepository {
  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Skill> findAll() {
    return em.createQuery("from Skill", SkillEntity.class)
        .getResultStream()
        .map(SkillMapper::toModel)
        .collect(Collectors.toList());
  }

  @Override
  public Skill save(Skill skill) {
    SkillEntity entity = SkillMapper.toEntity(skill);
    em.persist(entity);
    return SkillMapper.toModel(entity);
  }
}
