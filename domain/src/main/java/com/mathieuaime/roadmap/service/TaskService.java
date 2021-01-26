package com.mathieuaime.roadmap.service;

import com.mathieuaime.roadmap.model.Task;
import com.mathieuaime.roadmap.repository.CheckRepository;
import com.mathieuaime.roadmap.repository.RoadmapItemRepository;
import com.mathieuaime.roadmap.repository.TaskRepository;
import java.util.List;
import javax.transaction.Transactional;

@Transactional
public class TaskService {
  private final TaskRepository repository;
  private final CheckRepository checkRepository;
  private final RoadmapItemRepository roadmapItemRepository;

  public TaskService(
      TaskRepository repository,
      CheckRepository checkRepository,
      RoadmapItemRepository roadmapItemRepository) {
    this.repository = repository;
    this.checkRepository = checkRepository;
    this.roadmapItemRepository = roadmapItemRepository;
  }

  public List<Task> findAll() {
    return repository.findAll();
  }

  public void check(long userId, long taskId) {
    roadmapItemRepository
        .findByTask(taskId)
        .forEach(roadmapItem -> checkRepository.check(userId, roadmapItem.getId()));
  }

  public Task save(long roadmapId, long skillId, Task task) {
    boolean creation = task.getId() == null;

    task = repository.merge(task);

    if (creation) {
      roadmapItemRepository.create(roadmapId, skillId, task);
    }

    return task;
  }
}