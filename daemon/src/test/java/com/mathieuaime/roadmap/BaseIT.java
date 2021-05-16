package com.mathieuaime.roadmap;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MariaDBContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/data.sql")
abstract class BaseIT {

  static MariaDBContainer<?> mariaDBContainer = new MariaDBContainer<>("mariadb:10.3.6")
      .withPassword("inmemory")
      .withUsername("inmemory");
  @LocalServerPort
  int port;
  @Autowired
  TestRestTemplate restTemplate;

  @BeforeAll
  static void beforeAll() {
    mariaDBContainer.start();
  }

  @DynamicPropertySource
  static void dbProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", mariaDBContainer::getJdbcUrl);
    registry.add("spring.datasource.password", mariaDBContainer::getPassword);
    registry.add("spring.datasource.username", mariaDBContainer::getUsername);
  }

  String getBaseUrl() {
    return "http://localhost:" + port + "/api/v1";
  }
}