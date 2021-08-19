package com.mathieuaime.roadmap.it.benchmark;

import static com.mathieuaime.roadmap.fixture.TestFixture.randomSkill;
import static com.mathieuaime.roadmap.fixture.TestFixture.randomString;

import com.mathieuaime.roadmap.it.fixture.persistence.TestSkillRepository;
import com.mathieuaime.roadmap.persistence.entity.SkillEntity;
import com.mathieuaime.roadmap.service.SkillService;
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

@Tag("skill")
public class SkillServiceBenchmarkIT extends AbstractBenchmarkIT {

  private static SkillService skillService;
  private static TestSkillRepository testSkillRepository;

  @Autowired
  public void init(
      SkillService skillService,
      TestSkillRepository testSkillRepository
  ) {
    SkillServiceBenchmarkIT.skillService = skillService;
    SkillServiceBenchmarkIT.testSkillRepository = testSkillRepository;
  }

  @Test
  void runBenchmark() throws Exception {
    super.runBenchmarks();
  }

  @Benchmark
  public void findAll() {
    skillService.findAll();
  }

  @Benchmark
  public void save() {
    skillService.save(randomSkill());
  }

  @Setup(Level.Iteration)
  public void setUp(SetupParameters parameters) {
    int size = Integer.parseInt(parameters.initialSkills);

    for (int i = 0; i < size; i++) {
      var skillEntity = new SkillEntity(null, randomString(), randomString());
      testSkillRepository.save(skillEntity);
    }
  }

  @TearDown(Level.Iteration)
  public void tearDown() {
    testSkillRepository.deleteAll();
  }

  @State(value = Scope.Benchmark)
  public static class SetupParameters {

    @Param({"10"})
    String initialSkills;
  }
}