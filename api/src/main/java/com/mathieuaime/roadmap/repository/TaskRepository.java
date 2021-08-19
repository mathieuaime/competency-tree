package com.mathieuaime.roadmap.repository;

import com.mathieuaime.roadmap.model.Task;
import com.mathieuaime.roadmap.repository.projection.TaskProjection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TaskRepository {

  List<Task> findAll();

  Optional<Task> findByName(String name);

  Set<TaskProjection> findByRoadmap(String roadmapName);

  Set<TaskProjection> findByUserAndRoadmap(long userId, String roadmapName);

  Task merge(Task task);
}
