package com.mathieuaime.roadmap;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

import com.mathieuaime.roadmap.model.Category;
import com.mathieuaime.roadmap.web.dto.RoadmapDto;
import com.mathieuaime.roadmap.web.dto.SkillDto;
import com.mathieuaime.roadmap.web.dto.TaskDto;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

class RoadmapIT extends BaseIT {

  @Test
  public void findRoadmaps() {
    var type = new ParameterizedTypeReference<List<RoadmapDto>>() {
    };

    var response = this.restTemplate
        .exchange(getBaseUrl() + "/roadmaps", HttpMethod.GET, null, type);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    var expected = new RoadmapDto(1L, "frontend", "frontend description", emptyList());
    assertThat(response.getBody()).containsExactly(expected);
  }

  @Test
  public void findRoadmapByName() {
    var response = this.restTemplate
        .exchange(getBaseUrl() + "/roadmaps/frontend", HttpMethod.GET, null, RoadmapDto.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    var basicTasks = List.of(
        new TaskDto(1L, "Basics of HTTP", "Learn the basics of HTTP", false, true, Category.BASIC),
        new TaskDto(2L, "DNS", "Learn how a DNS works", false, false, Category.BASIC)
    );

    var beginnerTasks = List.of(
        new TaskDto(3L, "Learn HTML", "Learn the basics of HTML", false, true, Category.BEGINNER)
    );

    var expectedSkills = List.of(
        new SkillDto(1L, "INTERNET", "flip_to_front", Category.BASIC, false, basicTasks),
        new SkillDto(2L, "BASIC SKILLS", "flip_to_front", Category.BEGINNER, false, beginnerTasks)
    );

    var expectedRoadmap =
        new RoadmapDto(1L, "frontend", "frontend description", expectedSkills);
    assertThat(response.getBody()).isEqualTo(expectedRoadmap);
  }

  @Test
  public void findRoadmapByName_notFound() {
    var response = this.restTemplate
        .exchange(getBaseUrl() + "/roadmaps/unknown", HttpMethod.GET, null, RoadmapDto.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  public void findUserRoadmap() {
    var response = this.restTemplate
        .exchange(getBaseUrl() + "/me/roadmaps/frontend", HttpMethod.GET, null, RoadmapDto.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    var basicTasks = List.of(
        new TaskDto(1L, "Basics of HTTP", "Learn the basics of HTTP", true, true, Category.BASIC),
        new TaskDto(2L, "DNS", "Learn how a DNS works", false, false, Category.BASIC)
    );

    var beginnerTasks = List.of(
        new TaskDto(3L, "Learn HTML", "Learn the basics of HTML", false, true, Category.BEGINNER)
    );

    var expectedSkills = List.of(
        new SkillDto(1L, "INTERNET", "flip_to_front", Category.BASIC, true, basicTasks),
        new SkillDto(2L, "BASIC SKILLS", "flip_to_front", Category.BEGINNER, false, beginnerTasks)
    );

    var expectedRoadmap =
        new RoadmapDto(1L, "frontend", "frontend description", expectedSkills);
    assertThat(response.getBody()).isEqualTo(expectedRoadmap);
  }

  @Test
  public void findUserRoadmap_notFound() {
    var response = this.restTemplate
        .exchange(getBaseUrl() + "/me/roadmaps/unknown", HttpMethod.GET, null, RoadmapDto.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }
}