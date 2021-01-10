package com.excilys.roadmap.persistence.impl;

import com.excilys.roadmap.model.Task;
import com.excilys.roadmap.persistence.entity.RoadmapItemEntity;
import com.excilys.roadmap.repository.RoadmapItemRepository;
import com.excilys.roadmap.repository.projection.RoadmapItemProjection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class RoadmapItemRepositoryImpl implements RoadmapItemRepository {
  private static final String FIND_BY_TASK = """
        SELECT new com.excilys.roadmap.repository.projection.RoadmapItemProjection(ri.id) 
        FROM RoadmapItem ri 
        WHERE ri.task.id = ?1      
      """;

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<RoadmapItemProjection> findByTask(long taskId) {
    return em.createQuery(FIND_BY_TASK, RoadmapItemProjection.class)
        .setParameter(1, taskId)
        .getResultList();
  }

  @Override
  public void create(long roadmapId, long skillId, Task task) {
    RoadmapItemEntity entity =
        new RoadmapItemEntity(roadmapId, skillId, task.getId(), task.isRequired(),
            task.getCategory());
    em.persist(entity);
  }
}
