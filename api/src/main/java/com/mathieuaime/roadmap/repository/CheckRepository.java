package com.mathieuaime.roadmap.repository;

public interface CheckRepository {
  void check(long userId, long roadmapItemId);
}
