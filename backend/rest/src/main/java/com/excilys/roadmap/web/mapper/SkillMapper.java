package com.excilys.roadmap.web.mapper;

import com.excilys.roadmap.model.Skill;
import com.excilys.roadmap.web.dto.SkillDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TaskMapper.class)
public interface SkillMapper extends DtoMapper<SkillDto, Skill> {
}
