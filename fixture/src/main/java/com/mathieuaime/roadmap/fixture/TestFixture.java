package com.mathieuaime.roadmap.fixture;

import com.mathieuaime.roadmap.model.Category;
import com.mathieuaime.roadmap.model.Roadmap;
import com.mathieuaime.roadmap.model.Skill;
import com.mathieuaime.roadmap.model.Task;
import com.mathieuaime.roadmap.repository.projection.TaskProjection;
import java.util.Random;
import java.util.UUID;

public class TestFixture {

  public static Roadmap randomRoadmap() {
    return randomRoadmapFromId(null);
  }

  public static Roadmap randomRoadmapWithId() {
    return randomRoadmapFromId(randomLong());
  }

  public static Roadmap randomRoadmapFromId(Long id) {
    return new Roadmap(id, randomString(), randomString(), randomString(7));
  }

  public static Skill randomSkill() {
    return randomSkillFromId(null);
  }

  public static Skill randomSkillWithId() {
    return randomSkillFromId(randomLong());
  }

  public static Skill randomSkillFromCategory(Category category) {
    return new Skill(randomLong(), randomString(), randomString(), category);
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

  public static Task randomTaskFromCategory(Category category) {
    return new Task(randomLong(), randomString(), randomString(), randomBoolean(), randomBoolean(),
        category);
  }

  public static TaskProjection randomTaskProjection(Roadmap roadmap, Skill skill, Task task) {
    return new TaskProjection(
        roadmap.getId(), roadmap.getName(), roadmap.getDescription(), roadmap.getColor(),
        skill.getId(), skill.getName(), skill.getIcon(), task.getId(), task.getName(),
        task.getDescription(), task.getCategory(), task.isDone(), task.isRequired());
  }

  public static Category randomCategory() {
    return Category.values()[new Random().nextInt(Category.values().length)];
  }

  public static String randomString() {
    return UUID.randomUUID().toString();
  }

  public static String randomString(int size) {
    return randomString().substring(0, size);
  }

  private static boolean randomBoolean() {
    return new Random().nextBoolean();
  }

  private static long randomLong() {
    return new Random().nextLong();
  }
}
