package com.mathieuaime.roadmap.persistence.jpa;

import static java.util.stream.Collectors.toList;

import com.mathieuaime.roadmap.model.Skill;
import com.mathieuaime.roadmap.persistence.entity.SkillEntity;
import com.mathieuaime.roadmap.persistence.mapper.SkillMapper;
import com.mathieuaime.roadmap.repository.SkillRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
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
  public Optional<Skill> findByName(String name) {
    return em.unwrap(Session.class)
        .byNaturalId(SkillEntity.class)
        .using("name", name)
        .loadOptional()
        .map(SkillMapper::toModel);
  }

  @Override
  public Skill save(Skill skill) {
    SkillEntity entity = SkillMapper.toEntity(skill);
    entity = em.merge(entity);
    return SkillMapper.toModel(entity);
  }
}
