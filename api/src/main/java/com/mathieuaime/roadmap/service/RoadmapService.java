package com.mathieuaime.roadmap.service;

import com.mathieuaime.roadmap.model.Roadmap;
import java.util.List;
import java.util.Optional;

public interface RoadmapService {
  List<Roadmap> findAll();

  Optional<Roadmap> findByName(String roadmapName);

  Optional<Roadmap> findByUserAndName(long userId, String roadmapName);

  Roadmap save(Roadmap roadmap);
}
