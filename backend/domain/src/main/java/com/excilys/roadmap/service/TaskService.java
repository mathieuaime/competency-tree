package com.excilys.roadmap.service;

import com.excilys.roadmap.model.Task;
import com.excilys.roadmap.repository.CheckRepository;
import com.excilys.roadmap.repository.RoadmapItemRepository;
import com.excilys.roadmap.repository.TaskRepository;
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

    task = repository.retrieveOrCreate(task);

    if (creation) {
      roadmapItemRepository.create(roadmapId, skillId, task.getId(), task);
    }

    return task;
  }
}
