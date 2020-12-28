package com.excilys.roadmap.persistence.impl;

import com.excilys.roadmap.persistence.entity.CheckEntity;
import com.excilys.roadmap.repository.CheckRepository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CheckRepositoryImpl implements CheckRepository {
  @PersistenceContext
  private EntityManager em;

  @Override
  public void check(long userId, long roadmapItemId) {
    em.merge(CheckEntity.newDoneCheck(userId, roadmapItemId));
  }
}
