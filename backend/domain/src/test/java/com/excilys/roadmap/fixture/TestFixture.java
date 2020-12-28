package com.excilys.roadmap.fixture;

import com.excilys.roadmap.model.Category;
import com.excilys.roadmap.model.Roadmap;
import com.excilys.roadmap.model.Skill;
import com.excilys.roadmap.model.Task;
import java.util.Random;
import java.util.UUID;

public class TestFixture {
  private static final Category[] CATEGORIES = Category.values();

  public static Roadmap randomRoadmap() {
    Roadmap roadmap = new Roadmap();
    roadmap.setName(UUID.randomUUID().toString());
    roadmap.setDescription(UUID.randomUUID().toString());
    return roadmap;
  }

  public static Skill randomSkill() {
    Skill skill = new Skill();
    skill.setName(UUID.randomUUID().toString());
    skill.setIcon(UUID.randomUUID().toString());
    skill.setCategory(randomCategory());
    return skill;
  }

  public static Task randomTask() {
    Task task = new Task();
    task.setRequired(new Random().nextBoolean());
    task.setDone(new Random().nextBoolean());
    task.setCategory(randomCategory());
    task.setName(UUID.randomUUID().toString());
    task.setDescription(UUID.randomUUID().toString());
    return task;
  }

  private static Category randomCategory() {
    return CATEGORIES[new Random().nextInt(CATEGORIES.length)];
  }
}
