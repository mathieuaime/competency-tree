package com.mathieuaime.roadmap.it.benchmark;

import com.mathieuaime.roadmap.service.RoadmapService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

@Tag("roadmap")
@ActiveProfiles("demo")
public class FindRoadmapBenchmarkIT extends AbstractBenchmarkIT {

  private static RoadmapService roadmapService;

  @Autowired
  public void init(RoadmapService roadmapService) {
    FindRoadmapBenchmarkIT.roadmapService = roadmapService;
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
  public void findByName_notFound() {
    roadmapService.findByName("unknown name");
  }

  @Benchmark
  public void findByName() {
    roadmapService.findByName("frontend");
  }

  @Benchmark
  public void findUserRoadmaps_notFound() {
    roadmapService.findByUserAndName(1L, "unknown name");
  }

  @Benchmark
  public void findUserRoadmaps() {
    roadmapService.findByUserAndName(1L, "frontend");
  }
}