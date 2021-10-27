package com.mathieuaime.roadmap.it.selenium.extension;

import com.mathieuaime.roadmap.it.selenium.AbstractSeleniumIT;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.BrowserWebDriverContainer;

public class ScreenshotOnFailureExtension implements AfterEachCallback {

  private static final Logger log = LoggerFactory.getLogger(
      ScreenshotOnFailureExtension.class);

  @Override
  public void afterEach(ExtensionContext extensionContext) {
    if (extensionContext.getExecutionException().isPresent()) {
      BrowserWebDriverContainer<?> browserContainer = AbstractSeleniumIT.webDriverContainer;
      byte[] screenshot = browserContainer.getWebDriver().getScreenshotAs(OutputType.BYTES);

      try {
        Path path = Paths
            .get("target/selenium-screenshots")
            .resolve(String.format("%s-%s-%s.png", LocalDateTime.now(),
                extensionContext.getRequiredTestClass().getName(),
                extensionContext.getRequiredTestMethod().getName()));

        Files.createDirectories(path.getParent());
        Files.write(path, screenshot);
      } catch (IOException e) {
        log.error(e.getMessage());
      }
    }
  }
}