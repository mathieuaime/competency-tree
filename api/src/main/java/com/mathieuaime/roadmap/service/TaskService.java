package com.mathieuaime.roadmap.service;

import com.mathieuaime.roadmap.model.Task;
import java.util.List;

public interface TaskService {
  List<Task> findAll();

  void check(long userId, long taskId);

  Task save(long roadmapId, long skillId, Task task);
}
