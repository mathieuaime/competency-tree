package com.mathieuaime.roadmap.service;

import com.mathieuaime.roadmap.model.Task;
import com.mathieuaime.roadmap.repository.CheckRepository;
import com.mathieuaime.roadmap.repository.RoadmapItemRepository;
import com.mathieuaime.roadmap.repository.TaskRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Transactional
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;
  private final CheckRepository checkRepository;
  private final RoadmapItemRepository roadmapItemRepository;

  public TaskServiceImpl(
      TaskRepository taskRepository,
      CheckRepository checkRepository,
      RoadmapItemRepository roadmapItemRepository
  ) {
    this.taskRepository = taskRepository;
    this.checkRepository = checkRepository;
    this.roadmapItemRepository = roadmapItemRepository;
  }

  @Override
  public List<Task> findAll() {
    return taskRepository.findAll();
  }

  @Override
  public Optional<Task> findByName(String name) {
    return taskRepository.findByName(name);
  }

  @Override
  public void check(long userId, long taskId) {
    roadmapItemRepository
        .findByTask(taskId)
        .forEach(roadmapItem -> checkRepository.check(userId, roadmapItem.getId()));
  }

  @Override
  public Task save(long roadmapId, long skillId, Task task) {
    task = taskRepository.merge(task);

    roadmapItemRepository.merge(roadmapId, skillId, task);

    return task;
  }
}
