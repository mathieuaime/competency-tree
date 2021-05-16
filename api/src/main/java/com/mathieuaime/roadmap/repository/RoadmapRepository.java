package com.mathieuaime.roadmap.repository;

import com.mathieuaime.roadmap.model.Roadmap;
import java.util.List;

public interface RoadmapRepository {

  List<Roadmap> findAll();

  Roadmap save(Roadmap roadmap);
}
