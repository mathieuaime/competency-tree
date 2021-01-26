package com.mathieuaime.roadmap.web.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mathieuaime.roadmap.service.SkillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mathieuaime.roadmap.TestFixture;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SkillResource.class)
class SkillResourceTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private SkillService service;

  @Test
  void findAllShouldReturnSkillsFromService() throws Exception {
    var skill = TestFixture.randomSkillWithId();
    when(service.findAll()).thenReturn(List.of(skill));

    var requestBuilder = get("/skills");

    this.mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(skill.getId()))
        .andExpect(jsonPath("$[0].name").value(skill.getName()))
        .andExpect(jsonPath("$[0].icon").value(skill.getIcon()))
        .andExpect(jsonPath("$[0].category").value(skill.getCategory().name()));
  }

  @Test
  void saveShouldReturnSavedSkillFromService() throws Exception {
    var skill = TestFixture.randomSkill();
    var savedSkill = TestFixture.randomSkillWithId();
    when(service.save(skill)).thenReturn(savedSkill);

    var requestBuilder = put("/skills")
        .content(objectMapper.writeValueAsString(skill))
        .contentType(MediaType.APPLICATION_JSON);

    this.mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(savedSkill.getId()))
        .andExpect(jsonPath("$.name").value(savedSkill.getName()))
        .andExpect(jsonPath("$.icon").value(savedSkill.getIcon()))
        .andExpect(jsonPath("$.category").value(savedSkill.getCategory().name()));
  }
}