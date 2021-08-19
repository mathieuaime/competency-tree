package com.mathieuaime.roadmap.service;

import static com.mathieuaime.roadmap.fixture.TestFixture.randomSkill;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.mathieuaime.roadmap.model.Skill;
import com.mathieuaime.roadmap.repository.SkillRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SkillServiceImplTest {
  @Mock
  private SkillRepository skillRepository;

  @InjectMocks
  private SkillServiceImpl skillService;

  @Test
  void findAll() {
    Skill skill = randomSkill();

    when(skillRepository.findAll()).thenReturn(List.of(skill));

    List<Skill> skills = skillService.findAll();

    assertThat(skills).containsExactly(skill);
  }
}