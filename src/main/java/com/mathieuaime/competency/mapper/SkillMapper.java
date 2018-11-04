package com.mathieuaime.competency.mapper;

import com.mathieuaime.competency.model.Learn;
import com.mathieuaime.competency.model.Skill;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class SkillMapper {

  public Map<String, Object> toD3Format(Collection<Skill> skills) {
    List<Map<String, Object>> nodes = new ArrayList<>();
    List<Map<String, Object>> rels = new ArrayList<>();
    int i = 0;
    for (Skill skill : skills) {
      nodes.add(map("name", skill.getName(), "label", "skill"));
      int target = i;
      i++;
      for (Learn learn : skill.getLearns()) {
        Map<String, Object> coder = map("name", learn.getCompetency().getName(), "label", "coder");
        int source = nodes.indexOf(coder);
        if (source == -1) {
          nodes.add(coder);
          source = i++;
        }
        rels.add(map("source", source, "target", target));
      }
    }
    return map("nodes", nodes, "links", rels);
  }


  private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
    Map<String, Object> result = new HashMap<>(2);
    result.put(key1, value1);
    result.put(key2, value2);
    return result;
  }
}
