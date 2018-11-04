package com.mathieuaime.competency.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
@Data
public class Skill {

  @Id
  @GeneratedValue
  private Long id;
  private String name;

  @JsonIgnoreProperties("skill")
  @Relationship(type = "LEARN", direction = Relationship.INCOMING)
  private List<Learn> learns;
}
