package com.excilys.roadmap.repository;

import com.excilys.roadmap.model.Task;
import com.excilys.roadmap.repository.projection.RoadmapItemProjection;
import java.util.List;

public interface RoadmapItemRepository {
  List<RoadmapItemProjection> findByTask(long taskId);

  void create(long roadmapId, long skillId, Task task);
}
