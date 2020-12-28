package com.excilys.roadmap.repository;

import com.excilys.roadmap.model.Roadmap;
import java.util.List;

public interface RoadmapRepository {
  List<Roadmap> findAll();

  Roadmap save(Roadmap roadmap);
}
