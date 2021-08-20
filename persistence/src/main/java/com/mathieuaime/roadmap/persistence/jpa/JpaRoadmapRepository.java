package com.mathieuaime.roadmap.persistence.jpa;

import com.mathieuaime.roadmap.model.Roadmap;
import com.mathieuaime.roadmap.persistence.entity.RoadmapEntity;
import com.mathieuaime.roadmap.persistence.mapper.RoadmapMapper;
import com.mathieuaime.roadmap.repository.RoadmapRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class JpaRoadmapRepository implements RoadmapRepository {

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
  public Optional<Roadmap> findByName(String name) {
    return em.unwrap(Session.class)
        .byNaturalId(RoadmapEntity.class)
        .using("name", name)
        .loadOptional()
        .map(RoadmapMapper::toModel);
  }

  @Override
  public Roadmap save(Roadmap roadmap) {
    RoadmapEntity entity = RoadmapMapper.toEntity(roadmap);
    entity = em.merge(entity);
    return RoadmapMapper.toModel(entity);
  }
}
