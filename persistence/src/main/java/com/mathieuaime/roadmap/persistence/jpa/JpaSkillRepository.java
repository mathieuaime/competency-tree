package com.mathieuaime.roadmap.persistence.jpa;

import static java.util.stream.Collectors.toList;

import com.mathieuaime.roadmap.model.Skill;
import com.mathieuaime.roadmap.persistence.entity.SkillEntity;
import com.mathieuaime.roadmap.persistence.mapper.SkillMapper;
import com.mathieuaime.roadmap.repository.SkillRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class JpaSkillRepository implements SkillRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Skill> findAll() {
    return em.createQuery("from Skill", SkillEntity.class)
        .getResultStream()
        .map(SkillMapper::toModel)
        .collect(toList());
  }

  @Override
  public Skill save(Skill skill) {
    SkillEntity entity = SkillMapper.toEntity(skill);
    em.persist(entity);
    return SkillMapper.toModel(entity);
  }
}
