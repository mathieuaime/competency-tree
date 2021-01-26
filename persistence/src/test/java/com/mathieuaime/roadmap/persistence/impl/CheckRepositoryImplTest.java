package com.mathieuaime.roadmap.persistence.impl;

import static org.assertj.core.api.Assertions.assertThat;

import com.mathieuaime.roadmap.persistence.entity.CheckEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(CheckRepositoryImpl.class)
class CheckRepositoryImplTest {
  private static final int USER_ID = 1;
  private static final int ROADMAP_ITEM_ID = 2;

  @Autowired
  private CheckRepositoryImpl checkRepository;

  @Autowired
  private TestEntityManager em;

  @Test
  void check_withoutExistingCheck_shouldCreateIt() {
    checkRepository.check(USER_ID, ROADMAP_ITEM_ID);

    var check = em.find(CheckEntity.class, CheckEntity.id(USER_ID, ROADMAP_ITEM_ID));

    assertThat(check.getUserId()).isEqualTo(USER_ID);
    assertThat(check.getRoadmapItemId()).isEqualTo(ROADMAP_ITEM_ID);
    assertThat(check.isDone()).isTrue();
  }

  @Test
  void check_withExistingCheck_shouldUpdateIt() {
    em.persistAndFlush(CheckEntity.newUndoneCheck(USER_ID, ROADMAP_ITEM_ID));

    checkRepository.check(USER_ID, ROADMAP_ITEM_ID);

    var check = em.find(CheckEntity.class, CheckEntity.id(USER_ID, ROADMAP_ITEM_ID));

    assertThat(check.getUserId()).isEqualTo(USER_ID);
    assertThat(check.getRoadmapItemId()).isEqualTo(ROADMAP_ITEM_ID);
    assertThat(check.isDone()).isTrue();
  }
}