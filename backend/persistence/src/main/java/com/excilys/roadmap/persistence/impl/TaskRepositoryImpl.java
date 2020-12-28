package com.excilys.roadmap.persistence.impl;

import static java.util.stream.Collectors.toSet;

import com.excilys.roadmap.model.Task;
import com.excilys.roadmap.persistence.entity.TaskEntity;
import com.excilys.roadmap.persistence.mapper.TaskMapper;
import com.excilys.roadmap.repository.TaskRepository;
import com.excilys.roadmap.repository.projection.TaskProjection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepositoryImpl implements TaskRepository {
  private static final String FIND_ALL_BY_ROADMAP_QUERY =
      "select new com.excilys.roadmap.repository.projection.TaskProjection("
          + "item.roadmap.id, item.roadmap.name, item.roadmap.description,"
          + "item.skill.id, item.skill.name, item.skill.icon,"
          + "item.task.id, item.task.name, item.task.description,"
          + "item.category, false, item.required) "
          + "from RoadmapItem item "
          + "where item.roadmap.name = ?1";

  private static final String FIND_ALL_BY_USER_AND_ROADMAP_QUERY =
      "select new com.excilys.roadmap.repository.projection.TaskProjection("
          + "item.roadmap.id, item.roadmap.name, item.roadmap.description,"
          + "item.skill.id, item.skill.name, item.skill.icon,"
          + "item.task.id, item.task.name, item.task.description,"
          + "item.category, c.done, item.required) "
          + "from RoadmapItem item "
          + "left join Check c on item.id = c.id.roadmapItemId "
          + "where c.id.userId = ?1 and item.roadmap.name = ?2";

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Task> findAll() {
    return em.createQuery("from Task", TaskEntity.class)
        .getResultStream()
        .map(TaskMapper::toModel)
        .collect(Collectors.toList());
  }

  @Override
  public Set<TaskProjection> findByRoadmap(String roadmapName) {
    return em.createQuery(FIND_ALL_BY_ROADMAP_QUERY, TaskProjection.class)
        .setParameter(1, roadmapName)
        .getResultStream()
        .collect(toSet());
  }

  @Override
  public Set<TaskProjection> findByUserAndRoadmap(long userId, String roadmapName) {
    return em.createQuery(FIND_ALL_BY_USER_AND_ROADMAP_QUERY, TaskProjection.class)
        .setParameter(1, userId)
        .setParameter(2, roadmapName)
        .getResultStream()
        .collect(toSet());
  }

  @Override
  public Task retrieveOrCreate(Task task) {
    TaskEntity entity = TaskMapper.toEntity(task);

    if (entity.getId() == null) {
      Optional<TaskEntity> optEntity = findByName(task.getName());
      if (optEntity.isPresent()) {
        return TaskMapper.toModel(optEntity.get());
      } else {
        em.persist(entity);
      }
    } else {
      entity = em.merge(entity);
    }

    return task.setId(entity.getId());
  }

  private Optional<TaskEntity> findByName(String name) {
    return em.createQuery("from Task where name = ?1", TaskEntity.class)
        .setParameter(1, name)
        .getResultStream()
        .findFirst();
  }
}
