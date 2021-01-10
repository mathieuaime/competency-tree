package com.excilys.roadmap.config;

import com.excilys.roadmap.repository.CheckRepository;
import com.excilys.roadmap.repository.RoadmapItemRepository;
import com.excilys.roadmap.repository.RoadmapRepository;
import com.excilys.roadmap.repository.SkillRepository;
import com.excilys.roadmap.repository.TaskRepository;
import com.excilys.roadmap.service.RoadmapService;
import com.excilys.roadmap.service.SkillService;
import com.excilys.roadmap.service.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {
  @Bean
  public RoadmapService roadmapService(
      RoadmapRepository roadmapRepository,
      TaskRepository taskRepository) {
    return new RoadmapService(roadmapRepository, taskRepository);
  }

  @Bean
  public SkillService skillService(SkillRepository skillRepository) {
    return new SkillService(skillRepository);
  }

  @Bean
  public TaskService taskService(
      TaskRepository taskRepository,
      CheckRepository checkRepository,
      RoadmapItemRepository roadmapItemRepository) {
    return new TaskService(taskRepository, checkRepository, roadmapItemRepository);
  }
}
