package com.mathieuaime.roadmap.initializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mathieuaime.roadmap.model.Roadmap;
import com.mathieuaime.roadmap.model.Skill;
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

  private final Resource data;
  private final ObjectMapper objectMapper;
  private final RoadmapService roadmapService;
  private final SkillService skillService;
  private final TaskService taskService;

  public DataInitializer(
      @Value("classpath:data.json") Resource data,
      ObjectMapper objectMapper,
      RoadmapService roadmapService,
      SkillService skillService,
      TaskService taskService
  ) {
    this.data = data;
    this.objectMapper = objectMapper;
    this.roadmapService = roadmapService;
    this.skillService = skillService;
    this.taskService = taskService;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (data.exists()) {
      InitialData initialData = objectMapper.readValue(data.getFile(), InitialData.class);

      initialData.roadmaps().stream().map(InitialDataMapper::toModel).forEach(this::saveRoadmap);
      initialData.skills().stream().map(InitialDataMapper::toModel).forEach(this::saveSkill);
      initialData.tasks().forEach(this::saveTask);
      initialData.checks().forEach(this::check);
    } else {
      log.warn("data.json not found, not importing data");
    }
  }

  private void saveRoadmap(Roadmap roadmap) {
    roadmapService.save(roadmap);
  }

  private void saveSkill(Skill skill) {
    skillService.save(skill);
  }

  private void saveTask(TaskData tasksData) {
    long roadmapId = roadmapService.findByName(tasksData.roadmap()).orElseThrow().getId();
    long skillId = skillService.findByName(tasksData.skill()).orElseThrow().getId();

    taskService.save(roadmapId, skillId, InitialDataMapper.toModel(tasksData));
  }

  private void check(UserCheckData userCheckData) {
    long taskId = taskService.findByName(userCheckData.task()).orElseThrow().getId();

    taskService.check(userCheckData.userId(), taskId);
  }
}
