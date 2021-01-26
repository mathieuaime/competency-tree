package com.mathieuaime.roadmap.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.mathieuaime.roadmap.model.Skill;
import com.mathieuaime.roadmap.repository.SkillRepository;
import com.mathieuaime.roadmap.fixture.TestFixture;
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
    Skill skill = TestFixture.randomSkill();

    when(skillRepository.findAll()).thenReturn(List.of(skill));

    List<Skill> skills = skillService.findAll();

    assertThat(skills).containsExactly(skill);
  }
}