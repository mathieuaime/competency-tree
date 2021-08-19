package com.mathieuaime.roadmap.service;

import com.mathieuaime.roadmap.model.Task;
import java.util.List;
import java.util.Optional;

public interface TaskService {

  List<Task> findAll();

  Optional<Task> findByName(String name);

  void check(long userId, long taskId);

  Task save(long roadmapId, long skillId, Task task);
}
