package com.mathieuaime.roadmap.fixture;

import static java.util.UUID.randomUUID;

import com.mathieuaime.roadmap.model.Category;
import com.mathieuaime.roadmap.model.Roadmap;
import com.mathieuaime.roadmap.model.Skill;
import com.mathieuaime.roadmap.model.Task;
import java.util.Random;

public class TestFixture {
  private static final Category[] CATEGORIES = Category.values();

  public static Roadmap randomRoadmap() {
    return new Roadmap(null, randomUUID().toString(), randomUUID().toString());
  }

  public static Skill randomSkill() {
    return new Skill(null, randomUUID().toString(), randomUUID().toString(), randomCategory());
  }

  public static Task randomTask() {
    return randomTask(null);
  }

  public static Task randomTask(Long id) {
    return new Task(id, randomUUID().toString(), randomUUID().toString(),
        new Random().nextBoolean(), new Random().nextBoolean(), randomCategory());
  }

  private static Category randomCategory() {
    return CATEGORIES[new Random().nextInt(CATEGORIES.length)];
  }
}