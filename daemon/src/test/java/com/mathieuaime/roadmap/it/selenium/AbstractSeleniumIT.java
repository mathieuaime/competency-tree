package com.mathieuaime.roadmap.it.selenium;

import static org.awaitility.Awaitility.await;

import com.mathieuaime.roadmap.it.selenium.extension.ScreenshotOnFailureExtension;
import java.time.Instant;
import java.util.concurrent.Callable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.BrowserWebDriverContainer;

@Tag("selenium")
@ActiveProfiles("demo")
@ExtendWith(ScreenshotOnFailureExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractSeleniumIT {

  protected final Logger log = LoggerFactory.getLogger(getClass());

  @LocalServerPort
  protected int port;

  protected RemoteWebDriver webDriver;

  public static final BrowserWebDriverContainer<?> webDriverContainer =
      new BrowserWebDriverContainer<>().withCapabilities(new ChromeOptions());

  @BeforeAll
  static void beforeAll() {
    webDriverContainer.start();
  }

  @BeforeEach
  void beforeEach() {
    webDriver = webDriverContainer.getWebDriver();
  }

  @AfterEach
  void tearDown() {
    LogEntries logEntries = webDriver.manage().logs().get(LogType.BROWSER);
    for (LogEntry entry : logEntries) {
      log.info("{} - {} : {}",
          Instant.ofEpochMilli(entry.getTimestamp()), entry.getLevel(), entry.getMessage());
    }
  }

  String getBaseUrl() {
    return "http://host.docker.internal:" + port + "/#/";
  }

  WebElement findElementById(String id) {
    return findElementBy(By.id(id));
  }

  WebElement findElementById(WebElement root, String id) {
    return root.findElement(By.id(id));
  }

  WebElement findElementByClassName(String className) {
    return findElementBy(By.className(className));
  }

  WebElement findElementBy(By by) {
    return webDriver.findElement(by);
  }

  WebElement findElementByClassName(WebElement root, String className) {
    return root.findElement(By.className(className));
  }

  void waitUntil(Callable<Boolean> condition) {
    await().until(condition);
  }
}