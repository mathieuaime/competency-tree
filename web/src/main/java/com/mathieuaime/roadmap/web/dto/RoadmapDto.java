package com.mathieuaime.roadmap.web.dto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RoadmapDto {

  private Long id;
  private String name;
  private String description;
  private List<SkillDto> skills = Collections.emptyList();

  public RoadmapDto() {
  }

  public RoadmapDto(Long id, String name, String description, List<SkillDto> skills) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.skills = skills;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<SkillDto> getSkills() {
    return skills;
  }

  public void setSkills(List<SkillDto> skills) {
    this.skills = skills;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoadmapDto that = (RoadmapDto) o;
    return Objects.equals(id, that.id)
        && Objects.equals(name, that.name)
        && Objects.equals(description, that.description)
        && Objects.equals(skills, that.skills);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, skills);
  }

  @Override
  public String toString() {
    return "RoadmapDto{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", skills='" + skills + '\'' +
        '}';
  }
}
