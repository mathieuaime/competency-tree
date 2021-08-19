package com.mathieuaime.roadmap.initializer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mathieuaime.roadmap.model.Category;
import com.mathieuaime.roadmap.model.Roadmap;
import com.mathieuaime.roadmap.model.Skill;
import com.mathieuaime.roadmap.model.Task;
import com.mathieuaime.roadmap.service.RoadmapService;
import com.mathieuaime.roadmap.service.SkillService;
import com.mathieuaime.roadmap.service.TaskService;
import java.net.MalformedURLException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.core.io.FileUrlResource;

@ExtendWith(MockitoExtension.class)
class DataInitializerTest {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  @Mock
  private RoadmapService roadmapService;

  @Mock
  private SkillService skillService;

  @Mock
  private TaskService taskService;

  private DataInitializer initializer;

  private DataInitializer createInitializer(String location) throws MalformedURLException {
    FileUrlResource data = new FileUrlResource(location);
    return new DataInitializer(data, OBJECT_MAPPER, roadmapService, skillService, taskService);
  }

  @Test
  void dataInitialization_whenResourceDoesNotExist() throws Exception {
    initializer = createInitializer("src/test/resources/notFound.json");

    initializer.run(new DefaultApplicationArguments());

    verifyNoInteractions(roadmapService, skillService, taskService);
  }

  @Test
  void dataInitialization() throws Exception {
    initializer = createInitializer("src/test/resources/data.json");

    Roadmap roadmap = new Roadmap(null, "frontend", "frontend description", "#000000");

    Skill skill1 = new Skill(null, "BASIC SKILLS", "icon");
    Skill skill2 = new Skill(null, "BEGINNER SKILLS", "icon");
    Skill skill3 = new Skill(null, "CONFIRMED SKILLS", "icon");
    Skill skill4 = new Skill(null, "EXPERT SKILLS", "icon");

    Task task1 =
        new Task(null, "Basic task 1", "Learn the task 1", false, true, Category.BASIC);
    Task task2 =
        new Task(null, "Basic task 2", "Learn the task 2", false, false, Category.BASIC);
    Task task3 =
        new Task(null, "Beginner task 3", "Learn the task 3", false, true, Category.BEGINNER);
    Task task4 =
        new Task(null, "Confirmed task 4", "Learn the task 4", false, true, Category.CONFIRMED);
    Task task5 =
        new Task(null, "Expert task 5", "Learn the task 5", false, true, Category.EXPERT);

    when(roadmapService.findByName(roadmap.getName())).thenReturn(Optional.of(roadmap.withId(1L)));

    when(skillService.findByName(skill1.getName())).thenReturn(Optional.of(skill1.withId(1L)));
    when(skillService.findByName(skill2.getName())).thenReturn(Optional.of(skill2.withId(2L)));
    when(skillService.findByName(skill3.getName())).thenReturn(Optional.of(skill3.withId(3L)));
    when(skillService.findByName(skill4.getName())).thenReturn(Optional.of(skill4.withId(4L)));

    when(taskService.findByName(task2.getName())).thenReturn(Optional.of(task2.withId(2L)));

    initializer.run(new DefaultApplicationArguments());

    verify(roadmapService).save(roadmap);

    verify(skillService).save(skill1);
    verify(skillService).save(skill2);
    verify(skillService).save(skill3);
    verify(skillService).save(skill4);

    verify(taskService).save(1L, 1L, task1);
    verify(taskService).save(1L, 1L, task2);
    verify(taskService).save(1L, 2L, task3);
    verify(taskService).save(1L, 3L, task4);
    verify(taskService).save(1L, 4L, task5);

    verify(taskService).check(1L, 2L);
  }
}