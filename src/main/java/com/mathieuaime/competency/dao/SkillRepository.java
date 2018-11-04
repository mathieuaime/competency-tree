package com.mathieuaime.competency.dao;

import com.mathieuaime.competency.model.Skill;
import java.util.Collection;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface SkillRepository extends Neo4jRepository<Skill, Long> {

  Skill findByName(@Param("name") String title);

  Collection<Skill> findByNameLike(@Param("name") String title);

  @Query("MATCH (s:Skill)<-[r:LEARN]-(c:Competency) RETURN s,r,c LIMIT {limit}")
  Collection<Skill> graph(@Param("limit") int limit);
}
