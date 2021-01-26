package com.mathieuaime.roadmap.web.rest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mathieuaime.roadmap.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mathieuaime.roadmap.TestFixture;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TaskResource.class)
class TaskResourceTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private TaskService service;

  @Test
  void findAllShouldReturnTasksFromService() throws Exception {
    var task = TestFixture.randomTaskWithId();
    when(service.findAll()).thenReturn(List.of(task));

    var requestBuilder = get("/tasks");

    this.mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(task.getId()))
        .andExpect(jsonPath("$[0].name").value(task.getName()))
        .andExpect(jsonPath("$[0].description").value(task.getDescription()))
        .andExpect(jsonPath("$[0].category").value(task.getCategory().name()));
  }

  @Test
  void saveShouldReturnSavedRoadmapFromService() throws Exception {
    long roadmapId = 1L;
    long skillId = 2L;
    var task = TestFixture.randomTask();
    var savedTask = TestFixture.randomTaskWithId();
    when(service.save(roadmapId, skillId, task)).thenReturn(savedTask);

    var requestBuilder = put("/roadmaps/{roadmapId}/skills/{skillId}/tasks", roadmapId, skillId)
        .content(objectMapper.writeValueAsString(task))
        .contentType(MediaType.APPLICATION_JSON);

    this.mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(savedTask.getId()))
        .andExpect(jsonPath("$.name").value(savedTask.getName()))
        .andExpect(jsonPath("$.description").value(savedTask.getDescription()))
        .andExpect(jsonPath("$.category").value(savedTask.getCategory().name()));
  }

  @Test
  void checkShouldCheckTaskInService() throws Exception {
    long userId = 1L;
    long taskId = 2L;

    var requestBuilder = put("/tasks/{taskId}/check", taskId);

    this.mockMvc.perform(requestBuilder)
        .andDo(print())
        .andExpect(status().isNoContent());

    verify(this.service).check(userId, taskId);
  }
}