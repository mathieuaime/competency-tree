package com.mathieuaime.roadmap.persistence;

import com.mathieuaime.roadmap.model.Category;
import com.mathieuaime.roadmap.persistence.entity.RoadmapEntity;
import com.mathieuaime.roadmap.persistence.entity.RoadmapItemEntity;
import com.mathieuaime.roadmap.persistence.entity.SkillEntity;
import com.mathieuaime.roadmap.persistence.entity.TaskEntity;
import java.util.Random;
import java.util.UUID;

public class PersistenceTestFixture {

  public static RoadmapEntity randomRoadmapEntity() {
    return new RoadmapEntity(null, randomString(), randomString(), randomString(7));
  }

  public static SkillEntity randomSkillEntity() {
    return new SkillEntity(null, randomString(), randomString());
  }

  public static TaskEntity randomTaskEntity() {
    return new TaskEntity(null, randomString(), randomString());
  }

  public static RoadmapItemEntity randomRoadmapItemEntity(
      RoadmapEntity roadmap,
      SkillEntity skill,
      TaskEntity task) {
    return new RoadmapItemEntity(null, roadmap, skill, task, randomBoolean(), randomCategory());
  }

  private static String randomString() {
    return UUID.randomUUID().toString();
  }

  private static String randomString(int size) {
    return randomString().substring(0, size);
  }

  private static boolean randomBoolean() {
    return new Random().nextBoolean();
  }

  private static Category randomCategory() {
    return Category.values()[new Random().nextInt(Category.values().length)];
  }
}
