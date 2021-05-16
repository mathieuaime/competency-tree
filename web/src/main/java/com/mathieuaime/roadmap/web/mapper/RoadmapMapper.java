package com.mathieuaime.roadmap.web.mapper;

import static java.util.stream.Collectors.toList;

import com.mathieuaime.roadmap.model.Roadmap;
import com.mathieuaime.roadmap.model.Skill;
import com.mathieuaime.roadmap.web.dto.RoadmapDto;
import com.mathieuaime.roadmap.web.dto.SkillDto;
import java.util.List;

public final class RoadmapMapper {

  private RoadmapMapper() {
    throw new AssertionError();
  }

  public static Roadmap toModel(RoadmapDto dto) {
    List<Skill> skills = dto.getSkills().stream().map(SkillMapper::toModel).collect(toList());
    return new Roadmap(dto.getId(), dto.getName(), dto.getDescription(), skills);
  }

  public static RoadmapDto toDto(Roadmap model) {
    List<SkillDto> skills = model.getSkills().stream()
        .sorted()
        .map(SkillMapper::toDto)
        .collect(toList());
    return new RoadmapDto(model.getId(), model.getName(), model.getDescription(), skills);
  }
}
