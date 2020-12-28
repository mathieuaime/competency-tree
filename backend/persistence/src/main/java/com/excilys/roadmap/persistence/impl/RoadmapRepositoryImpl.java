package com.excilys.roadmap.persistence.impl;

import com.excilys.roadmap.model.Roadmap;
import com.excilys.roadmap.persistence.entity.RoadmapEntity;
import com.excilys.roadmap.persistence.mapper.RoadmapMapper;
import com.excilys.roadmap.repository.RoadmapRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class RoadmapRepositoryImpl implements RoadmapRepository {
  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Roadmap> findAll() {
    return em.createQuery("from Roadmap", RoadmapEntity.class)
        .getResultStream()
        .map(RoadmapMapper::toModel)
        .collect(Collectors.toList());
  }

  @Override
  public Roadmap save(Roadmap roadmap) {
    RoadmapEntity entity = RoadmapMapper.toEntity(roadmap);
    em.persist(entity);
    return RoadmapMapper.toModel(entity);
  }
}
