package com.mathieuaime.roadmap.config;

import com.mathieuaime.roadmap.repository.CheckRepository;
import com.mathieuaime.roadmap.repository.RoadmapItemRepository;
import com.mathieuaime.roadmap.repository.RoadmapRepository;
import com.mathieuaime.roadmap.repository.SkillRepository;
import com.mathieuaime.roadmap.repository.TaskRepository;
import com.mathieuaime.roadmap.service.RoadmapService;
import com.mathieuaime.roadmap.service.RoadmapServiceImpl;
import com.mathieuaime.roadmap.service.SkillService;
import com.mathieuaime.roadmap.service.SkillServiceImpl;
import com.mathieuaime.roadmap.service.TaskService;
import com.mathieuaime.roadmap.service.TaskServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
  @Bean
  public RoadmapService roadmapService(
      RoadmapRepository roadmapRepository,
      TaskRepository taskRepository
  ) {
    return new RoadmapServiceImpl(roadmapRepository, taskRepository);
  }

  @Bean
  public SkillService skillService(SkillRepository skillRepository) {
    return new SkillServiceImpl(skillRepository);
  }

  @Bean
  public TaskService taskService(
      TaskRepository taskRepository,
      CheckRepository checkRepository,
      RoadmapItemRepository roadmapItemRepository
  ) {
    return new TaskServiceImpl(taskRepository, checkRepository, roadmapItemRepository);
  }
}
