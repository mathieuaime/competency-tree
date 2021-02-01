package com.mathieuaime.roadmap.persistence.jpa;

import com.mathieuaime.roadmap.persistence.entity.CheckEntity;
import com.mathieuaime.roadmap.repository.CheckRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class JpaCheckRepository implements CheckRepository {
  @PersistenceContext
  private EntityManager em;

  @Override
  public void check(long userId, long roadmapItemId) {
    em.merge(CheckEntity.newDoneCheck(userId, roadmapItemId));
  }
}
