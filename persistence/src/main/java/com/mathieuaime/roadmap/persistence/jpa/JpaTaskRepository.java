package com.mathieuaime.roadmap.persistence.jpa;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import com.mathieuaime.roadmap.model.Task;
import com.mathieuaime.roadmap.persistence.entity.TaskEntity;
import com.mathieuaime.roadmap.persistence.mapper.TaskMapper;
import com.mathieuaime.roadmap.repository.TaskRepository;
import com.mathieuaime.roadmap.repository.projection.TaskProjection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class JpaTaskRepository implements TaskRepository {

  private static final String FIND_ALL_BY_ROADMAP_QUERY = """
        SELECT new com.mathieuaime.roadmap.repository.projection.TaskProjection(
        item.roadmap.id, item.roadmap.name, item.roadmap.description, item.roadmap.color,
        item.skill.id, item.skill.name, item.skill.icon,
        item.task.id, item.task.name, item.task.description,
        item.category, false, item.required)
        FROM RoadmapItem item
        WHERE item.roadmap.name = ?1
      """;

  private static final String FIND_ALL_BY_USER_AND_ROADMAP_QUERY = """
        SELECT new com.mathieuaime.roadmap.repository.projection.TaskProjection(
        item.roadmap.id, item.roadmap.name, item.roadmap.description, item.roadmap.color,
        item.skill.id, item.skill.name, item.skill.icon,
        item.task.id, item.task.name, item.task.description,
        item.category, c.done, item.required)
        FROM RoadmapItem item
        LEFT JOIN Check c on item.id = c.id.roadmapItemId
        WHERE c.id.userId = ?1
        AND item.roadmap.name = ?2
      """;

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<Task> findAll() {
    return em.createQuery("from Task", TaskEntity.class)
        .getResultStream()
        .map(TaskMapper::toModel)
        .collect(toList());
  }

  @Override
  public Optional<Task> findByName(String name) {
    return findEntityByName(name).map(TaskMapper::toModel);
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
  public Task merge(Task task) {
    TaskEntity entity = TaskMapper.toEntity(task);

    if (entity.getId() == null) {
      Optional<Task> optTask = findByName(task.getName());
      if (optTask.isPresent()) {
        return optTask.get();
      } else {
        em.persist(entity);
      }
    } else {
      entity = em.merge(entity);
    }

    return task.withId(entity.getId());
  }

  private Optional<TaskEntity> findEntityByName(String name) {
    return em.unwrap(Session.class)
        .byNaturalId(TaskEntity.class)
        .using("name", name)
        .loadOptional();
  }
}
