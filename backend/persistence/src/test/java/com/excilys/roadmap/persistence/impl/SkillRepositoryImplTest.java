package com.excilys.roadmap.persistence.impl;

import static com.excilys.roadmap.TestFixture.randomSkill;
import static com.excilys.roadmap.persistence.PersistenceTestFixture.randomSkillEntity;
import static org.assertj.core.api.Assertions.assertThat;

import com.excilys.roadmap.persistence.entity.SkillEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(SkillRepositoryImpl.class)
class SkillRepositoryImplTest {
  @Autowired
  private SkillRepositoryImpl skillRepository;

  @Autowired
  private TestEntityManager em;

  @Test
  void findAll() {
    var skillEntity = em.persistAndFlush(randomSkillEntity());

    var skills = skillRepository.findAll();

    assertThat(skills).hasSize(1);

    var skill = skills.get(0);
    assertThat(skill.getId()).isEqualTo(skillEntity.getId());
    assertThat(skill.getName()).isEqualTo(skillEntity.getName());
    assertThat(skill.getIcon()).isEqualTo(skillEntity.getIcon());
  }

  @Test
  void save() {
    var skill = randomSkill();

    skill = skillRepository.save(skill);

    var saved = em.find(SkillEntity.class, skill.getId());

    assertThat(skill.getId()).isEqualTo(saved.getId());
    assertThat(skill.getName()).isEqualTo(saved.getName());
    assertThat(skill.getIcon()).isEqualTo(saved.getIcon());
  }
}