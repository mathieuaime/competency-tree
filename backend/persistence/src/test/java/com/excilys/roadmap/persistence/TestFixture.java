package com.excilys.roadmap.persistence;

import com.excilys.roadmap.model.Category;
import com.excilys.roadmap.model.Roadmap;
import com.excilys.roadmap.model.Skill;
import com.excilys.roadmap.model.Task;
import com.excilys.roadmap.persistence.entity.RoadmapEntity;
import com.excilys.roadmap.persistence.entity.RoadmapItemEntity;
import com.excilys.roadmap.persistence.entity.SkillEntity;
import com.excilys.roadmap.persistence.entity.TaskEntity;
import java.util.Random;
import java.util.UUID;

public class TestFixture {
  private static final Category[] CATEGORIES = Category.values();

  public static Roadmap randomRoadmap() {
    return new Roadmap(null, randomString(), randomString());
  }

  public static RoadmapEntity randomRoadmapEntity() {
    return new RoadmapEntity(null, randomString(), randomString());
  }

  public static Skill randomSkill() {
    return new Skill(null, randomString(), randomString(), randomCategory());
  }

  public static SkillEntity randomSkillEntity() {
    return new SkillEntity(null, randomString(), randomString());
  }

  public static Task randomTask() {
    return new Task(null, randomString(), randomString(), randomBoolean(), randomBoolean(),
        randomCategory());
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

  private static boolean randomBoolean() {
    return new Random().nextBoolean();
  }

  private static Category randomCategory() {
    return CATEGORIES[new Random().nextInt(CATEGORIES.length)];
  }
}
