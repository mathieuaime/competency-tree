package com.mathieuaime.roadmap.initializer;

import com.mathieuaime.roadmap.model.Category;
import java.util.List;

public record InitialData(
    List<RoadmapData> roadmaps,
    List<SkillData> skills,
    List<TaskData> tasks,
    List<UserCheckData> checks
) {

}

record RoadmapData(String name, String description, String color) {

}

record SkillData(String name, String icon) {

}

record TaskData(String roadmap, String skill, String name, String description,
                boolean required, Category category) {

}

record UserCheckData(long userId, String task) {

}
