package com.mathieuaime.roadmap.it.benchmark;

import static com.mathieuaime.roadmap.fixture.TestFixture.randomString;
import static com.mathieuaime.roadmap.fixture.TestFixture.randomTask;

import com.mathieuaime.roadmap.it.fixture.persistence.TestRoadmapItemRepository;
import com.mathieuaime.roadmap.it.fixture.persistence.TestRoadmapRepository;
import com.mathieuaime.roadmap.it.fixture.persistence.TestSkillRepository;
import com.mathieuaime.roadmap.it.fixture.persistence.TestTaskRepository;
import com.mathieuaime.roadmap.persistence.entity.RoadmapEntity;
import com.mathieuaime.roadmap.persistence.entity.SkillEntity;
import com.mathieuaime.roadmap.persistence.entity.TaskEntity;
import com.mathieuaime.roadmap.service.TaskService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.springframework.beans.factory.annotation.Autowired;

@Tag("task")
public class TaskServiceBenchmarkIT extends AbstractBenchmarkIT {

  private static TaskService taskService;
  private static TestRoadmapItemRepository testRoadmapItemRepository;
  private static TestRoadmapRepository testRoadmapRepository;
  private static TestSkillRepository testSkillRepository;
  private static TestTaskRepository testTaskRepository;

  private long roadmapId;
  private long skillId;

  @Autowired
  public void init(
      TaskService taskService,
      TestRoadmapItemRepository testRoadmapItemRepository,
      TestRoadmapRepository testRoadmapRepository,
      TestSkillRepository testSkillRepository,
      TestTaskRepository testTaskRepository
  ) {
    TaskServiceBenchmarkIT.taskService = taskService;
    TaskServiceBenchmarkIT.testRoadmapItemRepository = testRoadmapItemRepository;
    TaskServiceBenchmarkIT.testRoadmapRepository = testRoadmapRepository;
    TaskServiceBenchmarkIT.testSkillRepository = testSkillRepository;
    TaskServiceBenchmarkIT.testTaskRepository = testTaskRepository;
  }

  @Test
  void runBenchmark() throws Exception {
    super.runBenchmarks();
  }

  @Benchmark
  public void findAll() {
    taskService.findAll();
  }

  @Benchmark
  public void save() {
    taskService.save(roadmapId, skillId, randomTask());
  }

  @Setup(Level.Iteration)
  public void setUp(SetupParameters parameters) {
    int size = Integer.parseInt(parameters.initialTasks);

    for (int i = 0; i < size; i++) {
      var taskEntity = new TaskEntity(null, randomString(), randomString());
      testTaskRepository.save(taskEntity);
    }

    roadmapId = testRoadmapRepository.save(
        new RoadmapEntity(null, randomString(), randomString(), randomString(7))).getId();
    skillId = testSkillRepository.save(
        new SkillEntity(null, randomString(), randomString())).getId();
  }

  @TearDown(Level.Iteration)
  public void tearDown() {
    testRoadmapItemRepository.deleteAll();
    testRoadmapRepository.deleteAll();
    testSkillRepository.deleteAll();
    testTaskRepository.deleteAll();
  }

  @State(value = Scope.Benchmark)
  public static class SetupParameters {

    @Param({"10"})
    String initialTasks;
  }
}