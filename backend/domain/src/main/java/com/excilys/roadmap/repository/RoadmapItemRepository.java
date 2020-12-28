package com.excilys.roadmap.repository;

import com.excilys.roadmap.model.Roadmap;
import com.excilys.roadmap.model.Task;
import java.util.List;

public interface RoadmapItemRepository {
  List<Roadmap> findByTask(long taskId);

  void create(long roadmapId, long skillId, long taskId, Task task);
}
