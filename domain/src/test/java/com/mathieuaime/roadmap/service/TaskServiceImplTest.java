package com.mathieuaime.roadmap.service;

import static com.mathieuaime.roadmap.TestFixture.randomTask;
import static com.mathieuaime.roadmap.TestFixture.randomTaskFromId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.mathieuaime.roadmap.model.Task;
import com.mathieuaime.roadmap.repository.CheckRepository;
import com.mathieuaime.roadmap.repository.RoadmapItemRepository;
import com.mathieuaime.roadmap.repository.TaskRepository;
import com.mathieuaime.roadmap.repository.projection.RoadmapItemProjection;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

  @Mock
  private TaskRepository taskRepository;
  @Mock
  private CheckRepository checkRepository;
  @Mock
  private RoadmapItemRepository roadmapItemRepository;

  @InjectMocks
  private TaskServiceImpl taskService;

  @Test
  void findAll() {
    Task task = randomTask();

    when(taskRepository.findAll()).thenReturn(List.of(task));

    List<Task> tasks = taskService.findAll();

    assertThat(tasks).containsExactly(task);
  }

  @Test
  void check_whenRoadmapItemNotFound_shouldDoNothing() {
    long userId = 1000;
    long taskId = 1;

    when(roadmapItemRepository.findByTask(taskId)).thenReturn(Collections.emptyList());

    taskService.check(userId, taskId);

    verifyNoMoreInteractions(checkRepository);
  }

  @Test
  void check_whenRoadmapItemFound_shouldCheckItem() {
    long userId = 1;
    long taskId = 2;
    var roadmapItemProjectionId = 10;

    var roadmapItemProjection = new RoadmapItemProjection(roadmapItemProjectionId);

    when(roadmapItemRepository.findByTask(taskId)).thenReturn(List.of(roadmapItemProjection));

    taskService.check(userId, taskId);

    verify(checkRepository).check(userId, roadmapItemProjectionId);
  }

  @Test
  void save_whenTheTaskDoesNotExist_shouldCreateTaskAndRoadmapItem() {
    long roadmapId = 1;
    long skillId = 2;
    Task task = randomTask();
    Task expectedTask = new Task(3L, task.getName(), task.getDescription());

    when(taskRepository.merge(task)).thenReturn(expectedTask);

    var savedTask = taskService.save(roadmapId, skillId, task);

    Assertions.assertThat(savedTask).isEqualTo(expectedTask);
    verify(roadmapItemRepository).merge(roadmapId, skillId, expectedTask);
  }

  @Test
  void save_whenTheTaskExists_shouldMergeTaskAndRoadmapItem() {
    long roadmapId = 1;
    long skillId = 2;
    Task task = randomTaskFromId(3L);

    when(taskRepository.merge(task)).thenReturn(task);

    var savedTask = taskService.save(roadmapId, skillId, task);

    Assertions.assertThat(savedTask).isEqualTo(task);
    verify(roadmapItemRepository).merge(roadmapId, skillId, task);
  }
}