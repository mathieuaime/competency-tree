package com.mathieuaime.competency.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "LEARN")
@Data
public class Learn {

  @Id
  @GeneratedValue
  private Long id;
  private List<String> needs = new ArrayList<>();

  @StartNode
  private Competency competency;

  @EndNode
  private Skill skill;

}
