package com.excilys.roadmap.repository;

import com.excilys.roadmap.model.Task;
import com.excilys.roadmap.repository.projection.TaskProjection;
import java.util.List;
import java.util.Set;

public interface TaskRepository {
  List<Task> findAll();

  Set<TaskProjection> findByRoadmap(String roadmapName);

  Set<TaskProjection> findByUserAndRoadmap(long userId, String roadmapName);

  Task retrieveOrCreate(Task task);
}
