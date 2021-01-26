package com.mathieuaime.roadmap;

import com.mathieuaime.roadmap.model.Category;
import com.mathieuaime.roadmap.model.Roadmap;
import com.mathieuaime.roadmap.model.Skill;
import com.mathieuaime.roadmap.model.Task;
import java.util.Random;
import java.util.UUID;

public class TestFixture {
  private static final Category[] CATEGORIES = Category.values();

  public static Roadmap randomRoadmap() {
    return randomRoadmapFromId(null);
  }

  public static Roadmap randomRoadmapWithId() {
    return randomRoadmapFromId(randomLong());
  }

  public static Roadmap randomRoadmapFromId(Long id) {
    return new Roadmap(id, randomString(), randomString());
  }

  public static Skill randomSkill() {
    return randomSkillFromId(null);
  }

  public static Skill randomSkillWithId() {
    return randomSkillFromId(randomLong());
  }

  private static Skill randomSkillFromId(Long id) {
    return new Skill(id, randomString(), randomString(), randomCategory());
  }

  public static Task randomTask() {
    return randomTaskFromId(null);
  }

  public static Task randomTaskWithId() {
    return randomTaskFromId(randomLong());
  }

  public static Task randomTaskFromId(Long id) {
    return randomTaskFromIdAndName(id, randomString());
  }

  public static Task randomTaskFromName(String name) {
    return randomTaskFromIdAndName(null, name);
  }

  public static Task randomTaskFromIdAndName(Long id, String name) {
    return new Task(id, name, randomString(), randomBoolean(), randomBoolean(), randomCategory());
  }

  private static String randomString() {
    return UUID.randomUUID().toString();
  }

  private static boolean randomBoolean() {
    return new Random().nextBoolean();
  }

  private static long randomLong() {
    return new Random().nextLong();
  }

  private static Category randomCategory() {
    return CATEGORIES[new Random().nextInt(CATEGORIES.length)];
  }
}
