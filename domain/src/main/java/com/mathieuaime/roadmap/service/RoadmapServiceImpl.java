package com.mathieuaime.roadmap.service;

import com.mathieuaime.roadmap.model.Roadmap;
import com.mathieuaime.roadmap.repository.RoadmapRepository;
import com.mathieuaime.roadmap.repository.TaskRepository;
import com.mathieuaime.roadmap.repository.projection.TaskProjection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoadmapServiceImpl implements RoadmapService {
  private final RoadmapRepository repository;
  private final TaskRepository taskRepository;

  public RoadmapServiceImpl(RoadmapRepository repository, TaskRepository taskRepository) {
    this.repository = repository;
    this.taskRepository = taskRepository;
  }

  @Override
  public List<Roadmap> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<Roadmap> findByName(String roadmapName) {
    Set<TaskProjection> allTasks = taskRepository.findByRoadmap(roadmapName);
    return new RoadmapExtractor(allTasks).getRoadmap();
  }

  @Override
  public Optional<Roadmap> findByUserAndName(long userId, String roadmapName) {
    Set<TaskProjection> userTasks = taskRepository.findByUserAndRoadmap(userId, roadmapName);
    Set<TaskProjection> allTasks = taskRepository.findByRoadmap(roadmapName);

    userTasks.addAll(allTasks);

    return new RoadmapExtractor(userTasks).getRoadmap();
  }

  @Override
  public Roadmap save(Roadmap roadmap) {
    return repository.save(roadmap);
  }
}
