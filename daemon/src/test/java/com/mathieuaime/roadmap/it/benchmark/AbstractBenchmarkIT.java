package com.mathieuaime.roadmap.it.benchmark;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Tag;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.results.BenchmarkResult;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MariaDBContainer;

@Tag("benchmark")
@State(Scope.Benchmark)
@SpringBootTest
public class AbstractBenchmarkIT {

  private static final double AVERAGE_EXPECTED_TIME_IN_MS = 100;

  private static final MariaDBContainer<?> mariaDBContainer =
      new MariaDBContainer<>("mariadb:10.3.6")
          .withReuse(true);

  static {
    mariaDBContainer.start();
  }

  @DynamicPropertySource
  static void registerPgProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", mariaDBContainer::getJdbcUrl);
    registry.add("spring.datasource.username", mariaDBContainer::getUsername);
    registry.add("spring.datasource.password", mariaDBContainer::getPassword);
  }

  protected void runBenchmarks() throws Exception {
    Options opts = getOptions();
    Collection<RunResult> runResults = new Runner(opts).run();
    assertOutputs(runResults);
  }

  protected Options getOptions() {
    return new OptionsBuilder()
        .include("\\." + this.getClass().getSimpleName() + "\\.")
        .mode(Mode.AverageTime)
        .timeUnit(TimeUnit.MILLISECONDS)
        .warmupIterations(1)
        .warmupTime(TimeValue.seconds(1L))
        .measurementIterations(3)
        .measurementTime(TimeValue.seconds(1L))
        .forks(0)
        .threads(4)
        .shouldDoGC(true)
        .shouldFailOnError(true)
        .resultFormat(ResultFormatType.JSON)
        .jvmArgs("-server")
        .build();
  }

  protected void assertOutputs(Collection<RunResult> results) {
    for (RunResult r : results) {
      for (BenchmarkResult rr : r.getBenchmarkResults()) {

        Mode mode = rr.getParams().getMode();
        double score = rr.getPrimaryResult().getScore();
        String methodName = rr.getPrimaryResult().getLabel();

        assertThat(mode)
            .withFailMessage("Test mode is not average mode. Method = {}", methodName)
            .isEqualTo(Mode.AverageTime);

        assertThat(score)
            .withFailMessage("Benchmark score = {} is higher than {} {}. Too slow performance !",
                score, AVERAGE_EXPECTED_TIME_IN_MS, rr.getScoreUnit())
            .isLessThanOrEqualTo(AVERAGE_EXPECTED_TIME_IN_MS);
      }
    }
  }
}