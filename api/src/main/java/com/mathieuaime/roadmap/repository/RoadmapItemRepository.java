package com.mathieuaime.roadmap.repository;

import com.mathieuaime.roadmap.model.Task;
import com.mathieuaime.roadmap.repository.projection.RoadmapItemProjection;
import java.util.List;

public interface RoadmapItemRepository {

  List<RoadmapItemProjection> findByTask(long taskId);

  void create(long roadmapId, long skillId, Task task);
}
