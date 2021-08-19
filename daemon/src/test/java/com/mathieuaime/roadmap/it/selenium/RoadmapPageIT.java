package com.mathieuaime.roadmap.it.selenium;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

@Tag("roadmap")
public class RoadmapPageIT extends AbstractSeleniumIT {

  @Test
  void displayRoadmapHeader() {
    webDriver.get(getBaseUrl() + "roadmap/frontend");

    WebElement roadmap = findElementById("roadmap");
    waitUntil(roadmap::isDisplayed);

    WebElement header = findElementById(roadmap, "header");
    waitUntil(header::isDisplayed);

    WebElement rank = findElementById(header, "rank");
    waitUntil(rank::isDisplayed);
    assertThat(rank.getText()).isEqualTo("INITIATE DEVELOPER");

    WebElement tasksLeft = findElementById(header, "tasks-left");
    waitUntil(tasksLeft::isDisplayed);
    assertThat(tasksLeft.getText()).isEqualTo("1 TASK LEFT UNTIL NEXT LEVEL");
  }
}
