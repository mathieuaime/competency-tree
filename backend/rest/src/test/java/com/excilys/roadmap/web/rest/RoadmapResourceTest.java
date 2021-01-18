package com.excilys.roadmap.web.rest;

import static com.excilys.roadmap.TestFixture.randomRoadmap;
import static com.excilys.roadmap.TestFixture.randomRoadmapWithId;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.excilys.roadmap.service.RoadmapService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RoadmapResource.class)
class RoadmapResourceTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private RoadmapService service;

  @Test
  void findAllShouldReturnRoadmapsFromService() throws Exception {
    var roadmap = randomRoadmapWithId();
    when(service.findAll()).thenReturn(List.of(roadmap));

    var requestBuilder = get("/roadmaps");

    this.mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(roadmap.getId()))
        .andExpect(jsonPath("$[0].name").value(roadmap.getName()))
        .andExpect(jsonPath("$[0].description").value(roadmap.getDescription()));
  }

  @Test
  void findByNameShouldReturnNotFoundStatusWhenNotFound() throws Exception {
    String name = "not found";
    when(service.findByName(name)).thenReturn(Optional.empty());

    var requestBuilder = get("/roadmaps/{name}", name);

    this.mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  void findByNameShouldReturnRoadmapFromServiceWhenFound() throws Exception {
    var roadmap = randomRoadmapWithId();
    when(service.findByName(roadmap.getName())).thenReturn(Optional.of(roadmap));

    var requestBuilder = get("/roadmaps/{name}", roadmap.getName());

    this.mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(roadmap.getId()))
        .andExpect(jsonPath("$.name").value(roadmap.getName()))
        .andExpect(jsonPath("$.description").value(roadmap.getDescription()));
  }

  @Test
  void findUserRoadmapsShouldReturnNotFoundStatusWhenNotFound() throws Exception {
    long userId = 1L;
    String name = "not found";
    when(service.findByUserAndName(userId, name)).thenReturn(Optional.empty());

    var requestBuilder = get("/me/roadmaps/{name}", name);

    this.mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  void findUserRoadmapsShouldReturnRoadmapFromServiceWhenFound() throws Exception {
    long userId = 1L;
    var roadmap = randomRoadmapWithId();
    when(service.findByUserAndName(userId, roadmap.getName())).thenReturn(Optional.of(roadmap));

    var requestBuilder = get("/me/roadmaps/{name}", roadmap.getName());

    this.mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(roadmap.getId()))
        .andExpect(jsonPath("$.name").value(roadmap.getName()))
        .andExpect(jsonPath("$.description").value(roadmap.getDescription()));
  }

  @Test
  void saveShouldReturnSavedRoadmapFromService() throws Exception {
    var roadmap = randomRoadmap();
    var savedRoadmap = randomRoadmapWithId();
    when(service.save(roadmap)).thenReturn(savedRoadmap);

    var requestBuilder = put("/roadmaps")
        .content(objectMapper.writeValueAsString(roadmap))
        .contentType(MediaType.APPLICATION_JSON);

    this.mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(savedRoadmap.getId()))
        .andExpect(jsonPath("$.name").value(savedRoadmap.getName()))
        .andExpect(jsonPath("$.description").value(savedRoadmap.getDescription()));
  }
}