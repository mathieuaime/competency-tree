package com.mathieuaime.roadmap.persistence.entity;

import com.mathieuaime.roadmap.model.Category;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity(name = "RoadmapItem")
@Table(name = "roadmap_item",
    indexes = @Index(columnList = "task_id"),
    uniqueConstraints = @UniqueConstraint(columnNames = {"roadmap_id", "skill_id", "task_id"})
)
public class RoadmapItemEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(nullable = false)
  private RoadmapEntity roadmap;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(nullable = false)
  private SkillEntity skill;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(nullable = false)
  private TaskEntity task;

  @Column(nullable = false)
  private boolean required = false;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Category category;

  public RoadmapItemEntity() {
  }

  public RoadmapItemEntity(
      long roadmapId,
      long skillId,
      long taskId,
      boolean required,
      Category category
  ) {
    this(null, new RoadmapEntity(roadmapId), new SkillEntity(skillId), new TaskEntity(taskId),
        required, category);
  }

  public RoadmapItemEntity(
      Long id,
      RoadmapEntity roadmap,
      SkillEntity skill,
      TaskEntity task,
      boolean required,
      Category category
  ) {
    this.id = id;
    this.roadmap = roadmap;
    this.skill = skill;
    this.task = task;
    this.required = required;
    this.category = category;
  }

  public Long getId() {
    return id;
  }

  public RoadmapEntity getRoadmap() {
    return roadmap;
  }

  public SkillEntity getSkill() {
    return skill;
  }

  public TaskEntity getTask() {
    return task;
  }

  public boolean isRequired() {
    return required;
  }

  public Category getCategory() {
    return category;
  }

  @Override
  public String toString() {
    return "RoadmapItemEntity{" +
        "id=" + id +
        ", required=" + required +
        ", category=" + category +
        '}';
  }
}
