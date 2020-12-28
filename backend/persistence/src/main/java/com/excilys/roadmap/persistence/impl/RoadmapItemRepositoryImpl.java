package com.excilys.roadmap.persistence.impl;

import static java.util.stream.Collectors.toList;

import com.excilys.roadmap.model.Roadmap;
import com.excilys.roadmap.model.Task;
import com.excilys.roadmap.persistence.entity.RoadmapEntity;
import com.excilys.roadmap.persistence.entity.RoadmapItemEntity;
import com.excilys.roadmap.persistence.mapper.RoadmapMapper;
import com.excilys.roadmap.repository.RoadmapItemRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class RoadmapItemRepositoryImpl implements RoadmapItemRepository {
  private static final String FIND_BY_TASK =
      "select ri.roadmap from RoadmapItem ri where ri.task.id = ?1";

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Roadmap> findByTask(long taskId) {
    return em.createQuery(FIND_BY_TASK, RoadmapEntity.class)
        .setParameter(1, taskId)
        .getResultStream()
        .map(RoadmapMapper::toModel)
        .collect(toList());
  }

  @Override
  public void create(long roadmapId, long skillId, long taskId, Task task) {
    RoadmapItemEntity entity =
        new RoadmapItemEntity(roadmapId, skillId, taskId, task.isRequired(), task.getCategory());
    em.persist(entity);
  }
}
