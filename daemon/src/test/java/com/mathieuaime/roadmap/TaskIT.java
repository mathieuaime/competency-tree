package com.mathieuaime.roadmap;

import static org.assertj.core.api.Assertions.assertThat;

import com.mathieuaime.roadmap.web.dto.TaskDto;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

class TaskIT extends BaseIT {

  @Test
  public void findTasks() {
    var type = new ParameterizedTypeReference<List<TaskDto>>() {
    };

    var response = this.restTemplate
        .exchange(getBaseUrl() + "/tasks", HttpMethod.GET, null, type);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    var expected = List.of(
        new TaskDto(1L, "Basics of HTTP", "Learn the basics of HTTP", false, false, null),
        new TaskDto(2L, "DNS", "Learn how a DNS works", false, false, null),
        new TaskDto(3L, "Learn HTML", "Learn the basics of HTML", false, false, null)
    );
    assertThat(response.getBody()).containsExactlyElementsOf(expected);
  }
}