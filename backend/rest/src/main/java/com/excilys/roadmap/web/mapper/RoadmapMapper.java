package com.excilys.roadmap.web.mapper;

import com.excilys.roadmap.model.Roadmap;
import com.excilys.roadmap.web.dto.RoadmapDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = SkillMapper.class)
public interface RoadmapMapper extends DtoMapper<RoadmapDto, Roadmap> {
}
