package com.mathieuaime.roadmap.it.selenium;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

@Tag("roadmap")
public class HomePageIT extends AbstractSeleniumIT {

  @Test
  void shouldDisplayRoadmaps() {
    webDriver.get(getBaseUrl());

    WebElement homepage = findElementByClassName("homepage");
    assertThat(homepage.isDisplayed()).isTrue();

    WebElement cardRow = findElementByClassName(homepage, "card-row");
    assertThat(cardRow.isDisplayed()).isTrue();
  }
}
