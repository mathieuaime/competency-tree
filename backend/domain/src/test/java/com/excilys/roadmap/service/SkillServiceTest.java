package com.excilys.roadmap.service;

import static com.excilys.roadmap.fixture.TestFixture.randomSkill;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.excilys.roadmap.model.Skill;
import com.excilys.roadmap.repository.SkillRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SkillServiceTest {
  @Mock
  private SkillRepository skillRepository;

  @InjectMocks
  private SkillService skillService;

  @Test
  void findAll() {
    Skill skill = randomSkill();

    when(skillRepository.findAll()).thenReturn(List.of(skill));

    List<Skill> skills = skillService.findAll();

    assertThat(skills).containsExactly(skill);
  }
}