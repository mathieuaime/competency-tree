package com.mathieuaime.competency.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
@Data
public class Competency {

  @Id
  @GeneratedValue
  private Long id;
  private String name;

  @Relationship(type = "LEARN")
  private List<Skill> skills = new ArrayList<>();
}
