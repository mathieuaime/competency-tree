package com.mathieuaime.roadmap.initializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mathieuaime.roadmap.service.RoadmapService;
import com.mathieuaime.roadmap.service.SkillService;
import com.mathieuaime.roadmap.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Profile("demo")
@Component
public class DataInitializer implements ApplicationRunner {

  private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);

  @Value("classpath:data.json")
  private Resource data;

  private final ObjectMapper objectMapper;
  private final RoadmapService roadmapService;
  private final SkillService skillService;
  private final TaskService taskService;

  public DataInitializer(
      ObjectMapper objectMapper,
      RoadmapService roadmapService,
      SkillService skillService,
      TaskService taskService
  ) {
    this.objectMapper = objectMapper;
    this.roadmapService = roadmapService;
    this.skillService = skillService;
    this.taskService = taskService;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (data.exists()) {
      InitialData initialData = objectMapper.readValue(data.getFile(), InitialData.class);

      initialData.roadmaps().stream().map(InitialDataMapper::toModel).forEach(roadmapService::save);
      initialData.skills().stream().map(InitialDataMapper::toModel).forEach(skillService::save);
      initialData.tasks().forEach(tasksData -> taskService
          .save(tasksData.roadmapId(), tasksData.skillId(), InitialDataMapper.toModel(tasksData)));
      initialData.checks().forEach(userCheckData -> taskService
          .check(userCheckData.userId(), userCheckData.roadmapItemId()));
    } else {
      log.warn("data.json not found, not importing data");
    }
  }
}
