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

record RoadmapData(long id, String name, String description) {

}

record SkillData(long id, String name, String icon) {

}

record TaskData(long roadmapId, long skillId, long id, String name, String description,
                boolean required, Category category) {

}

record UserCheckData(long userId, long roadmapItemId, boolean done) {

}
