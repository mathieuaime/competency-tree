package com.excilys.roadmap.web.mapper;

import static java.util.stream.Collectors.toList;

import com.excilys.roadmap.model.Roadmap;
import com.excilys.roadmap.model.Skill;
import com.excilys.roadmap.web.dto.RoadmapDto;
import com.excilys.roadmap.web.dto.SkillDto;
import java.util.Collection;

public final class RoadmapMapper {
  public static Roadmap toModel(RoadmapDto dto) {
    Collection<Skill> skills = dto.getSkills().stream().map(SkillMapper::toModel).collect(toList());
    return new Roadmap(dto.getId(), dto.getName(), dto.getDescription(), skills);
  }

  public static RoadmapDto toDto(Roadmap model) {
    Collection<SkillDto> skills =
        model.getSkills().stream().map(SkillMapper::toDto).collect(toList());
    return new RoadmapDto(model.getId(), model.getName(), model.getDescription(), skills);
  }
}
