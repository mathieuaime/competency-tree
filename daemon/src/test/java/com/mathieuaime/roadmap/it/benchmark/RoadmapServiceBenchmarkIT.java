package com.mathieuaime.roadmap.it.benchmark;

import static com.mathieuaime.roadmap.fixture.TestFixture.randomRoadmap;
import static com.mathieuaime.roadmap.fixture.TestFixture.randomString;

import com.mathieuaime.roadmap.it.fixture.persistence.TestRoadmapRepository;
import com.mathieuaime.roadmap.persistence.entity.RoadmapEntity;
import com.mathieuaime.roadmap.service.RoadmapService;
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

@Tag("roadmap")
public class RoadmapServiceBenchmarkIT extends AbstractBenchmarkIT {

  private static RoadmapService roadmapService;
  private static TestRoadmapRepository testRoadmapRepository;

  @Autowired
  public void init(
      RoadmapService roadmapService,
      TestRoadmapRepository testRoadmapRepository
  ) {
    RoadmapServiceBenchmarkIT.roadmapService = roadmapService;
    RoadmapServiceBenchmarkIT.testRoadmapRepository = testRoadmapRepository;
  }

  @Test
  void runBenchmark() throws Exception {
    super.runBenchmarks();
  }

  @Benchmark
  public void findAll() {
    roadmapService.findAll();
  }

  @Benchmark
  public void update() {
    roadmapService.save(randomRoadmap());
  }

  @Setup(Level.Iteration)
  public void setUp(SetupParameters parameters) {
    int sizeRoadmaps = Integer.parseInt(parameters.initialRoadmaps);

    String name = "name";
    for (int i = 0; i < sizeRoadmaps; ++i) {
      var roadmapEntity = new RoadmapEntity(null, name, randomString(), randomString(7));
      testRoadmapRepository.save(roadmapEntity);
      name = randomString();
    }
  }

  @TearDown(Level.Iteration)
  public void tearDown() {
    testRoadmapRepository.deleteAll();
  }

  @State(value = Scope.Benchmark)
  public static class SetupParameters {

    @Param({"10"})
    String initialRoadmaps;
  }
}