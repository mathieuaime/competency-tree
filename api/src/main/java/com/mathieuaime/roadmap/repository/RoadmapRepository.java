package com.mathieuaime.roadmap.repository;

import com.mathieuaime.roadmap.model.Roadmap;
import java.util.List;
import java.util.Optional;

public interface RoadmapRepository {

  List<Roadmap> findAll();

  Optional<Roadmap> findByName(String name);

  Roadmap save(Roadmap roadmap);
}
